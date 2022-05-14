package com.zhm.drug.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhm.drug.common.Result;
import com.zhm.drug.entity.Supplier;
import com.zhm.drug.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Description
 * @Author kknever
 * @Date 2022/4/10
 **/
@RestController
@RequestMapping(value="/supplier")
public class SupplierController {
    @Autowired
    ISupplierService supplierService;
    @PostMapping("/add")
    public Result<?> addSupplier(@RequestBody Supplier supplier){

        try {
            supplierService.addSupplier(supplier);
            return Result.success();
        } catch (Exception e) {
            return Result.error("1","添加失败");
        }
    }
    @PutMapping("/editSupplier")
    public Result<?> update(@RequestBody Supplier supplier){

        try {
            supplierService.editSupplier(supplier);
            return Result.success();
        } catch (Exception e) {
            return Result.error("1","更新失败");
        }
    }
    @DeleteMapping("/deleteSupplier/{id}")
    public Result<?> delete(@PathVariable Integer id){
        try {
            supplierService.delSupplierById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("1","更新失败");
        }
    }
    @RequestMapping(value="/supplierQueryPage")
    @ResponseBody
    public Result<?> supplierQueryPage(String search, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize ){
        try{
            IPage<Supplier> ipage = supplierService.selectSupplierPage(pageNum, pageSize, search);
            return Result.success(ipage);
        } catch (Exception e) {
            return Result.error("404", "查询不到数据");
        }

    }
}
