package org.example.frameworks.controllers;

import org.example.frameworks.dto.CommentDto;
import org.example.frameworks.services.CommentCRUDService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;



/**
 * TODO: Данный класс представляет собой REST-контроллер
 *  для работы с комментариями в приложении.
 *  Он обеспечивает базовые операции CRUD
 *  (Create, Read, Update, Delete) через HTTP-запросы.
 */

@RestController
@RequestMapping("/comment")
public class CommentController {

    /**
     * Сервисный слой для работы с комментариями.
     * Инъектируется через конструктор для обеспечения зависимости.
     */
    private final CommentCRUDService commentService;

    /**
     * Конструктор для инициализации контроллера.
     * @param commentService сервис для работы с комментариями
     */

    public CommentController(CommentCRUDService commentService) {
        this.commentService = commentService;
    }

    /**
     * Получение комментария по его идентификатору.
     *
     * @param id идентификатор комментария
     * @return DTO объект комментария
     */


    @GetMapping("/{id}")
    public CommentDto getCommentById(@PathVariable Integer id) {
        return commentService.getById(id);

    }

    /**
     * Получение всех комментариев.
     *
     * @return коллекция DTO объектов комментариев
     */

    @GetMapping
    public Collection<CommentDto> getAllComments() {
        return commentService.getAll();
    }

    /**
     * Создание нового комментария.
     *
     * @param commentDto данные комментария для создания
     */


    @PostMapping
    public void createComment(@RequestBody CommentDto commentDto) {
        commentService.create(commentDto);
    }

    /**
     * Обновление существующего комментария.
     *
     * @param id идентификатор комментария для обновления
     * @param commentDto обновленные данные комментария
     */

    @PutMapping("/{id}")
    public void updateComment(@PathVariable Integer id, @RequestBody CommentDto commentDto) {
        commentDto.setId(id);
        commentService.update(commentDto);

    }

    /**
     * Удаление комментария по его идентификатору.
     *
     * @param id идентификатор комментария для удаления
     */

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Integer id) {
        commentService.delete(id);
    }
}
