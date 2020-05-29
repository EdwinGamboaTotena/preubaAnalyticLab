package com.edwingamboa.busesApi.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edwingamboa.busesApi.models.DeviceType;

@Repository
public interface IDeviceTypeDao extends CrudRepository<DeviceType, Long> {

}
