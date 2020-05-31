package com.edwingamboa.busesApi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.edwingamboa.busesApi.daos.IDeviceDao;
import com.edwingamboa.busesApi.models.Device;
import com.edwingamboa.busesApi.services.Impls.DeviceServiceImpl;

@RunWith(SpringRunner.class)
public class DeviceServiceImplTest {

	@TestConfiguration
	static class DeviceImplTestContextConfiguration {
		@Bean
		public IDeviceService DeviceService() {
			return new DeviceServiceImpl();
		}
	}

	@Autowired
	private IDeviceService deviceService;

	@MockBean
	private IDeviceDao deviceDao;

	private static final String IP_ONE = "192.168.0.10";
	private static final String STATUS_ONE = "Active";

	private static final String IP_TWO = "192.168.0.11";
	private static final String STATUS_TWO = "Inactive";

	@Before
	public void init() {
		Device deviceOne = new Device();
		deviceOne.setId(1L);
		deviceOne.setIp(IP_ONE);
		deviceOne.setStatus(STATUS_ONE);

		Device deviceTwo = new Device();
		deviceTwo.setId(2L);
		deviceTwo.setIp(IP_TWO);
		deviceTwo.setStatus(STATUS_TWO);

		List<Device> lstDevice = new ArrayList<Device>();
		lstDevice.add(deviceOne);
		lstDevice.add(deviceTwo);

		Mockito.when(deviceDao.findAll()).thenReturn(lstDevice);
		Mockito.when(deviceDao.findById(deviceOne.getId())).thenReturn(null);
	}

	@Test
	public void findAllTest() {
		List<Device> lstDevice = deviceService.findAll();

		assertEquals(2, lstDevice.size());
		assertEquals(IP_ONE, lstDevice.get(0).getIp());
		assertEquals(2L, lstDevice.get(1).getId());
	}

	@Test
	public void findOneNullTest() {
		Device device = deviceService.findOne(3L);
		assertEquals(null, device);
	}
}
