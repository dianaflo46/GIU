package com.giu.model.GestionUsuarios;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class GestionarRolUsuarioRequestDTO {

    @NotNull(message = "El id de rol es obligatorio")
    private Long rolId;

    private String usuarioRed;
    private LocalDateTime fechaIn;
    private LocalDateTime fechaFin;

}