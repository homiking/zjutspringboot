package com.zhm.drug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhm.drug.entity.News;
import com.zhm.drug.mapper.NewsMapper;
import com.zhm.drug.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;

/**
 * @Description
 * @Author kknever
 * @Date 2022/4/10
 **/
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {
    @Autowired
    NewsMapper newsMapper;

    @Override
    public IPage<News> selectNewsPage(int pageNum, int pageSize, String param) {
        QueryWrapper<News> wrapper = new QueryWrapper<>();
        if(! StringUtils.isEmpty(param)) {
            // like 是模糊查询, eq 是完全相等的查询
            wrapper.like("name", param);
        }
        Page<News> page = new Page<>(pageNum, pageSize);
        return newsMapper.selectPage(page, wrapper);
    }

    @Override
    public int addNews(News news) {
        return newsMapper.insert(news);
    }

    @Override
    public int editNews(News news) {
        return newsMapper.updateById(news);
    }

    @Override
    public News queryNewsById(int id) {
        return newsMapper.selectById(id);
    }

    @Override
    public int delNewsById(int id) {
        return newsMapper.deleteById(id);
    }

    @Override
    public List<News> queryAllNews() {
       return newsMapper.selectList(null);
    }
}
