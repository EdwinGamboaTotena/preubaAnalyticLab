package com.edwingamboa.busesApi.daos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edwingamboa.busesApi.models.Bus;
import com.edwingamboa.busesApi.models.Device;

@Repository
public interface IDeviceDao extends CrudRepository<Device, Long> {

	public List<Device> findByBus(Bus bus);

}
