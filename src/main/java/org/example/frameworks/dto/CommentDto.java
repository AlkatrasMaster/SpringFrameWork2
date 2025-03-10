package org.example.frameworks.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * TODO: Данный класс представляет собой DTO
 *  (Data Transfer Object) для передачи данных
 *  о комментарии между слоями приложения.
 *  Он содержит основную информацию о комментарии и его связи с автором.
 */
@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
    private Integer id; // Уникальный идентификатор комментария
    private String text; // Текст комментария
    private Integer authorId; // Идентификатор автора, который оставил комментарий
}
