package com.zhm.drug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhm.drug.entity.Teacher;
import com.zhm.drug.mapper.TeacherMapper;
import com.zhm.drug.service.ITeacherService;
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
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public IPage<Teacher> selectTeacherPage(int pageNum, int pageSize, String param) {
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        if(! StringUtils.isEmpty(param)) {
            // like 是模糊查询, eq 是完全相等的查询
            wrapper.like("name", param);
        }
        Page<Teacher> page = new Page<>(pageNum, pageSize);
        return teacherMapper.selectPage(page, wrapper);
    }

    @Override
    public int addTeacher(Teacher teacher) {
        return teacherMapper.insert(teacher);
    }

    @Override
    public int editTeacher(Teacher teacher) {
        return teacherMapper.updateById(teacher);
    }

    @Override
    public Teacher queryTeacherById(int id) {
        return teacherMapper.selectById(id);
    }

    @Override
    public int delTeacherById(int id) {
        return teacherMapper.deleteById(id);
    }

    @Override
    public List<Teacher> queryAllTeacher() {
       return teacherMapper.selectList(null);
    }
}
