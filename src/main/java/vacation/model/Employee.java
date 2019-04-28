package vacation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Employee {
	
	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column(nullable=false)
	private String identification;
	
	@Column(nullable=false)
	private String name;
	
	@Column
	private String email;
	
	@Column
	private int yearsOfService;
	
	@Column
	private int daysOff;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Sector sector;
	
	@OneToMany(mappedBy="employee",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Vacation> vacation = new ArrayList<Vacation>();

	
	
	public List<Vacation> getVacation() {
		return vacation;
	}

	public void setVacation(List<Vacation> vacation) {
		this.vacation = vacation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getYearsOfService() {
		return yearsOfService;
	}

	public void setYearsOfService(int yearsOfService) {
		this.yearsOfService = yearsOfService;
	}

	public int getDaysOff() {
		return daysOff;
	}

	public void setDaysOff(int yearsOfService) {
		
		this.daysOff = 20+yearsOfService/5;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}
}