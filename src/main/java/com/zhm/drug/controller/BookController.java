package com.zhm.drug.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhm.drug.common.Result;
import com.zhm.drug.entity.Book;
import com.zhm.drug.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Description
 * @Author kknever
 * @Date 2022/4/10
 **/
@RestController
@RequestMapping(value="/book")
public class BookController {
    @Autowired
    IBookService bookService;
    @PostMapping("/add")
    public Result<?> addBook(@RequestBody Book book){

        try {
            bookService.addBook(book);
            return Result.success();
        } catch (Exception e) {
            return Result.error("1","添加失败");
        }
    }
    @PutMapping("/editBook")
    public Result<?> update(@RequestBody Book book){

        try {
            bookService.editBook(book);
            return Result.success();
        } catch (Exception e) {
            return Result.error("1","更新失败");
        }
    }
    @DeleteMapping("/deleteBook/{id}")
    public Result<?> delete(@PathVariable Integer id){
        try {
            bookService.delBookById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("1","更新失败");
        }
    }
    @RequestMapping(value="/bookQueryPage")
    @ResponseBody
    public Result<?> bookQueryPage(String search, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize ){
        try{
            IPage<Book> ipage = bookService.selectBookPage(pageNum, pageSize, search);
            return Result.success(ipage);
        } catch (Exception e) {
            return Result.error("404", "查询不到数据");
        }

    }
}
