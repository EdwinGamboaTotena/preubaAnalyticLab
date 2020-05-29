package com.edwingamboa.busesApi.services;

import java.util.List;

import com.edwingamboa.busesApi.models.DeviceType;

public interface IDeviceTypeService {

	public DeviceType findOne(Long id);

	public List<DeviceType> findAll();

	public DeviceType save(DeviceType bus);

	public void deletOne(Long id);
}
