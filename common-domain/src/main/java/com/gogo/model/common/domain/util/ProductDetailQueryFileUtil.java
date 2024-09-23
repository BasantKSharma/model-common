package com.gogo.model.common.domain.util;

import java.util.List;
import static com.gogo.model.common.domain.constants.FileConstants.*;

/**
 * Product utility - transform single line sql file into i) multi lines and then ii) format procedures
 **/
public class ProductDetailQueryFileUtil extends AbstractFileTransformation {

    final static ProductQueryFileUtil productQueryFileUtil = new ProductQueryFileUtil();

    final static ProductDetailQueryFileUtil detailQueryFileUtil = new ProductDetailQueryFileUtil();

    protected static Class<?>[] COL_TYPES = new Class<?>[]{Integer.class, String.class, String.class, Integer.class};

    public static void main(String[] args) {
        transform();
    }

    /**
     * Transform file
     */
    public static void transform() {
        //First create product map
        productQueryFileUtil.transformSingleLineIntoMultiline(SOURCE_DIRECTORY_1_DETAIL, TARGET_DIRECTORY_1_DETAIL);
        detailQueryFileUtil.createProductMap(TARGET_DIRECTORY_1_PRODUCT, MAP_PRODUCT, productQueryFileUtil);

        //Then transform detail file
        detailQueryFileUtil.transformSingleLineIntoMultiline(SOURCE_DIRECTORY_1_DETAIL, TARGET_DIRECTORY_1_DETAIL);
        detailQueryFileUtil.transformSingleLineIntoProcedure(TARGET_DIRECTORY_1_DETAIL, TARGET_DIRECTORY_2_DETAIL);
    }

    /**
     * Create a file with procedure
     */
    protected String processLineIntoProcedure(String line) {
        if (line == null || line.length() < 10) {
            return line;
        }

        List<String> parts = extractParts(line, COL_TYPES);
        StringBuilder sb = new StringBuilder("CALL p_insertProductDetail(\n");
        for (int i = 0; i < parts.size(); i++) {
            String value = parts.get(i).trim();
            if (i == 0) {
                //ignore
            } else if (i == 1) {
                sb.append("\t\t").append(value).append(",").append(" -- name\n");
            } else if (i == 2) {
                sb.append("\t\t").append(value).append(",").append(" -- value\n");
            } else if (i == 3) {
                value = extractKey(value);
                sb.append("\t\t'").append(MAP_PRODUCT.get(value)).append("'").append(" -- productName\n");
            }
        }
        sb.append("\t\t);");
        return sb.toString();
    }

    /**
     * Get procedure header
     * */
    protected String getProcedureHeader(){
        return "-- name | value | productName";
    }

    /**
     * Is Generate output file with procedure
     */
    @Override
    protected boolean isGenerateFileProcedure() {
        return true;
    }
}
