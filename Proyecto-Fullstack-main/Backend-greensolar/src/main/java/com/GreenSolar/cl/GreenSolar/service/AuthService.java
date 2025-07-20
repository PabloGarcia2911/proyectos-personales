package com.GreenSolar.cl.GreenSolar.service;

import com.GreenSolar.cl.GreenSolar.config.JwtUtil;
import com.GreenSolar.cl.GreenSolar.model.Usuario;
import com.GreenSolar.cl.GreenSolar.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!usuario.getPassword().equals(password)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return jwtUtil.generateToken(usuario.getUsername(), usuario.getRol());
    }
}
