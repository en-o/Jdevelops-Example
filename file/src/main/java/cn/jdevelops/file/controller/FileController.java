package cn.jdevelops.file.controller;

import cn.jdevelops.exception.exception.BusinessException;
import cn.jdevelops.file.OssOperateAPI;
import cn.jdevelops.file.bean.*;
import cn.jdevelops.result.result.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * 文件上传下载
 *
 * @author lxw
 * @version V1.0
 * @date 2021/11/10
 */
@RestController
@Api(tags = "文件操作", value = "文件管理")
public class FileController {


	@Autowired
	private OssOperateAPI fileOperation;



	@ApiOperation(value = "文件上传", notes = "文件管理")
	@PostMapping(value = "upload")
	public ResultVO<FilePathResult> upload(@Valid UploadDTO uploadDTO) {
		try {
			FilePathResult filePathResult = fileOperation.uploadFile(uploadDTO);
			return ResultVO.successForData(filePathResult);
		} catch (Exception e) {
			throw new BusinessException("文件上传失败！", e);
		}
	}


	@ApiOperation(value = "批量文件上传", notes = "文件管理")
	@PostMapping(value = "uploads")
	public ResultVO<List<FilePathResult>> uploads(@Valid UploadsDTO uploadDTO) {
		try {
			return ResultVO.successForData(fileOperation.uploadFile(uploadDTO));
		} catch (Exception e) {
			throw new BusinessException("文件上传失败！", e);
		}
	}



	@GetMapping("/download")
	@ApiOperation(value = "文件下载", notes = "文件管理")
	public void download(HttpServletResponse response, @Valid DownloadDTO dto) {
		try {
			fileOperation.downloadFile(response, dto);
		} catch (Exception e) {
			throw new BusinessException("文件下载失败！",e);
		}
	}

	@GetMapping("/getExpiryObjectUrl")
	@ApiOperation(value = "获取有效期访问地址", notes = "文件管理")
	public ResultVO<String> getExpiryObjectUrl(@Valid ExpireDateDTO dto) {
		try {
			String url = fileOperation.expireDateUrl(dto);
			return ResultVO.successForData(url);
		} catch (Exception e) {
			throw new BusinessException("获取有效期访问地址失败！");
		}
	}



	@DeleteMapping("/removeObjects")
	@ApiOperation(value = "删除", notes = "文件管理")
	public ResultVO<List<String>> removeObjects(@RequestBody @Valid RemoveFileDTO dto) {
		try {
			fileOperation.removeFiles(dto);
			return ResultVO.success();
		} catch (Exception e) {
			throw new BusinessException("删除失败！",e);
		}
	}
}
