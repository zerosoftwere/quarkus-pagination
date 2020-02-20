package net.xerosoft.common;

import lombok.Data;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

@Data
public class PageRequest {
    @DefaultValue("0") @QueryParam("index") private int index;
    @DefaultValue("10") @QueryParam("size") private int size;
}
