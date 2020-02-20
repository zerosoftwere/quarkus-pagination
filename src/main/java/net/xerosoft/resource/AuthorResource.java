package net.xerosoft.resource;

import io.quarkus.panache.common.Page;
import net.xerosoft.common.PageRequest;
import net.xerosoft.common.Paginated;
import net.xerosoft.model.Author;
import net.xerosoft.model.Quote;
import net.xerosoft.service.AuthorService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/authors")
public class AuthorResource {
    @Inject
    private AuthorService authorService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Paginated<Author> getAll(@BeanParam PageRequest pageRequest) {
        return authorService.getAll(Page.of(pageRequest.getIndex(), pageRequest.getSize()));
    }

    @GET
    @Path("{authorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Author getById(@PathParam("authorId") long id) {
        return authorService.findById(id);
    }

    @GET
    @Path("{authorId}/quotes")
    @Produces(MediaType.APPLICATION_JSON)
    public Paginated<Quote> getQuotesByAuthor(@PathParam("authorId") long id, @BeanParam PageRequest pageRequest) {
        return authorService.getQuotesByAuthor(id, Page.of(pageRequest.getIndex(), pageRequest.getSize()));
    }
}
