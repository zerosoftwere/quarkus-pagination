package net.xerosoft.resource;

import io.quarkus.panache.common.Page;
import net.xerosoft.common.PageRequest;
import net.xerosoft.common.Paginated;
import net.xerosoft.model.Quote;
import net.xerosoft.service.QuoteService;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/quotes")
public class QuoteResource {

    @Inject
    private QuoteService quoteService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Paginated<Quote> get(@BeanParam PageRequest pageRequest) {
        return quoteService.getAll(Page.of(pageRequest.getIndex(), pageRequest.getSize()));
    }
}