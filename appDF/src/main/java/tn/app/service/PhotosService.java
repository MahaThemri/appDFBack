package tn.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.app.model.Photos;
import tn.app.repository.PhotosRepository;

@Service
public class PhotosService implements PhotosServiceInterface {
	
	@Autowired
	PhotosRepository pr;
	

	@Override
	public Photos addPhotos(Photos photos) {
		return pr.save(photos);
	}

	@Override
	public void deletePhotos(Long id) {
		pr.deleteById(id);
	}

	@Override
	public void updatePhotos(Photos p) {
		 pr.save(p);
		
	}

}
