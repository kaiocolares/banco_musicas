package br.com.alura.musicas.banco_musicas.principal;

import br.com.alura.musicas.banco_musicas.model.Artista;
import br.com.alura.musicas.banco_musicas.model.Musicas;
import br.com.alura.musicas.banco_musicas.model.Tipo;
import br.com.alura.musicas.banco_musicas.repository.MusicRepository;

import java.util.*;

public class Principal {
    Scanner scanner = new Scanner(System.in);
    private MusicRepository repository;
    private List<Artista> artistas = new ArrayList<>();

    public Principal (MusicRepository repository) {
        this.repository = repository;
    }

    public void exibeMenu() {
        var opcao = -1;

        while (opcao != 0) {
            System.out.println("""
                *** Screen Sound Músicas ***
                1 - Cadastrar artistas
                2 - Cadastrar músicas
                3 - Listar músicas
                4 - Buscar músicas por artista
                5 - Pesquisar sobre um artista
                
                0 - Sair""");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusica();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    listarPorArtista();
                    break;
                case 5:
                    pesquisarSobre();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida !");
                    break;
            }
        }
    }

    public void cadastrarArtista() {
        var opcao = 1;
        while (opcao ==1) {
            System.out.println("Digite o nome do artista para cadastro: ");
            var nome = scanner.nextLine();
            Optional<Artista> artistaExistente = repository.findByNome(nome);
            if (artistaExistente.isPresent()){
                System.out.println("Artista ja cadastrado  no sistema");
            } else {
                System.out.println("Digite o tipo (solo, dupla ou banda): ");
                var tipoStr = scanner.nextLine().toUpperCase();
                Tipo tipo = Tipo.valueOf(tipoStr);
                Artista artista = new Artista();
                artista.setNome(nome);
                artista.setTipo(tipo);
                repository.save(artista);
                System.out.println("Artista cadastrado com sucesso");
            }
            System.out.println("Deseja cadastrar um novo Artista ? \n1 - sim\n2 - não");
            opcao = scanner.nextInt();
            scanner.nextLine();
        }
    }

    public void cadastrarMusica() {
        System.out.println("Quer adicionar música de qual cantor ?");
        listarArtistas();
        var artistaNome = scanner.nextLine().toLowerCase();
        Optional<Artista> artistaSelecionado = repository.findFirstByNomeContainingIgnoreCase(artistaNome);
        if (artistaSelecionado.isPresent()) {
            Artista artista = artistaSelecionado.get();
            System.out.println("Digite o nome da música: ");
            var nomeMusica = scanner.nextLine();
            Musicas musica = new Musicas(nomeMusica);
            musica.setArtista(artista);
            artista.getMusicas().add(musica);
            repository.save(artista);
        }
        else {
            System.out.println("Artista não encontrado!");
        }

    }

    public void listarMusicas() {

        artistas = repository.findAll();
        artistas.stream()
                .flatMap(a -> a.getMusicas().stream())
                .map(Musicas::getNome)
                .forEach(System.out::println);
    }

    public void listarPorArtista() {

        System.out.println("Escolha um artista: ");
        listarArtistas();
        var artistaNome = scanner.nextLine();
        Optional<Artista> artistaSelecionado = repository.findFirstByNomeContainingIgnoreCase(artistaNome);
        if (artistaSelecionado.isPresent()) {
            artistaSelecionado.get().getMusicas().stream().map(Musicas::getNome).forEach(System.out::println);
        }
        else {
            System.out.println("Artista não encontrado!");
        }

    }

    public void pesquisarSobre() {

    }

    public void listarArtistas() {
        artistas = repository.findAll();
        artistas.stream()
                .sorted(Comparator.comparing(Artista::getNome)).forEach(a -> System.out.println(a.getNome()));
    }

}


