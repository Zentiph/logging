package logging.format;

import java.time.format.DateTimeFormatter;

import logging.LogLevels;
import logging.LogRecord;

public class Formatter {
    public static final String DEFAULT_MESSAGE_TEMPLATE = "[{time}] [{level}] {name} - {message} ({filename}:{line})";
    public static final String DEFAULT_TIMESTAMP_TEMPLATE = "yyyy-MM-dd HH:mm:ss";
    public static final char DEFAULT_TERMINATOR = '\n';

    private String template;
    private String timestampTemplate;
    private char terminator;

    /**
     * Format a LogRecord with this Formatter's templates.
     *
     * @param record - LogRecord to format
     * @return Formatted LogRecord as a String
     */
    public String format(LogRecord record) {
        return template.replace(
            "{message}", record.message()
        ).replace(
            "{level}", LogLevels.toString(record.level())
        ).replace(
            "{levelNumber}", Integer.toString(LogLevels.toInt(record.level()))
        ).replace(
            "{name}", record.loggerName()
        ).replace(
            "{filepath}", record.filepath()
        ).replace(
            "{filename}", record.filename()
        ).replace(
            "{line}", Integer.toString(record.lineNumber())
        ).replace(
            "{time}", record.time().format(DateTimeFormatter.ofPattern(timestampTemplate))
        ) + terminator;
    }

    /**
     * Get this Formatter's template.
     *
     * @return Template
     */
    public String getTemplate() {
        return template;
    }

    /**
     * Get this Formatter's timestamp template.
     *
     * @return Timestamp template
     */
    public String getTimestampTemplate() {
        return timestampTemplate;
    }

    /**
     * Get this Formatter's terminator.
     *
     * @return Terminator
     */
    public char getTerminator() {
        return terminator;
    }

    /**
     * Initialize a new Formatter with default message and timestamp templates and a default terminator.
     */
    public Formatter() {
        this(DEFAULT_MESSAGE_TEMPLATE, DEFAULT_TIMESTAMP_TEMPLATE, DEFAULT_TERMINATOR);
    }

    /**
     * Initialize a new Formatter with a default timestamp template and a default terminator.
     *
     * @param template - Message formatting template (see below)
     * Formatting fields:
     * - {message}: The message to be logged
     * - {level}: The name of the LogLevel
     * - {levelNumber}: The numeric value of the LogLevel
     * - {name}: The name of the Logger
     * - {filepath}: The filepath where the log was made
     * - {filename}: The filename where the log was made
     * - {line}: The line where the log was made
     * - {time}: The date and time when the log was made (using {@link java.time.LocalDateTime})
     */
    public Formatter(String template) {
        this(template, DEFAULT_TIMESTAMP_TEMPLATE, DEFAULT_TERMINATOR);
    }

    /**
     * Initialize a new Formatter with a default terminator.
     *
     * @param template - Message formatting template (see below)
     * Formatting fields:
     * - {message}: The message to be logged
     * - {level}: The name of the LogLevel
     * - {levelNumber}: The numeric value of the LogLevel
     * - {name}: The name of the Logger
     * - {filepath}: The filepath where the log was made
     * - {filename}: The filename where the log was made
     * - {line}: The line where the log was made
     * - {time}: The date and time when the log was made (using {@link java.time.LocalDateTime})
     * @param timestampTemplate - Timestamp formatting template
     */
    public Formatter(String template, String timestampTemplate) {
        this(template, timestampTemplate, DEFAULT_TERMINATOR);
    }

    /**
     * Initialize a new Formatter.
     *
     * @param template - Message formatting template (see below)
     * Formatting fields:
     * - {message}: The message to be logged
     * - {level}: The name of the LogLevel
     * - {levelNumber}: The numeric value of the LogLevel
     * - {name}: The name of the Logger
     * - {filepath}: The filepath where the log was made
     * - {filename}: The filename where the log was made
     * - {line}: The line where the log was made
     * - {time}: The date and time when the log was made (using {@link java.time.LocalDateTime})
     * @param timestampTemplate - Timestamp formatting template
     * @param terminator - Character to add at the end of each message
     */
    public Formatter(String template, String timestampTemplate, char terminator) {
        this.template = template;
        this.timestampTemplate = timestampTemplate;
        this.terminator = terminator;
    }
}
