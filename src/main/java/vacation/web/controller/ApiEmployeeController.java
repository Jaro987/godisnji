package vacation.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vacation.model.Employee;
import vacation.service.EmployeeService;
import vacation.support.EmployeeDTOToEmployee;
import vacation.support.EmployeeToEmployeeDTO;
import vacation.web.dto.EmployeeDTO;

@RestController
@RequestMapping("/api/employees")
public class ApiEmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeToEmployeeDTO toEmployeeDTO;
	
	@Autowired
	private EmployeeDTOToEmployee toEmployee;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EmployeeDTO>> get(
			@RequestParam(required=false) String identification,
			@RequestParam(required=false) String name,
			@RequestParam(required=false) Long sectorId,
			@RequestParam(defaultValue="0") int pageNum){
		
		Page<Employee> employees;
		if(identification!=null || name!=null || sectorId!=null) {
			employees = employeeService.search(identification, name, sectorId, pageNum);
		}else {
			employees = employeeService.findAll(pageNum);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(employees.getTotalPages()));
		return new ResponseEntity<List<EmployeeDTO>>(toEmployeeDTO.convert(employees.getContent()),
				headers, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,
			value="/{id}")
	public ResponseEntity<EmployeeDTO> get(
			@PathVariable long id){
		Employee employee = employeeService.findOne(id);
		
		if(employee == null) {
			return new ResponseEntity<EmployeeDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<EmployeeDTO>(toEmployeeDTO.convert(employee), 
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<EmployeeDTO> add(
			@Validated @RequestBody EmployeeDTO newEmployee){
		
		Employee employee = toEmployee.convert(newEmployee);
		employeeService.save(employee);
		
		return new ResponseEntity<EmployeeDTO>(toEmployeeDTO.convert(employee),
				HttpStatus.CREATED); 
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}")
	public ResponseEntity<EmployeeDTO> edit (
			@PathVariable Long id,
			@Validated @RequestBody EmployeeDTO edited){
		
		if(!id.equals(edited.getId())) {
			return new ResponseEntity<EmployeeDTO>(HttpStatus.BAD_REQUEST);
		}
		
		Employee employee = toEmployee.convert(edited);
		employeeService.save(employee);
		
		return new ResponseEntity<EmployeeDTO>(toEmployeeDTO.convert(employee),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,
			value="/{id}")
	public ResponseEntity<EmployeeDTO> delete(@PathVariable Long id){
		employeeService.remove(id);
		
		return new ResponseEntity<EmployeeDTO>(HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler
	public ResponseEntity<Void> validationHandler(
					DataIntegrityViolationException e){
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
 
}
