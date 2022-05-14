package com.zhm.drug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhm.drug.entity.Supplier;
import com.zhm.drug.entity.User;
import com.zhm.drug.mapper.SupplierMapper;
import com.zhm.drug.mapper.UserMapper;
import com.zhm.drug.service.ISupplierService;
import com.zhm.drug.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import sun.swing.StringUIClientPropertyKey;

import java.util.List;

/**
 * @Description
 * @Author kknever
 * @Date 2022/4/10
 **/
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements ISupplierService {
    @Autowired
    SupplierMapper supplierMapper;

    @Override
    public IPage<Supplier> selectSupplierPage(int pageNum, int pageSize, String param) {
        QueryWrapper<Supplier> wrapper = new QueryWrapper<>();
        if(! StringUtils.isEmpty(param)) {
            // like 是模糊查询, eq 是完全相等的查询
            wrapper.like("name", param);
        }
        Page<Supplier> page = new Page<>(pageNum, pageSize);
        return supplierMapper.selectPage(page, wrapper);
    }

    @Override
    public int addSupplier(Supplier supplier) {
        return supplierMapper.insert(supplier);
    }

    @Override
    public int editSupplier(Supplier supplier) {
        return supplierMapper.updateById(supplier);
    }

    @Override
    public Supplier querySupplierById(int id) {
        return supplierMapper.selectById(id);
    }

    @Override
    public int delSupplierById(int id) {
        return supplierMapper.deleteById(id);
    }

    @Override
    public List<Supplier> queryAllSupplier() {
       return supplierMapper.selectList(null);
    }
}
