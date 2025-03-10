package org.example.frameworks.dto;

import lombok.Data;

import java.util.List;


/**
 * TODO: Данный класс представляет собой DTO
 *  (Data Transfer Object) для передачи данных
 *  об авторе между слоями приложения.
 *  Он содержит основную информацию об авторе и связанные с ним комментарии.
 */
@Data
public class AuthorDto {

    private Integer id; // Уникальный идентификатор автора

    private String firstName; // Имя автора

    private String lastName; // Фамилия автора

    private Long rating; // Рейтинг автора (например, основанный на количестве и качестве комментариев)

    private List<CommentDto> comments; // Список комментариев, оставленных автором
}
