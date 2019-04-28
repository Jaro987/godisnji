package vacation.service;

import java.util.List;

import vacation.model.Sector;

public interface SectorService {
	
	List<Sector> findAll();
	Sector findOne(Long id);
	void save(Sector sector);

}
