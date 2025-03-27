package org.alvarub.lab4tp1api.service;

import org.alvarub.lab4tp1api.config.exception.NotFoundException;
import org.alvarub.lab4tp1api.model.dto.NoticiaDTO;
import org.alvarub.lab4tp1api.model.entity.Empresa;
import org.alvarub.lab4tp1api.model.entity.Noticia;
import org.alvarub.lab4tp1api.repository.EmpresaRepository;
import org.alvarub.lab4tp1api.repository.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

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
                () -> new NotFoundException("Noticia con el id '" + id + "' no encontrada"));

        return toDTO(noticia);
    }

    public void deleteNoticiaById(Long id) {
        if (!noticiaRepo.existsById(id)) {
            throw new NotFoundException("Noticia con el id '" + id + "' no encontrada");
        }
        noticiaRepo.deleteById(id);
    }

    public void editNoticia(Long id, NoticiaDTO noticiaDTO) {
        if (!noticiaRepo.existsById(id)) {
            throw new NotFoundException("Noticia con id '" + id + "' no encontrada");
        }

        Noticia noticia = toEntity(noticiaDTO);
        noticia.setId(id);
        noticiaRepo.save(noticia);
    }

    // Mappers
    public NoticiaDTO toDTO(Noticia noticia) {

        return NoticiaDTO.builder()
                .id(noticia.getId())
                .titulo(noticia.getTitulo())
                .resumen(noticia.getResumen())
                .imagen(noticia.getImagen())
                .contenidoHTML(noticia.getContenidoHtml())
                .publicada(noticia.isPublicada())
                .fechaPublicacion(noticia.getFechaPublicacion())
                .idEmpresa(noticia.getEmpresa().getId())
                .build();
    }

    public Noticia toEntity(NoticiaDTO noticiaDTO) {

        Empresa empresa = empresaRepo.findById(noticiaDTO.getIdEmpresa())
                .orElseThrow(() -> new NotFoundException("Empresa con el id '" + noticiaDTO.getIdEmpresa() + "' no encontrada"));
        LocalDate fechaPubicacion = LocalDate.now();

        return Noticia.builder()
                .titulo(noticiaDTO.getTitulo())
                .resumen(noticiaDTO.getResumen())
                .imagen(noticiaDTO.getImagen())
                .contenidoHtml(noticiaDTO.getContenidoHTML())
                .publicada(noticiaDTO.isPublicada())
                .fechaPublicacion(fechaPubicacion)
                .empresa(empresa)
                .build();
    }
}
