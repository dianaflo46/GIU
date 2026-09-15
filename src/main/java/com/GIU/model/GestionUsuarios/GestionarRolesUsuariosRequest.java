package com.giu.model.GestionUsuarios;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data 
public class GestionarRolesUsuariosRequest {


    @NotEmpty
    private List<UsuarioRolRequest> usuariosRed;

    @NotBlank
    private String usuarioModificacion;
}
