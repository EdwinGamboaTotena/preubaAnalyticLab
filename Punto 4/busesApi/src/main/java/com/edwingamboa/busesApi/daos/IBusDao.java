package com.edwingamboa.busesApi.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edwingamboa.busesApi.models.Bus;

@Repository
public interface IBusDao extends CrudRepository<Bus, Long> {

}
