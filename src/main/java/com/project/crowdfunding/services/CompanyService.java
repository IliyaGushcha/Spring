package com.project.crowdfunding.services;

import com.project.crowdfunding.dto.BonusDto;
import com.project.crowdfunding.dto.CommentDto;
import com.project.crowdfunding.dto.NewsDto;
import com.project.crowdfunding.models.*;
import com.project.crowdfunding.rep.CompanyRepository;
import com.project.crowdfunding.rep.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    NewsService newsService;

    @Autowired
    BonusService bonusService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    public void save(Company company) {
        companyRepository.save(company);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company findCompanyById(long id) {
        return companyRepository.findCompanyById(id);
    }

    public void deleteCompany(long id) {
        companyRepository.deleteById(id);
    }

    public void addNewsToCompany(long id, NewsDto newsDto) {
        Company company = findCompanyById(id);
        News news = new News();
        news.setName(newsDto.getName());
        news.setDescription(newsDto.getDescription());
        news.setCompany(company);
        newsService.saveNews(news);
    }

    public void support(long companyId, long bonusId) {
        Company company = findCompanyById(companyId);
        Bonus bonus = bonusService.findBonusById(bonusId);
        int currentAmount = company.getCurrentAmount() + bonus.getCost();
        company.setCurrentAmount(currentAmount);
        save(company);
    }
}
