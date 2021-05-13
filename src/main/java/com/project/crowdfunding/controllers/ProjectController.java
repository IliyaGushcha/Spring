package com.project.crowdfunding.controllers;

import com.project.crowdfunding.dto.*;
import com.project.crowdfunding.models.Company;
import com.project.crowdfunding.models.User;
import com.project.crowdfunding.services.BonusService;
import com.project.crowdfunding.services.CommentService;
import com.project.crowdfunding.services.CompanyService;
import com.project.crowdfunding.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class ProjectController {

    @Autowired
    CompanyService companyService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @Autowired
    BonusService bonusService;

    @GetMapping("/all")
    public List<Company> authenticateUser() {

        return companyService.findAll();
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public User userProfile(@RequestParam long id) {
        return userService.userInfo(id);
    }


    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/company/{id}")
    public Company showCompany(@PathVariable(value = "id") long companyId) {

        return companyService.findCompanyById(companyId);
    }

    @DeleteMapping("/company/{id}/delete")
    public void deleteCompany(@PathVariable(value = "id") long companyId) {
        companyService.deleteCompany(companyId);
    }

    @PostMapping(value = "company/add/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addCompany(@PathVariable("id") long id, @ModelAttribute CompanyRequest companyRequest) throws IOException {
        userService.addCompany(id, companyRequest);
    }

    @PostMapping("company/{id}/news/add")
    public void addNews(@PathVariable("id") long id, @RequestBody NewsDto news) {
        companyService.addNewsToCompany(id, news);
    }

    @PostMapping("company/comment/add")
    public void addComment(@RequestBody CommentDto commentDto) {
        commentService.addComment(commentDto.getUserId(), commentDto.getCompanyId(), commentDto.getText());
    }


    @GetMapping("company/{id}/comments/show")
    public List<ShowComment> showComments(@PathVariable long id) {
        return commentService.getComments(id);
    }

    @PostMapping("company/{id}/bonus/add")
    public void addBonus(@PathVariable long id, @RequestBody BonusDto bonus) {
        bonusService.addBonus(bonus, id);
    }

    @PostMapping("company/support")
    public void support(@RequestBody SupportDto supportDto) {
        companyService.support(supportDto.getCompanyId(), supportDto.getBonusId());
        userService.addBonus(supportDto.getUserId(), supportDto.getBonusId());
    }
}
