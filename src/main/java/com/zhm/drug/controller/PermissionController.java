package com.zhm.drug.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhm.drug.common.Result;
import com.zhm.drug.entity.Permission;
import com.zhm.drug.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Description
 * @Author kknever
 * @Date 2022/4/10
 **/
@RestController
@RequestMapping(value="/permission")
public class PermissionController {
    @Autowired
    IPermissionService permissionService;
    @PostMapping("/add")
    public Result<?> addPermission(@RequestBody Permission permission){

        try {
            permissionService.addPermission(permission);
            return Result.success();
        } catch (Exception e) {
            return Result.error("1","添加失败");
        }
    }
    @PutMapping("/editPermission")
    public Result<?> update(@RequestBody Permission permission){

        try {
            permissionService.editPermission(permission);
            return Result.success();
        } catch (Exception e) {
            return Result.error("1","更新失败");
        }
    }
    @DeleteMapping("/deletePermission/{id}")
    public Result<?> delete(@PathVariable Integer id){
        try {
            permissionService.delPermissionById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("1","更新失败");
        }
    }
    @RequestMapping(value="/permissionQueryPage")
    @ResponseBody
    public Result<?> permissionQueryPage(String search, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize ){
        try{
            IPage<Permission> ipage = permissionService.selectPermissionPage(pageNum, pageSize, search);
            return Result.success(ipage);
        } catch (Exception e) {
            return Result.error("404", "查询不到数据");
        }

    }
    @GetMapping("/all")
    public Result<?> all() {
        return Result.success(permissionService.selectList());
    }

}
