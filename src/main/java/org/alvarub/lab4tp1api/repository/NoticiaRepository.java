package org.alvarub.lab4tp1api.repository;

import org.alvarub.lab4tp1api.model.entity.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {

    List<Noticia> findByEmpresaId(Long idEmpresa);

    @Query("SELECT n FROM Noticia n WHERE " +
            "LOWER(n.titulo) LIKE LOWER(CONCAT('%', :texto, '%')) " +
            "OR LOWER(n.resumen) LIKE LOWER(CONCAT('%', :texto, '%')) " +
            "ORDER BY n.fechaPublicacion DESC")
    List<Noticia> findByTexto(@Param("texto") String texto);
}
