package com.xib.assessment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse<T> implements Serializable {

    private Integer code;
    private String message;
    private String warning;
    private String error;
    private T data;
}
