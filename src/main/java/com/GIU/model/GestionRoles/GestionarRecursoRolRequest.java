package com.giu.model.GestionRoles;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class GestionarRecursoRolRequest {

    @NotNull(message = "El recurso es obligatorio")
    private Long recuId;

    @NotNull(message = "El rol es obligatorio")
    private Long rolId;

    @NotNull(message = "La operación es obligatoria")
    @Min(value = 0, message = "La operación debe ser 0 o 1")
    @Max(value = 1, message = "La operación debe ser 0 o 1")
    private Integer operacion;

    @NotBlank(message = "El usuario de modificación es obligatorio")
    private String usuarioModificacion;
}
