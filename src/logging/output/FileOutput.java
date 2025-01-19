package logging.output;

import java.io.FileWriter;
import java.io.IOException;

import logging.format.Formatter;

public class FileOutput extends Output {
    private String filepath;
    private FileWriter fileWriter;

    /**
     * Send a message to this FileOutput's file.
     * The message will be formatted by this Output's Formatter if it exists,
     * otherwise it will fall back to the attached Logger's Formatter.
     *
     * @param message - Message to send
     * @return If the message was successfully sent
     */
    public boolean send(String message) {
        try {
            fileWriter.write(message);
            return true;
        } catch (IOException e) {
            // pass
        }
        return false;
    }

    /**
     * Get this FileOutput's filepath.
     *
     * @return Filepath
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * Initialize a FileOutput with a default Formatter.
     *
     * @param filepath - File to log in
     * @throws IOException If a FileWriter cannot be initialized using filepath
     */
    public FileOutput(String filepath) throws IOException {
        this(filepath, null);
    }

    /**
     * Initialize a FileOutput.
     *
     * @param filepath - File to log in
     * @param formatter - Formatter to use
     * @throws IOException If a FileWriter cannot be initialized using filepath
     */
    public FileOutput(String filepath, Formatter formatter) throws IOException {
        super(formatter);
        this.filepath = filepath;
        this.fileWriter = new FileWriter(filepath);
    }
}
