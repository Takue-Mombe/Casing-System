package com.cases.cases.Repositories;

import com.cases.cases.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findBySupportCaseId(Long supportCaseId);
}
