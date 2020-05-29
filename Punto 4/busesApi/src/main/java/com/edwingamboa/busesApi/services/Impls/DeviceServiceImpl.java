package com.edwingamboa.busesApi.services.Impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edwingamboa.busesApi.daos.IDeviceDao;
import com.edwingamboa.busesApi.models.Bus;
import com.edwingamboa.busesApi.models.Device;
import com.edwingamboa.busesApi.services.IDeviceService;

@Service
public class DeviceServiceImpl implements IDeviceService {

	@Autowired
	private IDeviceDao deviceDao;

	@Override
	public Device findOne(Long id) {
		return deviceDao.findById(id).orElse(null);
	}

	@Override
	public List<Device> findAll() {
		return (List<Device>) deviceDao.findAll();
	}

	@Override
	public List<Device> findByBus(Bus bus) {
		return deviceDao.findByBus(bus);
	}

	@Override
	public Device save(Device device) {
		return deviceDao.save(device);
	}

	@Override
	public void deletOne(Long id) {
		deviceDao.deleteById(id);
	}

}
