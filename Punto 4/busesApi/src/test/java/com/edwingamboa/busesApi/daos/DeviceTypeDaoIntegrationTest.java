package com.edwingamboa.busesApi.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.edwingamboa.busesApi.models.DeviceType;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DeviceTypeDaoIntegrationTest {

	@Autowired
	private IDeviceTypeDao deviceTypeDao;
	
	private static final String NAME = "NVR";
	
	@Test
	public void registrerTest() {
		DeviceType deviceType = new DeviceType();
				
		deviceType.setName(NAME);

		DeviceType deviceTypeSaved = deviceTypeDao.save(deviceType);
		
		assertEquals(NAME, deviceTypeSaved.getName());
		assertNotEquals(0, deviceTypeSaved.getId());
	}
}
