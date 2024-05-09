package exercise.controller;

import exercise.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentRepository commentsRepository;

    @GetMapping(path = "")
    public List<Comment> index() {
        return commentsRepository.findAll();
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@RequestBody Comment comment) {
        return commentsRepository.save(comment);
    }

    @GetMapping("/{id}")
    public Comment show(@PathVariable Long id) {
        return commentsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
    }

    @PutMapping("/{id}")
    public Comment update(@PathVariable Long id, @RequestBody Comment comment) {
        var findComment = commentsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
        findComment.setBody(comment.getBody());
        findComment.setPostId(comment.getPostId());
        commentsRepository.save(findComment);
        return findComment;
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        commentsRepository.deleteById(id);
    }
}
// END
