package com.zhm.drug.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.zhm.drug.common.Result;
import com.zhm.drug.controller.dto.FileVO;
import com.zhm.drug.util.AliOssUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${server.port}")
    private String port;

    @Value("localhost")
    private String ip;
    /**
     * 上传接口
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result<?> upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();  // 获取源文件的名称
        // 定义文件的唯一标识（前缀）
        String flag = IdUtil.fastSimpleUUID();
        String rootFilePath = System.getProperty("user.dir") + "/src/main/resources/file/" + flag + "_" + originalFilename;  // 获取上传的路径
        File rootFile = new File(rootFilePath);
        if (!rootFile.getParentFile().exists()) {
            rootFile.getParentFile().mkdirs();
        }
        FileUtil.writeBytes(file.getBytes(), rootFilePath);  // 把文件写入到上传的路径
        return Result.success("http://" + ip + ":" + port + "/file/" + flag);  // 返回结果 url
    }

    /**
     * 富文本文件上传接口
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/editor/upload")
    public JSON editorUpload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();  // 获取源文件的名称
        // 定义文件的唯一标识（前缀）
        String flag = IdUtil.fastSimpleUUID();
        String rootFilePath = System.getProperty("user.dir") + "/src/main/resources/file/" + flag + "_" + originalFilename;  // 获取上传的路径
        File rootFile = new File(rootFilePath);
        if (!rootFile.getParentFile().exists()) {
            rootFile.getParentFile().mkdirs();
        }
        FileUtil.writeBytes(file.getBytes(), rootFilePath);  // 把文件写入到上传的路径
        String url = "http://" + ip + ":" + port + "/file/" + flag;
        JSONObject json = new JSONObject();
        json.set("errno", 0);
        //JSONArray arr = new JSONArray();
        JSONObject data = new JSONObject();
        data.set("url", url);

        json.set("data", data);
        return json;  // 返回结果 url
    }

    /**
     * 下载接口
     * @param flag
     * @param response
     */
    @GetMapping("/{flag}")
    public void getFiles(@PathVariable String flag, HttpServletResponse response) {
        OutputStream os;  // 新建一个输出流对象
        String basePath = System.getProperty("user.dir") + "/src/main/resources/file/" ;  // 定于文件上传的根路径
        List<String> fileNames = FileUtil.listFileNames(basePath);  // 获取所有的文件名称
        String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");  // 找到跟参数一致的文件
        try {
            if (StrUtil.isNotEmpty(fileName)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basePath + fileName);  // 通过文件的路径读取文件字节流
                os = response.getOutputStream();   // 通过输出流返回文件
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            System.out.println("文件下载失败");
        }
    }


    /**
     * OSS文件上传
     * @param file
     * @return
     */
    @PostMapping("/editor/upload/oss")
    public JSON editorossUpload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();  // 获取源文件的名称
        // 定义文件的唯一标识（前缀）
        String flag = IdUtil.fastSimpleUUID();
        String rootFilePath = System.getProperty("user.dir") + "/files/" + flag + "_" + originalFilename;  // 获取上传的路径
        File rootFile = new File(rootFilePath);
        if (!rootFile.getParentFile().exists()) {
            rootFile.getParentFile().mkdirs();
        }
        String url = AliOssUtil.upload("editor/image/", file);  // 返回结果
        JSONObject json = new JSONObject();
        json.set("errno", 0);
        JSONArray arr = new JSONArray();
        JSONObject data = new JSONObject();
        arr.add(data);
        data.set("url", url);
        json.set("data", arr);
        return json;  // 返回结果 url

    }
    /**
     * OSS文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload/oss/cover")
    public Result<?> ossUpload(MultipartFile file) {
        return Result.success(AliOssUtil.upload("image/cover/", file));  // 返回结果 url
    }
    @PostMapping("/upload/oss/teacher")
    public Result<?> ossUploadTeacher(MultipartFile file) {
        return Result.success(AliOssUtil.upload("image/teacher/", file));  // 返回结果 url
    }
    @PostMapping("/upload/oss/lost")
    public Result<?> ossUploadLost(MultipartFile file) {
        return Result.success(AliOssUtil.upload("image/lost/", file));  // 返回结果 url
    }
    @PostMapping("/upload/oss/avatar")
    public Result<?> ossUploadAvatar(MultipartFile file) {
        return Result.success(AliOssUtil.upload("image/Avatar/", file));  // 返回结果 url
    }

    /**
     * OSS文件删除
     * @param fileVO 文件存储路径
     * @return
     */
    @DeleteMapping("/delete/oss")
    public Result<?> ossUpload(@RequestBody FileVO fileVO) {
        AliOssUtil.delete(fileVO.getFilekey());
        return Result.success();
    }
}
