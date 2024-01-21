package com.xib.assessment.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Centralized exception handling across all @RequestMapping methods in @Controller classes.
 * Provides specific handlers for different types of exceptions.
 */
@RestControllerAdvice
@RequiredArgsConstructor
public class ErrorAdvice {
    ResponseObject errorResponse = new ResponseObject(); //Injected Object

    /**
     * Handles InvalidFormatExceptions thrown by the application.
     *
     * @param ex                 The caught InvalidFormatException.
     * @param httpServletRequest The incoming HTTP request.
     * @return A ResponseEntity containing the error details.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<?> formatExceptionHandler(InvalidFormatException ex,
                                                    HttpServletRequest httpServletRequest) {
        Map<String, String> errorMessage = new HashMap<>();
        errorMessage.put("error", ex.getOriginalMessage());
        String endpoint = httpServletRequest.getRequestURI();
        String error = "Invalid format";
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Endpoint", endpoint);
        errorResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorResponse.setStatus(false);
        errorResponse.setMessage(error);
        errorResponse.setData(errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
    }

    /**
     * Handles HttpMediaTypeNotSupportedExceptions thrown when an unsupported media type is encountered.
     *
     * @param ex                 The caught HttpMediaTypeNotSupportedException.
     * @param httpServletRequest The incoming HTTP request.
     * @return A ResponseEntity containing the error details.
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Object> handleMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex,
                                                                       HttpServletRequest httpServletRequest) {
        String endpoint = httpServletRequest.getRequestURI();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Endpoint", endpoint);
        String error = "Unsupported media type. Supported media types are: " +
                ex.getSupportedMediaTypes();
        errorResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorResponse.setStatus(false);
        errorResponse.setMessage(error);
        errorResponse.setData(null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
    }

    /**
     * Handles MethodArgumentNotValidExceptions for invalid method arguments.
     *
     * @param ex                 The caught MethodArgumentNotValidException.
     * @param httpServletRequest The incoming HTTP request.
     * @return A ResponseEntity containing the error details.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex,
                                                                    HttpServletRequest httpServletRequest) {
        Map<String, String> errorMessage = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
        {
            errorMessage.put(error.getField(), error.getDefaultMessage());
        });
        String error = "Invalid method argument.";
        String endpoint = httpServletRequest.getRequestURI();
        errorResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorResponse.setStatus(false);
        errorResponse.setMessage(error);
        errorResponse.setData(errorMessage);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Endpoint", endpoint);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
    }

    /**
     * Handles MissingServletRequestPartException which occurs when a part of a multipart request not found.
     *
     * @param ex                 The caught MissingServletRequestPartException.
     * @param httpServletRequest The incoming HTTP request.
     * @return A ResponseEntity containing the error details.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<?> MissingServletRequestPartExceptionHandler(MissingServletRequestPartException ex,
                                                                       HttpServletRequest httpServletRequest) {
        try {
            Map<String, String> errorMessage = new HashMap<>();
            errorMessage.put("Error", ex.getMessage());
            String endpoint = httpServletRequest.getRequestURI();
            HttpHeaders headers = new HttpHeaders();
            headers.add("X-Endpoint", endpoint);
            String error = "Servlet request part is missing";
            errorResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
            errorResponse.setStatus(false);
            errorResponse.setMessage(error);
            errorResponse.setData(errorMessage);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        } catch (Exception exception) {
            return null;
        }
    }

    /**
     * Handles HttpMessageNotReadableException which occurs when the request body is invalid or missing.
     *
     * @param ex                 The caught HttpMessageNotReadableException.
     * @param httpServletRequest The incoming HTTP request.
     * @return A ResponseEntity containing the error details.
     */
    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<?> handleHttpMessageNotReadableException(org.springframework.http.converter.HttpMessageNotReadableException ex,
                                                                   HttpServletRequest httpServletRequest) {
        String endpoint = httpServletRequest.getRequestURI();
        String error = "Invalid request body. Please check the request and try again.";
        // You can customize the response as per your requirements
        errorResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase().isEmpty());
        errorResponse.setMessage(error);
        errorResponse.setData(null);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles HttpRequestMethodNotSupportedException which occurs when a requested HTTP method is not supported for a specific endpoint.
     *
     * @param ex                 The caught HttpRequestMethodNotSupportedException.
     * @param httpServletRequest The incoming HTTP request.
     * @return A ResponseEntity indicating the HTTP method is not supported.
     */
    @ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<?> handleHttpRequestMethodNotSupportedException(
            org.springframework.web.HttpRequestMethodNotSupportedException ex,
            HttpServletRequest httpServletRequest) {
        String error = "The requested HTTP method is not supported.";
        String endpoint = httpServletRequest.getRequestURI();
        errorResponse.setCode(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()));
        errorResponse.setStatus(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase().isEmpty());
        errorResponse.setMessage(error);
        errorResponse.setData(null);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResponse);
    }
}