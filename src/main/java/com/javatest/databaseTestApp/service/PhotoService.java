package com.javatest.databaseTestApp.service;

import com.javatest.databaseTestApp.dto.PhotoDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface PhotoService {

    PhotoDto uploadPhoto(MultipartFile file) throws IOException;

    PhotoDto getPhotoDetailsByName(String name);

    byte[] getPhotoByName(String name);
}
