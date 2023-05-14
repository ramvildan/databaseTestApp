package com.javatest.databaseTestApp.controller;

import com.javatest.databaseTestApp.dto.PhotoDto;
import com.javatest.databaseTestApp.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/upload/photo")
    public ResponseEntity<PhotoDto> uploadPhoto(@RequestParam("photo")MultipartFile file) throws IOException {

        PhotoDto uploadedPhoto = photoService.uploadPhoto(file);

        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadedPhoto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = {"get/photo/info/{name}"})
    public ResponseEntity<PhotoDto> getPhotoDetails(@PathVariable("name") String name) {

        PhotoDto photoDetails = photoService.getPhotoDetailsByName(name);

        return ResponseEntity.status(HttpStatus.OK)
                .body(photoDetails);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = {"get/photo/{name}"})
    public ResponseEntity<byte[]> getPhoto(@PathVariable("name") String name) {

        byte[] photo = photoService.getPhotoByName(name);

        return ResponseEntity.status(HttpStatus.OK)
                .body(photo);
    }
}
