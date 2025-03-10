package org.example.frameworks.controllers;

import lombok.RequiredArgsConstructor;
import org.example.frameworks.dto.AuthorDto;
import org.example.frameworks.services.AuthorCRUDService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;



/**
 * TODO: Данный класс представляет собой
 *  REST-контроллер для работы с авторами в приложении.
 *  Он обеспечивает базовые операции CRUD
 *  (Create, Read, Update, Delete) через HTTP-запросы.
 */


@RequestMapping("/author")
@RestController
@RequiredArgsConstructor
public class AuthorController {

    /**
     * Сервисный слой для работы с авторами.
     * Инъектируется через конструктор благодаря @RequiredArgsConstructor.
     */

    private final AuthorCRUDService authorService;



    @GetMapping
    public Collection<AuthorDto> getAll() { // Получение списка всех авторов.
        return authorService.getAll();
    }

    @PostMapping
    public void create(@RequestBody AuthorDto authorDto) { // Создание нового автора.
        authorService.create(authorDto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody AuthorDto authorDto) { // Обновление существующего автора.
        authorDto.setId(id);
        authorService.update(authorDto);
    }

    @DeleteMapping
    public void delete(@PathVariable Integer id) {  // Удаление автора по идентификатору.
        authorService.delete(id);
    }
}
