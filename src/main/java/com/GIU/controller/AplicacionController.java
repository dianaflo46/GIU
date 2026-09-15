package com.giu.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giu.model.GestionRoles.RolResponseDTO;
import com.giu.model.GestionUsuarios.GestionarRolUsuarioRequest;
import com.giu.model.GestionUsuarios.GestionarRolesUsuariosRequest;
import com.giu.model.GestionUsuarios.UsuarioRolResponseDTO;
import com.giu.service.GestionRolesService;
import com.giu.service.GestionUsuariosService;
import com.giu.utils.RespuestaGenerica;
import com.giu.utils.TipoRespuesta;

@RestController
@RequestMapping("/api/aplicaciones/{apliId}")
public class AplicacionController {

        private final GestionUsuariosService usuarioService;
        private final GestionRolesService rolesService;

        public AplicacionController(
                        GestionUsuariosService usuarioService,
                        GestionRolesService rolesService) {

                this.usuarioService = usuarioService;
                this.rolesService = rolesService;
        }

        /**
         * Consultar roles de una aplicación
         *
         * Método: GET
         * Ruta: /api/aplicaciones/{apliId}/roles
         *
         * Ejemplo:
         * GET /api/aplicaciones/1/roles
         */
        @GetMapping("/roles")
        public ResponseEntity<RespuestaGenerica<List<RolResponseDTO>>> obtenerRoles(
                        @PathVariable Long apliId) {

                List<RolResponseDTO> roles = rolesService.obtenerRoles(apliId);

                RespuestaGenerica<List<RolResponseDTO>> respuesta = new RespuestaGenerica<>(
                                TipoRespuesta.EXITOSO,
                                roles);

                return ResponseEntity.ok(respuesta);
        }

        /**
         * Consultar asignación de rol de un usuario
         *
         * Método: GET
         * Ruta: /api/aplicaciones/{apliId}/rol/usuarios/{usuarioRed}
         *
         * Ejemplo:
         * GET /api/aplicaciones/1/rol/usuarios/UUU111
         */
        @GetMapping("/rol/usuarios/{usuarioRed}")
        public ResponseEntity<RespuestaGenerica<UsuarioRolResponseDTO>> obtenerRolUsuario(
                        @PathVariable Long apliId,
                        @PathVariable String usuarioRed) {

                UsuarioRolResponseDTO resultado = usuarioService.obtenerRolUsuario(
                                usuarioRed,
                                apliId);

                RespuestaGenerica<UsuarioRolResponseDTO> respuesta = new RespuestaGenerica<>(
                                TipoRespuesta.EXITOSO,
                                resultado);

                return ResponseEntity.ok(respuesta);
        }

        /**
         * Asignar rol a un usuario para una aplicación específica
         *
         * Método: POST
         * Ruta: /api/aplicaciones/{apliId}/usuarios/asignacion-rol
         *
         * Ejemplo:
         * POST /api/aplicaciones/1/usuarios/asignacion-rol
         */
        @PostMapping("/usuarios/asignacion-rol")
        public ResponseEntity<RespuestaGenerica<Void>> asignarRolUsuario(
                        @PathVariable Long apliId,
                        @Valid @RequestBody GestionarRolUsuarioRequest request,
                        BindingResult bindingResult) {

                if (bindingResult.hasErrors()) {
                        RespuestaGenerica<Void> respuesta = new RespuestaGenerica<>(
                                        TipoRespuesta.DATOS_INVALIDOS,
                                        null);
                        return ResponseEntity.ok(respuesta);
                }

                usuarioService.asignarRolUsuario(
                                apliId,
                                request);

                RespuestaGenerica<Void> respuesta = new RespuestaGenerica<>(
                                TipoRespuesta.EXITOSO,
                                null);

                return ResponseEntity.ok(respuesta);
        }

        /**
         * Retirar rol a un usuario para una aplicación específica
         *
         * Método: DELETE
         * Ruta: /api/aplicaciones/{apliId}/usuarios/{usuarioRed}
         *
         * Ejemplo:
         * POST /api/aplicaciones/1/usuarios/uuu111
         */
        @DeleteMapping("/usuarios/{usuarioRed}")
        public ResponseEntity<RespuestaGenerica<Void>> retirarRolUsuario(
                        @PathVariable Long apliId,
                        @Valid @RequestBody GestionarRolUsuarioRequest request,
                        BindingResult bindingResult) {

                if (bindingResult.hasErrors()) {
                        RespuestaGenerica<Void> respuesta = new RespuestaGenerica<>(
                                        TipoRespuesta.DATOS_INVALIDOS,
                                        null);
                        return ResponseEntity.ok(respuesta);
                }

                usuarioService.retirarRolUsuario(
                                apliId,
                                request);

                RespuestaGenerica<Void> respuesta = new RespuestaGenerica<>(
                                TipoRespuesta.EXITOSO,
                                null);

                return ResponseEntity.ok(respuesta);
        }

        /**
         * Retirar rol a un usuario para una aplicación específica
         *
         * Método: DELETE
         * Ruta: /api/aplicaciones/{apliId}/usuarios/{usuarioRed}
         *
         * Ejemplo:
         * POST /api/aplicaciones/1/usuarios/uuu111
         */
        @DeleteMapping("/usuario")
        public ResponseEntity<RespuestaGenerica<Void>> retirarRolesUsuarios(
                        @PathVariable Long apliId,
                        @Valid @RequestBody GestionarRolesUsuariosRequest request,
                        BindingResult bindingResult) {

                if (bindingResult.hasErrors()) {
                        RespuestaGenerica<Void> respuesta = new RespuestaGenerica<>(
                                        TipoRespuesta.DATOS_INVALIDOS,
                                        null);
                        return ResponseEntity.ok(respuesta);
                }

                usuarioService.retirarRolesUsuarios(apliId, request);

                RespuestaGenerica<Void> respuesta = new RespuestaGenerica<>(
                                TipoRespuesta.EXITOSO,
                                null);

                return ResponseEntity.ok(respuesta);
        }
}
