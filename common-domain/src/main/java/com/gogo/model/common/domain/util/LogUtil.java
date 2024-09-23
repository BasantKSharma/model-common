package com.gogo.model.common.domain.util;

/**
 * File utility methods
 **/
public final class LogUtil {

    public static final int CHARS_BOTH_SIDE = 20;

    /**
     * Print the message
     */
    public static void print(String message) {
        int messageLength = message.length();
        StringBuilder formattedLineBuilder = new StringBuilder();
        int limit = messageLength + (CHARS_BOTH_SIDE * 2);
        formattedLineBuilder.append("=".repeat(Math.max(0, limit)));

        LogUtil.logInfo(formattedLineBuilder.toString());
        LogUtil.logInfo(" ".repeat(CHARS_BOTH_SIDE) + message + " ");
        LogUtil.logInfo(formattedLineBuilder.toString());
    }

    /**
     * Logging information util
     */
    public static void logError(String text) {
        log("ERROR", text);
    }

    public static void logInfo(String text) {
        log("INFO", text);
    }

    public static void logWarn(String text) {
        log("WARN", text);
    }

    public static void log(String prefix, String text) {
        System.out.println(prefix + " : " + text);
    }

    public static void logConnectError(String url) {
        logError("Failed to connect to the url [" + url + "]");
    }
}
