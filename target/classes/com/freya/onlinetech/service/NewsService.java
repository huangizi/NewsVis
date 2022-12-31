package com.freya.onlinetech.service;

import com.freya.onlinetech.mapper.CommentsMapper;
import com.freya.onlinetech.mapper.NewsMapper;
import com.freya.onlinetech.pojo.CommentsRoot;
import com.freya.onlinetech.pojo.Guanchazhe;
import com.freya.onlinetech.pojo.NewsTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    //获取观察者网的数据
    public List<Guanchazhe> getGuanchazhe()
    {
        List<Guanchazhe> res = newsMapper.getGuanchazhe();
        return res;
    }

    //获取观察者网词云的数据
    public Map<String,Integer> getCloudText()
    {
        Map<String,Integer> res = new HashMap<>();
        List<Guanchazhe> list = newsMapper.getGuanchazhe();
        for (int i = 0; i < list.size(); i++)
        {
            Guanchazhe a = list.get(i);
            String keyWords = a.getKey_word();
            if (keyWords != null)
            {
                List<String> keyWordlist = Arrays.asList(keyWords.split(","));
                for (String s : keyWordlist) {
                    if(res.containsKey(s))
                    {
                        int v = res.get(s);
                        res.put(s,v+1);
                    }else{
                        res.put(s,1);
                    }
                }
            }

        }
        return res;
    }

}
