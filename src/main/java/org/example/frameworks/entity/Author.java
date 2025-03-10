package org.example.frameworks.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * TODO: Данный класс представляет собой JPA-сущность
 *  (Entity) для работы с данными об авторах в базе данных.
 *  Он определяет структуру таблицы и связи с другими сущностями.
 */


@Entity
@Getter
@Setter
@Table(name = "author")
public class Author {

    /**
     * Уникальный идентификатор автора в базе данных.
     * Генерируется автоматически при сохранении записи.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    /**
     * Имя автора.
     * Сохраняется в колонке first_name таблицы author.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Фамилия автора.
     * Сохраняется в колонке last_name таблицы author.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Рейтинг автора.
     * Сохраняется в колонке rating таблицы author.
     */
    @Column(name = "rating")
    private Long rating;


    /**
     * Список комментариев, оставленных автором.
     * Создает связь "один ко многим" с сущностью Comment.
     * При операциях с автором каскадно применяются все операции к комментариям.
     */
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Comment> comments;
}
