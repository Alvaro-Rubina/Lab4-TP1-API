package org.alvarub.lab4tp1api.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128, nullable = false)
    private String titulo;

    @Column(length = 1024)
    private String resumen;

    @Column(length = 128)
    private String imagen;

    @Column(length = 20480)
    private String contenidoHtml;

    @Column(length = 1)
    private char publicada;

    @Temporal(TemporalType.DATE)
    private LocalDate fechaPublicacion;

    @ManyToOne
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;
}
