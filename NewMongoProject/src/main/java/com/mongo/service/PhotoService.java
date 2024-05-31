package com.mongo.service;

import org.springframework.web.multipart.MultipartFile;

import com.mongo.entity.Photo;

public interface PhotoService {

	String addPhoto(String originalFilename, MultipartFile image) throws Exception;

	Photo getPhoto(String id);

}
