package org.alvarub.lab4tp1api.service;

import org.alvarub.lab4tp1api.model.dto.EmpresaDTO;
import org.alvarub.lab4tp1api.config.exception.NotFoundException;
import org.alvarub.lab4tp1api.model.entity.Empresa;
import org.alvarub.lab4tp1api.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public void agregarEmpresa(EmpresaDTO empresaDTO) {
        Empresa empresa = toEntity(empresaDTO);
        empresaRepository.save(empresa);


    }

    public void actualizarEmpresa(Long id,EmpresaDTO empresaDTO) {
        if(!empresaRepository.existsById(id)){
            throw new NotFoundException("Empresa con id " + id + " no encontrado.");
        }
        Empresa empresa = toEntity(empresaDTO);
        empresa.setId(id);
        empresaRepository.save(empresa);



    }

    public void eliminarEmpresa(Long id) {
        if(!empresaRepository.existsById(id)){
            throw new NotFoundException("Empresa con id " + id + "no encontrada");

        }
        empresaRepository.deleteById(id);

    }

    public List<EmpresaDTO> getEmpresas() {
        List<Empresa> empresas = empresaRepository.findAll();
        List<EmpresaDTO> empresaDTOS = new ArrayList<>();

        for(Empresa empresa : empresas){
            empresaDTOS.add(toDTO(empresa));
        }
        return empresaDTOS;

    }

    public EmpresaDTO getEmpresa(Long id) {
Empresa empresa = empresaRepository.findById(id).orElseThrow(
        ()-> new NotFoundException("Empresa con el id " + id + " no encontrada")
        );
return toDTO(empresa);

    }

    public EmpresaDTO toDTO(Empresa empresa) {
        EmpresaDTO empresaDTO = new EmpresaDTO();

        return EmpresaDTO.builder()
                .id(empresa.getId())
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
    public Empresa toEntity(EmpresaDTO empresaDTO){
        return Empresa.builder()
                .denominacion(empresaDTO.getDenominacion())
                .telefono(empresaDTO.getTelefono())
                .horarioAtencion(empresaDTO.getHorarioDeAtencion())
                .quienesSomos(empresaDTO.getQuienesSomos())
                .latitud(empresaDTO.getLatitud())
                .longitud(empresaDTO.getLongitud())
                .domicilio(empresaDTO.getDomicilio())
                .email(empresaDTO.getEmail())
                .build();
    }

}
