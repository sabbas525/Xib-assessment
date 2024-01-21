package com.xib.assessment.exception;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * A generic response wrapper used across the application to standardize API responses.
 */
@Data
@Component
public class ResponseObject {
    private String code;     // Return Code for decision making
    private boolean status;  // Return Status for decision making
    private String message;  //Return Message if required
    private Object data;    // Return Response Object

    /**
     * Formats the response for API calls.
     *
     * @return A string representation of the API response.
     */
    public String responseObjectMethod() {
        return "API Response{" +
                "code='" + code + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * Formats the response for level check operations.
     *
     * @return A string representation of the level check response.
     */
    public String responseObjectlevelCheck() {
        return "levelCheck Response{" +
                "code='" + code + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}