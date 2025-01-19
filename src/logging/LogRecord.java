package logging;

import java.time.LocalDateTime;

public record LogRecord(String message, LogLevel level, String loggerName, LocalDateTime time, StackTraceElement stackFrame) {
    /**
     * Return the filepath of this LogRecord if it can be found.
     *
     * @return The filepath if found, otherwise the filename
     */
    public String filepath() {
        String path = getFullPath(stackFrame.getClassName(), stackFrame.getFileName());
        if (path != null) {
            return path;
        }
        return stackFrame.getFileName(); // Fall back to filename if path can't be found
    }

    /**
     * Get the filename this LogRecord was created in.
     *
     * @return Filename
     */
    public String filename() {
        return stackFrame.getFileName();
    }

    /**
     * Get the line number this LogRecord was created in.
     *
     * @return Line number
     */
    public int lineNumber() {
        return stackFrame.getLineNumber();
    }

    private static String getFullPath(String className, String fileName) {
        if (fileName == null) {
            return null;
        }

        // Try to load the class as a resource
        String resourcePath = className.replace('.', '/') + ".class";
        var resource = Thread.currentThread().getContextClassLoader().getResource(resourcePath);
        if (resource != null) {
            return resource.getPath();
        }
        return null;
    }
}
