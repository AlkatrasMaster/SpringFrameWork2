package org.example.frameworks.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


/**
 * TODO: Данный класс представляет собой JPA-сущность
 *  для работы с комментариями в базе данных.
 *  Он определяет структуру таблицы и связи с другими сущностями,
 *  включая автоматическую запись времени создания.
 */
@Entity
@Getter
@Setter
@Table(name = "comment")
public class Comment {


    /**
     * Уникальный идентификатор комментария в базе данных.
     * Генерируется автоматически при сохранении записи.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Текст комментария.
     * Сохраняется в колонке text таблицы comment.
     */

    @Column(name = "text")
    private String text;


    /**
     * Ссылка на автора, который оставил комментарий.
     * Создает связь "многие к одному" с сущностью Author.
     * Колонка author_id в таблице comment будет содержать id автора.
     */
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    /**
     * Время создания комментария.
     * Автоматически устанавливается при создании записи в базе данных.
     * Сохраняется в колонке creation_time таблицы comment.
     */
    @CreationTimestamp
    @Column(name = "creation_time")
    private LocalDateTime time;
}
