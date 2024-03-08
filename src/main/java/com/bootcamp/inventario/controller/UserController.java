package com.bootcamp.inventario.controller;

import com.bootcamp.inventario.model.Usuario;
import com.bootcamp.inventario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


@Controller
public class UserController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/newUser")
    public String newUser(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes redirectAttributes) {
        usuarioService.addUsuario(usuario);
        redirectAttributes.addFlashAttribute("insertSuccess", true);
        return "redirect:/";
    }

    @GetMapping("editUser/{id}")
    public String editUser(@PathVariable("id") int id, Model model){
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);

        if(usuario.isPresent()){
            model.addAttribute("usuario", usuario);
            return "editFormUser";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/editUser")
    public String editUser(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes redirectAttributes) {
        usuarioService.addUsuario(usuario);
        redirectAttributes.addFlashAttribute("editSuccess", true);
        return "redirect:/";
    }


    @PostMapping("/searchUser")
    public String searchUser(@RequestParam(name = "id") int id, RedirectAttributes redirectAttributes,
                                Model model) {
        Optional<Usuario> foundUser = usuarioService.getUsuarioById(id);
        model.addAttribute("usuario", new Usuario());
        if (foundUser.isPresent()) {
            redirectAttributes.addFlashAttribute("foundUser", foundUser);
        } else {
            redirectAttributes.addFlashAttribute("notFound", true);
        }

        return "redirect:/";
    }

    @PostMapping("/deleteUsuario")
    public String deleteUsuario(@RequestParam(name = "id") int id, RedirectAttributes redirectAttributes) {
        boolean deleteSuccess = usuarioService.deleteUsuarioById(id);
        if (deleteSuccess) {
            redirectAttributes.addFlashAttribute("deleteSuccess", true);
        } else {
            redirectAttributes.addFlashAttribute("notFound", true);
        }

        return "redirect:/";
    }

}


