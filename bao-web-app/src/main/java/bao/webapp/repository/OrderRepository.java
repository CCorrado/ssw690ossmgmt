package bao.webapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bao.webapp.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long>{

}
