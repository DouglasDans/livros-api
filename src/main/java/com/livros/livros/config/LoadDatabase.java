package com.livros.livros.config;

import com.livros.livros.model.entities.Autor;
import com.livros.livros.model.entities.Editora;
import com.livros.livros.model.repositories.IAutorRepository;
import com.livros.livros.model.repositories.IEditoraRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
//@Profile("dev")
public class LoadDatabase {

    Logger log = LogManager.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(
            IAutorRepository autorRepository,
            IEditoraRepository editoraRepository
    ) {
        return args -> {
            Autor autor1 = new Autor("Valdir Azevedo", "Brasileiro", "1967-10-06");
            autorRepository.saveAll(Arrays.asList(autor1));

            Editora editora1 = new Editora("Saraiva", "91643714000173", "Brasil");
            editoraRepository.saveAll(Arrays.asList(editora1));


            log.info(">>>> [LoadDatabase] dados inseridos no Banco de dados");
        };
    }
}
