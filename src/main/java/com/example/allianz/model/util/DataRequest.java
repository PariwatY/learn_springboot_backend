package com.example.allianz.model.util;

import lombok.Data;

@Data
public class DataRequest<T> {
    private HeaderData header;
    private BodyData<T> body;
    private StatusData status;
}
