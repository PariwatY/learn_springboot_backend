package com.example.allianz.model.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;



@Data
public class HeaderData {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String sendDateTime;
    private String responseDateTime;
}
