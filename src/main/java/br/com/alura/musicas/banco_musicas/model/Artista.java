package br.com.alura.musicas.banco_musicas.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musicas> musicas;

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

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public List<Musicas> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musicas> musicas) {
        this.musicas = musicas;
    }
}
