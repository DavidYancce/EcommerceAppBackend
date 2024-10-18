package com.ecommerceproject.providers.storage.s3.controller;

import com.ecommerceproject.providers.storage.s3.services.S3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class S3Controller {

    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String url = s3Service.uploadFile(file);
        return ResponseEntity.ok("Archivo subido exitosamente con url: " + url);
    }

    @GetMapping("/download/{key}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String key) {
        byte[] data = s3Service.downloadFile(key);
        return ResponseEntity.ok().body(data);
    }

    @DeleteMapping("/delete/{key}")
    public ResponseEntity<String> deleteFile(@PathVariable String key) {
        s3Service.deleteFile(key);
        return ResponseEntity.ok("Archivo eliminado exitosamente.");
    }
}

