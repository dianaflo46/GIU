package com.giu.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.giu.model.GestionAplicaciones.AdministradorAplicacionResponseDTO;
import com.giu.model.GestionAplicaciones.AplicacionResponseDTO;
import com.giu.model.GestionAplicaciones.CrearAplicacionRequest;
import com.giu.model.GestionAplicaciones.GestionarAdministradorRequest;
import com.giu.model.GestionAplicaciones.ModificarAplicacionRequest;
import com.giu.repository.GestionAplicacionesRepository;

@Service
public class GestionAplicacionesService {

    // Se inyecta el repositorio de gestión de aplicaciones en el servicio
    private final GestionAplicacionesRepository gestionAplicacionesRepository;

    // Constructor para inyectar el repositorio de gestión de aplicaciones
    public GestionAplicacionesService(GestionAplicacionesRepository gestionAplicacionesRepository) {
        this.gestionAplicacionesRepository = gestionAplicacionesRepository;
    }

    // Método para obtener aplicación según el codigo y el estado
    public List<AplicacionResponseDTO> obtenerAplicacion(String codigo, String estado) {
        return gestionAplicacionesRepository.obtenerAplicacion(codigo, estado);
    }

    // Método obtener los administradores de las aplicaciones
    public List<AdministradorAplicacionResponseDTO> obtenerAdministradorAplicacion(String usuarioRed, Long apliId) {
        return gestionAplicacionesRepository.obtenerAdministradorAplicacion(usuarioRed, apliId);
    }

    // Método crear aplicación
    public void crearAplicacion(CrearAplicacionRequest request) {

        gestionAplicacionesRepository.crearAplicacion(
                request.getCodigo(),
                request.getNombre(),
                request.getDescripcion(),
                request.getAdministracion(),
                request.getUsuarioCreacion());
    }

    // Método modificar aplicación
    public void modificarAplicacion(ModificarAplicacionRequest request) {

        gestionAplicacionesRepository.modificarAplicacion(
                request.getNombre(),
                request.getCodigo(),
                request.getDescripcion(),
                request.getEstado(),
                request.getAdministracion(),
                request.getUsuarioModificacion());
    }

    // Método para gestionar administradores de aplicaciones
    public void gestionarAdministrador(GestionarAdministradorRequest request) {

        Timestamp fechaIn = request.getFechaIn() != null
                ? Timestamp.valueOf(request.getFechaIn())
                : null;

        Timestamp fechaFin = request.getFechaFin() != null
                ? Timestamp.valueOf(request.getFechaFin())
                : null;

        gestionAplicacionesRepository.gestionarAdministrador(
                request.getUsuarioRed(),
                request.getApliId(),
                request.getOperacion(),
                fechaIn,
                fechaFin,
                request.getUsuarioModificacion());
    }
}
