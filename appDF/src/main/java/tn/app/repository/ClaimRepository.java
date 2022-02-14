package tn.app.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.app.model.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
	
	
	@Query(value="select * from claim c where c.num_claim =:id",nativeQuery = true)
	Claim find(@Param("id") long id);
	
	
	

}

