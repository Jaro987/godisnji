package vacation.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import vacation.model.Employee;
import vacation.web.dto.EmployeeDTO;

@Component
public class EmployeeToEmployeeDTO 
	implements Converter<Employee, EmployeeDTO>{

	public EmployeeDTO convert(Employee source) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setId(source.getId());
		dto.setIdentification(source.getIdentification());
		dto.setName(source.getName());
		dto.setEmail(source.getEmail());
		dto.setYearsOfService(source.getYearsOfService());
		dto.setDaysOff(source.getDaysOff());
		dto.setSectorId(source.getSector().getId());
		dto.setSectorName(source.getSector().getSectorName());
		return dto;
	}
	
	public List<EmployeeDTO> convert(List<Employee> employees){
		List<EmployeeDTO> ret = new ArrayList<EmployeeDTO>();
		
		for(Employee e : employees) {
			ret.add(convert(e));
		}
		return ret;
	}
}