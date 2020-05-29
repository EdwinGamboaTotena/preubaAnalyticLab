package com.edwingamboa.busesApi.services;

import java.util.List;

import com.edwingamboa.busesApi.models.Bus;

public interface IBusService {

	public Bus findOne(Long id);

	public List<Bus> findAll();

	public Bus save(Bus bus);

	public void deletOne(Long id);
}
