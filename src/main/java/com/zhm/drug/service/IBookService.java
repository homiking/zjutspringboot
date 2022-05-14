package com.zhm.drug.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhm.drug.entity.Book;

import java.util.List;

public interface IBookService extends IService<Book> {
    /**
     * 分页查询供应商数据
     * @param pageNum 页数
     * @param pageSize 每页多少条数据
     * @param param 查询参数
     * @return
     */
    public IPage<Book> selectBookPage(int pageNum, int pageSize, String param);

    /**
     * 添加供应商信息
     * @param book
     */
    public int addBook(Book book);

    /**
     * 修改供应商信息
     * @param book
     * @return
     */
    public int editBook(Book book);

    /**
     * 根据主键查询供应商信息
     * @param id
     * @return
     */
    public Book queryBookById(int id);

    /**
     * 根据主键删除供应商信息
     * @param id
     * @return
     */
    public int delBookById(int id);

    /**
     * 查询所有供应商数据
     * @return
     */
    public List<Book> queryAllBook();

}
