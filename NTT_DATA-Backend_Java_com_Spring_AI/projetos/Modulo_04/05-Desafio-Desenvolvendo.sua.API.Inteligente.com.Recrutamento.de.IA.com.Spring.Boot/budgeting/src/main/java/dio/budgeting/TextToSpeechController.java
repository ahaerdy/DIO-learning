package dio.budgeting;

import com.google.genai.Client;
import com.google.genai.types.Blob;
import com.google.genai.types.Candidate;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import com.google.genai.types.PrebuiltVoiceConfig;
import com.google.genai.types.SpeechConfig;
import com.google.genai.types.VoiceConfig;

import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TextToSpeechController {

    private final Client geminiClient;

    // Ajuste 1: reaproveita a mesma property já usada pelo GoogleGenAiChatModel
    // (spring.ai.google.genai.api-key), em vez de ler a chave por um caminho paralelo.
    // Isso evita ter duas "fontes de verdade" para a mesma credencial no projeto.
    public TextToSpeechController(@Value("${spring.ai.google.genai.api-key}") String apiKey) {
        if (!StringUtils.hasText(apiKey)) {
            throw new IllegalArgumentException(
                    "A propriedade spring.ai.google.genai.api-key não foi resolvida. " +
                            "Verifique se a variável de ambiente GEMINI_API_KEY está definida.");
        }
        this.geminiClient = Client.builder()
                .apiKey(apiKey)
                .build();
    }

    // Ajuste 2: com.google.genai.Client implementa AutoCloseable. Como aqui ele vive
    // como bean singleton (criado uma vez, reaproveitado em todas as requisições),
    // fechamos explicitamente no shutdown do contexto Spring em vez de deixar para o
    // encerramento "solto" da JVM.
    @PreDestroy
    public void close() {
        geminiClient.close();
    }

    @PostMapping(value = "/synthesize", produces = "audio/wav")
    public ResponseEntity<Resource> synthesize(@RequestBody SynthesizeRequest request) throws IOException {

        if (request == null || !StringUtils.hasText(request.text())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O campo 'text' não pode ser vazio.");
        }

        GenerateContentConfig config = GenerateContentConfig.builder()
                .responseModalities("AUDIO")
                .speechConfig(SpeechConfig.builder()
                        .voiceConfig(VoiceConfig.builder()
                                .prebuiltVoiceConfig(PrebuiltVoiceConfig.builder()
                                        .voiceName("Kore")
                                        .build())
                                .build())
                        .build())
                .build();

        GenerateContentResponse response = geminiClient.models.generateContent(
                "gemini-2.5-flash-preview-tts",
                request.text(),
                config
        );

        List<Part> parts = response.candidates()
                .flatMap(candidates -> candidates.stream().findFirst())
                .flatMap(Candidate::content)
                .flatMap(Content::parts)
                .orElse(new ArrayList<>());

        byte[] pcmAudio = parts.stream()
                .map(part -> part.inlineData().flatMap(Blob::data))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Nenhum áudio retornado pelo Gemini"));

        byte[] wavAudio = wrapPcmAsWav(pcmAudio, 24000, 1, 16);
        var resource = new ByteArrayResource(wavAudio);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("audio.wav")
                                .build()
                                .toString())
                .body(resource);
    }

    private static byte[] wrapPcmAsWav(byte[] pcmData, int sampleRate, int channels, int bitsPerSample)
            throws IOException {
        int byteRate = sampleRate * channels * bitsPerSample / 8;
        int blockAlign = channels * bitsPerSample / 8;
        int dataSize = pcmData.length;

        ByteBuffer header = ByteBuffer.allocate(44).order(ByteOrder.LITTLE_ENDIAN);
        header.put("RIFF".getBytes());
        header.putInt(36 + dataSize);
        header.put("WAVE".getBytes());
        header.put("fmt ".getBytes());
        header.putInt(16);
        header.putShort((short) 1);
        header.putShort((short) channels);
        header.putInt(sampleRate);
        header.putInt(byteRate);
        header.putShort((short) blockAlign);
        header.putShort((short) bitsPerSample);
        header.put("data".getBytes());
        header.putInt(dataSize);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(header.array());
        out.write(pcmData);
        return out.toByteArray();
    }

    public record SynthesizeRequest(String text) {
    }
}