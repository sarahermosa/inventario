package com.bootcamp.inventario.service;

import com.bootcamp.inventario.model.Usuario;
import com.bootcamp.inventario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository UsuarioRepository;

    public List<Usuario> getAllUsuarios() {
        return UsuarioRepository.findAll();
    }

    public void addUsuario(Usuario Usuario) {
        UsuarioRepository.save(Usuario);
    }

    public Optional<Usuario> getUsuarioById(int id) {
        return UsuarioRepository.findById(id);
    }

    public boolean deleteUsuarioById(int id) {
        if (UsuarioRepository.existsById(id)) {
            UsuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
