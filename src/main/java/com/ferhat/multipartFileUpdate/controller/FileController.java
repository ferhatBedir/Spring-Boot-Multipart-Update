package com.ferhat.multipartFileUpdate.controller;

import com.ferhat.multipartFileUpdate.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    private FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/uploadfile")
    public void uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        fileService.storeFile(file);
    }

    @GetMapping("/getfile")
    public ResponseEntity<InputStreamResource> downlandFile(@RequestParam(value = "id") String id) throws IOException {
        GridFsResource file = fileService.getFile(id);

        return ResponseEntity.ok().contentType(resolveContentType(file.getContentType())).body(new InputStreamResource(file.getInputStream()));
    }

    private MediaType resolveContentType(String contentType) {
        if (StringUtils.equalsIgnoreCase(contentType, "image/jpg") ||
                StringUtils.equalsIgnoreCase(contentType, "image/jpeg")) {
            return MediaType.IMAGE_JPEG;
        } else if (StringUtils.equalsIgnoreCase(contentType, "image/png")) {
            return MediaType.IMAGE_PNG;
        }
        return MediaType.APPLICATION_OCTET_STREAM;
    }


}
