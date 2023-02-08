package com.mnia.orderservice.domain.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ErrorResponse {

    @JsonProperty("error_description")
    private String errorDescription;
    @JsonProperty("active")
    private boolean active;
    @JsonProperty("error")
    private String error;

}
