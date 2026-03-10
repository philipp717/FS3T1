package com.example.fs3t1.controller;

import com.example.fs3t1.model.Usuario;
import com.example.fs3t1.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String loginPage(HttpSession session) {
        if (session.getAttribute("usuarioId") != null) return "redirect:/";
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attrs) {
        return usuarioService.login(email, password).map(u -> {
            session.setAttribute("usuarioId", u.getId());
            session.setAttribute("usuarioNombre", u.getNombre());
            session.setAttribute("usuarioRol", u.getRol());
            if ("ADMIN".equals(u.getRol())) return "redirect:/admin";
            return "redirect:/";
        }).orElseGet(() -> {
            attrs.addFlashAttribute("error", "Email o contraseña incorrectos");
            return "redirect:/login";
        });
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/registro")
    public String registroPage(HttpSession session) {
        if (session.getAttribute("usuarioId") != null) return "redirect:/";
        return "registro";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre,
                           @RequestParam String email,
                           @RequestParam String password,
                           HttpSession session,
                           RedirectAttributes attrs) {
        if (usuarioService.findByEmail(email).isPresent()) {
            attrs.addFlashAttribute("error", "El email ya está registrado");
            return "redirect:/registro";
        }
        Usuario nuevo = new Usuario(null, nombre, email, password, "USUARIO");
        Usuario guardado = usuarioService.crear(nuevo);
        session.setAttribute("usuarioId", guardado.getId());
        session.setAttribute("usuarioNombre", guardado.getNombre());
        session.setAttribute("usuarioRol", guardado.getRol());
        return "redirect:/";
    }
}
