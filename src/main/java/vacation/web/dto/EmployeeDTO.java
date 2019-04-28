package vacation.web.dto;

import org.hibernate.validator.constraints.Length;

public class EmployeeDTO {

	private Long id;
	@Length(min=5, max=5)
	private String identification;
	@Length(min=1)
	private String name;
	private String email;
	private int yearsOfService;
	private int daysOff;
	private Long sectorId;
	private String sectorName;
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
	public void setDaysOff(int daysOff) {
		this.daysOff = daysOff;
	}
	public Long getSectorId() {
		return sectorId;
	}
	public void setSectorId(Long sectorId) {
		this.sectorId = sectorId;
	}
	public String getSectorName() {
		return sectorName;
	}
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
}
