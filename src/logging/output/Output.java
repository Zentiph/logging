package logging.output;

import logging.format.Formatter;

public abstract class Output {
    private Formatter formatter;
    private boolean enabled;

    /**
     * Send a message to an output channel.
     * The message will be formatted by this Output's Formatter if it exists,
     * otherwise it will fall back to the attached Logger's Formatter.
     * This method should be overridden by subclasses.
     *
     * @param message - Message to send
     * @return If the message was successfully sent
     */
    public abstract boolean send(String message);

    /**
     * Get this Output's Formatter.
     *
     * @return Formatter
     */
    public Formatter getFormatter() {
        return formatter;
    }

    /**
     * Set this Output's Formatter.
     *
     * @param form - Formatter
     */
    public void setFormatter(Formatter form) {
        formatter = form;
    }

    /**
     * Remove this Output's Formatter.
     */
    public void removeFormatter() {
        formatter = null;
    }

    /**
     * Check if this Output is enabled.
     *
     * @return Enabled status
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Enable this Output.
     */
    public void enable() {
        enabled = true;
    }

    /**
     * Disable this output.
     */
    public void disable() {
        enabled = false;
    }

    /**
     * Initialize an Output with no Formatter.
     */
    public Output() {
        this(null);
    }

    /**
     * Initialize an Output with a Formatter.
     *
     * @param formatter - Formatter to give the Output.
     * This Formatter will override the attached Logger's Formatter.
     */
    public Output(Formatter formatter) {
        this.formatter = formatter;
    }
}
