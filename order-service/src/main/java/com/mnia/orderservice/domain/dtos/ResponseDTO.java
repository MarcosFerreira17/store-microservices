package com.mnia.orderservice.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String created;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String kind;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Meta meta;

    public ResponseDTO(String id, String created, String kind) {
        this.id = id;
        this.created = created;
        this.kind = kind;
    }
}
