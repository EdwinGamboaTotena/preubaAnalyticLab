package com.edwingamboa.busesApi.services;

import java.util.List;

import com.edwingamboa.busesApi.models.Bus;
import com.edwingamboa.busesApi.models.Device;

public interface IDeviceService {

	public Device findOne(Long id);

	public List<Device> findAll();
	
	public List<Device> findByBus(Bus bus);

	public Device save(Device bus);

	public void deletOne(Long id);
}
