package org.alvarub.lab4tp1api.controller;

import org.alvarub.lab4tp1api.model.dto.EmpresaDTO;
import org.alvarub.lab4tp1api.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("/api/empresas")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;
    @PostMapping
    public ResponseEntity<String> agregarEmpresa(@RequestBody EmpresaDTO empresaDTO){
        empresaService.agregarEmpresa(empresaDTO);
        return ResponseEntity.ok("Empresa registrada con exito");
    }
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<EmpresaDTO> getEmpresaById(@PathVariable Long id){
        EmpresaDTO empresaDTO = empresaService.getEmpresa(id);
        return ResponseEntity.ok(empresaDTO);
    }

    @GetMapping
    @ResponseBody
    public List<EmpresaDTO> getEmpresas(){ return empresaService.getEmpresas();

    }
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarEmpresa(@PathVariable Long id,
                                                    @RequestBody EmpresaDTO empresaDTO){
        empresaService.actualizarEmpresa(id,empresaDTO);
        return ResponseEntity.ok("Empresaeditada con exito)");
    }

    @DeleteMapping
    public ResponseEntity<String> eliminarEmpresa(@PathVariable Long id){
        empresaService.eliminarEmpresa(id);
        return ResponseEntity.ok("Empresa eliminada con exito");
    }


}

