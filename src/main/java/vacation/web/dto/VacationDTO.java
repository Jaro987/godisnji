package vacation.web.dto;

public class VacationDTO {

	private Long id;
	private String startingDate;
	private String endingDate;
	private int workDays;
	private Long employeeId;
	private String employeeName;
	private String employeeIndentification;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStartingDate() {
		return startingDate;
	}
	public void setStartingDate(String startingDate) {
		this.startingDate = startingDate;
	}
	public String getEndingDate() {
		return endingDate;
	}
	public void setEndingDate(String endingDate) {
		this.endingDate = endingDate;
	}
	public int getWorkDays() {
		return workDays;
	}
	public void setWorkDays(int workDays) {
		this.workDays = workDays;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeIndentification() {
		return employeeIndentification;
	}
	public void setEmployeeIndentification(String employeeIndentification) {
		this.employeeIndentification = employeeIndentification;
	}
	
}
