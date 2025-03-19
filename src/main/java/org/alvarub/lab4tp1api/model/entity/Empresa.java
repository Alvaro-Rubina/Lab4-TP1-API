package org.alvarub.lab4tp1api.model.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, nullable = false)
    private String denominacion;

    @Column(length = 50)
    private String telefono;

    @Column(length = 256)
    private String horarioAtencion;

    @Column(length = 1024)
    private String quienesSomos;

    private Double latitud;
    private Double longitud;

    @Column(length = 256)
    private String domicilio;

    @Column(length = 75)
    private String email;

}
