package net.xerosoft.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import net.xerosoft.common.Paginated;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "authors")
public class Author extends PanacheEntity {

    public String name;

    @CreationTimestamp
    public LocalDateTime createAt;

    @UpdateTimestamp
    public LocalDateTime updateAt;

    public static Paginated<Author> find(Page page) {
        PanacheQuery<Author> query = Author.findAll().page(page);
        int pages = query.pageCount();
        long total = query.count();
        List<Author> content = query.list();
        int count = content.size();
        return Paginated.of(page.index, pages, count, total, content);
    }

    public static Paginated<Author> findByName(String name, Page page) {
        PanacheQuery<Author> query = Author.find("LOWER(name) LIKE CONCAT('%', LOWER(?1), '%')", name).page(page);
        int pages = query.pageCount();
        long total = query.count();
        List<Author> content = query.list();
        int count = content.size();
        return Paginated.of(page.index, pages, count, total, content);
    }
}
