package com.giu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.giu.model.GestionarRolUsuarioRequest;
import com.giu.model.UsuarioAplicacionDTO;
import com.giu.model.UsuarioRequestDTO;
import com.giu.model.UsuarioRolResponseDTO;
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
        public List<UsuarioAplicacionDTO> obtenerUsuariosPorAplicacion(Long apliId, String estado) {

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
                                request.getUsuarioCreacion());
        }

        // Método para modificar la información de un usuario existente en el sistema
        public void modificarUsuario(UsuarioRequestDTO request) {

                gestionUsuariosRepository.modificarUsuario(
                                request.getUsuarioRed(),
                                request.getNombre(),
                                request.getCorreo(),
                                request.getNumeroIdentificacion(),
                                request.getUsuarioModificacion());
        }

        // Método para gestionar el rol de un usuario en una aplicación específica
        public void gestionarRolUsuario(Long apliId, GestionarRolUsuarioRequest request) {

                gestionUsuariosRepository.gestionarRolUsuario(apliId, request);
        }


}
