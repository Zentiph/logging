package logging.output;

import java.io.PrintStream;

import logging.format.Formatter;

public class StreamOutput extends Output {
    private PrintStream stream;

    /**
     * Send a message to this StreamOutput's stream.
     * The message will be formatted by this Output's Formatter if it exists,
     * otherwise it will fall back to the attached Logger's Formatter.
     *
     * @param message - Message to send
     * @return If the message was successfully sent
     */
    public boolean send(String message) {
        stream.print(message);
        stream.flush();
        return true;
    }

    /**
     * Get this StreamOutput's stream.
     *
     * @return Stream
     */
    public PrintStream getStream() {
        return stream;
    }

    /**
     * Initialize a StreamOutput with the default stream System.err.
     */
    public StreamOutput() {
        this(System.err, null);
    }

    /**
     * Initialize a StreamOutput with a custom stream and default Formatter.
     *
     * @param stream - Output stream
     */
    public StreamOutput(PrintStream stream) {
        this(stream, null);
    }

    /**
     * Initialize a StreamOutput with a custom stream and Formatter.
     * @param stream - Output stream
     * @param formatter - Formatter to use
     */
    public StreamOutput(PrintStream stream, Formatter formatter) {
        super(formatter);
        this.stream = stream;
    }
}
