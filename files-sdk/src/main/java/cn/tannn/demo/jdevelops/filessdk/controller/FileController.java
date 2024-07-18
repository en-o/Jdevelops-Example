package cn.tannn.demo.jdevelops.filessdk.controller;

import cn.tannn.cat.file.sdk.api.UploadFile;
import cn.tannn.cat.file.sdk.api.UploadFiles;
import cn.tannn.cat.file.sdk.bean.FileIndex;
import cn.tannn.jdevelops.files.sdk.FileOperateService;
import cn.tannn.jdevelops.result.response.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author <a href="https://t.tannn.cn/">tan</a>
 * @date 2024/7/2 下午1:37
 */
@RequestMapping("files")
@Slf4j
@RestController
public class FileController {

    private final FileOperateService fileOperateService;

    public FileController(FileOperateService fileOperateService) {
        this.fileOperateService = fileOperateService;
    }

    /**
     * master上传
     *
     * @param upload UploadFile
     * @return FileStorageVO
     * @throws IOException Exception
     */
    @PostMapping(value = "upload")
    @Operation(summary = "上传文件", description = "master上传")
    public ResultVO<FileIndex> upload(@Valid UploadFile upload) throws IOException {
        FileIndex fileIndex = fileOperateService.upload(upload);
        return ResultVO.success(fileIndex);
    }


    /**
     * 选择存储器上传
     *
     * @param upload    UploadFile
     * @param storageId 存储器ID [ftp:1, local:2 , minio:3 , qiniu:4 ] ,必须是配置了
     * @return FileStorageVO
     * @throws IOException Exception
     */
    @PostMapping(value = "upload/{storageId}")
    @Operation(summary = "上传文件", description = "选择存储器上传")
    public ResultVO<FileIndex> upload(@PathVariable("storageId") Long storageId, @Valid UploadFile upload) throws IOException {
        FileIndex fileIndex = fileOperateService.upload(storageId, upload);
        return ResultVO.success(fileIndex);
    }


    /**
     * master 批量上传文件
     *
     * @param uploads UploadFiles
     * @return String
     * @throws IOException Exception
     */

    @PostMapping(value = "uploads")
    @Operation(summary = "批量上传文件", description = "master上传")
    public ResultVO<String> uploads(@Valid UploadFiles uploads) throws IOException {
        fileOperateService.uploads(uploads);
        return ResultVO.success("批量上传成功");
    }


    /**
     * 下载文件
     *
     * @param fileIndex 文件存储的索引
     * @param response  HttpServletResponse
     */
    @PostMapping(value = "download")
    @Operation(summary = "下载文件")
    @Parameter(name = "fileStorageId", description = "文件索引的ID", required = true)
    public void download(@RequestBody @Valid FileIndex fileIndex, HttpServletResponse response) {
        // FileIndex(上传接口返回的数据) 一般会存在库里，真实情况下只需要传入一个唯一ID进行查询 fileIndex数据然后传入就行了
        fileOperateService.download(fileIndex, response);
    }


    /**
     * 删除文件
     *
     * @param fileIndex 文件存储的索引
     * @return String
     */
    @DeleteMapping("/remove")
    @Operation(summary = "删除文件")
    @Parameter(name = "fileIndexId", description = "文件索引的ID", required = true)
    public ResultVO<String> remove(@RequestBody @Valid FileIndex fileIndex) {
        // FileIndex(上传接口返回的数据) 一般会存在库里，真实情况下只需要传入一个唯一ID进行查询 fileIndex数据然后传入就行了
        fileOperateService.remove(fileIndex);
        return ResultVO.successMessage("删除成功");
    }


}
