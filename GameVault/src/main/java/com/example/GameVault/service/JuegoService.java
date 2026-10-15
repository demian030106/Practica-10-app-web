package com.example.GameVault.service;

import com.example.GameVault.models.Juego;
import com.example.GameVault.repository.JuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class JuegoService {

    @Autowired
    JuegoRepository juegoRepository;

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    public List<Juego> listarTodos(){
        return juegoRepository.findAll();
    }

    public List<Juego> buscarPorTitulo(String palabra) {
        if (palabra == null || palabra.trim().isEmpty()) {
            return listarTodos();
        }
        return juegoRepository.findByTituloContainingIgnoreCase(palabra);
    }

    public void guardarJuego(Juego juego,
                             MultipartFile portada){
        String nombreArchivo = "default.png";

        if(!portada.isEmpty()){
            nombreArchivo = guardarImagenLocal(portada);

        }

        juego.setPortadaUrl(nombreArchivo);
        juegoRepository.save(juego);

    }

    public String guardarImagenLocal(MultipartFile portada){
        try {

            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String nombreArchivo = UUID.randomUUID() + "_" + portada.getOriginalFilename();
            Path filePath = uploadPath.resolve(nombreArchivo);

            Files.copy(portada.getInputStream(), filePath);
            return nombreArchivo;
        } catch (IOException e) {
            e.printStackTrace();
            return "default.png";

        }
    }
}
