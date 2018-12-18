package com.ferhat.multipartFileUpdate;

import com.ferhat.multipartFileUpdate.entity.File;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({File.class})
public class MultipartFileUpdateApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultipartFileUpdateApplication.class, args);
	}

}

