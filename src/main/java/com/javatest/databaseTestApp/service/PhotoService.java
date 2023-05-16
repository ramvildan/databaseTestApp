package com.javatest.databaseTestApp.service;

import com.javatest.databaseTestApp.dto.PhotoDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface PhotoService {

    PhotoDto upload(Integer userDetailsId, MultipartFile file) throws IOException;

    PhotoDto update(Integer userDetailsId, MultipartFile file) throws IOException;

    void delete(Integer userDetailsId);

    PhotoDto getPhotoDetailsById(Integer userDetailsId);

    byte[] getPhotoById(Integer userId);
}
