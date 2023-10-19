package xyz.fursov.springmvc.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Book {
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String author;
    @NotNull
    private int year;
}
