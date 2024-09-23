package com.gogo.model.common.domain.util;

import com.gogo.model.common.domain.config.ServiceUrlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Component
public final class CommonUtil {

    private static Integer lineSize;

    @Value("${shopping.app.ui.line.size.message:30}")
    public void setLineSize(Integer lineSize) {
        CommonUtil.lineSize = lineSize;
    }

    @Autowired
    public ServiceUrlConfig serviceUrlConfig;

    public static void sleep() {
        sleep(1);
    }

    public static void sleep(int seconds) {
        LogUtil.logInfo("Thread sleeping for [" + seconds + "] seconds");
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException ex) {
            LogUtil.logWarn("Thread interrupted" + ex.getMessage());
        }
        LogUtil.logInfo("Thread wake up");
    }

    public static String getFormattedMessage(String message) {
        int maxLength = lineSize;
        StringBuilder formattedMessage = new StringBuilder();
        int startIndex = 0;
        while (startIndex < message.length()) {
            int endIndex = Math.min(startIndex + maxLength, message.length());
            if (endIndex < message.length() && !Character.isWhitespace(message.charAt(endIndex))) {
                while (endIndex > startIndex && !Character.isWhitespace(message.charAt(endIndex))) {
                    endIndex--;
                }
                if (endIndex == startIndex) {
                    endIndex = Math.min(startIndex + maxLength, message.length());
                }
            }
            formattedMessage.append(message, startIndex, endIndex).append("\n");
            startIndex = endIndex + 1;
        }
        return formattedMessage.toString();
    }


    /**
     * Get formatted url based on DTO class
     * TODO
     **/
    public static <T> String getServiceUrl(ServiceUrlConfig serviceUrlConfig, Class<T> responseType, String inputUrl) {
        if (inputUrl.startsWith("http")) {
            return inputUrl;
        }
        return "TODO" + inputUrl;
    }

    /**
     * Get the field value using reflection.
     */
    public static <T> Object getFieldValue(T data, String fieldName) {
        if (fieldName.contains("(")) {
            fieldName = fieldName.substring(0, fieldName.indexOf('('));
        }

        Class<?> clazz = data.getClass();
        fieldName = firstCharToLowerCase(fieldName);
        Object fieldValue = "ERROR";
        while (clazz != null && clazz != Object.class) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                fieldValue = field.get(data);
                if (fieldValue != null && Collection.class.isAssignableFrom(field.getType())) {
                    if (fieldValue instanceof Set<?>) {
                        boolean isSetOfString = ((Set<?>) fieldValue).stream().allMatch(element -> element instanceof String);
                        if (isSetOfString) {
                            fieldValue = fieldValue.toString();
                        }
                    }
                } else {
                    fieldValue = fieldValue != null ? fieldValue.toString() : "";
                }
                break;
            } catch (NoSuchFieldException | IllegalAccessException e) {
                fieldValue = invokeMethod(data, fieldName);
            }
            clazz = clazz.getSuperclass();
        }

        if ("ERROR".equals(fieldValue)) {
            LogUtil.logError("Error in extracting attribute [" + fieldName + "] from Dto [" + data.getClass().getName() + "]");
        }

        return fieldValue;
    }

    /**
     * Invoke the method
     */
    private static <T> Object invokeMethod(T data, String methodName) {
        Class<?> clazz = data.getClass();
        methodName = "get" + firstCharToUpperCase(methodName);
        try {
            Method method = clazz.getDeclaredMethod(methodName);
            method.setAccessible(true);
            return method.invoke(data);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // Handle method invocation exception, fallback to field access
            return "ERROR";
        }
    }

    /**
     * Capitalize the attribute
     */
    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * Capitalize the attribute of last segment separated by delimiter
     */
    public static String capitalize(String input, String delimiter) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        String[] pathSegments = input.split(delimiter);
        String lastSegment = pathSegments[pathSegments.length - 1];
        if ("home".equalsIgnoreCase(lastSegment) && pathSegments.length >= 2) {
            return capitalize(pathSegments[pathSegments.length - 2]);
        }
        return capitalize(lastSegment);
    }

    /**
     * Convert first character of string to lower case
     */
    public static String firstCharToLowerCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * Convert first character of string to upper case
     */
    public static String firstCharToUpperCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * Get current date
     */
    public static Date getCurrentDate() {
        return java.sql.Timestamp.valueOf(LocalDateTime.now());
    }
}
