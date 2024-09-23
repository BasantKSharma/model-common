package com.gogo.model.common.domain.util;

import static com.gogo.model.common.domain.constants.FileConstants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Product utility - transform single line sql file into i) multi lines and then ii) format procedures
 **/
public final class ProductQueryFileUtil extends AbstractFileTransformation {

    final static ProductQueryFileUtil productQueryFileUtil = new ProductQueryFileUtil();

    public static void main(String[] args) {
       transform();
    }

    /**
     * Transform file
     * */
    public static void transform() {
        productQueryFileUtil.transformSingleLineIntoMultiline(SOURCE_DIRECTORY_1_PRODUCT, TARGET_DIRECTORY_1_PRODUCT);
        productQueryFileUtil.transformSingleLineIntoProcedure(TARGET_DIRECTORY_1_PRODUCT, TARGET_DIRECTORY_2_PRODUCT);
    }

    /**
     * Extract the line into multiple column parts
     * */
    @Override
    protected List<String> extractParts(String line) {
        String[] columns = line.substring(1, line.length() - 1).split(",'");
        for (int i = 0; i < columns.length; i++) {
            columns[i] = removeQuotes(columns[i]);
        }
        List<String> columnsList = new ArrayList<>();
        for (int i = 0; i < columns.length; i++) {
            if (i > 5) {
                StringTokenizer tokenizer = new StringTokenizer(columns[i], ",");
                while (tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken().trim();
                    try {
                        int intValue = Integer.parseInt(token);
                        columnsList.add(String.valueOf(intValue));
                    } catch (NumberFormatException e) {
                        token = removeQuotes(token);
                        columnsList.add(token);
                    }
                }
            } else {
                columnsList.add(columns[i]);
            }
        }

        return columnsList;
    }

    /**
     * Create a file with procedure
     */
    protected String processLineIntoProcedure(String line) {
        if (line == null || line.length() < 10) {
            return line;
        }

        List<String> parts = extractParts(line);
        StringBuilder sb = new StringBuilder("CALL p_insertProduct(\n");
        for (int i = 0; i < parts.size(); i++) {
            String value = parts.get(i).trim();

            if (i == 0) {
                //ignore
            } else if (i == 1) {
                sb.append("\t\t'").append(value).append("',").append(" -- name\n");
            } else if (i == 2) {
                sb.append("\t\t'").append(value).append("',").append(" -- alias\n");
            } else if (i == 3) {
                sb.append("\t\t'").append(value).append("',").append(" -- short_desc\n");
            } else if (i == 4) {
                sb.append("\t\t'").append(value).append("',").append(" -- full_desc\n");
            } else if (i == 5) {
                sb.append("\t\t'").append(value).append("',").append(" -- main_image\n");
            } else if (i == 6) {
                sb.append("\t\t'").append(value).append("',");
            } else if (i == 7) {
                sb.append(" now()").append(",").append(" -- createdAt, updatedAt\n");
            } else if (i == 8) {
                sb.append("\t\t").append(value).append(",");
            } else if (i == 9) {
                sb.append(value).append(",").append(" -- isEnabled, isInStock\n");
            } else if (i == 10) {
                sb.append("\t\t").append(value).append(",");
            } else if (i == 11) {
                sb.append(value).append(",");
            } else if (i == 12) {
                sb.append(value).append(",").append(" -- cost, price, discountPercent\n");
            } else if (i == 13) {
                sb.append("\t\t").append("'<BrandName>',");
            } else if (i == 14) {
                sb.append(" '<CategoryName>'").append(",").append(" -- brandName, categoryName\n");
            } else if (i == 15) {
                sb.append("\t\t").append(value).append(",");
            } else if (i == 16) {
                sb.append(value).append(",");
            } else if (i == 17) {
                sb.append(value).append(",").append(" -- length, width, height\n");
            } else if (i == 18) {
                if (value.endsWith(")")) {
                    value = value.replaceAll("\\)", "");
                } else if (value.endsWith(");")) {
                    value = value.replaceAll("\\);", "");
                }
                sb.append("\t\t").append(value).append(",");
                sb.append("1").append(" -- averageRating, reviewCount\n");
            }
        }
        sb.append("\t\t);");
        return sb.toString();
    }
}
