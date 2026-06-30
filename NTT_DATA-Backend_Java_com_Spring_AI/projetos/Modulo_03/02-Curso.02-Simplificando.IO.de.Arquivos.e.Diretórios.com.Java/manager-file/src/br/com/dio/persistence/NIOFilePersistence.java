package br.com.dio.persistence;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.stream.Stream;

public class NIOFilePersistence extends FilePersistence{

    public NIOFilePersistence(String fileName) throws IOException {
        super(fileName);
        var file = new File(currentDir + storedDir);
        if (!file.exists() && !file.mkdirs()) throw new IOException("Erro ao criar arquivo");
        cleanFile();
    }

    @Override
    public String write(final String data) {
        try(var file = new RandomAccessFile(new File(currentDir + storedDir + fileName), "rw")){
            file.seek(file.length());
            file.writeBytes(data);
            file.writeBytes(System.lineSeparator());
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return data;
    }

    @Override
    public String findAll() {
        var content = new StringBuilder();
        File file = new File(currentDir + storedDir + fileName);
        if (!file.exists()) return "";
        try(var raf = new RandomAccessFile(file, "r");
            var channel = raf.getChannel()){
            var buffer = ByteBuffer.allocate(256);
            while (channel.read(buffer) != -1){
                buffer.flip();
                while (buffer.hasRemaining()){
                    content.append((char) buffer.get());
                }
                buffer.clear();
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return content.toString();
    }

    @Override
    public String findBy(final String sentence) {
        try {
            var lines = java.nio.file.Files.readAllLines(java.nio.file.Path.of(currentDir + storedDir + fileName));
            return lines.stream()
                    .filter(line -> line.contains(sentence))
                    .findFirst()
                    .orElse("Registro não encontrado");
        } catch (IOException ex) {
            return "Erro ao buscar: " + ex.getMessage();
        }
    }
}
