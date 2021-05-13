package com.project.crowdfunding.services;

import com.project.crowdfunding.dto.BonusDto;
import com.project.crowdfunding.models.Bonus;
import com.project.crowdfunding.models.Company;
import com.project.crowdfunding.rep.BonusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BonusService {

    @Autowired
    BonusRepository bonusRepository;

    @Autowired
    CompanyService companyService;

    public void save(Bonus bonus) {
        bonusRepository.save(bonus);
    }

    public Bonus findBonusById(long id) {
        return bonusRepository.findById(id).get();
    }

    public void addBonus(BonusDto bonus, long companyId) {
        Company company = companyService.findCompanyById(companyId);
        Bonus newBonus = new Bonus();
        newBonus.setName(bonus.getName());
        newBonus.setCost(bonus.getCost());
        newBonus.setCompany(company);
        save(newBonus);
    }
}
