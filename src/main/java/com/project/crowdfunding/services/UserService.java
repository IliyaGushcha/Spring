package com.project.crowdfunding.services;

import com.project.crowdfunding.dto.UserDto;
import com.project.crowdfunding.models.*;
import com.project.crowdfunding.dto.CompanyRequest;
import com.project.crowdfunding.rep.RoleRepository;
import com.project.crowdfunding.rep.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    RoleRepository roleRepository;

    public User userInfo(Long id) {
        return userRepository.findById(id).get();
    }

    public List<UserDto> getAllUsers() {
        List <UserDto> userDtoList = new ArrayList<>();
        for(User user : userRepository.findAll()) {
            userDtoList.add(new UserDto(user.getId(), user.getUsername(), user.getRole().getName().name()));
        }
        return userDtoList;
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

    public void changeRole(long userId, String role) {
        User user = userRepository.findById(userId).get();
        ERole eRole = ERole.valueOf(role);
        Role newRole = new Role(eRole);
        roleRepository.save(newRole);
        user.setRole(newRole);
        userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
