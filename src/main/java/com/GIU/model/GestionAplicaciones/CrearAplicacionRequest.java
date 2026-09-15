package com.giu.model.GestionAplicaciones;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CrearAplicacionRequest {

    @NotBlank(message = "El código es obligatorio")
    @Size(max = 50, message = "El código no puede superar los 50 caracteres")
    private String codigo;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombre;

    @Size(max = 200, message = "La descripción no puede superar los 200 caracteres")
    private String descripcion;

    @NotBlank(message = "La administración es obligatoria")
    private String administracion;

    @NotBlank(message = "El usuario de creación es obligatorio")
    private String usuarioCreacion;
}
