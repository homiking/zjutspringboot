package com.zhm.drug.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhm.drug.common.Result;
import com.zhm.drug.entity.News;
import com.zhm.drug.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Description
 * @Author kknever
 * @Date 2022/4/10
 **/
@RestController
@RequestMapping(value="/news")
public class NewsController {
    @Autowired
    INewsService newsService;
    @PostMapping("/add")
    public Result<?> addNews(@RequestBody News news){

        try {
            newsService.addNews(news);
            return Result.success();
        } catch (Exception e) {
            return Result.error("1","添加失败");
        }
    }
    @PutMapping("/editNews")
    public Result<?> update(@RequestBody News news){

        try {
            newsService.editNews(news);
            return Result.success();
        } catch (Exception e) {
            return Result.error("1","更新失败");
        }
    }
    @DeleteMapping("/deleteNews/{id}")
    public Result<?> delete(@PathVariable Integer id){
        try {
            newsService.delNewsById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("1","更新失败");
        }
    }
    @RequestMapping(value="/newsQueryPage")
    @ResponseBody
    public Result<?> newsQueryPage(String search, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize ){
        try{
            IPage<News> ipage = newsService.selectNewsPage(pageNum, pageSize, search);
            return Result.success(ipage);
        } catch (Exception e) {
            return Result.error("404", "查询不到数据");
        }

    }
}
