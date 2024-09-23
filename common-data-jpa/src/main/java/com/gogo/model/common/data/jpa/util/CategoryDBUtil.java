package com.gogo.model.common.data.jpa.util;

import com.gogo.model.common.domain.constants.DatabaseConstants;
import com.gogo.model.common.domain.constants.GenericConstants;
import com.gogo.model.common.domain.util.AbstractFileTransformation;
import com.gogo.model.common.domain.util.LogUtil;

import java.io.File;
import java.sql.*;

import static com.gogo.model.common.domain.constants.GenericConstants.FORWARD_SLASH;

/**
 * User file utility - rename from <id>.png to <email>.png
 **/
public final class CategoryDBUtil extends AbstractFileTransformation {

    private static String QUERY = "select image, name from category";

    private static String FOLDER_PATH = GenericConstants.ABSOLUTE_FILE_PATH_IMAGE_FOLDER_CATEGORY;

    public static void main(String[] args) {
       transform();
    }

    /**
     * Rename file
     * */
    public static void transform() {
        try (Connection connection = DriverManager.getConnection(DatabaseConstants.DB_URL, DatabaseConstants.DB_USERNAME, DatabaseConstants.DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String image = resultSet.getString("image");
                String name = resultSet.getString("name");
                // Create oldFileName and newFileName objects
                String fileName = name;
                if(!name.contains("&")){
                    fileName = name.replaceAll(" ","_");
                }
                File oldFileName = new File(FOLDER_PATH + FORWARD_SLASH + fileName + ".png");
                File oldFileName2 = new File(FOLDER_PATH + FORWARD_SLASH + name + ".png");
                File newFileName = new File(FOLDER_PATH + FORWARD_SLASH + image + ".png");
                // Check if the old file exists before renaming
                if (oldFileName.exists()  || oldFileName2.exists()) {
                    boolean isRenamed = false;//oldFileName.renameTo(newFileName);
                    //LogUtil.logInfo("File exists, rename result : " + isRenamed);
                } else {
                    LogUtil.logInfo("Old file does not exist: " + oldFileName.getAbsolutePath());
                }
            }
        } catch (SQLException e) {
            LogUtil.logError(e.getMessage());
        }
    }
}
