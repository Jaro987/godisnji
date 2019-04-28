package vacation.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import vacation.model.Employee;
import vacation.repository.EmployeeRepository;
import vacation.service.EmployeeService;

@Service
@Transactional
public class JpaEmployeeService 
	implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	public Page<Employee> findAll(int pageNum) {
		return employeeRepository.findAll(new PageRequest(pageNum, 5));
	}
	
	
	public Employee findOne(Long id) {
		return employeeRepository.findOne(id);
	}
	
	
	public void save(Employee employee) {
		employeeRepository.save(employee);		
	}
	
	
	public Employee remove(Long id) {
		Employee employee = employeeRepository.findOne(id);
		if(employee!=null) {
			employeeRepository.delete(employee);
		}
		return employee;
	}

	
	public Page<Employee> findBySectorId(int pageNum, Long sectorId) {
		return employeeRepository.findBySectorId(sectorId, new PageRequest(pageNum, 5));
	}

	public Page<Employee> search(String identification, String name, Long sectorId, int page) {
		if(identification!=null) {
			identification = "%" + identification + "%";
		}
		return employeeRepository.search(identification, name, sectorId, new PageRequest(page, 5));
	}
	
	

}
