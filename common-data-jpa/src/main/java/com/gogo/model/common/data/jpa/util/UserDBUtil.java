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
public final class UserDBUtil extends AbstractFileTransformation {

    private static String QUERY = "select id, email from user";

    private static String FOLDER_PATH = GenericConstants.ABSOLUTE_FILE_PATH_IMAGE_FOLDER_USER;

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
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                File oldFileName = new File(FOLDER_PATH + FORWARD_SLASH + id + ".png");
                File newFileName = new File(FOLDER_PATH + FORWARD_SLASH + email + ".png");
                boolean isRenamed = oldFileName.renameTo(newFileName);
                LogUtil.logInfo("Rename result : "+isRenamed);
            }
        } catch (SQLException e) {
            LogUtil.logError(e.getMessage());
        }
    }
}
