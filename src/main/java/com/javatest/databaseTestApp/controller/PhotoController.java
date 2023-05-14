package com.javatest.databaseTestApp.controller;

import com.javatest.databaseTestApp.dto.PhotoDto;
import com.javatest.databaseTestApp.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Log4j2
@RestController
@RequestMapping("/photo")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<PhotoDto> uploadPhoto(@RequestParam("photo")MultipartFile file) throws IOException {

        return ResponseEntity.status(HttpStatus.OK)
                .body(photoService.uploadPhoto(file));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/update/{name}")
    public ResponseEntity<PhotoDto> updatePhoto(@PathVariable("name") String photoName,
                                                @RequestParam("photo")MultipartFile file) throws IOException {

        log.info("updatePhoto: photoName = {}", photoName);

        return ResponseEntity.status(HttpStatus.OK)
                .body(photoService.updatePhoto(photoName, file));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deletePhoto(@PathVariable("name") String photoName) {

        log.info("deletePhoto: photoName = {}", photoName);

        photoService.deletePhoto(photoName);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/get/photo/info/{name}")
    public ResponseEntity<PhotoDto> getPhotoDetails(@PathVariable("name") String photoName) {

        log.info("getPhotoDetails: photoName = {}", photoName);

        return ResponseEntity.status(HttpStatus.OK)
                .body(photoService.getPhotoDetailsByName(photoName));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/get/photo/{name}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable("name") String photoName) {

        log.info("getPhoto: photoName = {}", photoName);

        return ResponseEntity.status(HttpStatus.OK)
                .body(photoService.getPhotoByName(photoName));
    }
}
