package com.example.allianz.model.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class DataResponse<T> {
    private HeaderData header;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BodyData<T> body;
    private StatusData status;
}
