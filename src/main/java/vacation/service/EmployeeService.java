package vacation.service;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import vacation.model.Employee;

public interface EmployeeService {
	
	Page<Employee> findAll(int pageNum);
	Employee findOne(Long id);
	void save (Employee employee);
	Employee remove(Long id);
	Page<Employee>findBySectorId(int pageNum, Long sectorId);
	Page<Employee> search(
			@Param("identification") String identification,
			@Param("name") String name,
			@Param("sectorId") Long sectorId,
			int page);

}
