package com.mysite.sbbfinal.file.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class FileUploadConfig {
	
	@Value("${file.upload.directory:/tmp/uploads}")
	private String uploadDirectory;
	
	@PostConstruct
	public void init() {
		try {
			Files.createDirectories(Paths.get(uploadDirectory));
		} catch(IOException e){
			throw new RuntimeException("Could not create upload directory");
			
		}
	}

}
