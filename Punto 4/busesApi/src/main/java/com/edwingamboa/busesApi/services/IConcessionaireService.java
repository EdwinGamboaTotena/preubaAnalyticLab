package com.edwingamboa.busesApi.services;

import java.util.List;

import com.edwingamboa.busesApi.models.Concessionaire;

public interface IConcessionaireService {

	public Concessionaire findOne(Long id);

	public List<Concessionaire> findAll();

	public Concessionaire save(Concessionaire concessionaire);

	public void deletOne(Long id);
}
