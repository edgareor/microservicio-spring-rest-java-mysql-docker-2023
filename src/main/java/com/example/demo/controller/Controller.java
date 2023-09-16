package com.example.demo.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Persona;
import com.example.demo.service.IPersonaService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(path = "/api")
public class Controller {

	@Autowired
	IPersonaService per;

	@GetMapping("/hola-mundo")
	public String hello(@RequestParam(defaultValue = "World") String name) {
		try {
			return "Hello %s!".formatted(name);
		} catch (Exception ex) {
			System.out.println(ex);
			return ex.getMessage();
		}
	}

	@GetMapping("/fechas/actual")
	public ResponseEntity<Object> fechaActual() {
		try {
			Map<Object, Object> response = new HashMap<>();
			response.put("message", Instant.now().toString());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
		}
	}

	@GetMapping("/path-param-query-string/{data}")
	public ResponseEntity<Object> pathParamQueryString(@PathVariable String data,
			@RequestParam(required = false) String param1, @RequestParam(required = false) String param2) {
		try {
			Map<Object, Object> responseQueryParams = new HashMap<>();
			responseQueryParams.put("param1", param1);
			responseQueryParams.put("param2", param2);

			Map<Object, Object> responseData = new HashMap<>();
			responseData.put("pathParam", data);
			responseData.put("queryParam", responseQueryParams);

			Map<Object, Object> response = new HashMap<>();
			response.put("data", responseData);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
		}
	}

	@GetMapping("/headers")
	public ResponseEntity<Object> header(@RequestHeader Map<String, String> headers,
			@RequestHeader String authorization) {
		try {
			Map<Object, Object> response = new HashMap<>();
			response.put("headers-entrada", headers);
			response.put("header-individual", authorization);

			HttpHeaders headersRespuesta = new HttpHeaders(); // Agregar headers a respuesta.
			headersRespuesta.add("nombreHeader", "valueHeader");

			response.put("header-respuesta", headersRespuesta);
			return ResponseEntity.status(HttpStatus.OK).headers(headersRespuesta).body(response);
		} catch (Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<Object> listAll() {
		try {
			Map<Object, Object> response = new HashMap<>();
			response.put("message", per.getPersonas());
			return ResponseEntity.status(200).body(response);
		} catch (Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
		}
	}

	@GetMapping("/all-desc-by-age")
	public ResponseEntity<Object> listAllDescByAge() {
		try {
			Map<Object, Object> response = new HashMap<>();
			response.put("message", per.getAllDescByAge());
			return ResponseEntity.status(200).body(response);
		} catch (Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
		}
	}

	@PostMapping(path = "/personas", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> save(@Valid @RequestBody Persona persona) {
		try {
			Map<Object, Object> response = new HashMap<>();
			if (per.exists(persona.getPkPersona())) {
				response.put("message", "Ya existe un registro con el ID: " + persona.getPkPersona());
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			} else {
				per.save(persona);
				response.put("message", "Entidad introducida correctamente");
				return ResponseEntity.status(HttpStatus.CREATED).body(response);
			}
		} catch (Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
		}
	}

	@PutMapping(path = "/personas", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> update(@Valid @RequestBody Persona persona) {
		try {
			List<Persona> personaF = per.findById(persona.getPkPersona());
			Map<Object, Object> response = new HashMap<>();

			if (personaF.isEmpty()) {
				response.put("message", "No se encontro registro");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			} else {
				per.save(persona);
				response.put("message", "Entidad actualizada correctamente");
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
			}
		} catch (Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
		}
	}

	@DeleteMapping("/personas/{id}")
	public ResponseEntity<Object> delete(@PathVariable long id) {
		try {
			List<Persona> personaF = per.findById(id);
			System.out.println(personaF);
			Map<Object, Object> response = new HashMap<>();

			if (personaF.isEmpty()) {
				response.put("message", "No se encontro registro");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			} else {
				per.deleteByPersona(personaF.get(0));
				response.put("message", "Entidad eliminada correctamente");
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
		} catch (Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
		}
	}

	@GetMapping("/store-procedure")
	public ResponseEntity<Object> storeProcedure(@RequestParam(required = false) int start,
			@RequestParam(required = false) int end) {
		try {
			double promedio = per.storeProcedure(start, end);
			Map<Object, Object> response = new HashMap<>();
			response.put("promedio", promedio);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
		}
	}

	@GetMapping("/function")
	public ResponseEntity<Object> function(@RequestParam(required = false) int radio) {
		try {
			double area = per.function(radio);
			Map<Object, Object> response = new HashMap<>();
			response.put("area", area);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
		}
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
		List<Object> errorsMessage = new ArrayList<>();
		Map<String, Object> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String errorMessage = error.getDefaultMessage();
			errorsMessage.add(errorMessage);
		});
		errors.put("Errors", errorsMessage);
		return errors;
	}
}
