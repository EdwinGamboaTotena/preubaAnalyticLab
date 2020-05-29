package com.edwingamboa.busesApi.services.Impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edwingamboa.busesApi.daos.IBusDao;
import com.edwingamboa.busesApi.models.Bus;
import com.edwingamboa.busesApi.services.IBusService;

@Service
public class BusServiceImpl implements IBusService {

	@Autowired
	private IBusDao busDao;

	@Override
	public Bus findOne(Long id) {
		return busDao.findById(id).orElse(null);
	}

	@Override
	public List<Bus> findAll() {
		return (List<Bus>) busDao.findAll();
	}

	@Override
	public Bus save(Bus bus) {
		return busDao.save(bus);
	}

	@Override
	public void deletOne(Long id) {
		busDao.deleteById(id);
	}

}
