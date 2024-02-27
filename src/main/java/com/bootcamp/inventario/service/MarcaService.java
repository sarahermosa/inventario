package com.bootcamp.inventario.service;

import com.bootcamp.inventario.model.Marca;
import com.bootcamp.inventario.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository MarcaRepository;

    public List<Marca> getAllMarcas() {
        return MarcaRepository.findAll();
    }

    public void addMarca(Marca Marca) {
        MarcaRepository.save(Marca);
    }

    public Optional<Marca> getMarcaById(int id) {
        return MarcaRepository.findById(id);
    }

    public boolean deleteMarcaById(int id) {
        if (MarcaRepository.existsById(id)) {
            MarcaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
