package com.example.GameVault.repository;

import com.example.GameVault.models.Usuario;
import org.hibernate.internal.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);



}
