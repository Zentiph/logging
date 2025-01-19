package logging.format;

import logging.LogLevel;
import logging.LogLevels;
import logging.LogRecord;
import logging.format.color.Ansi;

// TODO: ADD ABILITY TO PASS IN THEIR OWN COLORS
public class ColorFormatter extends Formatter {
    private String debugColor;
    private String infoColor;
    private String warnColor;
    private String errorColor;
    private String fatalColor;

    private static final String DEFAULT_DEBUG = "\033[32m";             // Green
    private static final String DEFAULT_INFO = "\033[34m";              // Blue
    private static final String DEFAULT_WARN = "\033[33m";              // Yellow
    private static final String DEFAULT_ERROR = "\033[31m";             // Red
    private static final String DEFAULT_FATAL = "\033[31m" + "\033[1m"; // Red bold
    private static final String RESET = "\033[0m";

    public String format(LogRecord record) {
        return new Formatter(getFormatFromLevel(record.level())).format(record);
    }

    /**
     * Set the colors of this ColorFormatter.
     * Enter parameters as null to leave them unchanged.
     *
     * @param debug - DEBUG color
     * @param info - INFO color
     * @param warn - WARN color
     * @param error - ERROR color
     * @param fatal - FATAL color
     */
    public void setColors(Ansi debug, Ansi info, Ansi warn, Ansi error, Ansi fatal) {
        if (debug != null) {
            debugColor = debug.getCode();
        }
        if (info != null) {
            infoColor = info.getCode();
        }
        if (warn != null) {
            warnColor = warn.getCode();
        }
        if (error != null) {
            errorColor = error.getCode();
        }
        if (fatal != null) {
            fatalColor = fatal.getCode();
        }
    }

    /**
     * Set the DEBUG color of this ColorFormatter.
     *
     * @param color - DEBUG color
     */
    public void setDebugColor(Ansi color) {
        debugColor = color.getCode();
    }

    /**
     * Set the INFO color of this ColorFormatter.
     *
     * @param color - INFO color
     */
    public void setInfoColor(Ansi color) {
        infoColor = color.getCode();
    }

    /**
     * Set the WARN color of this ColorFormatter.
     *
     * @param color - WARN color
     */
    public void setWarnColor(Ansi color) {
        warnColor = color.getCode();
    }

    /**
     * Set the ERROR color of this ColorFormatter.
     *
     * @param color - ERROR color
     */
    public void setErrorColor(Ansi color) {
        errorColor = color.getCode();
    }

    /**
     * Set the FATAL color of this ColorFormatter.
     *
     * @param color - FATAL color
     */
    public void setFatalColor(Ansi color) {
        fatalColor = color.getCode();
    }

    /**
     * Reset this ColorFormatter's colors to their default values.
     */
    public void resetColors() {
        debugColor = DEFAULT_DEBUG;
        infoColor = DEFAULT_INFO;
        warnColor = DEFAULT_WARN;
        errorColor = DEFAULT_ERROR;
        fatalColor = DEFAULT_FATAL;
    }

    /**
     * Reset this ColorFormatter's DEBUG color to its default value.
     */
    public void resetDebugColor() {
        debugColor = DEFAULT_DEBUG;
    }

    /**
     * Reset this ColorFormatter's INFO color to its default value.
     */
    public void resetInfoColor() {
        infoColor = DEFAULT_INFO;
    }

    /**
     * Reset this ColorFormatter's WARN color to its default value.
     */
    public void resetWarnColor() {
        warnColor = DEFAULT_WARN;
    }

    /**
     * Reset this ColorFormatter's ERROR color to its default value.
     */
    public void resetErrorColor() {
        errorColor = DEFAULT_ERROR;
    }

    /**
     * Reset this ColorFormatter's FATAL color to its default value.
     */
    public void resetFatalColor() {
        fatalColor = DEFAULT_FATAL;
    }

    /**
     * Get the format used for the given level.
     *
     * @param level - Level to get the format for
     * @return The format for the given level
     */
    private String getFormatFromLevel(LogLevel level) {
        switch (LogLevels.toInt(level)) {
            case 0:
                return debugColor + getTemplate() + RESET;

            case 1:
                return infoColor + getTemplate() + RESET;

            case 2:
                return warnColor + getTemplate() + RESET;

            case 3:
                return errorColor + getTemplate() + RESET;

            case 4:
                return fatalColor + getTemplate() + RESET;

            default:
                break;
        }

        return null;
    }

    /**
     * Initialize a new Formatter with default message and timestamp templates and a default terminator.
     */
    public ColorFormatter() {
        super(DEFAULT_MESSAGE_TEMPLATE, DEFAULT_TIMESTAMP_TEMPLATE, DEFAULT_TERMINATOR);
        this.debugColor = DEFAULT_DEBUG;
        this.infoColor = DEFAULT_INFO;
        this.warnColor = DEFAULT_WARN;
        this.errorColor = DEFAULT_ERROR;
        this.fatalColor = DEFAULT_FATAL;
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
    public ColorFormatter(String template) {
        super(template, DEFAULT_TIMESTAMP_TEMPLATE, DEFAULT_TERMINATOR);
        this.debugColor = DEFAULT_DEBUG;
        this.infoColor = DEFAULT_INFO;
        this.warnColor = DEFAULT_WARN;
        this.errorColor = DEFAULT_ERROR;
        this.fatalColor = DEFAULT_FATAL;
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
    public ColorFormatter(String template, String timestampTemplate) {
        super(template, timestampTemplate, DEFAULT_TERMINATOR);
        this.debugColor = DEFAULT_DEBUG;
        this.infoColor = DEFAULT_INFO;
        this.warnColor = DEFAULT_WARN;
        this.errorColor = DEFAULT_ERROR;
        this.fatalColor = DEFAULT_FATAL;
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
    public ColorFormatter(String template, String timestampTemplate, char terminator) {
        super(template, timestampTemplate, terminator);
        this.debugColor = DEFAULT_DEBUG;
        this.infoColor = DEFAULT_INFO;
        this.warnColor = DEFAULT_WARN;
        this.errorColor = DEFAULT_ERROR;
        this.fatalColor = DEFAULT_FATAL;
    }
}
