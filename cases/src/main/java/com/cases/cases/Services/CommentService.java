package com.cases.cases.Services;
import com.cases.cases.Models.Comment;
import com.cases.cases.Repositories.CommentRepository;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findBySupportCaseId(Long supportCaseId) {
        return commentRepository.findBySupportCaseId(supportCaseId);
    }

    public List<Comment> findByUserId(Long userId) {
        return commentRepository.findByUserId(userId);
    }
    public List<Comment> getCommentsByUserId(Long userId) {
        return commentRepository.findByUserId(userId);
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
}
