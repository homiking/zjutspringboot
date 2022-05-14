package com.zhm.drug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhm.drug.entity.Lost;
import com.zhm.drug.mapper.LostMapper;
import com.zhm.drug.service.ILostService;
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
public class LostServiceImpl extends ServiceImpl<LostMapper, Lost> implements ILostService {
    @Autowired
    LostMapper lostMapper;

    @Override
    public IPage<Lost> selectLostPage(int pageNum, int pageSize, String param) {
        QueryWrapper<Lost> wrapper = new QueryWrapper<>();
        if(! StringUtils.isEmpty(param)) {
            // like 是模糊查询, eq 是完全相等的查询
            wrapper.like("name", param);
        }
        Page<Lost> page = new Page<>(pageNum, pageSize);
        return lostMapper.selectPage(page, wrapper);
    }

    @Override
    public int addLost(Lost lost) {
        return lostMapper.insert(lost);
    }

    @Override
    public int editLost(Lost lost) {
        return lostMapper.updateById(lost);
    }

    @Override
    public Lost queryLostById(int id) {
        return lostMapper.selectById(id);
    }

    @Override
    public int delLostById(int id) {
        return lostMapper.deleteById(id);
    }

    @Override
    public List<Lost> queryAllLost() {
       return lostMapper.selectList(null);
    }
}
