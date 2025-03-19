package org.alvarub.lab4tp1api.model.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class NoticiaDTO {

    private String titulo;
    private String resumen;
    private String imagen;
    private String contenidoHTML;
    private boolean publicada;
    private LocalDate fechaPublicacion;
    private Long idEmpresa;
}
