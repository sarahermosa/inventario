package com.bootcamp.inventario.service;

import com.bootcamp.inventario.model.Computer;
import com.bootcamp.inventario.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComputerService {

    @Autowired
    private ComputerRepository computerRepository;

    public List<Computer> getAllComputers() {
        return computerRepository.findAll();
    }

    public void addComputer(Computer computer) {
        computerRepository.save(computer);
    }

    public Optional<Computer> getComputerById(Long id) {
        return computerRepository.findById(id);
    }

    public boolean deleteComputerById(Long id) {
        if (computerRepository.existsById(id)) {
            computerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
