package com.zhm.drug.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhm.drug.common.Result;
import com.zhm.drug.entity.Teacher;
import com.zhm.drug.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Description
 * @Author kknever
 * @Date 2022/4/10
 **/
@RestController
@RequestMapping(value="/teacher")
public class TeacherController {
    @Autowired
    ITeacherService teacherService;
    @PostMapping("/add")
    public Result<?> addTeacher(@RequestBody Teacher teacher){

        try {
            teacherService.addTeacher(teacher);
            return Result.success();
        } catch (Exception e) {
            return Result.error("1","添加失败");
        }
    }
    @PutMapping("/editTeacher")
    public Result<?> update(@RequestBody Teacher teacher){

        try {
            teacherService.editTeacher(teacher);
            return Result.success();
        } catch (Exception e) {
            return Result.error("1","更新失败");
        }
    }
    @DeleteMapping("/deleteTeacher/{id}")
    public Result<?> delete(@PathVariable Integer id){
        try {
            teacherService.delTeacherById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("1","更新失败");
        }
    }
    @RequestMapping(value="/teacherQueryPage")
    @ResponseBody
    public Result<?> teacherQueryPage(String search, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize ){
        try{
            IPage<Teacher> ipage = teacherService.selectTeacherPage(pageNum, pageSize, search);
            return Result.success(ipage);
        } catch (Exception e) {
            return Result.error("404", "查询不到数据");
        }

    }
}
