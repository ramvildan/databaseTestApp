package com.javatest.databaseTestApp.service.impl;

import com.javatest.databaseTestApp.converter.PhotoConverter;
import com.javatest.databaseTestApp.dto.PhotoDto;
import com.javatest.databaseTestApp.entity.Photo;
import com.javatest.databaseTestApp.exception.PhotoNotFoundException;
import com.javatest.databaseTestApp.exception.UserInfoNotFoundException;
import com.javatest.databaseTestApp.repository.PhotoRepository;
import com.javatest.databaseTestApp.repository.UserInfoRepository;
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

    private final UserInfoRepository userInfoRepository;

    private final PhotoConverter photoConverter;

    private final PhotoRepository photoRepository;

    @Override
    public PhotoDto upload(Integer userInfoId, MultipartFile file) throws IOException {

        Photo uploadedPhoto = Photo.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .photo(PhotoUtility.compressPhoto(file.getBytes()))
                .userInfo(userInfoRepository.findById(userInfoId)
                        .orElseThrow(() -> new UserInfoNotFoundException(userInfoId)))
                .uploadedAt(new Date())
                .updatedAt(new Date())
                .isDeleted(false)
                .build();

        return photoConverter.fromPhotoToPhotoDto(
                photoRepository.save(uploadedPhoto)
        );
    }

    @Override
    public PhotoDto update(Integer userInfoId, MultipartFile file) throws IOException {

        Photo photoToUpdate = photoRepository
                .findById(userInfoId)
                .orElseThrow(() -> new UserInfoNotFoundException(userInfoId));

        photoToUpdate.setName(file.getOriginalFilename());
        photoToUpdate.setType(file.getContentType());
        photoToUpdate.setPhoto(PhotoUtility.compressPhoto(file.getBytes()));
        photoToUpdate.setUpdatedAt(new Date());

        return photoConverter.fromPhotoToPhotoDto(
                photoRepository.save(photoToUpdate)
        );
    }

    @Override
    public void delete(Integer userInfoId) {

        Photo photoToDelete = photoRepository
                .findByUserInfoIdAndIsDeletedIsFalse(userInfoId)
                .orElseThrow(() -> new PhotoNotFoundException(userInfoId));

        photoToDelete.setIsDeleted(true);
        photoToDelete.setUpdatedAt(new Date());

        photoConverter.fromPhotoToPhotoDto(
                photoRepository.save(photoToDelete)
        );
    }

    @Override
    public PhotoDto getPhotoDetailsById(Integer userInfoId) {

        Photo dbPhoto = photoRepository
                .findByUserInfoIdAndIsDeletedIsFalse(userInfoId)
                .orElseThrow(() -> new PhotoNotFoundException(userInfoId));

        return PhotoDto.builder()
                .id(dbPhoto.getId())
                .name(dbPhoto.getName())
                .type(dbPhoto.getType())
                .photo(PhotoUtility.decompressPhoto(dbPhoto.getPhoto()))
                .build();
    }

    @Override
    public byte[] getPhotoById(Integer userInfoId) {

        Photo dbPhoto = photoRepository
                .findByUserInfoIdAndIsDeletedIsFalse(userInfoId)
                .orElseThrow(() -> new PhotoNotFoundException(userInfoId));

        return PhotoUtility.decompressPhoto(dbPhoto.getPhoto());
    }
}
