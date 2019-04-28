package vacation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vacation.model.Employee;

@Repository
public interface EmployeeRepository 
	extends JpaRepository<Employee, Long>{
	
	Page<Employee> findBySectorId(Long id, Pageable pageRequest);
	@Query("SELECT e FROM Employee e WHERE "
			+ "(:identification IS NULL or e.identification like :identification) AND "
			+ "(:name IS NULL or e.name like :name ) AND "
			+ "(:sectorId IS NULL OR e.sector.id = :sectorId)"
			)
	Page<Employee> search(
			@Param("identification") String identification,
			@Param("name") String name,
			@Param("sectorId") Long sectorId,
			Pageable pageRequest);
}
