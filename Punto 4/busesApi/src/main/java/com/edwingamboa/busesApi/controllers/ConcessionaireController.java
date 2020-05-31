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

import com.edwingamboa.busesApi.models.Concessionaire;
import com.edwingamboa.busesApi.services.IConcessionaireService;

@RestController
@RequestMapping("/concessionaire")
public class ConcessionaireController {

	@Autowired
	private IConcessionaireService concessionaireService;

	private static final String CREADO = "Se a registrado el nuevo concesionario satisfactoriamente.";
	private static final String EDITADO = "Se a editado la informacion del concesionario satisfactorimante.";
	private static final String ELIMINADO = "Se a eliminado la informacion del concesionario.";
	private static final String NO_ENCONTRADO = "No se a encontrado ningun consecionario con ese ID.";
	private static final String NO_ENCONTRADOS = "No hay concesionarios registrados.";
	private static final String ERROR_NO_ESPERADO = "Lo sentimos, a ocurrido un error inesperado.";

	@GetMapping("")
	public ResponseEntity<?> listConcessionaire() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Concessionaire> listConcessionaire = concessionaireService.findAll();
			response.put("exito", true);
			response.put("listConcessionaire", listConcessionaire);
			response.put("mensaje", (listConcessionaire.size() > 0) ? "" : NO_ENCONTRADOS);
			return new ResponseEntity<Map<String, Object>>(response,
					(listConcessionaire.size() > 0) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			response.put("exito", false);
			response.put("error", e.getMessage());
			response.put("mensaje", ERROR_NO_ESPERADO);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findConcessionaire(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Concessionaire concessionaire = concessionaireService.findOne(id);
			response.put("exito", true);
			response.put("concessionaire", concessionaire);
			response.put("mensaje", (concessionaire != null) ? "" : NO_ENCONTRADO);
			return new ResponseEntity<Map<String, Object>>(response,
					(concessionaire != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			response.put("exito", false);
			response.put("error", e.getMessage());
			response.put("mensaje", ERROR_NO_ESPERADO);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("")
	public ResponseEntity<?> createOrEditConcessionaire(@RequestBody Concessionaire concessionaire) {
		Map<String, Object> response = new HashMap<>();
		try {
			response.put("mensaje", (concessionaire.getId() != 0) ? EDITADO : CREADO);
			concessionaireService.save(concessionaire);
			response.put("exito", true);
			response.put("concessionaire", concessionaire);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("exito", false);
			response.put("error", e.getMessage());
			response.put("mensaje", ERROR_NO_ESPERADO);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteConcessionaire(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Concessionaire concessionaire = concessionaireService.findOne(id);
			if (concessionaire != null) {
				concessionaireService.deletOne(id);
				response.put("exito", true);
				response.put("mensaje", ELIMINADO);
			} else {
				response.put("exito", false);
				response.put("mensaje", NO_ENCONTRADO);
			}
			return new ResponseEntity<Map<String, Object>>(response,
					(concessionaire != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			response.put("exito", false);
			response.put("error", e.getMessage());
			response.put("mensaje", ERROR_NO_ESPERADO);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
