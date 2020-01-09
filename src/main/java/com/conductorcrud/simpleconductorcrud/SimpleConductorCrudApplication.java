package com.conductorcrud.simpleconductorcrud;

import com.conductorcrud.simpleconductorcrud.model.Pessoas;
import com.conductorcrud.simpleconductorcrud.repository.PessoasRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.stream.LongStream;

@SpringBootApplication
public class SimpleConductorCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleConductorCrudApplication.class, args);
	}

	@Bean
	CommandLineRunner init(PessoasRepository repository) {
		return args -> {
			repository.deleteAll();
			LongStream.range(1, 11)
					.mapToObj(i -> {
						Pessoas p = new Pessoas();
						p.setNome("Pessoa " + i);
						p.setCpf("" + i + i);
						p.setDataNascimento(new Date(new java.util.Date().getTime()));
						return p;
					})
					.map(v -> repository.save(v))
					.forEach(System.out::println);
		};
	}

}
