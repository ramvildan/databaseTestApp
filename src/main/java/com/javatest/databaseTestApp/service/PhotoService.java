package com.javatest.databaseTestApp.service;

import com.javatest.databaseTestApp.dto.PhotoDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface PhotoService {

    PhotoDto upload(MultipartFile file) throws IOException;

    PhotoDto update(Integer userId, MultipartFile file) throws IOException;

    void delete(Integer userId);

    PhotoDto getPhotoDetailsById(Integer userId);

    byte[] getPhotoById(Integer userId);
}
