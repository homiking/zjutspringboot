package com.zhm.drug.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhm.drug.common.Result;
import com.zhm.drug.entity.Lost;
import com.zhm.drug.service.ILostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Description
 * @Author kknever
 * @Date 2022/4/10
 **/
@RestController
@RequestMapping(value="/lost")
public class LostController {
    @Autowired
    ILostService lostService;
    @PostMapping("/add")
    public Result<?> addLost(@RequestBody Lost lost){

        try {
            lostService.addLost(lost);
            return Result.success();
        } catch (Exception e) {
            return Result.error("1","添加失败");
        }
    }
    @PutMapping("/editLost")
    public Result<?> update(@RequestBody Lost lost){

        try {
            lostService.editLost(lost);
            return Result.success();
        } catch (Exception e) {
            return Result.error("1","更新失败");
        }
    }
    @DeleteMapping("/deleteLost/{id}")
    public Result<?> delete(@PathVariable Integer id){
        try {
            lostService.delLostById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("1","更新失败");
        }
    }
    @RequestMapping(value="/lostQueryPage")
    @ResponseBody
    public Result<?> lostQueryPage(String search, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize ){
        try{
            IPage<Lost> ipage = lostService.selectLostPage(pageNum, pageSize, search);
            return Result.success(ipage);
        } catch (Exception e) {
            return Result.error("404", "查询不到数据");
        }

    }
}
