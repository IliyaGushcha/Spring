package com.project.crowdfunding.services;

import com.project.crowdfunding.dto.ShowComment;
import com.project.crowdfunding.models.Comment;
import com.project.crowdfunding.models.Company;
import com.project.crowdfunding.models.User;
import com.project.crowdfunding.rep.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CompanyService companyService;

    @Autowired
    UserService userService;

    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    public Comment findCommentByUserId(long id) {
        return commentRepository.findCommentByUserId(id);
    }

    public List<Comment> findAllByCompanyId(long id) {
        return commentRepository.findAllByCompanyId(id);
    }


    public List<ShowComment> getComments(long companyId) {
        List<Comment> comments = findAllByCompanyId(companyId);
        List<ShowComment> showComments = new ArrayList<>();
        for(Comment comment: comments) {
            ShowComment showComment = new ShowComment();
            showComment.setText(comment.getText());
            showComment.setUsername(comment.getUser().getUsername());

            showComments.add(showComment);
        }
        return showComments;
    }

    public void addComment(long userId, long companyId, String text) {
        User user = userService.userInfo(userId);
        Company company = companyService.findCompanyById(companyId);
        Comment comment = new Comment();
        comment.setText(text);
        comment.setUser(user);
        comment.setCompany(company);
        save(comment);
    }
}
