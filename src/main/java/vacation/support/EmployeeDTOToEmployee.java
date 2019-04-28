package vacation.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import vacation.model.Employee;
import vacation.service.EmployeeService;
import vacation.service.SectorService;
import vacation.web.dto.EmployeeDTO;

@Component
public class EmployeeDTOToEmployee 
	implements Converter<EmployeeDTO, Employee>{
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private SectorService sectorService;
	
	public Employee convert(EmployeeDTO source) {
		Employee employee;
		if(source.getId()==null) {
			employee = new Employee();
			employee.setSector(sectorService.findOne(source.getSectorId()));
		}else {
			employee = employeeService.findOne(source.getId());
		}
		
		employee.setIdentification(source.getIdentification());
		employee.setName(source.getName());
		employee.setEmail(source.getEmail());
		employee.setYearsOfService(source.getYearsOfService());
		employee.setDaysOff(source.getDaysOff());
		
		return employee;
	}
}
