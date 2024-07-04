package cn.tannn.demo.jdevelops.filessdk.controller;

import cn.tannn.cat.file.sdk.constants.OSSConstants;
import cn.tannn.cat.file.sdk.core.ftp.FtpUtils;
import cn.tannn.cat.file.sdk.utils.ResponseFile;
import cn.tannn.jdevelops.files.sdk.FileOperateService;
import cn.tannn.jdevelops.files.sdk.config.OssConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * ftp 特殊处理
 *
 * @author <a href="https://t.tannn.cn/">tan</a>
 * @date 2024/7/4 下午12:26
 */
@Slf4j
@RestController
public class FtpController {

    private final FileOperateService fileOperateService;
    private final OssConfig ossConfig;
    public FtpController(FileOperateService fileOperateService, OssConfig ossConfig) {
        this.fileOperateService = fileOperateService;
        this.ossConfig = ossConfig;
    }

    @Operation(summary = "ftp文件预览")
    @Parameter(name = "filePath", description = "文件索引的path", required = true)
    @Parameter(name = "storage", description = "文件存储器类型字典", required = true)
    @GetMapping(value ="/operation/"+ OSSConstants.FTP_VIEWS_API_NAME + "/{storage}")
    public void views(String storage, String filePath, HttpServletResponse response) {
        // 最好用 fileIndex ，我这里只是个测试所以有些参数是写死的
//        FileIndx fileIndex = fileIndexMetaService.findPathAndStorage;
//        FTPClient ftpClient = FtpUtils.createFtpClient(fileIndexgetStorageId());
//        fileOperateService.download(fileIndex,response);

        FTPClient ftpClient = FtpUtils.createFtpClient(ossConfig.getFtp().toStorage());
        try {
            // remote:文件存储在FTP的位置
            String fileNameEncoding = new String(filePath.getBytes(StandardCharsets.UTF_8)
                    , StandardCharsets.ISO_8859_1);
            InputStream inputStream = ftpClient.retrieveFileStream(fileNameEncoding);

//            HttpServletResponse downResponse = ResponseFile.customResponse(response
//                    , index.getType(), index.getFreshName());

            HttpServletResponse downResponse = ResponseFile.customResponse(response
                    , "image/png", "test.png");
            downResponse.setContentType("application/octet-stream");

            //设置文件大小
//            downResponse.setHeader("Content-Length", String.valueOf(index.getSize()));
            downResponse.setHeader("Content-Length", String.valueOf(28326));
            IOUtils.copy(inputStream, downResponse.getOutputStream());
        } catch (Exception e) {
            log.error("ftp文件view失败：index filePath : {}", filePath, e);
        }finally {
            FtpUtils.disConnection(ftpClient);
        }
    }
}
