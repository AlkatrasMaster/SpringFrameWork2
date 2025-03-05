package org.example.frameworks.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.frameworks.dto.CommentDto;
import org.example.frameworks.entity.Comment;
import org.example.frameworks.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.TreeMap;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommentCRUDService implements CRUDService<CommentDto> {

    private final CommentRepository repository;
    @Override
    public CommentDto getById(Integer id) {
        log.info("Get vy ID:" + id);
        Comment comment = repository.findById(id).orElseThrow();
        return mapToDto(comment);
    }

    @Override
    public Collection<CommentDto> getAll() {
        log.info("Get all");
        return repository.findAll()
                .stream()
                .map(CommentCRUDService::mapToDto)
                .toList();
    }

    @Override
    public void create(CommentDto item) {
        log.info("Create");
        Comment comment = mapToEntity(item);
        repository.save(comment);

    }

    @Override
    public void update(CommentDto item) {
        log.info("Update ");
        Comment comment = mapToEntity(item);
        repository.save(comment);

    }

    @Override
    public void delete(Integer id) {
        log.info("Delete " + id);
        repository.deleteById(id);
    }

    public static CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setText(comment.getText());
        commentDto.setAuthor(comment.getAuthor());
        return commentDto;

    }

    public static Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setText(commentDto.getText());
        comment.setAuthor(commentDto.getAuthor());
        return comment;

    }
}
