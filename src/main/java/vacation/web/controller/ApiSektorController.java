package vacation.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vacation.model.Sector;
import vacation.service.EmployeeService;
import vacation.service.SectorService;
import vacation.support.EmployeeToEmployeeDTO;
import vacation.support.SectorToSectorDTO;
import vacation.web.dto.SectorDTO;

@RestController
@RequestMapping(value="/api/sectors")
public class ApiSektorController {
	
	@Autowired
	private SectorService sectorService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeToEmployeeDTO toEmployeeDTO;
	
	@Autowired
	private SectorToSectorDTO toSectorDTO;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<SectorDTO>> get(){
		return new ResponseEntity<List<SectorDTO>>(
				toSectorDTO.convert(sectorService.findAll()),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<SectorDTO> get(
			@PathVariable Long id){
		Sector sector = sectorService.findOne(id);
		
		if(sector == null) {
			return new ResponseEntity<SectorDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<SectorDTO>(toSectorDTO.convert(sector),
				HttpStatus.OK);
	}

}
