package com.freya.onlinetech.mapper;

import com.freya.onlinetech.pojo.CommentsRoot;
import com.freya.onlinetech.pojo.Guanchazhe;
import com.freya.onlinetech.pojo.NewsTitle;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NewsMapper {
    //获取新闻事件（获取Title）
    List<NewsTitle> getNewsTitle();
    //获取观察者网的新闻数据
    List<Guanchazhe> getGuanchazhe();
}
