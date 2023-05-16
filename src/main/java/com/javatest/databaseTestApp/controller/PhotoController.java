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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Log4j2
@RestController
@RequestMapping("users/details/photo")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

//    @PreAuthorize("hasAuthority('APP_USER')")
    @PostMapping("/create/{userDetailsId}")
    public ResponseEntity<PhotoDto> upload(@PathVariable("userDetailsId") Integer userDetailsId,
                                           @RequestParam("photo") MultipartFile file) throws IOException {

        return ResponseEntity.status(HttpStatus.OK)
                .body(photoService.upload(userDetailsId, file));
    }

//    @PreAuthorize("hasAuthority('APP_USER')")
    @PutMapping("/update/{userDetailsId}")
    public ResponseEntity<PhotoDto> update(@PathVariable("userDetailsId") Integer userDetailsId,
                                                @RequestParam("photo") MultipartFile file) throws IOException {

        log.info("updatePhoto: userDetailsId = {}", userDetailsId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(photoService.update(userDetailsId, file));
    }

//    @PreAuthorize("hasAuthority('APP_USER')")
    @DeleteMapping("/delete/{userDetailsId}")
    public ResponseEntity<Void> delete(@PathVariable("userDetailsId") Integer userDetailsId) {

        log.info("deletePhoto: userDetailsId = {}", userDetailsId);

        photoService.delete(userDetailsId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

//    @PreAuthorize("hasAuthority('APP_USER')")
    @GetMapping("/get/info/{userDetailsId}")
    public ResponseEntity<PhotoDto> getDetails(@PathVariable("userDetailsId") Integer userDetailsId) {

        log.info("getPhotoDetails: userDetailsId = {}", userDetailsId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(photoService.getPhotoDetailsById(userDetailsId));
    }

//    @PreAuthorize("hasAuthority('APP_USER')")
    @GetMapping("/get/{userDetailsId}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable("userDetailsId") Integer userDetailsId) {

        log.info("getPhoto: userDetailsId = {}", userDetailsId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(photoService.getPhotoById(userDetailsId));
    }
}
