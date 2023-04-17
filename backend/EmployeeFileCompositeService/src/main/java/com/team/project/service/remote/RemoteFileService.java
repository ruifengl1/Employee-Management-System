package com.team.project.service.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient("file-service")
public interface RemoteFileService {
    @GetMapping("file-service/list/files")
    ResponseEntity<List<String>> getListOfFiles();

    @PostMapping("file-service/upload")
    ResponseEntity<String> uploadFile(@RequestParam("fileName") String fileName,
                                      @RequestParam("file") MultipartFile file);

    @GetMapping(value = "file-service/download/{*filename}")
    ResponseEntity<byte[]> downloadFile(@PathVariable String filename);
}
