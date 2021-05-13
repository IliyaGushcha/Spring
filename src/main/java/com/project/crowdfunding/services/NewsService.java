package com.project.crowdfunding.services;

import com.project.crowdfunding.models.News;
import com.project.crowdfunding.rep.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    @Autowired
    NewsRepository newsRepository;

    public void saveNews(News news) {
        newsRepository.save(news);
    }
}
