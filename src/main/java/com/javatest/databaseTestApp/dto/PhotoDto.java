package com.javatest.databaseTestApp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhotoDto {

    private Integer id;
    private String name;
    private String type;
    private byte[] photo;
}
