package org.alvarub.lab4tp1api.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
@Entity
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, nullable = false)
    private String titulo;

    @Column(length = 1024)
    private String resumen;

    @Column(length = 128)
    private String imagen;

    @Column(length = 20480)
    private String contenidoHtml;

    private boolean publicada;

    @Temporal(TemporalType.DATE)
    private LocalDate fechaPublicacion;

    @ManyToOne
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Empresa empresa;
}
