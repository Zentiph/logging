package logging;

public class LogLevels {
    /**
     * Return the String representation of the given LogLevel.
     *
     * @param logLevel - LogLevel to convert to String
     * @return String representation of logLevel; null if conversion fails
     */
    public static String toString(LogLevel logLevel) {
        switch (logLevel) {
            case LogLevel.DEBUG:
                return "DEBUG";

            case LogLevel.INFO:
                return "INFO";

            case LogLevel.WARN:
                return "WARN";

            case LogLevel.WARNING:
                return "WARNING";

            case LogLevel.ERROR:
                return "ERROR";

            case LogLevel.FATAL:
                return "FATAL";

            case LogLevel.CRITICAL:
                return "CRITICAL";

            default:
                return null;
        }
    }

    /**
     * Return the int representation of the given LogLevel.
     *
     * @param logLevel - LogLevel to convert to int
     * @return The int representation of logLevel; -1 if conversion fails
     */
    public static int toInt(LogLevel logLevel) {
        switch (logLevel) {
            case LogLevel.DEBUG:
                return 0;

            case LogLevel.INFO:
                return 1;

            case LogLevel.WARN:
                return 2;

            case LogLevel.WARNING:
                return 2;

            case LogLevel.ERROR:
                return 3;

            case LogLevel.FATAL:
                return 4;

            case LogLevel.CRITICAL:
                return 4;

            default:
                return -1;
        }
    }

    /**
     * Get the LogLevel associated with an int.
     *
     * @param i - int to convert to LogLevel
     * @return The LogLevel associated with i; null if conversion fails
     */
    public static LogLevel fromInt(int i) {
        switch (i) {
            case 0:
                return LogLevel.DEBUG;

            case 1:
                return LogLevel.INFO;

            case 2:
                return LogLevel.WARN;

            case 3:
                return LogLevel.ERROR;

            case 4:
                return LogLevel.FATAL;

            default:
                return null;
        }
    }

    /**
     * Get the LogLevel associated with a String.
     *
     * @param s - String to convert to LogLevel
     * @return The LogLevel associated with s; null if conversion fails
     */
    public static LogLevel fromString(String s) {
        s = s.toUpperCase();

        switch (s) {
            case "DEBUG":
                return LogLevel.DEBUG;

            case "INFO":
                return LogLevel.INFO;

            case "WARN":
                return LogLevel.WARN;

            case "WARNING":
                return LogLevel.WARNING;

            case "ERROR":
                return LogLevel.ERROR;

            case "FATAL":
                return LogLevel.FATAL;

            case "CRITICAL":
                return LogLevel.CRITICAL;

            default:
                return null;
        }
    }
}
