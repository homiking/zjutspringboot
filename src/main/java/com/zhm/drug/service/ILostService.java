package com.zhm.drug.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhm.drug.entity.Lost;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface ILostService extends IService<Lost> {
    /**
     * 分页查询供应商数据
     * @param pageNum 页数
     * @param pageSize 每页多少条数据
     * @param param 查询参数
     * @return
     */
    public IPage<Lost> selectLostPage(int pageNum, int pageSize, String param);

    /**
     * 添加供应商信息
     * @param lost
     */
    public int addLost(Lost lost);

    /**
     * 修改供应商信息
     * @param lost
     * @return
     */
    public int editLost(Lost lost);

    /**
     * 根据主键查询供应商信息
     * @param id
     * @return
     */
    public Lost queryLostById(int id);

    /**
     * 根据主键删除供应商信息
     * @param id
     * @return
     */
    public int delLostById(int id);

    /**
     * 查询所有供应商数据
     * @return
     */
    public List<Lost> queryAllLost();

}
