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

import com.edwingamboa.busesApi.daos.IDeviceTypeDao;
import com.edwingamboa.busesApi.models.DeviceType;
import com.edwingamboa.busesApi.services.Impls.DeviceTypeServiceImpl;

@RunWith(SpringRunner.class)
public class DeviceTypeServiceImplTest {

	@TestConfiguration
	static class DeviceTypeImplTestContextConfiguration {
		@Bean
		public IDeviceTypeService DeviceTypeService() {
			return new DeviceTypeServiceImpl();
		}
	}

	@Autowired
	private IDeviceTypeService deviceTypeService;

	@MockBean
	private IDeviceTypeDao deviceTypeDao;

	private static final String NAME_ONE = "CANBUS";
	private static final String NAME_TWO = "NVR";

	@Before
	public void init() {
		DeviceType deviceTypeOne = new DeviceType();
		deviceTypeOne.setId(1L);
		deviceTypeOne.setName(NAME_ONE);

		DeviceType deviceTypeTwo = new DeviceType();
		deviceTypeTwo.setId(2L);
		deviceTypeTwo.setName(NAME_TWO);

		List<DeviceType> lstDeviceType = new ArrayList<DeviceType>();
		lstDeviceType.add(deviceTypeOne);
		lstDeviceType.add(deviceTypeTwo);

		Mockito.when(deviceTypeDao.findAll()).thenReturn(lstDeviceType);
		Mockito.when(deviceTypeDao.findById(deviceTypeOne.getId())).thenReturn(null);
	}

	@Test
	public void findAllTest() {
		List<DeviceType> lstDeviceType = deviceTypeService.findAll();

		assertEquals(2, lstDeviceType.size());
		assertEquals(NAME_ONE, lstDeviceType.get(0).getName());
		assertEquals(2L, lstDeviceType.get(1).getId());
	}

	@Test
	public void findOneNullTest() {
		DeviceType deviceType = deviceTypeService.findOne(3L);
		assertEquals(null, deviceType);
	}
}
