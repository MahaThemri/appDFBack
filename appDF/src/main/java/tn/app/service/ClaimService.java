package tn.app.service;

import java.io.IOException;
import java.io.InputStream;

import java.sql.Blob;

import java.util.List;

import org.hibernate.SessionFactory;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import tn.app.model.Claim;
import tn.app.model.Contract;
import tn.app.model.Photos;
import tn.app.repository.ClaimRepository;
import tn.app.repository.ContractRepository;
import tn.app.repository.PhotosRepository;

@Service
public class ClaimService implements ClaimServiceInterface {
	
	@Autowired
	ClaimRepository cr;
	
	@Autowired
	ContractRepository cor;
	
	@Autowired 
	PhotosRepository pr ;
	
	
	
	
	
	
	@Override
	public List<Claim> getClaims() {
		return (List<Claim>)cr.findAll();
	}


	@Override
	public Claim addClaim(Claim c) {
		return cr.save(c);
	}
	
	

	@Override
	public List<Claim> deleteClaim(Long id) {
		cr.deleteById(id);
		return (List<Claim>)cr.findAll();
		
	}

	@Override
	public Claim updateClaim(Claim c) {
		return cr.save(c);
		
	}
	// contract services
	@Override
	public Contract addContract(Contract co) {
		return cor.save(co);
	}

	@Override
	public List<Contract> getContracts() {
		return (List<Contract>)cor.findAll();
	}

	
		// Photos service 
	
	
	@Override
	public Photos addPhotos(Photos photos) {
		return pr.save(photos);
	}

	@Override
	public List<Photos> deletePhotos(Long id) {
		pr.deleteById(id);
		return (List<Photos>)pr.findAll();
	}

	@Override
	public Photos updatePhotos(Photos photos) {
		return pr.save(photos);
	}

	@Override
	public List<Photos> getPhotos() {
		return (List<Photos>)pr.findAll();
	}
	
	
	public Photos store(MultipartFile file) throws IOException {
	    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	    //Photos FileDB = new Photos(fileName, file.getContentType(), file.getBytes());
	    Photos FileDB = new Photos();
	    
	    FileDB.setData(file.getBytes());
	    FileDB.setName(fileName);
	    return pr.save(FileDB);
	  }


	
		
	
	
	
	
	
    
	
	
	
	
	
	
	
	/*@Override
	public Photos addingPhotos(Photos photos) {
		
		MultipartFile file = null ;
	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	try {
		photos.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
	} catch (IOException e) {
		e.printStackTrace();
	}
	return pr.save(photos);
} */
	

}

