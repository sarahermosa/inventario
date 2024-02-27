package com.bootcamp.inventario.controller;

import com.bootcamp.inventario.model.Computer;
import com.bootcamp.inventario.model.Marca;
import com.bootcamp.inventario.model.Usuario;
import com.bootcamp.inventario.model.OS;
import com.bootcamp.inventario.service.ComputerService;
import com.bootcamp.inventario.service.MarcaService;
import com.bootcamp.inventario.service.OSService;
import com.bootcamp.inventario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;


@Controller
public class ComputerController {

    @Autowired
    private ComputerService computerService;
    @Autowired
    private MarcaService marcaService;
    @Autowired
    private OSService osService;
    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/")
    public String getIndexPage(Model model) {
        List<Computer> computerList = computerService.getAllComputers();
        List<Marca> marcasList = marcaService.getAllMarcas();
        List<Usuario> usuariosList = usuarioService.getAllUsuarios();
        List<OS> ossList = osService.getAllOSs();
        model.addAttribute("computers", computerList);
        model.addAttribute("marcas", marcasList);
        model.addAttribute("usuarios", usuariosList);
        model.addAttribute("oss", ossList);
        model.addAttribute("computer", new Computer());
        return "index";
    }

    @PostMapping("/newComputer")
    public String newComputer(@ModelAttribute("computer") Computer computer, RedirectAttributes redirectAttributes) {
        computerService.addComputer(computer);
        redirectAttributes.addFlashAttribute("insertSuccess", true);
        return "redirect:/";
    }

    @PostMapping("/searchComputer")
    public String searchComputer(@RequestParam(name = "id") Long id, RedirectAttributes redirectAttributes,
                                Model model) {
        Optional<Computer> foundComputer = computerService.getComputerById(id);
        model.addAttribute("computer", new Computer());
        if (foundComputer.isPresent()) {
            redirectAttributes.addFlashAttribute("foundComputer", foundComputer);
        } else {
            redirectAttributes.addFlashAttribute("notFound", true);
        }

        return "redirect:/";
    }

    @PostMapping("/deleteComputer")
    public String deleteComputer(@RequestParam(name = "id") Long id, RedirectAttributes redirectAttributes) {
        boolean deleteSuccess = computerService.deleteComputerById(id);
        if (deleteSuccess) {
            redirectAttributes.addFlashAttribute("deleteSuccess", true);
        } else {
            redirectAttributes.addFlashAttribute("notFound", true);
        }

        return "redirect:/";
    }

    @GetMapping("/computerAnalysis")
    public String computerAnalysis(Model model) {
        List<Computer> computers = computerService.getAllComputers();
        model.addAttribute("computers", computers);
        return "computerAnalysis"; // Thymeleaf template name
    }


}


