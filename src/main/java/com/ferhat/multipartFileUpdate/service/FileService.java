package com.ferhat.multipartFileUpdate.service;

import com.ferhat.multipartFileUpdate.entity.File;
import com.ferhat.multipartFileUpdate.repository.FileRepository;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    private FileRepository fileRepository;
    private GridFsTemplate gridFsTemplate;
    private GridFsOperations gridFsOperations;

    @Autowired
    public FileService(FileRepository fileRepository, GridFsTemplate gridFsTemplate, GridFsOperations gridFsOperations) {
        this.fileRepository = fileRepository;
        this.gridFsTemplate = gridFsTemplate;
        this.gridFsOperations = gridFsOperations;
    }

    public void storeFile(MultipartFile file) throws Exception {
        if (file.isEmpty() || file == null) {
            throw new Exception("File is empty or null");
        }

        ObjectId gridFsFileId = gridFsTemplate.store(file.getInputStream(),
                file.getOriginalFilename(),
                file.getContentType());

        String gridFsFile = gridFsFileId.toHexString();
        File file1 = new File();
        file1.setUploadFileId(gridFsFile);
        fileRepository.save(file1);
    }

    public GridFsResource getFile(String id) {
        File file = fileRepository.findFirstById(id);
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(file.getUploadFileId()));

        GridFSFile foundFile = gridFsTemplate.findOne(query);

        return gridFsOperations.getResource(foundFile.getFilename());
    }
}
