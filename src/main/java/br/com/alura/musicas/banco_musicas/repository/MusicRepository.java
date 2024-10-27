package br.com.alura.musicas.banco_musicas.repository;

import br.com.alura.musicas.banco_musicas.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MusicRepository extends JpaRepository<Artista, Long> {
    Optional<Artista> findByNome(String nome);

    Optional<Artista> findFirstByNomeContainingIgnoreCase(String artistaNome);
}
