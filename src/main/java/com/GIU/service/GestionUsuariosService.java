package com.giu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.giu.model.GestionUsuarios.GestionarRolUsuarioRequest;
import com.giu.model.GestionUsuarios.GestionarRolesUsuariosRequest;
import com.giu.model.GestionUsuarios.UsuarioAplicacionResponseDTO;
import com.giu.model.GestionUsuarios.UsuarioRequestDTO;
import com.giu.model.GestionUsuarios.UsuarioRolRequest;
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
        public List<UsuarioRequestDTO> obtenerUsuarios(String usuarioRed, String estado) {

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
        public void crearUsuario(UsuarioRequestDTO request) {

                gestionUsuariosRepository.crearUsuario(
                                request.getUsuarioRed(),
                                request.getNombre(),
                                request.getCorreo(),
                                request.getNumeroIdentificacion(),
                                request.getSuperAdministrador(),
                                request.getUsuarioCreacion());
        }

        // Método para modificar la información de un usuario existente en el sistema
        public void modificarUsuario(UsuarioRequestDTO request) {

                gestionUsuariosRepository.modificarUsuario(
                                request.getUsuarioRed(),
                                request.getNombre(),
                                request.getCorreo(),
                                request.getNumeroIdentificacion(),
                                request.getSuperAdministrador(),
                                request.getUsuarioModificacion());
        }

        // Método asignar rol a usuario
        public void asignarRolUsuario(Long apliId, GestionarRolUsuarioRequest request) {

                gestionUsuariosRepository.gestionarRolUsuario(apliId, request, 0);
        }

        // Método actualizar vigencia de un rol a usuario
        public void actualizarVigenciaRolUsuario(Long apliId, GestionarRolUsuarioRequest request) {

                gestionUsuariosRepository.gestionarRolUsuario(apliId, request, 2);
        }

        // Método retirar rol a un usuario
        public void retirarRolUsuario(Long apliId, GestionarRolUsuarioRequest request) {

                gestionUsuariosRepository.gestionarRolUsuario(apliId, request, 1);
        }

        public void retirarRolesUsuarios(Long apliId, GestionarRolesUsuariosRequest request) {

                for (UsuarioRolRequest usuario : request.getUsuariosRed()) {

                        GestionarRolUsuarioRequest rolRequest = new GestionarRolUsuarioRequest();

                        rolRequest.setUsuarioRed(usuario.getUsuarioRed());
                        rolRequest.setRolId(usuario.getRolId());
                        rolRequest.setUsuarioModificacion(request.getUsuarioModificacion());

                        gestionUsuariosRepository.gestionarRolUsuario(apliId, rolRequest, 1);
                }
        }

}
