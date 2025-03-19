package org.alvarub.lab4tp1api;

import org.alvarub.lab4tp1api.model.entity.Empresa;
import org.alvarub.lab4tp1api.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping("/agregar")
    public ResponseEntity agregarEmpresa(EmpresaDTO empresaDTO) {

    }

    @GetMapping("/{id}")
    public ResponseEntity buscarEmpresa(@RequestBody @PathVariable Long id) {

    }











}
