package com.javatest.databaseTestApp.converter;

import com.javatest.databaseTestApp.dto.PhotoDto;
import com.javatest.databaseTestApp.entity.Photo;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class PhotoConverter {

    public PhotoDto fromPhotoToPhotoDto(Photo photo) {

        if(isNull(photo)) {
            return null;
        }

        return PhotoDto.builder()
                .name(photo.getName())
                .type(photo.getType())
                .photo(photo.getPhoto())
                .build();
    }
}
