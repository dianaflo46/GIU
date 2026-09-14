package com.giu.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.giu.utils.RespuestaGenerica;
import com.giu.utils.TipoRespuesta;
import com.giu.model.GestionarEstadoUsuarioRequest;
import com.giu.model.UsuarioRequestDTO;
import com.giu.service.GestionSeguridadService;
import com.giu.service.GestionUsuariosService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

        private final GestionUsuariosService usuarioService;
        private final GestionSeguridadService gestionSeguridadService;

        public UsuarioController(GestionUsuariosService usuarioService, GestionSeguridadService gestionSeguridadService) {
                this.usuarioService = usuarioService;
                this.gestionSeguridadService = gestionSeguridadService;
        }

        /**
         * Consultar usuarios
         *
         * Método: GET
         * Ruta: /api/usuarios
         *
         * Ejemplo ruta con filtros:
         * /api/usuarios?usuarioRed=UUU111&estado=Activo
         */
        @GetMapping
        public ResponseEntity<RespuestaGenerica<List<UsuarioRequestDTO>>> obtenerUsuarios(
                        @RequestParam(required = false) String usuarioRed,
                        @RequestParam(required = false) String estado) {

                List<UsuarioRequestDTO> usuarios = usuarioService.obtenerUsuarios(
                                usuarioRed,
                                estado);

                RespuestaGenerica<List<UsuarioRequestDTO>> RespuestaGenerica = new RespuestaGenerica<>(
                                TipoRespuesta.EXITOSO, usuarios);

                return ResponseEntity.ok(RespuestaGenerica);
        }

        /**
         * Crear usuario
         *
         * Método: POST
         * Ruta: /api/usuarios
         *
         * Ejemplo de cuerpo de la solicitud:
         * {
         * "usuarioRed": "uuu111",
         * "nombre": "DANIEL MUÑOZ",
         * "correo": "user@gmail.com",
         * "numeroIdentificacion": "123456789",
         * "usuarioModificacion": "uuu00"
         * }
         */
        @PostMapping
        public ResponseEntity<RespuestaGenerica<Void>> crearUsuario(
                        @Valid @RequestBody UsuarioRequestDTO request,
                        BindingResult bindingResult) {

                if (bindingResult.hasErrors()) {
                        RespuestaGenerica<Void> respuesta = new RespuestaGenerica<>(
                                        TipoRespuesta.DATOS_INVALIDOS,
                                        null);
                        return ResponseEntity.ok(respuesta);
                }
                usuarioService.crearUsuario(request);

                RespuestaGenerica<Void> RespuestaGenerica = new RespuestaGenerica<>(TipoRespuesta.EXITOSO, null);

                return ResponseEntity.ok(RespuestaGenerica);
        }

        /**
         * Modificar usuario
         *
         * Método: PUT
         * Ruta: /api/usuarios
         *
         * Ejemplo de cuerpo de la solicitud:
         * { id : 1, falta
         * "usuarioRed": "uuu111",
         * "nombre": "DANIEL MUÑOZ",
         * "correo": "user@gmail.com",
         * "numeroIdentificacion": "123456789",
         * "usuarioModificacion": "uuu00"
         * }
         */
        @PutMapping
        public ResponseEntity<RespuestaGenerica<Void>> modificarUsuario(
                        @Valid @RequestBody UsuarioRequestDTO request,
                        BindingResult bindingResult) {

                if (bindingResult.hasErrors()) {
                        RespuestaGenerica<Void> respuesta = new RespuestaGenerica<>(
                                        TipoRespuesta.DATOS_INVALIDOS,
                                        null);
                        return ResponseEntity.ok(respuesta);
                }

                usuarioService.modificarUsuario(request);

                RespuestaGenerica<Void> RespuestaGenerica = new RespuestaGenerica<>(TipoRespuesta.EXITOSO, null);

                return ResponseEntity.ok(RespuestaGenerica);
        }

        /***
         * Gestionar estado de usuario
         * 
         * Método: PUT
         * Ruta: /api/usuarios/gestionar-estado
         * 
         * Ejemplo de cuerpo de la solicitud:
         * {
         * "apliId": 1,
         * "usuarioRed": "uuu111",
         * "operacion": 1, /0 = Activar Y 2 = Desactivar/
         * "rolId": "ROL123",
         * "usuarioModificacion": "uuu111"
         * * }
         */
        @PutMapping("/gestionar-estado")
        public ResponseEntity<RespuestaGenerica<Void>> gestionarEstadoUsuario(
                        @Valid @RequestBody GestionarEstadoUsuarioRequest request,
                        BindingResult bindingResult) {

                if (bindingResult.hasErrors()) {

                        RespuestaGenerica<Void> respuesta = new RespuestaGenerica<>(
                                        TipoRespuesta.DATOS_INVALIDOS,
                                        null);

                        return ResponseEntity.ok(respuesta);
                }
                gestionSeguridadService.gestionarEstadoUsuario(
                                request);

                RespuestaGenerica<Void> respuesta = new RespuestaGenerica<>(
                                TipoRespuesta.EXITOSO,
                                null);

                return ResponseEntity.ok(respuesta);
        }
}