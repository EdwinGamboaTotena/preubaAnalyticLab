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

import com.edwingamboa.busesApi.daos.IBusDao;
import com.edwingamboa.busesApi.models.Bus;
import com.edwingamboa.busesApi.models.Concessionaire;
import com.edwingamboa.busesApi.services.Impls.BusServiceImpl;

@RunWith(SpringRunner.class)
public class BusServiceImplTest {

	@TestConfiguration
	static class BusServiceImplTestContextConfiguration {
		@Bean
		public IBusService BusService() {
			return new BusServiceImpl();
		}
	}

	@Autowired
	private IBusService busService;

	@MockBean
	private IBusDao busDao;

	private static final String TYPE_ONE = "Bi-articulado";
	private static final String MOTOR_ONE = "Electric";
	private static final String BRAKES_ONE = "Freno Regenerativo";
	private static final String TYPE_TWO = "Autobus";
	private static final String MOTOR_TWO = "Hybrid";
	private static final String BRAKES_TWO = "Frenos mecánicos";
	private static final Concessionaire CONCESSIONARIRE = null;

	@Before
	public void init() {
		Bus busOne = new Bus();
		busOne.setId(1L);
		busOne.setType(TYPE_ONE);
		busOne.setMotor(MOTOR_ONE);
		busOne.setBrakes(BRAKES_ONE);
		busOne.setConsessionaire(CONCESSIONARIRE);

		Bus busTwo = new Bus();
		busTwo.setId(2L);
		busTwo.setType(TYPE_TWO);
		busTwo.setMotor(MOTOR_TWO);
		busTwo.setBrakes(BRAKES_TWO);
		busTwo.setConsessionaire(CONCESSIONARIRE);

		List<Bus> lstBus = new ArrayList<Bus>();
		lstBus.add(busOne);
		lstBus.add(busTwo);

		Mockito.when(busDao.findAll()).thenReturn(lstBus);
		Mockito.when(busDao.findById(busOne.getId())).thenReturn(null);
	}

	@Test
	public void findAllTest() {
		List<Bus> lstBus = busService.findAll();

		assertEquals(2, lstBus.size());
		assertEquals(TYPE_ONE, lstBus.get(0).getType());
		assertEquals(2L, lstBus.get(1).getId());
	}

	@Test
	public void findOneNullTest() {
		Bus bus = busService.findOne(3L);
		assertEquals(null, bus);
	}
}
