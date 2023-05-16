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
@RequestMapping("users/info/photo")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/create/{userInfoId}")
    public ResponseEntity<PhotoDto> upload(@PathVariable("userInfoId") Integer userInfoId,
                                           @RequestParam("photo") MultipartFile file) throws IOException {

        return ResponseEntity.status(HttpStatus.OK)
                .body(photoService.upload(userInfoId, file));
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping("/update/{userInfoId}")
    public ResponseEntity<PhotoDto> update(@PathVariable("userInfoId") Integer userInfoId,
                                                @RequestParam("photo") MultipartFile file) throws IOException {

        log.info("updatePhoto: userInfoId = {}", userInfoId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(photoService.update(userInfoId, file));
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @DeleteMapping("/delete/{userInfoId}")
    public ResponseEntity<Void> delete(@PathVariable("userInfoId") Integer userInfoId) {

        log.info("deletePhoto: userInfoId = {}", userInfoId);

        photoService.delete(userInfoId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/get/info/{userInfoId}")
    public ResponseEntity<PhotoDto> getDetails(@PathVariable("userInfoId") Integer userInfoId) {

        log.info("getPhotoDetails: userInfoId = {}", userInfoId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(photoService.getPhotoDetailsById(userInfoId));
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/get/{userDetailsId}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable("userDetailsId") Integer userDetailsId) {

        log.info("getPhoto: userDetailsId = {}", userDetailsId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(photoService.getPhotoById(userDetailsId));
    }
}
