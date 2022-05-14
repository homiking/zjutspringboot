package com.zhm.drug.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhm.drug.entity.Teacher;

import java.util.List;

public interface ITeacherService extends IService<Teacher> {
    /**
     * 分页查询供应商数据
     * @param pageNum 页数
     * @param pageSize 每页多少条数据
     * @param param 查询参数
     * @return
     */
    public IPage<Teacher> selectTeacherPage(int pageNum, int pageSize, String param);

    /**
     * 添加供应商信息
     * @param teacher
     */
    public int addTeacher(Teacher teacher);

    /**
     * 修改供应商信息
     * @param teacher
     * @return
     */
    public int editTeacher(Teacher teacher);

    /**
     * 根据主键查询供应商信息
     * @param id
     * @return
     */
    public Teacher queryTeacherById(int id);

    /**
     * 根据主键删除供应商信息
     * @param id
     * @return
     */
    public int delTeacherById(int id);

    /**
     * 查询所有供应商数据
     * @return
     */
    public List<Teacher> queryAllTeacher();

}
