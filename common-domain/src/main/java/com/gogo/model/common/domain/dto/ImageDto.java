package com.gogo.model.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gogo.model.common.domain.util.FileUtil;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

import java.util.List;

import static com.gogo.model.common.domain.constants.GenericConstants.*;

/**
 * Base class for DTO 's having the image
 */
@Getter
@Setter
public abstract class ImageDto extends AbstractDto {

    String image;

    List<String> extraImages;

    @Transient
    @JsonIgnore
    @IgnoreForBinding
    public String getImagePath() {
        String image = getImageName();
        if (StringUtils.isBlank(image)) {
            image = DEFAULT_IMAGE;
            return RELATIVE_FILE_PATH_IMAGE_FOLDER_GENERIC + image;
        }
        if (!image.endsWith(DEFAULT_EXTENSION_IMAGE)) {
            image = image + DEFAULT_EXTENSION_IMAGE;
        }

        return FileUtil.getImageFolder(this.getClass()) + image;
    }

    @Transient
    @JsonIgnore
    @IgnoreForBinding
    public String getImageName() {
        if (image != null && image.contains(FORWARD_SLASH)) {
            int lastIndexOfSlash = image.lastIndexOf(FORWARD_SLASH);
            if (lastIndexOfSlash != -1) {
                return image.substring(lastIndexOfSlash + 1);
            }
        }
        return image;
    }
}
