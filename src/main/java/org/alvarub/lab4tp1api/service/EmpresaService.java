package org.alvarub.lab4tp1api.service;

import org.alvarub.lab4tp1api.model.dto.EmpresaDTO;
import org.alvarub.lab4tp1api.model.entity.Empresa;
import org.alvarub.lab4tp1api.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public void agregarEmpresa(EmpresaDTO empresa) {


    }

    public void actualizarEmpresa(EmpresaDTO empresa) {


    }

    public void eliminarEmpresa(EmpresaDTO empresa) {


    }

    public List<EmpresaDTO> getEmpresas() {



    }

    public EmpresaDTO getEmpresa(Long id) {

    }

    public EmpresaDTO toDTO(Empresa empresa) {
        EmpresaDTO empresaDTO = new EmpresaDTO();

        return EmpresaDTO.builder()
                .denominacion(empresa.getDenominacion())
                .telefono(empresa.getTelefono())
                .horarioDeAtencion(empresa.getHorarioAtencion())
                .quienesSomos(empresa.getQuienesSomos())
                .latitud(empresa.getLatitud())
                .longitud(empresa.getLongitud())
                .domicilio(empresa.getDomicilio())
                .email(empresa.getEmail())
                .build();
    }

}
