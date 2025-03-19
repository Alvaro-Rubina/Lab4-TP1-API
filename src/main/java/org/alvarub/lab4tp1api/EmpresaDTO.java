package org.alvarub.lab4tp1api;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmpresaDTO {

    private String denominacion;
    private String telefono;
    private String horarioDeAtencion;
    private String quienesSomos;
    private Double latitud;
    private Double longitud;
    private String domicilio;
    private String email;
}
