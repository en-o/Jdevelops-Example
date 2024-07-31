package cn.tannn.demo.jdevelops.frameworksquick.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户接口
 *
 * @author <a href="https://t.tannn.cn/">tan</a>
 * @version V1.0
 * @date 2024/7/31 上午9:32
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("init")
    public void init(){
        userDao.save(User.create("张三", "北京", "zhangsan", "12345678901"));
        userDao.save(User.create("李四", "重庆", "cq", "1254889"));
    }

    @GetMapping("find")
    public List<User> find(){
        return userDao.findAll();
    }

}
