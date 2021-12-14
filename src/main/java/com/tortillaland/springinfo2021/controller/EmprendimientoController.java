package com.tortillaland.springinfo2021.controller;

import javax.validation.Valid;
import com.tortillaland.springinfo2021.dto.EventoDTO;
import com.tortillaland.springinfo2021.entity.Emprendimieto;
import com.tortillaland.springinfo2021.repository.EmprendimientoRepository;
import com.tortillaland.springinfo2021.repository.EventoRepository;
import com.tortillaland.springinfo2021.service.EmprendimientoService;
import com.tortillaland.springinfo2021.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmprendimientoController {
    private final EmprendimientoRepository emprendimientoRepository;
    private final EmprendimientoService emprendimientoService;
    private final EventoRepository eventoRepository;
    private final EventoService eventoService;
    @Autowired
    public EmprendimientoController(EmprendimientoRepository emprendimientoRepository,
                                    EmprendimientoService emprendimientoService,
                                    EventoRepository eventoRepository,
                                    EventoService eventoService) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.emprendimientoService = emprendimientoService;
        this.eventoRepository = eventoRepository;
        this.eventoService = eventoService;
    }

    @PostMapping(value = "/api/v1/usuarios/{id}/emprendimientos")
    public ResponseEntity<?> crearEmprendimiento(@PathVariable("id") Long usuarioId,
                                                 @Valid @RequestBody Emprendimieto emprendimiento) {
        return new ResponseEntity<>(emprendimientoService.guardar(usuarioId, emprendimiento), HttpStatus.CREATED);                                          
    }
    @PutMapping(value = "/api/v1/emprendimientos/{id}/quitar")
    public Emprendimieto eliminarEmprendimiento(@PathVariable("id") Long id, Emprendimieto emprendimiento) {
        return this.emprendimientoService.eliminar(id, emprendimiento);
    }
    @PutMapping(value = "/api/v1/emprendimientos/{id}")
    public Emprendimieto modificarEmprendimiento(@PathVariable("id") Long id,
                                                  @Valid @RequestBody Emprendimieto emprendimiento) {
        return this.emprendimientoService.modificar(id, emprendimiento);
    }
    @GetMapping(value = "/ap1/v1/emprendimientos")
    public ResponseEntity<?> obtenerTodosLosEmprendimientos(
            @RequestParam(name = "nombre", required = false) String nombre) {
        return new ResponseEntity<>(emprendimientoService.obtenerTodos(nombre) ,HttpStatus.OK);
    }
    @GetMapping(value = "/api/v1/emprendimientos/no_publicados")
    public ResponseEntity<?> obtenerEmprendimientosNoPublicados() {
        return new ResponseEntity<>(emprendimientoService.obtenerNoPublicados(), HttpStatus.OK);
    }
    @PostMapping(value = "/api/v1/emprendimientos/{emprendimientoId}/eventos/{eventoId}")
    public ResponseEntity<?> registrarEvento(@PathVariable("emprendimientoId") Long emprendimientoId,
                                             @PathVariable("eventoId") Long eventoId, EventoDTO eventoDTO) {
        emprendimientoRepository.findById(emprendimientoId);
        eventoRepository.findById(eventoId);
        return new ResponseEntity<>(eventoService.registrar(emprendimientoId, eventoId, eventoDTO), HttpStatus.CREATED);                                     
    }
}