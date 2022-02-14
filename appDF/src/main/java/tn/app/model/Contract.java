package tn.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;


@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Contract implements Serializable {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long numContract;
	@Temporal (TemporalType.DATE)
	private Date startDate;
	private String insuredName;
	private String registration;
	@Temporal (TemporalType.DATE)
	private Date endDate;
	
	
	
	
	public Long getNumContract() {
		return numContract;
	}




	public void setNumContract(Long numContract) {
		this.numContract = numContract;
	}




	public Date getStartDate() {
		return startDate;
	}




	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}




	public String getInsuredName() {
		return insuredName;
	}




	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}




	public String getRegistration() {
		return registration;
	}




	public void setRegistration(String registration) {
		this.registration = registration;
	}




	public Date getEndDate() {
		return endDate;
	}




	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}




	



	@JsonIgnore
	@OneToOne(mappedBy="contract")
	private Claim claim;

}
