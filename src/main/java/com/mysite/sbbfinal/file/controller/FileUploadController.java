package com.mysite.sbbfinal.file.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/file")
public class FileUploadController {
	
		@Value("${file.upload.directory}")
		private String uploadDirectory;
		
		// 파일업로드 양식
		@GetMapping("/fileform")
		public String fileform() {
			return "file-form";
		}
		
		//파일 단일 업로드
		@PostMapping("/upload")
		public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
			
			String originalFilename = file.getOriginalFilename();
			String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;
			
			Path filePath = Paths.get(uploadDirectory, uniqueFilename);
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
			return ResponseEntity.ok("success");
		}
		
		// 다중 파일 업로드
	    @PostMapping("/upload-multiple")
	    public ResponseEntity<List<String>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
	        List<String> uploadedFiles = new ArrayList<>();
	
	        for (MultipartFile file : files) {
	            try {
	                String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
	                Path filePath = Paths.get(uploadDirectory, uniqueFilename);
	                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	                uploadedFiles.add(uniqueFilename);
	            } catch (IOException e) {
	                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                        .body(List.of("Failed to upload files: " + e.getMessage()));
	            }
	        }
	
	        return ResponseEntity.ok(uploadedFiles);
	    }
	    
//	 // 파일 다운로드
//	    @GetMapping("/download/{filename}")
//	    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
//	        try {
//	            Path filePath = Paths.get(uploadDirectory).resolve(filename);
//	            Resource resource = (Resource) new UrlResource(filePath.toUri());
//	
//	            if (((org.springframework.core.io.Resource) resource).exists() || ((org.springframework.core.io.Resource) resource).isReadable()) {
//	                return ResponseEntity.ok()
//	                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//	                        .body(resource);
//	            } else {
//	//                throw new RuntimeException("Could not read the file!");
//	                throw new FileNotFoundException("File not found: " + filename);
//	            }
//	        } catch (MalformedURLException e) {
//	            throw new RuntimeException("Error: " + e.getMessage());
//	        }
//	    }

}
