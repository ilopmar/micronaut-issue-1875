package validator.header.controller;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;

@Introspected
public class Book {

    @NotBlank
    private String title;

    public Book() {
    }

    public Book(@NotBlank String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

