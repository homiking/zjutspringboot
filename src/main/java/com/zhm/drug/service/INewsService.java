package com.zhm.drug.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhm.drug.entity.News;

import java.util.List;

public interface INewsService extends IService<News> {
    /**
     * 分页查询供应商数据
     * @param pageNum 页数
     * @param pageSize 每页多少条数据
     * @param param 查询参数
     * @return
     */
    public IPage<News> selectNewsPage(int pageNum, int pageSize, String param);

    /**
     * 添加供应商信息
     * @param news
     */
    public int addNews(News news);

    /**
     * 修改供应商信息
     * @param news
     * @return
     */
    public int editNews(News news);

    /**
     * 根据主键查询供应商信息
     * @param id
     * @return
     */
    public News queryNewsById(int id);

    /**
     * 根据主键删除供应商信息
     * @param id
     * @return
     */
    public int delNewsById(int id);

    /**
     * 查询所有供应商数据
     * @return
     */
    public List<News> queryAllNews();

}
