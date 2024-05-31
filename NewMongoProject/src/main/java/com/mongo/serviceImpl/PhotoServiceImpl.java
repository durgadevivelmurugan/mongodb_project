package com.mongo.serviceImpl;

 

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongo.entity.Photo;
import com.mongo.repository.PhotoRepository;
import com.mongo.service.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService {

	@Autowired
	PhotoRepository photoRepository;
	
	@Override
	public String addPhoto(String originalFilename, MultipartFile image) throws Exception {
		Photo photo=new Photo();
		
		photo.setTitle(originalFilename);
		//BsonBinarySubType.BINARY==indicate that the data is binary. 
		photo.setPhoto(new Binary(BsonBinarySubType.BINARY,image.getBytes()));
		return photoRepository.save(photo).getId();
	}

	@Override
	public Photo getPhoto(String id) {
		
		return photoRepository.findById(id).get();
	}

}
