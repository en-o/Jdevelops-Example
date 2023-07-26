package cn.tan.authentication.sas.resource.web;

import cn.jdevelops.api.result.response.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

	@GetMapping("/resource1")
	public ResultVO getResource1(){
		return ResultVO.success("服务A -> 资源1 -> 读权限");
	}

	@GetMapping("/resource2")
	public ResultVO getResource2(){
		return ResultVO.success("服务A -> 资源2 -> 写权限");
	}

	@GetMapping("/resource3")
	public ResultVO resource3(){
		return ResultVO.success("服务A -> 资源3 -> profile 权限");
	}

	@GetMapping("/api/publicResource")
	public ResultVO publicResource() {
		return ResultVO.success("服务A -> 公共资源");
	}
}
