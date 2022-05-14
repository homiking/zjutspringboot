package com.zhm.drug.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhm.drug.entity.Supplier;
import com.zhm.drug.entity.User;

import java.util.List;

public interface ISupplierService extends IService<Supplier> {
    /**
     * 分页查询供应商数据
     * @param pageNum 页数
     * @param pageSize 每页多少条数据
     * @param param 查询参数
     * @return
     */
    public IPage<Supplier> selectSupplierPage(int pageNum, int pageSize, String param);

    /**
     * 添加供应商信息
     * @param supplier
     */
    public int addSupplier(Supplier supplier);

    /**
     * 修改供应商信息
     * @param supplier
     * @return
     */
    public int editSupplier(Supplier supplier);

    /**
     * 根据主键查询供应商信息
     * @param id
     * @return
     */
    public Supplier querySupplierById(int id);

    /**
     * 根据主键删除供应商信息
     * @param id
     * @return
     */
    public int delSupplierById(int id);

    /**
     * 查询所有供应商数据
     * @return
     */
    public List<Supplier> queryAllSupplier();

}
