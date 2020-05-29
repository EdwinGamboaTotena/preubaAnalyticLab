package com.edwingamboa.busesApi.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edwingamboa.busesApi.models.Bus;
import com.edwingamboa.busesApi.models.Device;
import com.edwingamboa.busesApi.services.IDeviceService;

@RestController
@RequestMapping("/device")
public class DeviceController {

	@Autowired
	private IDeviceService deviceService;

	private static final String CREADO = "Se a registrado el nuevo dispositivo satisfactoriamente.";
	private static final String EDITADO = "Se a editado la informacion del dispositivo satisfactorimante.";
	private static final String ELIMINADO = "Se a eliminado la informacion del dispositivo.";
	private static final String NO_ENCONTRADO = "No se a encontrado ningun dispositivo con ese ID.";
	private static final String NO_ENCONTRADOS = "No hay dispositivos registrados.";
	private static final String ERROR_NO_ESPERADO = "Lo sentimos, a ocurrido un error inesperado.";

	@GetMapping("")
	public ResponseEntity<?> listDevice() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Device> listDevice = deviceService.findAll();
			response.put("exito", true);
			response.put("listDevice", listDevice);
			response.put("mensaje", (listDevice.size() > 0) ? "" : NO_ENCONTRADOS);
			return new ResponseEntity<Map<String, Object>>(response,
					(listDevice.size() > 0) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			response.put("exito", false);
			response.put("error", e.getMessage());
			response.put("mensaje", ERROR_NO_ESPERADO);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findDevice(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Device device = deviceService.findOne(id);
			response.put("exito", true);
			response.put("device", device);
			response.put("mensaje", (device != null) ? "" : NO_ENCONTRADO);
			return new ResponseEntity<Map<String, Object>>(response,
					(device != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			response.put("exito", false);
			response.put("error", e.getMessage());
			response.put("mensaje", ERROR_NO_ESPERADO);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("")
	public ResponseEntity<?> createOrEditDevice(@RequestBody Device device) {
		Map<String, Object> response = new HashMap<>();
		try {
			response.put("mensaje", (device.getId() != 0) ? EDITADO : CREADO);
			deviceService.save(device);
			response.put("exito", true);
			response.put("device", device);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("exito", false);
			response.put("error", e.getMessage());
			response.put("mensaje", ERROR_NO_ESPERADO);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/deviceForBus")
	public ResponseEntity<?> findDeviceByBus(@RequestBody Bus bus) {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Device> listDevice = deviceService.findByBus(bus);
			response.put("exito", true);
			response.put("listDevice", listDevice);
			response.put("mensaje", (listDevice.size() > 0) ? "" : NO_ENCONTRADOS);
			return new ResponseEntity<Map<String, Object>>(response,
					(listDevice.size() > 0) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			response.put("exito", false);
			response.put("error", e.getMessage());
			response.put("mensaje", ERROR_NO_ESPERADO);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteDevice(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Device device = deviceService.findOne(id);
			if (device != null) {
				deviceService.deletOne(id);
				response.put("exito", true);
				response.put("mensaje", ELIMINADO);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			} else {
				response.put("exito", false);
				response.put("mensaje", NO_ENCONTRADO);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response.put("exito", false);
			response.put("error", e.getMessage());
			response.put("mensaje", ERROR_NO_ESPERADO);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
