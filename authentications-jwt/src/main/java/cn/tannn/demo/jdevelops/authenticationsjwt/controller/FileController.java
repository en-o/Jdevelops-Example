package cn.tannn.demo.jdevelops.authenticationsjwt.controller;

import cn.jdevelops.file.oss.api.OssOperateAPI;
import cn.jdevelops.file.oss.api.bean.*;
import cn.tannn.jdevelops.exception.built.BusinessException;
import cn.tannn.jdevelops.result.response.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * 测试本地文件访问呢鉴权
 * @see <a href="https://www.yuque.com/tanning/yg9ipo/lkyzxvgfqgwqrtkt">Local OSS Resource access authentication</a>
 */
@RestController
@Tag(name = "文件上传下载",description = "文件测试")
public class FileController {


	@Autowired
	private OssOperateAPI fileOperation;


	@Operation(summary = "文件上传")
	@PostMapping(value = "upload")
	public ResultVO<FilePathResult> upload(@Valid UploadDTO uploadDTO) {
		try {
			FilePathResult filePathResult = fileOperation.uploadFile(uploadDTO);
			return ResultVO.success(filePathResult);
		} catch (Exception e) {
			throw new BusinessException("文件上传失败！", e);
		}
	}


	@Operation(summary = "批量文件上传")
	@PostMapping(value = "uploads")
	public ResultVO<List<FilePathResult>> uploads(@Valid UploadsDTO uploadDTO) {
		try {
			return ResultVO.success(fileOperation.uploadFile(uploadDTO));
		} catch (Exception e) {
			throw new BusinessException("文件上传失败！", e);
		}
	}



	@GetMapping("/download")
	@Operation(summary = "文件下载")
	public void download(@ParameterObject  @Valid DownloadDTO dto, HttpServletResponse response) {
		try {
			fileOperation.downloadFile(response, dto);
		} catch (Exception e) {
			throw new BusinessException("文件下载失败！",e);
		}
	}

	@GetMapping("/expiry/url")
	@Operation(summary = "获取有效期访问地址")
	public ResultVO<String> getExpiryObjectUrl(@ParameterObject @Valid ExpireDateDTO dto) {
		try {
			String url = fileOperation.expireDateUrl(dto);
			return ResultVO.success(url);
		} catch (Exception e) {
			throw new BusinessException("获取有效期访问地址失败！");
		}
	}



	@DeleteMapping("/remove")
	@Operation(summary = "删除")
	public ResultVO<List<String>> removeObjects(@RequestBody @Valid RemoveFileDTO dto) {
		try {
			fileOperation.removeFiles(dto);
			return ResultVO.success();
		} catch (Exception e) {
			throw new BusinessException("删除失败！",e);
		}
	}
}
