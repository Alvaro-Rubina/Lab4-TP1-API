package org.alvarub.lab4tp1api.repository;

import org.alvarub.lab4tp1api.model.entity.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {

    List<Noticia> findByEmpresaId(Long idEmpresa);
}
