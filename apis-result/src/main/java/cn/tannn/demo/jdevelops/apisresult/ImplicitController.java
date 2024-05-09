package cn.tannn.demo.jdevelops.apisresult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 隐式强制返回包裹类：jdevelops.api.result.enabled=true
 * <p> prod 配置文件
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/5/9 上午9:38
 */
@RestController
@RequestMapping("implicit")
public class ImplicitController {


    @GetMapping("/string")
    public String string(){
        return "测试隐式添加包裹类";
    }


    @GetMapping("/list")
    public List<String> list(){
        return Collections.singletonList("测试隐式添加包裹类");
    }

    @GetMapping("/map")
    public Map<String,String> map(){
        return Collections.singletonMap("tan","测试隐式添加包裹类");
    }

    @GetMapping("/bean")
    public User bean(){
        return new User("tan",1,2);
    }
}
