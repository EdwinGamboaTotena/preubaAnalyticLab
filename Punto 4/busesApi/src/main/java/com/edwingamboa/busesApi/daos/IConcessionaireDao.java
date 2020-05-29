package com.edwingamboa.busesApi.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edwingamboa.busesApi.models.Concessionaire;

@Repository
public interface IConcessionaireDao extends CrudRepository<Concessionaire, Long> {

}
