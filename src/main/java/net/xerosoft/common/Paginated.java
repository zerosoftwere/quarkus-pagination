package net.xerosoft.common;

import lombok.Data;

import java.util.List;

@Data(staticConstructor = "of")
public class Paginated<T> {
    private final long index;
    private final long pages;
    private final long count;
    private final long total;
    private final List<T> content;
}
