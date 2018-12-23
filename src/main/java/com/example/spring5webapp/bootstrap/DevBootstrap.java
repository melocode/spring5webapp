
package com.example.spring5webapp.bootstrap;

import com.example.spring5webapp.model.Author;
import com.example.spring5webapp.model.Book;
import com.example.spring5webapp.model.Publisher;
import com.example.spring5webapp.repositories.AuthorRepository;
import com.example.spring5webapp.repositories.BookRepository;
import com.example.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        intiData();
    }

    private void intiData() {
        //Eric
        Author ericAuthor = new Author("Eric", "Evans");
        Publisher harperPub = new Publisher("Harper Collins", "123 Apple Drive");
        Book dddBook = new Book("Domain Driven Design", "1234", harperPub);
        ericAuthor.getBooks().add(dddBook);
        dddBook.getAuthors().add(ericAuthor);

        publisherRepository.save(harperPub);
        authorRepository.save(ericAuthor);
        bookRepository.save(dddBook);


        //Rod
        Author rodAuthor = new Author("Rod", "Johnson");
        Publisher wroxPub = new Publisher("Wrox", "123 Main Street");
        Book noEJBBook = new Book("J2EE Development without EJB", "23444", wroxPub );
        rodAuthor.getBooks().add(noEJBBook);
        noEJBBook.getAuthors().add(rodAuthor);

        publisherRepository.save(wroxPub);
        authorRepository.save(rodAuthor);
        bookRepository.save(noEJBBook);
    }

}
