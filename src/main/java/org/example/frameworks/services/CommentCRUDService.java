package org.example.frameworks.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.frameworks.dto.CommentDto;
import org.example.frameworks.entity.Author;
import org.example.frameworks.entity.Comment;
import org.example.frameworks.repository.AuthorRepository;
import org.example.frameworks.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.TreeMap;


/**
 * TODO: Данный класс представляет собой сервисный слой для работы с комментариями,
 *  реализующий CRUD-операции (Create, Read, Update, Delete)
 *  и обеспечивающий связь между комментариями и авторами.
 */


@RequiredArgsConstructor
@Slf4j
@Service
public class CommentCRUDService implements CRUDService<CommentDto> {


    /**
     * Репозиторий для работы с комментариями в базе данных.
     * Инъектируется через конструктор благодаря @RequiredArgsConstructor.
     */
    private final CommentRepository commentRepository;

    /**
     * Репозиторий для работы с авторами в базе данных.
     * Используется для проверки существования автора при создании/обновлении комментария.
     */
    private final AuthorRepository authorRepository;

    /**
     * Получение комментария по идентификатору.
     * Логирует попытку получения и выбрасывает исключение, если комментарий не найден.
     *
     * @param id идентификатор комментария
     * @return DTO объект комментария
     */
    @Override
    public CommentDto getById(Integer id) {
        log.info("Get vy ID:" + id);
        Comment comment = commentRepository.findById(id).orElseThrow();
        return mapToDto(comment);
    }


    /**
     * Получение всех комментариев.
     * Логирует операцию и преобразует все сущности в DTO объекты.
     *
     * @return коллекция DTO объектов комментариев
     */
    @Override
    public Collection<CommentDto> getAll() {
        log.info("Get all");
        return commentRepository.findAll()
                .stream()
                .map(CommentCRUDService::mapToDto)
                .toList();
    }


    /**
     * Создание нового комментария.
     * Проверяет существование автора и устанавливает связь между комментарием и автором.
     *
     * @param commentDto данные комментария для создания
     */
    @Override
    public void create(CommentDto commentDto) {
        log.info("Create");
        Comment comment = mapToEntity(commentDto);
        Integer authorId = commentDto.getAuthorId();
        Author author = authorRepository.findById(authorId).orElseThrow();
        comment.setAuthor(author);
        commentRepository.save(comment);

    }

    /**
     * Обновление существующего комментария.
     * Проверяет существование автора и обновляет связь между комментарием и автором.
     *
     * @param commentDto данные комментария для обновления
     */
    @Override
    public void update(CommentDto commentDto) {
        log.info("Update ");
        Comment comment = mapToEntity(commentDto);
        Integer authorId = commentDto.getAuthorId();
        Author author = authorRepository.findById(authorId).orElseThrow();
        comment.setAuthor(author);
        commentRepository.save(comment);

    }

    /**
     * Удаление комментария по идентификатору.
     * Логирует операцию удаления.
     *
     * @param id идентификатор комментария для удаления
     */
    @Override
    public void delete(Integer id) {
        log.info("Delete " + id);
        commentRepository.deleteById(id);
    }

    /**
     * Преобразование сущности комментария в DTO.
     * Копирует основные поля и устанавливает ID автора.
     *
     * @param comment сущность для преобразования
     * @return DTO объект комментария
     */
    public static CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setText(comment.getText());
        commentDto.setAuthorId(comment.getAuthor().getId());
        return commentDto;

    }

    /**
     * Преобразование DTO в сущность комментария.
     * Копирует основные поля из DTO в сущность.
     *
     * @param commentDto DTO для преобразования
     * @return сущность комментария
     */
    public static Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setText(commentDto.getText());
        return comment;

    }
}
