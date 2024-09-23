package com.gogo.model.common.domain.util;

import com.gogo.model.common.domain.constants.FileConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.gogo.model.common.domain.constants.GenericConstants.FILE_PATH_EXTRAS;
import static com.gogo.model.common.domain.constants.GenericConstants.FORWARD_SLASH;

/**
 * File utility methods
 **/
public final class FileUtil {

    public static String getRootFolder() {
        return "users/";
    }

    public static void main(String[] args) {
        String sourceDirectory = "/Users/basantsharma/Downloads/Brands/brand-logos";
        String targetDirectory = "/Users/basantsharma/Downloads/Brands/brand-logos";
        removeImagesFromDirectories(sourceDirectory, targetDirectory);
    }

    private static void removeImagesFromDirectories(String sourceDirectory, String targetDirectory) {
        try {
            Files.walk(Paths.get(sourceDirectory))
                    .filter(Files::isRegularFile)
                    .forEach(filePath -> {
                        try {
                            String contentType = Files.probeContentType(filePath);
                            if (contentType != null && contentType.equals("image/png")) {
                                Files.move(filePath, Paths.get(targetDirectory, filePath.getFileName().toString()));
                            }
                        } catch (IOException e) {
                            LogUtil.logError(e.getMessage());
                        }
                    });
            removeSubdirectories(sourceDirectory);
            LogUtil.logInfo("PNG images moved successfully and subdirectories removed.");
        } catch (IOException e) {
            LogUtil.logError(e.getMessage());
        }
    }

    private static void removeSubdirectories(String directory) {
        File dir = new File(directory);
        File[] subdirectories = dir.listFiles(File::isDirectory);
        if (subdirectories != null) {
            for (File subdirectory : subdirectories) {
                removeSubdirectories(subdirectory.getAbsolutePath());
                subdirectory.delete();
            }
        }
    }

    /**
     * Save file in directory
     **/
    public static void save(List<String> sourceFiles, String targetPath) throws IOException {
        if (sourceFiles != null) {
            for (String file : sourceFiles) {
                save(file, targetPath);
            }
        } else {
            LogUtil.logWarn("No file present in source directory");
        }
    }

    /**
     * Save file in directory
     **/
    public static String save(String sourceFile, String targetPath) {
        if (sourceFile == null) {
            LogUtil.logWarn("Empty source file ");
            return null;
        }
        String fileName = new File(sourceFile).getName();
        Path sourcePath = Paths.get(sourceFile);
        if (!Files.exists(sourcePath)) {
            sourcePath = Paths.get(FileConstants.TEMP_FOLDER + sourceFile);
        }

        try {
            if (Files.exists(sourcePath)) {
                Path permanentFilePath = Paths.get(targetPath, fileName);
                Path destinationFolderPath = Paths.get(targetPath);
                Files.createDirectories(destinationFolderPath);
                try {
                    Files.move(sourcePath, permanentFilePath);
                    LogUtil.logInfo("File moved from " + sourcePath + " to " + permanentFilePath);
                } catch (FileAlreadyExistsException ex) {
                    LogUtil.logWarn("File [" + sourceFile + "] already exists in the " + permanentFilePath);
                }
                return permanentFilePath.toString();
            } else {
                LogUtil.logError("File does not exist in the source path : " + sourceFile);
            }
        } catch (Exception e) {
            LogUtil.logError(e.getMessage());
            LogUtil.logError("Exception on file save : " + sourceFile);

        }
        return null;
    }

    /**
     * Save multipart file in temporary directory
     **/
    public static void saveTemporary(MultipartFile multipartFile) throws IOException {
        save(multipartFile, FileConstants.TEMP_FOLDER);
    }

    /**
     * Save array of multipart files in temporary directory
     **/
    public static void saveTemporary(List<MultipartFile> multipartFiles) throws IOException {
        if (multipartFiles != null) {
            for (MultipartFile file : multipartFiles) {
                save(file, FileConstants.TEMP_FOLDER);
            }
        }
    }

    /**
     * Save multipart file in directory
     **/
    public static void save(MultipartFile multipartFile, String folder) throws IOException {
        if (multipartFile.isEmpty()) {
            LogUtil.logWarn("No file found in multipart - " + multipartFile.getSize());
            return;
        }
        //String filename = UUID.randomUUID() + "--" + multipartFile.getOriginalFilename();
        String filename = multipartFile.getOriginalFilename();
        Path destinationFolderPath = Paths.get(folder);
        Files.createDirectories(destinationFolderPath);
        Path destinationPath = destinationFolderPath.resolve(filename);
        Files.write(destinationPath, multipartFile.getBytes());
        LogUtil.logInfo("File successfully saved into folder [" + folder + "] with fileName = [" + filename + "]");
    }

    /**
     * Derive the extra image filename, including the full path from parent product
     **/
    public static String getExtraImage(String mainImage, String extraImage) {
        if (StringUtils.isBlank(mainImage)) {
            return extraImage;
        }

        mainImage = mainImage.substring(0, mainImage.lastIndexOf(FORWARD_SLASH) + 1);
        extraImage = mainImage + FILE_PATH_EXTRAS + extraImage;
        return extraImage;
    }

}
