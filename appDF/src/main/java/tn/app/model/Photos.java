package tn.app.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;





@Entity
public class Photos implements Serializable{

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long idPhotos;
	private String name;
	@Lob
	private byte[] data;
	 
	public Photos(){}
	public Photos(String name,  byte[] data) {
		this.name = name;
	   
	    this.data = data;
		
	}




	public Long getIdPhotos() {
		return idPhotos;
	}




	public void setIdPhotos(Long idPhotos) {
		this.idPhotos = idPhotos;
	}




	public byte[] getData() {
		return data;
	}




	public void setData(byte[] data) {
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	



	public Claim getClaim() {
		return claim;
	}
	public void setClaim(Claim claim) {
		this.claim = claim;
	}






	@JsonIgnore
	@ManyToOne
	private Claim claim;
	
}
