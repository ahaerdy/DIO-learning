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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "GEMINI_API_KEY", matches = ".+")
public class GeminiSpeechModelIT {

    @Test
    public void should_produceAudio_when_textIsProvided() throws IOException {
        // GoogleGenAiChatOptions (Spring AI) ainda não expõe responseModalities/speechConfig,
        // então usamos o SDK Java do Google GenAI diretamente — o mesmo client que o
        // GoogleGenAiChatModel usa por baixo dos panos.
        Client client = Client.builder()
                .apiKey(System.getenv("GEMINI_API_KEY"))
                .build();

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

        GenerateContentResponse response = client.models.generateContent(
                "gemini-2.5-flash-preview-tts",
                "O valor total do serviço ficou em 80 reais. Posso confirmar o pagamento?",
                config);

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
                .orElseThrow(() -> new AssertionError("Nenhum áudio retornado pelo Gemini"));

        assertThat(pcmAudio).hasSizeGreaterThan(1024);

        // O Gemini TTS retorna PCM cru (24kHz, mono, 16 bits) — precisa de cabeçalho WAV
        // para virar um arquivo de áudio de verdade e reproduzível.
        byte[] wavAudio = wrapPcmAsWav(pcmAudio, 24000, 1, 16);

        Path tempFile = Files.createTempFile("AUDIO_", ".wav");
        Files.write(tempFile, wavAudio);
        System.out.println(tempFile.toAbsolutePath());
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
        header.putInt(16); // tamanho do subchunk fmt (PCM)
        header.putShort((short) 1); // formato = PCM
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
}