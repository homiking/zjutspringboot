package com.zhm.drug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhm.drug.entity.Book;
import com.zhm.drug.mapper.BookMapper;
import com.zhm.drug.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;

/**
 * @Description
 * @Author kknever
 * @Date 2022/4/10
 **/
@CacheConfig(cacheNames = "book")
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

    @Autowired
    BookMapper bookMapper;

    @Cacheable(key="'bookpage'")
    @Override
    public IPage<Book> selectBookPage(int pageNum, int pageSize, String param) {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        if(! StringUtils.isEmpty(param)) {
            // like 是模糊查询, eq 是完全相等的查询
            wrapper.like("name", param);
        }
        Page<Book> page = new Page<>(pageNum, pageSize);
        return bookMapper.selectPage(page, wrapper);
    }
    @CacheEvict(key="'bookpage'")
    @Override
    public int addBook(Book book) {
        return bookMapper.insert(book);
    }
    @CacheEvict(key="'bookpage'")
    @Override
    public int editBook(Book book) {
        return bookMapper.updateById(book);
    }

    @Override
    public Book queryBookById(int id) {
        return bookMapper.selectById(id);

    }
    @CacheEvict(key="'bookpage'")
    @Override
    public int delBookById(int id) {
        return bookMapper.deleteById(id);
    }

    @Override
    public List<Book> queryAllBook() {
       return bookMapper.selectList(null);
    }
}
