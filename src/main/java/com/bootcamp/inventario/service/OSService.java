package com.bootcamp.inventario.service;

import com.bootcamp.inventario.model.OS;
import com.bootcamp.inventario.repository.OSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OSService {

    @Autowired
    private OSRepository OSRepository;

    public List<OS> getAllOSs() {
        return OSRepository.findAll();
    }

    public void addOS(OS OS) {
        OSRepository.save(OS);
    }

    public Optional<OS> getOSById(int id) {
        return OSRepository.findById(id);
    }

    public boolean deleteOSById(int id) {
        if (OSRepository.existsById(id)) {
            OSRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
