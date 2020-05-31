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

import com.edwingamboa.busesApi.models.DeviceType;
import com.edwingamboa.busesApi.services.IDeviceTypeService;

@RestController
@RequestMapping("/deviceType")
public class DeviceTypeController {

	@Autowired
	private IDeviceTypeService deviceTypeService;

	private static final String CREADO = "Se a registrado el nuevo tipo de dispositivo satisfactoriamente.";
	private static final String EDITADO = "Se a editado la informacion del tipo de dispositivo satisfactorimante.";
	private static final String ELIMINADO = "Se a eliminado la informacion del tipo de dispositivo.";
	private static final String NO_ENCONTRADO = "No se a encontrado ningun tipo de dispositivo con ese ID.";
	private static final String NO_ENCONTRADOS = "No hay tipos de dispositivos registrados.";
	private static final String ERROR_NO_ESPERADO = "Lo sentimos, a ocurrido un error inesperado.";

	@GetMapping("")
	public ResponseEntity<?> listDevice() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<DeviceType> listDeviceType = deviceTypeService.findAll();
			response.put("exito", true);
			response.put("listDevice", listDeviceType);
			response.put("mensaje", (listDeviceType.size() > 0) ? "" : NO_ENCONTRADOS);
			return new ResponseEntity<Map<String, Object>>(response,
					(listDeviceType.size() > 0) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
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
			DeviceType deviceType = deviceTypeService.findOne(id);
			response.put("exito", true);
			response.put("device", deviceType);
			response.put("mensaje", (deviceType != null) ? "" : NO_ENCONTRADO);
			return new ResponseEntity<Map<String, Object>>(response,
					(deviceType != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			response.put("exito", false);
			response.put("error", e.getMessage());
			response.put("mensaje", ERROR_NO_ESPERADO);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("")
	public ResponseEntity<?> createOrEditDevice(@RequestBody DeviceType deviceType) {
		Map<String, Object> response = new HashMap<>();
		try {
			response.put("mensaje", (deviceType.getId() != 0) ? EDITADO : CREADO);
			deviceTypeService.save(deviceType);
			response.put("exito", true);
			response.put("deviceType", deviceType);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
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
			DeviceType deviceType = deviceTypeService.findOne(id);
			if (deviceType != null) {
				deviceTypeService.deletOne(id);
				response.put("exito", true);
				response.put("mensaje", ELIMINADO);
			} else {
				response.put("exito", false);
				response.put("mensaje", NO_ENCONTRADO);
			}
			return new ResponseEntity<Map<String, Object>>(response,
					(deviceType != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			response.put("exito", false);
			response.put("error", e.getMessage());
			response.put("mensaje", ERROR_NO_ESPERADO);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
