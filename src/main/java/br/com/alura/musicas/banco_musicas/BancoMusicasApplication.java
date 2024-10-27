package br.com.alura.musicas.banco_musicas;

import br.com.alura.musicas.banco_musicas.principal.Principal;
import br.com.alura.musicas.banco_musicas.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BancoMusicasApplication implements CommandLineRunner {
	@Autowired
	private MusicRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(BancoMusicasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal(repository);
		principal.exibeMenu();
	}
}
