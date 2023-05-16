package com.javatest.databaseTestApp.service;

import com.javatest.databaseTestApp.dto.PhotoDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface PhotoService {

    PhotoDto upload(Integer userInfoId, MultipartFile file) throws IOException;

    PhotoDto update(Integer userInfoId, MultipartFile file) throws IOException;

    void delete(Integer userInfoId);

    PhotoDto getPhotoDetailsById(Integer userInfoId);

    byte[] getPhotoById(Integer userInfoId);
}
