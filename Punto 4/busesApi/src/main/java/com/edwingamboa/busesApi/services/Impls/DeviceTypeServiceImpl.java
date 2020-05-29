package com.edwingamboa.busesApi.services.Impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edwingamboa.busesApi.daos.IDeviceTypeDao;
import com.edwingamboa.busesApi.models.DeviceType;
import com.edwingamboa.busesApi.services.IDeviceTypeService;

@Service
public class DeviceTypeServiceImpl implements IDeviceTypeService {

	@Autowired
	private IDeviceTypeDao deviceTypeDao;

	@Override
	public DeviceType findOne(Long id) {
		return deviceTypeDao.findById(id).orElse(null);
	}

	@Override
	public List<DeviceType> findAll() {
		return (List<DeviceType>) deviceTypeDao.findAll();
	}

	@Override
	public DeviceType save(DeviceType deviceType) {
		return deviceTypeDao.save(deviceType);
	}

	@Override
	public void deletOne(Long id) {
		deviceTypeDao.deleteById(id);
	}

}
