package bao.webapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bao.webapp.model.Business;

@Repository
public interface BusinessRepository extends CrudRepository<Business,Long>{

}
