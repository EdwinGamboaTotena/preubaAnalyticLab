package com.edwingamboa.busesApi.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.edwingamboa.busesApi.models.Bus;
import com.edwingamboa.busesApi.models.Device;
import com.edwingamboa.busesApi.models.DeviceType;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DeviceDaoIntegrationTest {

	@Autowired
	private IDeviceDao deviceDao;

	private static final String IP = "192.168.0.10";
	private static final String STATUS = "Active";
	private static final Bus BUS = null;
	private static final DeviceType DEVICE_TYPE = null;

	@Test
	public void registerTest() {
		Device device = new Device();

		device.setIp(IP);
		device.setStatus(STATUS);
		device.setBus(BUS);
		device.setDeviceType(DEVICE_TYPE);

		Device deviceSaved = deviceDao.save(device);

		assertEquals(IP, deviceSaved.getIp());
		assertEquals(STATUS, deviceSaved.getStatus());
		assertEquals(BUS, deviceSaved.getBus());
		assertEquals(DEVICE_TYPE, deviceSaved.getDeviceType());
		assertNotEquals(0, deviceSaved.getId());
	}

	@Test
	public void editedTest() {
		Device device = new Device();

		device.setId(1L);
		device.setIp(IP);
		device.setStatus(STATUS);

		Device deviceSaved = deviceDao.save(device);

		assertEquals(IP, deviceSaved.getIp());
		assertEquals(STATUS, deviceSaved.getStatus());
		assertNotEquals(0, deviceSaved.getId());
	}
}
