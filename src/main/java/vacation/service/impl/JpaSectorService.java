package vacation.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vacation.model.Sector;
import vacation.repository.SectorRepository;
import vacation.service.SectorService;

@Service
@Transactional
public class JpaSectorService 
	implements SectorService{
	
	@Autowired
	private SectorRepository sectorRepository;

	public List<Sector> findAll() {
		return sectorRepository.findAll();
	}

	public Sector findOne(Long id) {
		return sectorRepository.findOne(id);
	}

	public void save(Sector sector) {
		sectorRepository.save(sector);		
	}

}
