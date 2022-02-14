package tn.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.app.model.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract,Long> {

} 
