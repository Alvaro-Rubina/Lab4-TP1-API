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
import java.util.stream.Collectors;

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
        return noticias.stream()
                .sorted((n1, n2) -> n2.getFechaPublicacion().compareTo(n1.getFechaPublicacion()))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public NoticiaDTO getNoticiaById(Long id) {
        Noticia noticia = noticiaRepo.findById(id).orElseThrow(
                () -> new NotFoundException("Noticia con el id '" + id + "' no encontrada"));

        return toDTO(noticia);
    }

    public List<NoticiaDTO> getNoticiasByEmpresa(Long idEmpresa) {
        List<Noticia> noticias = noticiaRepo.findByEmpresaId(idEmpresa);
        return noticias.stream()
                .filter(Noticia::isPublicada)
                .sorted((n1, n2) -> n2.getFechaPublicacion().compareTo(n1.getFechaPublicacion()))
                .map(this::toDTO)
                .collect(Collectors.toList());
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

    // Método para buscar noticias por texto
    public List<NoticiaDTO> getNoticiasByTexto(String texto) {
        List<Noticia> noticias = noticiaRepo.findByTexto(texto);
        return noticias.stream()
                .map(this::toDTO) // Convierte cada entidad Noticia a NoticiaDTO
                .collect(Collectors.toList());
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

        LocalDate fechaPublicacion = (noticiaDTO.getFechaPublicacion() == null) ? LocalDate.now() : noticiaDTO.getFechaPublicacion();

        return Noticia.builder()
                .titulo(noticiaDTO.getTitulo())
                .resumen(noticiaDTO.getResumen())
                .imagen(noticiaDTO.getImagen())
                .contenidoHtml(noticiaDTO.getContenidoHTML())
                .publicada(noticiaDTO.isPublicada())
                .fechaPublicacion(fechaPublicacion)
                .empresa(empresa)
                .build();
    }
}
