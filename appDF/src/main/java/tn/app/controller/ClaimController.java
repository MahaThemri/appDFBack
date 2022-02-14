package tn.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;


import java.util.Optional;

import java.util.zip.DataFormatException;
import java.util.zip.Deflater;

import java.util.zip.Inflater;

import tn.app.model.Claim;
import tn.app.model.Contract;
import tn.app.model.Photos;
import tn.app.model.TheUser;
import tn.app.repository.ClaimRepository;
import tn.app.repository.ContractRepository;
import tn.app.repository.PhotosRepository;
import tn.app.repository.UserRepository;
import tn.app.service.ClaimService;
import tn.app.service.ClaimServiceInterface;

@RestController
@CrossOrigin(origins = "*")
public class ClaimController {
	
	@Autowired
	ClaimServiceInterface cs;
	@Autowired
	ContractRepository cor ;
	@Autowired 
	UserRepository ur ;
	@Autowired
	PhotosRepository pr ;
	@Autowired 
	ClaimService claimservice ;
	@Autowired 
	ClaimRepository cr;
	
	public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";
	/*@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder ;*/
	
	
	@GetMapping("/")
	public String Home(){
		return ("authenticated successfully");
	}
	
	@PostMapping("/addClaim")
    ResponseEntity<?> addingClaim(@RequestBody Claim c) {
		cs.addClaim(c);
        return new ResponseEntity<>(new MessageResponseModel("the claim has been added"), HttpStatus.CREATED);
    }
	
	@GetMapping("/getClaims")
	public List<Claim> gettingClaim() {
		return cs.getClaims();
		
	}
	
	@PutMapping("/modifyClaim")
	public List<Claim> modifyClaim(@RequestBody Claim c) {
	 cs.updateClaim(c);
	 return cs.getClaims(); 
	}
	
	@DeleteMapping("/deleteClaim/{id}")
	ResponseEntity<?> deleteClaim(@PathVariable("id") Long id) {
		cs.deleteClaim(id);
		return new ResponseEntity<>(new MessageResponseModel("the claim got deleted "), HttpStatus.CREATED);
	}
	
	// *********************************** Contract controller 
	@PostMapping("/addContract")
    public Contract addingContract(@RequestBody Contract co) {
		return cs.addContract(co);
       
    }
	
	@GetMapping("/getContract")
	public List<Contract> gettingContrat() {
		return cs.getContracts();
		
	}
	
	
	
	// ******************************************Photos controller 
    
	
	@PostMapping("/addPhotos")
    ResponseEntity<?> addingPhotos(@RequestBody Photos p) {
		cs.addPhotos(p);
        return new ResponseEntity<>(new MessageResponseModel("photos added"), HttpStatus.CREATED);
    }
	
	@GetMapping("/getPhotos/{id}")
	public List<Photos> gettingPhotos(@PathVariable("id") Long id) {
		List<Photos> liste = pr.find(id);
		return liste ;
		
	}
	
	@PutMapping("/modifyPhotos/{id}")
	public Photos modifyPhotos(@RequestParam("file") MultipartFile file,@PathVariable("id") Long id) throws IOException {
		Photos p = new Photos();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		p.setData(file.getBytes());
		p.setName(fileName);
		p.setIdPhotos(id);
	return pr.save(p);
	}
	
	@DeleteMapping("/deletePhotos/{id}")
	ResponseEntity<?> deletePhotos(@PathVariable("id") Long id) {
		cs.deletePhotos(id);
		return new ResponseEntity<>(new MessageResponseModel("photos got deleted "), HttpStatus.CREATED);
	}
	
	
	
	 @PostMapping("/upload/{id}")
	  public void uploadFile(@RequestParam("file") MultipartFile file,@PathVariable("id") Long id)throws IOException {
		 Photos img = new Photos(file.getOriginalFilename(),file.getBytes());
		 Claim claim = cr.find(id);
		 img.setClaim(claim);
		 
		 pr.save(img);
	   /* try {
	    	claimservice.store(file);
	    } catch (Exception e) {
	      System.out.println("error while uploading") ;*/
	      
	    }
	 
	 
	 
	 
	 
	 
	 
	 
	 


    // uncompress the image bytes before returning it to the angular application

    public static byte[] decompressBytes(byte[] data) {

        Inflater inflater = new Inflater();

        inflater.setInput(data);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

        byte[] buffer = new byte[1024];

        try {

            while (!inflater.finished()) {

                int count = inflater.inflate(buffer);

                outputStream.write(buffer, 0, count);

            }

            outputStream.close();

        } catch (IOException ioe) {

        } catch (DataFormatException e) {

        }

        return outputStream.toByteArray();

    }
    
    
    
 // compress the image bytes before storing it in the database
  
        public static byte[] compressBytes(byte[] data) {

            Deflater deflater = new Deflater();

            deflater.setInput(data);
  
            deflater.finish();
 
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
 
            byte[] buffer = new byte[1024];
 
            while (!deflater.finished()) {

                int count = deflater.deflate(buffer);
 
                outputStream.write(buffer, 0, count);
 
            }
 
            try {
 
                outputStream.close();
 
            } catch (IOException e) {

            }

            System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

            return outputStream.toByteArray();
  
        }


    
    
	  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*@PostMapping("/upload")
	   public Photos saveImage(@RequestBody Photos p)
	    {	
	    	return cs.addingPhotos(p);
	    	
	    }*/
	
	
	/*
	public String upload(@RequestParam("files") MultipartFile[] files) {
		  StringBuilder fileNames = new StringBuilder();
		  for (MultipartFile file : files) {
			  Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
			  fileNames.append(file.getOriginalFilename()+" ");
			  try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		  }
		  
		  
		  return "uploaded";
	  }*/
	
	
	
	
	
	// ****************** user **********************
	
	
	/*@PostMapping("/addUser") 
	private String createUserAccount(@RequestBody TheUser user) {
		String password = user.getPswd();
		String EncryptPassword = bCryptPasswordEncoder.encode(password);
		user.setPswd(EncryptPassword);
		ur.save(user);
		return "account added";
		
		
	}*/













/*
 
    
    
    {
    "startDate": "2021-01-02",
    "insuredName": "Personne",
    "registration": "205Tunis9874",
    "endDate": "2022-12-02"
}
    
    
    {
    "creationDate": "2018-12-02",
    "accidentDate": "2020-12-02",
    "status": "Open",
    "contract": {
        "numContract": 1
    }
}



{
    "path": "src/",
    "claim": {
        "numClaim": 1
    }
}
    
}*/
