package tn.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.app.model.TheUser;


@Repository
public interface UserRepository extends JpaRepository<TheUser, Long>{
	TheUser findByUsername(String username);

}
