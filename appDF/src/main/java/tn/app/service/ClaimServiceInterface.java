package tn.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tn.app.model.Claim;
import tn.app.model.Contract;
import tn.app.model.Photos;

public interface ClaimServiceInterface {
	
	Claim addClaim(Claim claim);
	List<Claim> deleteClaim(Long id);
	Claim updateClaim(Claim claim);
	List<Claim> getClaims();
	//contract
	Contract addContract(Contract co);
	List<Contract> getContracts();
	//photos
	Photos addPhotos(Photos photos) ;
	List<Photos> deletePhotos(Long id) ;
	Photos updatePhotos(Photos photos);
	List<Photos> getPhotos();
	
	
	

}
