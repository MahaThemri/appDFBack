package tn.app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;



@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Claim implements Serializable  {
	
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	
	private Long numClaim;
	@Temporal (TemporalType.DATE)
    private Date creationDate ;
	@Temporal (TemporalType.DATE)
    private Date accidentDate ;
    @Enumerated(EnumType.STRING)
    private statusType status;
    
    
    
    public Long getNumClaim() {
		return numClaim;
	}


	public void setNumClaim(Long numClaim) {
		this.numClaim = numClaim;
	}


	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public Date getAccidentDate() {
		return accidentDate;
	}


	public void setAccidentDate(Date accidentDate) {
		this.accidentDate = accidentDate;
	}


	public statusType getStatus() {
		return status;
	}


	public void setStatus(statusType status) {
		this.status = status;
	}


	public Contract getContract() {
		return contract;
	}


	public void setContract(Contract contract) {
		this.contract = contract;
	}
	
	

	/*public Set<Photos> getPhotos() {
		return photos;
	}


	public void setPhotos(Set<Photos> photos) {
		this.photos = photos;
	}*/
	
	


	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="claim")
	private Set<Photos> photos;
    
	@JsonIgnore
    @OneToOne
    private Contract contract;



	
}
