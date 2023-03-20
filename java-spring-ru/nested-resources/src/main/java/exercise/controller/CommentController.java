package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.stream.Collectors;


@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    // BEGIN
    @GetMapping("/{postId}/comments/{commentId}")
    public Comment getComment(@PathVariable(value = "postId") long postId,
                              @PathVariable(value = "commentId") long commentId) {
        Comment result = commentRepository.findByIdAndPostId(commentId, postId);
        if(result == null) {
            throw new ResourceNotFoundException("Comment not found");
        }
        return result;
    }

    @GetMapping("/{postId}/comments")
    public Iterable<Comment> getComments(@PathVariable(value = "postId") long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @PostMapping("/{postId}/comments")
    public void createComment(@PathVariable(value = "postId") long postId, @RequestBody Comment newComment) {
        newComment.setPost(postRepository.getPostById(postId));
        commentRepository.save(newComment);
    }

    @PatchMapping("/{postId}/comments/{commentId}")
    public void updateComment(@PathVariable(value = "postId") long postId,
                              @PathVariable(value = "commentId") long commentId,
                              @RequestBody Comment newComment) {
        Comment comment = commentRepository.findByIdAndPostId(commentId, postId);
        if (comment == null) {
            throw new ResourceNotFoundException("Comment not found");
        }
        newComment.setId(comment.getId());
        newComment.setPost(postRepository.getPostById(postId));
        commentRepository.save(newComment);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable(value = "postId") long postId,
                              @PathVariable(value = "commentId") long commentId) {
        Comment actualComment = commentRepository.findByIdAndPostId(commentId, postId);

        if (actualComment == null){
            throw new ResourceNotFoundException("Comment not found");
        }
        commentRepository.delete(actualComment);
    }
    // END
}
