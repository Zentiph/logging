package logging;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import logging.format.Formatter;
import logging.output.FileOutput;
import logging.output.Output;
import logging.output.StreamOutput;

public class Logger {
    // How many steps up the stack we need to go to find
    // the location of the original caller
    private final int STACK_LEVEL = 3;

    private String name;
    private LogLevel baseLevel;
    private boolean enabled;
    private Formatter formatter;
    private ArrayList<Output> outputs;

    public static final LogLevel DEFAULT_LEVEL = LogLevel.INFO;
    public static final Formatter DEFAULT_FORMATTER = new Formatter();

    /**
     * Set up this Logger.
     * Identical to calling Logger().config with all null parameters.
     */
    public void setUp() {
        config(null, null, null, null);
    }

    /**
     * Configure this Logger without a filepath.
     * Enter parameters as null to leave them unchanged.
     *
     * @param level - Base LogLevel
     * @param form - Formatter to use
     * @param outs - Collection of outputs
     * @param e - Whether the logger should be enabled
     */
    public void config(
        LogLevel level,
        Formatter form,
        ArrayList<Output> outs,
        Boolean e
    ) {
        if (level != null) {
            baseLevel = level;
        }
        if (form != null) {
            formatter = form;
        }
        if (outs != null) {
            outputs.clear();
            for (Output output : outs) {
                outputs.add(output);
            }
        }
        if (e != null) {
            enabled = e;
        }

        if (outputs.size() == 0) {
            outputs.add(new StreamOutput());
        }
    }

    /**
     * Configure this Logger.
     * Enter parameters as null to leave them unchanged.
     *
     * @param level - Base LogLevel
     * @param form - Formatter to use
     * @param outs - Collection of outputs
     * @param e - Whether the logger should be enabled
     * @param filepath - Filepath to log to (will automatically add a FileOutput to the Logger)
     * @throws IOException If an IOException occurs when adding a FileOutput
     */
    public void config(
        LogLevel level,
        Formatter form,
        ArrayList<Output> outs,
        Boolean e,
        String filepath
    ) throws IOException {
        if (filepath != null) {
            outputs.add(new FileOutput(filepath));
        }
        config(level, form, outs, e);
    }

    /**
     * Reset this Logger's config options to their default state.
     */
    public void resetConfig() {
        baseLevel = DEFAULT_LEVEL;
        formatter = DEFAULT_FORMATTER;
        outputs.clear();
        enabled = true;
    }

    /**
     * Log a message with this Logger's base level.
     *
     * @param message - Message to log
     */
    public void log(Object message) {
        logInner(message, baseLevel);
    }
    /**

    * Log a message.
     *
     * @param message - Message to log
     * @param level - LogLevel of the log
     */
    public void log(Object message, LogLevel level) {
        logInner(message, level);
    }

    /**
     * Log a message with LogLevel DEBUG.
     *
     * @param message - Message to log
     */
    public void debug(Object message) {
        logInner(message, LogLevel.DEBUG);
    }

    /**
     * Log a message with LogLevel INFO.
     *
     * @param message - Message to log
     */
    public void info(Object message) {
        logInner(message, LogLevel.INFO);
    }

    /**
     * Log a message with LogLevel WARN.
     *
     * @param message - Message to log
     */
    public void warn(Object message) {
        logInner(message, LogLevel.WARN);
    }

    /**
     * Log a message with LogLevel WARNING.
     *
     * @param message - Message to log
     */
    public void warning(Object message) {
        logInner(message, LogLevel.WARNING);
    }

    /**
     * Log a message with LogLevel ERROR.
     *
     * @param message - Message to log
     */
    public void error(Object message) {
        logInner(message, LogLevel.ERROR);
    }

    /**
     * Log a message with LogLevel FATAL.
     *
     * @param message - Message to log
     */
    public void fatal(Object message) {
        logInner(message, LogLevel.FATAL);
    }

    /**
     * Log a message with LogLevel CRITICAL.
     *
     * @param message - Message to log
     */
    public void critical(Object message) {
        logInner(message, LogLevel.CRITICAL);
    }

    /**
     * Generate a basic Logger for instant, quick logging.
     *
     * @return Basic Logger
     */
    public static Logger basicLogger(String name) {
        return new Logger(name, DEFAULT_LEVEL, DEFAULT_FORMATTER);
    }

