package com.livros.livros.config;

import com.livros.livros.model.entities.Autor;
import com.livros.livros.model.entities.Editora;
import com.livros.livros.model.entities.Livro;
import com.livros.livros.model.repositories.IAutorRepository;
import com.livros.livros.model.repositories.IEditoraRepository;
import com.livros.livros.model.repositories.ILivroRepository;
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
            IEditoraRepository editoraRepository,
            ILivroRepository livroRepository
    ) {
        return args -> {
            Autor autor1 = new Autor("Valdir Azevedo", "Brasileiro", "1967-10-06");
            autorRepository.saveAll(Arrays.asList(autor1));

            Editora editora1 = new Editora("Saraiva", "91643714000173", "Brasil");
            editoraRepository.saveAll(Arrays.asList(editora1));

            Livro livro1 = new Livro(
                    "9788532511015",
                    "Harry Potter e a Pedra Filosofal",
                    "O livro conta a história de Harry Potter, um órfão criado pelos tios que descobre, em seu décimo primeiro aniversário, que é um bruxo.",
                    "1999-10-10",
                    1,
                    autor1,
                    editora1
            );

            livroRepository.saveAll(Arrays.asList(livro1));


            log.info(">>>> [LoadDatabase] dados inseridos no Banco de dados");
        };
    }
}
