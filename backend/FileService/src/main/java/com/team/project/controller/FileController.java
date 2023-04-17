package com.team.project.controller;

import com.team.project.service.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class FileController {
    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("list/files")
    public ResponseEntity<List<String>> getListOfFiles() {
        return new ResponseEntity<>(fileService.listFiles(), HttpStatus.OK);
    }

    @PostMapping(value = "upload")
    public ResponseEntity<String> uploadFile(@RequestParam("fileName") String fileName,
                                             @RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(fileService.uploadFile(fileName, file), HttpStatus.OK);
    }

    @GetMapping(value = "download/{*filename}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String filename) throws IOException {
        filename = filename.substring(1);
        ByteArrayOutputStream downloadInputStream = fileService.downloadFile(filename);
        return ResponseEntity.ok()
                .contentType(contentType(filename))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(downloadInputStream.toByteArray());
    }

    private MediaType contentType(String filename) {
        String[] fileArrSplit = filename.split("\\.");
        String fileExtension = fileArrSplit[fileArrSplit.length - 1];
        switch (fileExtension) {
            case "txt":
                return MediaType.TEXT_PLAIN;
            case "png":
                return MediaType.IMAGE_PNG;
            case "jpg":
                return MediaType.IMAGE_JPEG;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

}
