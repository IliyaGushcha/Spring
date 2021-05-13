package com.project.crowdfunding.services;

import com.project.crowdfunding.models.Bonus;
import com.project.crowdfunding.models.Company;
import com.project.crowdfunding.models.User;
import com.project.crowdfunding.dto.CompanyRequest;
import com.project.crowdfunding.rep.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService {

    @Autowired
    CompanyService companyService;

    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BonusService bonusService;

    @Autowired
    CommentService commentService;

    public User userInfo(Long id) {
        return userRepository.findById(id).get();
    }

    public void addCompany(long id, CompanyRequest company) throws IOException {
        User user = userRepository.findById(id).get();
        Company newCompany = new Company();
        newCompany.setUser(user);
        newCompany.setName(company.getName());
        newCompany.setDescription(company.getDescription());
        newCompany.setSubject(company.getSubject());
        newCompany.setVideo(company.getVideo());
        newCompany.setAmount(company.getAmount());
        newCompany.setDeadline(company.getDeadline());
        companyService.save(newCompany);
        cloudinaryService.upload(company.getImage(), newCompany);
    }

    public void addBonus(long userId, long bonusId) {
        User user = userInfo(userId);
        Bonus bonus = bonusService.findBonusById(bonusId);
        bonus.setUser(user);
        bonusService.save(bonus);
    }
}
