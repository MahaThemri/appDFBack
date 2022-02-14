package tn.app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.app.model.Claim;
import tn.app.model.Photos;

@Repository
@Transactional
public interface PhotosRepository extends JpaRepository<Photos, Long> {
	
	@Query(value="select * from photos p where p.claim_num_claim =:id ",nativeQuery = true)
	List<Photos>find(@Param("id") long id);

}
