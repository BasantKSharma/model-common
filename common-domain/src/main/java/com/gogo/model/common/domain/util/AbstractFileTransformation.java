package com.gogo.model.common.domain.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * Common utility methods
 */
public abstract class AbstractFileTransformation {

    protected final static Map<String, String> MAP_PRODUCT = new HashMap<>();

    /**
     * Transform single line into multiline file
     */
    protected void transformSingleLineIntoMultiline(String sourceFileName, String outputFileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(sourceFileName))) {
            if (isGenerateFileMultipleLines()) {
                Files.write(Paths.get(outputFileName), new ArrayList<>());
            }
            String line;
            int lineCount = 0;
            int processedLineCount = 0;
            while ((line = br.readLine()) != null) {
                String processedLine = processLine(line);
                if (isPrintEnabled()) {
                    System.out.println(processedLine);
                }
                if (isGenerateFileMultipleLines()) {
                    Path path = Paths.get(outputFileName);
                    Files.write(path, Collections.singletonList(processedLine), StandardOpenOption.APPEND);
                }
                lineCount++;
                processedLineCount += processedLine.split("\n").length;
            }
            LogUtil.logInfo("Total lines in source file: " + lineCount);
            LogUtil.logInfo("Total lines in target file: " + processedLineCount);
        } catch (IOException e) {
            LogUtil.logError(e.getMessage());
        }
    }

    /**
     * Transform lines into procedure format
     */
    protected void transformSingleLineIntoProcedure(String sourceFileName, String outputFileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(sourceFileName))) {
            if (isGenerateFileProcedure()) {
                Files.write(Paths.get(outputFileName), new ArrayList<>());
            }
            String line;
            int lineCount = 0;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("--") || line.startsWith("INSERT INTO")) {
                    // Skip empty lines, comments, and the initial INSERT INTO statement
                    continue;
                }
                String processedLine = processLineIntoProcedure(line);
                if (isPrintEnabled()) {
                    System.out.println(processedLine);
                }
                if (isGenerateFileProcedure()) {
                    Path path = Paths.get(outputFileName);
                    if (lineCount == 0) {
                        Files.write(path, Collections.singletonList(getProcedureHeader()), StandardOpenOption.APPEND);
                    }
                    Files.write(path, Collections.singletonList(processedLine), StandardOpenOption.APPEND);
                }
                lineCount = lineCount + 1;
            }
        } catch (IOException e) {
            LogUtil.logError(e.getMessage());
        }
    }

    protected String processLineIntoProcedure(String line) {
        throw new RuntimeException("Implement it");
    }

    /**
     * Transform lines into procedure format
     */
    protected void createProductMap(String sourceFileName, Map<String, String> mapProduct, ProductQueryFileUtil productQueryFileUtil) {
        try (BufferedReader br = new BufferedReader(new FileReader(sourceFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<String> columns = productQueryFileUtil.extractParts(line);
                if (!columns.isEmpty() && columns.size() > 1) {
                    //System.out.println(columns.get(0) + "--" + columns.get(1));
                    String key = extractKey(columns.get(0));
                    String value = columns.get(1).trim();
                    mapProduct.put(key, value);
                }
            }
        } catch (IOException e) {
            LogUtil.logError(e.getMessage());
        }
    }

    /**
     * Splitter regex which split single line into multiple lines
     */
    protected String processLine(String line) {
        return line.replaceAll("\\s*\\(\\d+,", "\n$0");
    }

    /**
     * Extract the line into multiple column parts
     */
    protected List<String> extractParts(String line) {
        List<String> parts = new ArrayList<>();
        if (line == null || line.isEmpty()) {
            return parts;
        }
        line = line.trim();
        line = line.replaceAll("^\\(|\\).*$", "");
        String[] split = line.split("(?<!\\\\),"); // Negative lookbehind to avoid splitting escaped commas
        for (int i = 0; i < split.length; i++) {
            parts.add(split[i].replaceAll("^\"|\"$", "")); // Remove leading and trailing double quotes
        }
        return parts;
    }


    /**
     * Extract the line based on column types
     */
    protected List<String> extractParts(String line, Class<?>[] COL_TYPES) {
        line = line.trim();
        if (line.startsWith("(")) {
            line = line.substring(1);
        }
        if (line.endsWith(")")) {
            line = line.substring(0, line.length() - 1);
        }
        if (line.endsWith("),") || line.endsWith(");")) {
            line = line.substring(0, line.length() - 2);
        }
        //line = line.strip().substring(1, line.length() - 1);
        String[] parts = line.split(",(?=(?:[^']*'[^']*')*[^']*$)", -1);
        for (int i = 0; i < parts.length; i++) {
            Class<?> type = COL_TYPES[i];
            if (type == Integer.class) {
                int value = Integer.parseInt(parts[i].strip());
                //System.out.println("Part " + (i + 1) + ": " + value);
            } else if (type == String.class) {
                String value = parts[i].strip();
                value = value.substring(1, value.length() - 1);
                //System.out.println("Part " + (i + 1) + ": " + value);
            }

        }
        return Arrays.stream(parts).toList();
    }

    /**
     * Extract the key from the value
     */
    protected String extractKey(String value) {
        List<String> parts = new ArrayList<>();
        String key = value.trim().replaceAll("\\(", "");
        key = key.replaceAll("\\)", "");
        return key;
    }

    /**
     * Remove quotes from file
     **/
    protected String removeQuotes(String text) {
        if (text.endsWith("'")) {
            text = text.substring(0, text.length() - 1);
        }
        return text;
    }

    /**
     * Get procedure header
     */
    protected String getProcedureHeader() {
        return "-- AUTO GENERATED \n";
    }

    /**
     * Is Generate output file with multiple lines
     */
    protected boolean isGenerateFileMultipleLines() {
        return true;
    }

    /**
     * Is Generate output file with procedure
     */
    protected boolean isGenerateFileProcedure() {
        return true;
    }

    /**
     * Is print output in console
     */
    protected boolean isPrintEnabled() {
        return true;
    }
}
