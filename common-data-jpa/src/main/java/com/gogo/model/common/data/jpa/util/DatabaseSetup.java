package com.gogo.model.common.data.jpa.util;

import com.gogo.model.common.domain.util.LogUtil;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * Create database for shopping app.
 * Execute sql file containing DDL/DML statement.
 * */
public final class DatabaseSetup {

    private final JdbcTemplate jdbcTemplate;

    private final String directoryPath;

    private final String procedureSeparator;

    public DatabaseSetup(String dataSourceUrl, String dataSourceUsername, String dataSourcePassword, String directoryPath, String procedureSeparator) {
        this.directoryPath = directoryPath;
        this.procedureSeparator = procedureSeparator;

        // Manually create and configure a DataSource
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(dataSourceUrl);
        dataSource.setUsername(dataSourceUsername);
        dataSource.setPassword(dataSourcePassword);

        // Manually create a JdbcTemplate instance
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void executeDbScripts() {
        File directory = new File(directoryPath);
        System.out.println("DataInitializer begins ...");
        processFiles(directory);
        System.out.println("DataInitializer completed.");
    }

    /**
     * Process the scripts in current folders or sub folders.
     */
    private void processFiles(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            Arrays.sort(files, Comparator.comparing(File::getName));
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println("Directory: " + file.getName());
                    processFiles(file);
                } else if (file.isFile() && file.getName().toLowerCase().endsWith(".sql")) {
                    try {
                        Resource resource = new FileSystemResource(file.getPath());
                        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
                        System.out.println(" --> File :" + resource.getFile());
                        if (procedureSeparator != null && file.getName().contains("procedure")) {
                            //For procedure
                            ScriptUtils.executeSqlScript(Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection(),
                                    encodedResource, false, false, ScriptUtils.DEFAULT_COMMENT_PREFIX, "$$",
                                    ScriptUtils.DEFAULT_BLOCK_COMMENT_START_DELIMITER, ScriptUtils.DEFAULT_BLOCK_COMMENT_END_DELIMITER);
                        } else {
                            //For other statements
                            ScriptUtils.executeSqlScript(Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection(),
                                    resource);
                        }
                        //Thread.sleep(1000);
                    } catch (Exception e) {
                        LogUtil.logError(e.getMessage());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // Example usage
        DatabaseSetup dataInitializer = new DatabaseSetup(
                "jdbc:mysql://localhost:3306/shopping",
                "root",
                "password",
                "shopping-reference/db",
                "$$"
        );
        dataInitializer.executeDbScripts();
    }
}