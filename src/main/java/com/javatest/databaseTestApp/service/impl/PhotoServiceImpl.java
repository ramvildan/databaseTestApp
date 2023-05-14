package com.javatest.databaseTestApp.service.impl;

import com.javatest.databaseTestApp.converter.PhotoConverter;
import com.javatest.databaseTestApp.dto.PhotoDto;
import com.javatest.databaseTestApp.entity.Photo;
import com.javatest.databaseTestApp.repository.PhotoRepository;
import com.javatest.databaseTestApp.service.PhotoService;
import com.javatest.databaseTestApp.util.PhotoUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoConverter photoConverter;

    private final PhotoRepository photoRepository;

    @Override
    public PhotoDto uploadPhoto(MultipartFile file) throws IOException {

        Photo uploadedPhoto = Photo.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .photo(PhotoUtility.compressPhoto(file.getBytes()))
                .build();

        return photoConverter.fromPhotoToPhotoDto(
                photoRepository.save(uploadedPhoto)
        );
    }

    @Override
    public PhotoDto getPhotoDetailsByName(String name) {

        Optional<Photo> dbPhoto = photoRepository.findByName(name);

        return PhotoDto.builder()
                .name(dbPhoto.get().getName())
                .type(dbPhoto.get().getType())
                .photo(PhotoUtility.decompressPhoto(dbPhoto.get().getPhoto()))
                .build();
    }

    @Override
    public byte[] getPhotoByName(String name) {

        Optional<Photo> dbPhoto = photoRepository.findByName(name);

        return PhotoUtility.decompressPhoto(dbPhoto.get().getPhoto());
    }
}
