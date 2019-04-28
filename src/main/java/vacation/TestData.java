package vacation;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vacation.model.Employee;
import vacation.model.Sector;
import vacation.service.EmployeeService;
import vacation.service.SectorService;

@Component
public class TestData {
	@Autowired
	private SectorService sectorService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostConstruct
	public void init() {
		Sector s1 = new Sector();
		s1.setSectorName("informatika");
		s1.setBonus(5);
		sectorService.save(s1);
		
		Sector s2 = new Sector();
		s2.setSectorName("administracija");
		s2.setBonus(2);
		sectorService.save(s2);
		
		Employee r1 = new Employee();
		r1.setIdentification("#R131");
		r1.setName("Nikola Nikolic");
		r1.setEmail("g@v.com");
		r1.setYearsOfService(25);
		r1.setSector(s1);
		employeeService.save(r1);
		
		Employee r2 = new Employee();
		r2.setIdentification("#R121");
		r2.setName("Sava Savic");
		r2.setEmail("s@s.com");
		r2.setYearsOfService(41);
		r2.setSector(s2);
		employeeService.save(r2);
	}

}
