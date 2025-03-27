package org.alvarub.lab4tp1api.controller;

import org.alvarub.lab4tp1api.model.dto.NoticiaDTO;
import org.alvarub.lab4tp1api.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/noticias")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;

    @PostMapping
    public ResponseEntity<String> saveNoticia(@RequestBody NoticiaDTO noticiaDTO) {
        noticiaService.saveNoticia(noticiaDTO);
        return ResponseEntity.ok("Noticia registrada con exito");
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<NoticiaDTO> getNoticia(@PathVariable Long id) {
        NoticiaDTO noticiaDTO = noticiaService.getNoticiaById(id);
        return ResponseEntity.ok(noticiaDTO);
    }
    
    @GetMapping
    @ResponseBody
    public List<NoticiaDTO> getNoticias(@RequestParam(required = false) Long idEmpresa) {
        if (idEmpresa != null) {
            return noticiaService.getNoticiasByEmpresa(idEmpresa);
        }
        return noticiaService.getAllNoticias();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editNoticia(@PathVariable Long id,
                                              @RequestBody NoticiaDTO noticiaDTO) {
        noticiaService.editNoticia(id, noticiaDTO);
        return ResponseEntity.ok("Noticia editada con exito");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNoticia(@PathVariable Long id) {
        noticiaService.deleteNoticiaById(id);
        return ResponseEntity.ok("Noticia eliminada con exito");
    }

}
