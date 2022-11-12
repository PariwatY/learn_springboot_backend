package com.example.allianz.model.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class StatusData {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String statusName;
    private String statusCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public StatusData(){
        //Empty Constructor
    }

    public  StatusData successStatusData(String statusName, String message){

        return generateStatus(statusName,"S",message);
    }

    public  StatusData errorStatusData(String statusName, String message){
        return generateStatus(statusName,"E",message);
    }


    private static StatusData generateStatus(String statusName, String statusCode, String message){
        StatusData statusData = new StatusData();
        statusData.setStatusName(statusName);
        statusData.setStatusCode(statusCode);
        statusData.setMessage(message);
        return statusData;
    }
}
