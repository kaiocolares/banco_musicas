package br.com.alura.musicas.banco_musicas.model;

import jakarta.persistence.*;

@Entity
public class Musicas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    @ManyToOne
    private Artista artista;

    public Musicas() {}

    public Musicas(String nomeMusica) {
        this.nome = nomeMusica;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }
}
