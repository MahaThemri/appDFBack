package tn.app.service;

import tn.app.model.Photos;

public interface PhotosServiceInterface {
	
	Photos addPhotos(Photos photos);
	void deletePhotos(Long id);
	void updatePhotos(Photos photos);

}
