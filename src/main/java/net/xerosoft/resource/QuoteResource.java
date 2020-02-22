package net.xerosoft.resource;

import io.quarkus.panache.common.Page;
import lombok.extern.slf4j.Slf4j;
import net.xerosoft.common.PageRequest;
import net.xerosoft.common.Paginated;
import net.xerosoft.model.Quote;
import net.xerosoft.service.QuoteService;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/quotes")
@Slf4j
public class QuoteResource {

    @Inject
    private QuoteService quoteService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Paginated<Quote> get(@QueryParam List<String> authors, @BeanParam PageRequest pageRequest) {
        if (authors.isEmpty())
            return quoteService.getAll(Page.of(pageRequest.getIndex(), pageRequest.getSize()));
        return quoteService.findByAuthors(authors, Page.of(pageRequest.getIndex(), pageRequest.getSize()));
    }
}