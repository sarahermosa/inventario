package com.bootcamp.inventario.controller;

import com.bootcamp.inventario.model.Computer;
import com.bootcamp.inventario.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class ComputerRestController {

    @Autowired
    private ComputerService computerService;

    //Get all computers list
    @GetMapping("/computers")
    public List<Computer> getComputers() {
        return computerService.getAllComputers();
    }

    //Post for create a new computer
    @PostMapping("/newComputer")
    public void newComputer(@RequestBody Computer newComputer) {
        computerService.addComputer(newComputer);
    }

    //Get only one computer

    @GetMapping("/computers/{id}")
    public Optional<Computer> getComputer(@PathVariable Long id) {
        return computerService.getComputerById(id);
    }

    //Edit computer
    @PutMapping("editComputer/{id}")
    public Computer editComputer(@RequestBody Computer newComputer, @PathVariable("id") Long id){
        return computerService.getComputerById(id)
                .map(computer -> {
                    computer.setTag(newComputer.getTag());
                    computer.setMarca(newComputer.getMarca());
                    computer.setModel(newComputer.getModel());
                    computer.setUsuario(newComputer.getUsuario());
                    computer.setOs(newComputer.getOs());
                    computer.setRam(newComputer.getRam());
                    computer.setDisco(newComputer.getDisco());
                    computer.setCpu(newComputer.getCpu());
                    computer.setFecha_compra(newComputer.getFecha_compra());
                    computerService.addComputer(computer);
                    return computer;
                })
                .orElseGet(() -> {
                    newComputer.setIdComputer(id);
                    computerService.addComputer(newComputer);
                    return newComputer;
                });
    }

    //Delete computer by id
    @DeleteMapping("/deleteComputer/{id}")
    public String deleteComputer(@PathVariable Long id) {
        boolean deleteSuccess = computerService.deleteComputerById(id);
        if (deleteSuccess) {
            return "Successfully removed";
        } else {
            return "There is no computer to delete";
        }

    }
}


