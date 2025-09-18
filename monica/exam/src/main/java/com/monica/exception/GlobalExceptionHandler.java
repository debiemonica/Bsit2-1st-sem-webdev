package com.monica.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatch(MethodArgumentTypeMismatchException ex, Model model) {
        String val = String.valueOf(ex.getValue());
        model.addAttribute("message", "Invalid id format: " + val);
        return "error";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrity(DataIntegrityViolationException ex, Model model) {
        model.addAttribute("message", "Database error: " + ex.getMostSpecificCause().getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleAll(Exception ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error";
    }
}
