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
import com.edwingamboa.busesApi.services.IBusService;;

@RestController
@RequestMapping("/bus")
public class BusController {

	@Autowired
	private IBusService busService;

	private static final String CREADO = "Se a registrado el nuevo bus satisfactoriamente.";
	private static final String EDITADO = "Se a editado la informacion del bus satisfactorimante.";
	private static final String ELIMINADO = "Se a eliminado la informacion del bus.";
	private static final String NO_ENCONTRADO = "No se a encontrado ningun bus con ese ID.";
	private static final String NO_ENCONTRADOS = "No hay buses registrados.";
	private static final String ERROR_NO_ESPERADO = "Lo sentimos, a ocurrido un error inesperado.";

	@GetMapping("")
	public ResponseEntity<?> listDevice() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Bus> listBus = busService.findAll();
			response.put("exito", true);
			response.put("listBus", listBus);
			response.put("mensaje", (listBus.size() > 0) ? "" : NO_ENCONTRADOS);
			return new ResponseEntity<Map<String, Object>>(response,
					(listBus.size() > 0) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
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
			Bus bus = busService.findOne(id);
			response.put("exito", true);
			response.put("bus", bus);
			response.put("mensaje", (bus != null) ? "" : NO_ENCONTRADO);
			return new ResponseEntity<Map<String, Object>>(response,
					(bus != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			response.put("exito", false);
			response.put("error", e.getMessage());
			response.put("mensaje", ERROR_NO_ESPERADO);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("")
	public ResponseEntity<?> createOrEditDevice(@RequestBody Bus bus) {
		Map<String, Object> response = new HashMap<>();
		try {
			response.put("mensaje", (bus.getId() != 0) ? EDITADO : CREADO);
			bus = busService.save(bus);
			response.put("exito", true);
			response.put("bus", bus);
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
			Bus bus = busService.findOne(id);
			if (bus != null) {
				busService.deletOne(id);
				response.put("exito", true);
				response.put("mensaje", ELIMINADO);
			} else {
				response.put("exito", false);
				response.put("mensaje", NO_ENCONTRADO);
			}
			return new ResponseEntity<Map<String, Object>>(response,
					(bus != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			response.put("exito", false);
			response.put("error", e.getMessage());
			response.put("mensaje", ERROR_NO_ESPERADO);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
