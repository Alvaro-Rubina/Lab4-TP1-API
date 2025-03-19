package org.alvarub.lab4tp1api.service;

import org.alvarub.lab4tp1api.config.exception.NotFoundException;
import org.alvarub.lab4tp1api.model.dto.NoticiaDTO;
import org.alvarub.lab4tp1api.model.entity.Empresa;
import org.alvarub.lab4tp1api.model.entity.Noticia;
import org.alvarub.lab4tp1api.repository.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepo;

    @Autowired
    private EmpresaService empresaService;

    //
    public void saveNoticia(NoticiaDTO noticiaDTO) {
        Noticia noticia = toEntity(noticiaDTO);
        noticiaRepo.save(noticia);
    }

    public List<NoticiaDTO> getAllNoticias() {
        List<Noticia> noticias = noticiaRepo.findAll();
        List<NoticiaDTO> noticiasDTO = new ArrayList<>();

        for (Noticia noticia : noticias) {
            noticiasDTO.add(toDTO(noticia));
        }
        return noticiasDTO;
    }

    public NoticiaDTO getNoticiaById(Long id) {
        Noticia noticia = noticiaRepo.findById(id).orElseThrow(
                () -> new NotFoundException("Noticia con el id '" + id + "' no encontrado"));

        return toDTO(noticia);
    }

    // Mappers
    public NoticiaDTO toDTO(Noticia noticia) {

        return NoticiaDTO.builder()
                .titulo(noticia.getTitulo())
                .resumen(noticia.getResumen())
                .imagen(noticia.getImagen())
                .contenidoHTML(noticia.getContenidoHtml())
                .publicada(noticia.isPublicada())
                .fechaPublicacion(noticia.getFechaPublicacion())
                .build();
    }

    public Noticia toEntity(NoticiaDTO noticiaDTO) {

        return Noticia.builder()
                .titulo(noticiaDTO.getTitulo())
                .resumen(noticiaDTO.getResumen())
                .imagen(noticiaDTO.getImagen())
                .contenidoHtml(noticiaDTO.getContenidoHTML())
                .publicada(noticiaDTO.isPublicada())
                .fechaPublicacion(noticiaDTO.getFechaPublicacion())
                .build();
    }
}
