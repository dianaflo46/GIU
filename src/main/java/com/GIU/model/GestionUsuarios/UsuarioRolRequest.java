package com.giu.model.GestionUsuarios;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UsuarioRolRequest {
    @NotBlank
    private String usuarioRed;

    @NotNull
    private Long rolId;
}
