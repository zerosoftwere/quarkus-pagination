package net.xerosoft.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import net.xerosoft.common.Paginated;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "quotes")
public class Quote extends PanacheEntity {
    @Type(type = "text")
    public String quote;

    @ManyToOne
    public Author author;

    @CreationTimestamp
    public LocalDateTime createdAt;
    @UpdateTimestamp
    public LocalDateTime updatedAt;

    public static Paginated<Quote> find(Page page) {
        PanacheQuery<Quote> query = Quote.findAll().page(page);
        int pages = query.pageCount();
        long total = query.count();
        List<Quote> content = query.list();
        int count = content.size();
        return Paginated.of(page.index, pages, count, total, content);
    }

    public static Paginated<Quote> findByAuthor(long AuthorId, Page page) {
        PanacheQuery<Quote> query = Quote.find("author_id=?1", AuthorId).page(page);
        int pages = query.pageCount();
        long total = query.count();
        List<Quote> content = query.list();
        int count = content.size();
        return Paginated.of(page.index, pages, count, total, content);
    }
}
