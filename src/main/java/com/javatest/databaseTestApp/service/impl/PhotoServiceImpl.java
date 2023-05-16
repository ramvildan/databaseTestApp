package com.javatest.databaseTestApp.service.impl;

import com.javatest.databaseTestApp.converter.PhotoConverter;
import com.javatest.databaseTestApp.dto.PhotoDto;
import com.javatest.databaseTestApp.entity.Photo;
import com.javatest.databaseTestApp.exception.PhotoNotFoundException;
import com.javatest.databaseTestApp.exception.UserDetailsNotFoundException;
import com.javatest.databaseTestApp.repository.PhotoRepository;
import com.javatest.databaseTestApp.repository.UserDetailsRepository;
import com.javatest.databaseTestApp.service.PhotoService;
import com.javatest.databaseTestApp.util.PhotoUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final UserDetailsRepository userDetailsRepository;

    private final PhotoConverter photoConverter;

    private final PhotoRepository photoRepository;

    @Override
    public PhotoDto upload(Integer userDetailsId, MultipartFile file) throws IOException {

        Photo uploadedPhoto = Photo.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .photo(PhotoUtility.compressPhoto(file.getBytes()))
                .userDetails(userDetailsRepository.findById(userDetailsId)
                        .orElseThrow(() -> new UserDetailsNotFoundException(userDetailsId)))
                .uploadedAt(new Date())
                .updatedAt(new Date())
                .isDeleted(false)
                .build();

        return photoConverter.fromPhotoToPhotoDto(
                photoRepository.save(uploadedPhoto)
        );
    }

    @Override
    public PhotoDto update(Integer userDetailsId, MultipartFile file) throws IOException {

        Photo photoToUpdate = photoRepository
                .findById(userDetailsId)
                .orElseThrow(() -> new UserDetailsNotFoundException(userDetailsId));

        photoToUpdate.setName(file.getOriginalFilename());
        photoToUpdate.setType(file.getContentType());
        photoToUpdate.setPhoto(PhotoUtility.compressPhoto(file.getBytes()));
        photoToUpdate.setUpdatedAt(new Date());

        return photoConverter.fromPhotoToPhotoDto(
                photoRepository.save(photoToUpdate)
        );
    }

    @Override
    public void delete(Integer userDetailsId) {

        Photo photoToDelete = photoRepository
                .findByUserDetailsIdAndIsDeletedIsFalse(userDetailsId)
                .orElseThrow(() -> new PhotoNotFoundException(userDetailsId));

        photoToDelete.setIsDeleted(true);
        photoToDelete.setUpdatedAt(new Date());

        photoConverter.fromPhotoToPhotoDto(
                photoRepository.save(photoToDelete)
        );
    }

    @Override
    public PhotoDto getPhotoDetailsById(Integer userDetailsId) {

        Photo dbPhoto = photoRepository
                .findByUserDetailsIdAndIsDeletedIsFalse(userDetailsId)
                .orElseThrow(() -> new PhotoNotFoundException(userDetailsId));

        return PhotoDto.builder()
                .name(dbPhoto.getName())
                .type(dbPhoto.getType())
                .photo(PhotoUtility.decompressPhoto(dbPhoto.getPhoto()))
                .build();
    }

    @Override
    public byte[] getPhotoById(Integer userDetailsId) {

        Photo dbPhoto = photoRepository
                .findByUserDetailsIdAndIsDeletedIsFalse(userDetailsId)
                .orElseThrow(() -> new PhotoNotFoundException(userDetailsId));

        return PhotoUtility.decompressPhoto(dbPhoto.getPhoto());
    }
}
