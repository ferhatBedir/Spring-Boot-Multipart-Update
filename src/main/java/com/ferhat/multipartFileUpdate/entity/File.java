package com.ferhat.multipartFileUpdate.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.annotation.Id;

@ConfigurationProperties(prefix = "file")
@Data
public class File {

    @Id
    private String id;

    private String uploadFileId;
}
