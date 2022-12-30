package com.freya.onlinetech.service;

import com.freya.onlinetech.mapper.CommentsMapper;
import com.freya.onlinetech.mapper.NewsMapper;
import com.freya.onlinetech.pojo.CommentsRoot;
import com.freya.onlinetech.pojo.NewsTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsMapper newsMapper;

    //获取最新的新闻的标题 一共10条
    public List<NewsTitle> getNewsTitle()
    {
        List<NewsTitle> res = newsMapper.getNewsTitle();
        return res;
    }
}