    /**
     * Get this Logger's name.
     *
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Get this Logger's Formatter.
     *
     * @return Formatter
     */
    public Formatter getFormatter() {
        return formatter;
    }

    /**
     * Get this Logger's Formatter as another class.
     * This method is primarily for casting a Formatter
     * to a subclass, like ColorFormatter.
     *
     * @param <F> - Formatter type
     * @param type - Formatter type
     * @return Formatter casted to the type given
     */
    public <F extends Formatter> F getFormatterAs(Class<F> type) {
        return type.cast(formatter);
    }

    /**
     * Set this Logger's Formatter.
     *
     * @param form - Formatter
     */
    public void setFormatter(Formatter form) {
        formatter = form;
    }

    /**
     * Get this Logger's Outputs.
     *
     * @return Outputs
     */
    public ArrayList<Output> getOutputs() {
        return outputs;
    }

    /**
     * Add an Output to this Logger.
     *
     * @param output - Output to add
     */
    public void addOutput(Output output) {
        outputs.add(output);
    }

    /**
     * Remove an Output from this Logger.
     *
     * @param output - Output to remove
     */
    public void removeOutput(Output output) {
        int i = 0;
        for (Output existingOutput : outputs) {
            if (existingOutput.equals(output)) {
                break;
            }
            i++;
        }
        outputs.remove(i);
    }

    /**
     * Remove all Outputs from this Logger.
     */
    public void removeAllOutputs() {
        outputs.clear();
    }

    /**
     * Get this Logger's base logging level.
     *
     * @return Base logging level
     */
    public LogLevel getBaseLevel() {
        return baseLevel;
    }

    /**
     * Set this Logger's base logging level.
     *
     * @param level - Base logging level
     */
    public void setBaseLevel(LogLevel level) {
        baseLevel = level;
    }

    /**
     * Check whether this Logger is enabled.
     *
     * @return Enabled status
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Enable this Logger.
     */
    public void enable() {
        enabled = true;
    }

    /**
     * Disable this Logger.
     */
    public void disable() {
        enabled = false;
    }

    /**
     * Internal backend logging function.
     * Format and send message to each Output in this Logger's outputs ArrayList.
     *
     * @param message - Message to log
     * @param level - LogLevel of the log
     * @throws IllegalArgumentException If message cannot be converted to a String
     */
    private void logInner(Object message, LogLevel level) throws IllegalArgumentException {
        String messageString = message.toString();

        if (messageString == null) {
            throw new IllegalArgumentException("Log message object's .toString() method must not return null");
        }

        // Stop here if this Logger is disabled or the LogLevel is under baseLevel
        if (!enabled || LogLevels.toInt(baseLevel) > LogLevels.toInt(level)) {
            return;
        }

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        LogRecord record = new LogRecord(messageString, level, name, LocalDateTime.now(), stackTrace[STACK_LEVEL]);

        for (Output output : outputs) {
            String out;

            if (output.getFormatter() != null) {
                out = output.getFormatter().format(record);
            } else {
                out = formatter.format(record);
            }

            boolean sent = output.send(out);
            // If an IOException occurs, disable that Output and log it.
            if (!sent) {
                output.disable();
                logInner(
                    "Output " + output.toString() + " raised an IOException and has been disabled",
                    LogLevel.ERROR
                );
            }
        }
    }

    /**
     * Initialize a new Logger with base logging level of LogLevel.INFO and a default Formatter.
     *
     * @param name - Name of this Logger
     */
    public Logger(String name) {
        this(name, DEFAULT_LEVEL, DEFAULT_FORMATTER);
    }

    /**
     * Initialize a new Logger with a default Formatter.
     *
     * @param name - Name of this Logger
     * @param baseLevel - Lowest LogLevel to track
     */
    public Logger(String name, LogLevel baseLevel) {
        this(name, baseLevel, DEFAULT_FORMATTER);
    }

    /**
     * Initialize a new Logger.
     *
     * @param name - Name of this Logger
     * @param baseLevel - Lowest LogLevel to track
     * @param formatter - Formatter to use
     */
    public Logger(String name, LogLevel baseLevel, Formatter formatter) {
        this.name = name;
        this.baseLevel = baseLevel;
        this.enabled = true;
        this.formatter = formatter;
        this.outputs = new ArrayList<>();
    }
}
