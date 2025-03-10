package org.example.frameworks.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.frameworks.dto.AuthorDto;
import org.example.frameworks.entity.Author;
import org.example.frameworks.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;


/**
 * TODO: Данный класс представляет собой сервисный слой для работы с данными об авторах,
 *  реализующий CRUD-операции (Create, Read, Update, Delete).
 *  Он обеспечивает преобразование данных между сущностями базы данных и DTO-объектами,
 *  а также логирование операций.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorCRUDService implements CRUDService<AuthorDto> {

    /**
     * Репозиторий для работы с базой данных.
     * Инъектируется через конструктор благодаря @RequiredArgsConstructor.
     */

    private final AuthorRepository repository;


    /**
     * Получение автора по идентификатору.
     * Логирует попытку получения и выбрасывает исключение, если автор не найден.
     *
     * @param id идентификатор автора
     * @return DTO объект автора
     */
    @Override
    public AuthorDto getById(Integer id) {
        log.info("Get by id " + id);
        return mapToDto(repository.findById(id).orElseThrow());

    }


    /**
     * Получение всех авторов.
     * Преобразует все сущности в DTO объекты.
     *
     * @return коллекция DTO объектов авторов
     */
    @Override
    public Collection<AuthorDto> getAll() {
        return repository.findAll()
                .stream()
                .map(AuthorCRUDService::mapToDto)
                .toList();
    }

    /**
     * Создание нового автора.
     * Преобразует DTO в сущность и сохраняет в базе данных.
     *
     * @param authorDto данные автора для создания
     */

    @Override
    public void create(AuthorDto authorDto) {
        repository.save(mapToEntity(authorDto));

    }

    /**
     * Обновление существующего автора.
     * Преобразует DTO в сущность и сохраняет в базе данных.
     *
     * @param authorDto данные автора для обновления
     */
    @Override
    public void update(AuthorDto authorDto) {
        repository.save(mapToEntity(authorDto));
    }


    /**
     * Удаление автора по идентификатору.
     *
     * @param id идентификатор автора для удаления
     */
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);

    }

    /**
     * Преобразование DTO в сущность.
     * Рекурсивно преобразует вложенные комментарии.
     *
     * @param authorDto DTO объект для преобразования
     * @return сущность автора
     */
    public static Author mapToEntity(AuthorDto authorDto) {
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        author.setRating(authorDto.getRating());
        author.setComments(
           authorDto.getComments()
              .stream()
              .map(CommentCRUDService::mapToEntity)
        .toList());
        return author;

    }

    /**
     * Преобразование сущности в DTO.
     * Рекурсивно преобразует вложенные комментарии.
     *
     * @param author сущность для преобразования
     * @return DTO объект автора
     */
    public static AuthorDto mapToDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setFirstName(author.getFirstName());
        authorDto.setLastName(author.getLastName());
        authorDto.setRating(author.getRating());
        authorDto.setComments(
             author.getComments()
                .stream()
                .map(CommentCRUDService::mapToDto)
                .toList()
        );
        return authorDto;
    }
}
