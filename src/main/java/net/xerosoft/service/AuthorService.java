package net.xerosoft.service;

import io.quarkus.panache.common.Page;
import net.xerosoft.common.Paginated;
import net.xerosoft.model.Author;
import net.xerosoft.model.Quote;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AuthorService {

    @Inject private QuoteService quoteService;

    public Paginated<Author> getAll(Page page) {
        return Author.find(page);
    }

    public Author findById(long id) {
        return Author.findById(id);
    }

    public Paginated<Quote> getQuotesByAuthor(long id, Page page) {
        return quoteService.findByAuthor(id, page);
    }

    public Paginated<Author> findByNames(String name, Page page) {
        return Author.findByName(name, page);
    }
}
