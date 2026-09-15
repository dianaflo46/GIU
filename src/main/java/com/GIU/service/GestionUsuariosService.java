package com.giu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.giu.model.GestionUsuarios.CrearUsuarioRequestDTO;
import com.giu.model.GestionUsuarios.GestionarRolUsuarioRequestDTO;
import com.giu.model.GestionUsuarios.GestionarRolesUsuariosRequestDTO;
import com.giu.model.GestionUsuarios.ModificarUsuarioRequestDTO;
import com.giu.model.GestionUsuarios.UsuarioAplicacionResponseDTO;
import com.giu.model.GestionUsuarios.UsuarioResponseDTO;
import com.giu.model.GestionUsuarios.UsuarioRolRequestDTO;
import com.giu.model.GestionUsuarios.UsuarioRolResponseDTO;
import com.giu.repository.GestionUsuariosRepository;

@Service
public class GestionUsuariosService {

        // Se inyecta el repositorio de gestión de usuarios en el servicio
        private final GestionUsuariosRepository gestionUsuariosRepository;

        // Constructor para inyectar el repositorio de gestión de usuarios
        public GestionUsuariosService(GestionUsuariosRepository gestionUsuariosRepository) {
                this.gestionUsuariosRepository = gestionUsuariosRepository;
        }

        // Método para obtener los usuarios según el usuario de red y el estado
        public List<UsuarioResponseDTO> obtenerUsuarios(String usuarioRed, String estado) {

                return gestionUsuariosRepository.obtenerUsuarios(usuarioRed, estado);
        }

        // Método para obtener los usuarios asociados a una aplicación específica según
        // el estado
        public List<UsuarioAplicacionResponseDTO> obtenerUsuariosPorAplicacion(Long apliId, String estado) {
                return gestionUsuariosRepository.obtenerUsuarioXAplicacion(apliId, estado);
        }

        // Método para obtener el rol de un usuario específico en una aplicación
        public UsuarioRolResponseDTO obtenerRolUsuario(String usuarioRed, Long apliId) {

                return gestionUsuariosRepository.obtenerRolUsuario(usuarioRed, apliId);
        }

        // Método para crear un nuevo usuario en el sistema
        public void crearUsuario(CrearUsuarioRequestDTO request, String usuarioCreacion) {

                gestionUsuariosRepository.crearUsuario(
                                request.getUsuarioRed(),
                                request.getNombre(),
                                request.getCorreo(),
                                request.getNumeroIdentificacion(),
                                request.getSuperAdministrador(),
                                usuarioCreacion);
        }

        // Método para modificar la información de un usuario existente en el sistema
        public void modificarUsuario(ModificarUsuarioRequestDTO request, String usuarioModificacion) {

                gestionUsuariosRepository.modificarUsuario(
                                request.getUsuarioRed(),
                                request.getNombre(),
                                request.getCorreo(),
                                request.getNumeroIdentificacion(),
                                request.getSuperAdministrador(),
                                usuarioModificacion);
        }

        // Método asignar rol a usuario
        public void asignarRolUsuario(Long apliId, GestionarRolUsuarioRequestDTO request, String usuarioModificacion) {
                gestionUsuariosRepository.gestionarRolUsuario(apliId, request,usuarioModificacion, 0);
        }

        // Método actualizar vigencia de un rol a usuario
        public void actualizarVigenciaRolUsuario(Long apliId, GestionarRolUsuarioRequestDTO request, String usuarioModificacion) {
                gestionUsuariosRepository.gestionarRolUsuario(apliId, request,usuarioModificacion, 2);
        }

        // Método retirar rol a un usuario
        public void retirarRolUsuario(Long apliId, GestionarRolUsuarioRequestDTO request, String usuarioModificacion) {
                gestionUsuariosRepository.gestionarRolUsuario(apliId, request,usuarioModificacion, 1);
        }

        public void retirarRolesUsuarios(Long apliId, GestionarRolesUsuariosRequestDTO request, String usuarioModificacion) {

                for (UsuarioRolRequestDTO usuario : request.getUsuariosRed()) {

                        GestionarRolUsuarioRequestDTO rolRequest = new GestionarRolUsuarioRequestDTO();

                        rolRequest.setUsuarioRed(usuario.getUsuarioRed());
                        rolRequest.setRolId(usuario.getRolId());

                        gestionUsuariosRepository.gestionarRolUsuario(apliId, rolRequest,usuarioModificacion, 1);
                }
        }

}
