package vacation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vacation.model.Sector;

public interface SectorRepository 
extends JpaRepository<Sector, Long> {
	
	

}
