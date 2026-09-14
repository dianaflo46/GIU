package com.giu.service;

import org.springframework.stereotype.Service;

import com.giu.model.GestionarEstadoUsuarioRequest;
import com.giu.repository.GestionSeguridadRepository;

@Service
public class GestionSeguridadService {

    private final GestionUsuariosService gestionUsuariosService;
    // Se inyecta el repositorio de gestión de seguridad en el servicio
    private final GestionSeguridadRepository gestionSeguridadRepository;

    // Constructor para inyectar el repositorio de gestión de seguridad
    public GestionSeguridadService(GestionSeguridadRepository gestionSeguridadRepository, GestionUsuariosService gestionUsuariosService) {
        this.gestionSeguridadRepository = gestionSeguridadRepository;
        this.gestionUsuariosService = gestionUsuariosService;
    }


    // Método para gestionar el estado de un usuario en el sistema
    public void gestionarEstadoUsuario(
            GestionarEstadoUsuarioRequest request) {

        gestionSeguridadRepository.gestionarEstadoUsuario(
                request);
        if(request.getOperacion() == 0 && request.getRolId() != null) {
            // Si la operación es 0 (desactivar) y se proporciona un rol, se reactiva el rol del usuario
            
        }
    }
}
