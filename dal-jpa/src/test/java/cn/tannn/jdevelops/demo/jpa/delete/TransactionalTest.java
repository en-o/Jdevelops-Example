package cn.tannn.jdevelops.demo.jpa.delete;

import cn.tannn.jdevelops.demo.jpa.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * 执行下面的sql录入测试数据
  INSERT INTO `sys_user` (`id`,`update_user_name`, `create_user_name`, `create_time`, `update_time`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`)
   VALUES ('100', 'admin', 'admin', '2021-12-10 15:02:49', '2021-12-10 15:03:00', 'delete6', '111', '222', 'LR-01', '1231', '1312', NULL);
 */
@SpringBootTest
public class TransactionalTest {

    @Autowired
    private UserService userService;

    /**
     * 正常删除
     */
    @Test
    void normalDelete(){
        userService.deleteEq("userNo", "delete6");
    }

    /**
     * 异常回滚（错误跟delete在同一个事务里）
     */
    @Test
    @Transactional(rollbackFor = Exception.class)
    void testDeleteTransactional() {
        userService.deleteEq("userNo", "delete6");
        System.out.println(1 / 0);
    }

    /**
     * 不加事务注解会不回滚，因为 错误跟 delete不在同一个事务里
     */
    @Test
    void testDeleteTransactional1(){
        userService.deleteEq("userNo", "delete6");
        System.out.println(1/0);
    }


    /**
     * 添加事务但是捕捉了异常： 不会回滚因为错误被捕捉
     */
    @Test
    @Transactional(rollbackFor = Exception.class)
    @Rollback(value = false)// 事务自动回滚，默认是true。可以不写
    void testDeleteTransactional2(){
        userService.deleteEq("userNo", "delete6");
        try {
            System.out.println(1/0);
        }catch (Exception e){
//            e.printStackTrace();
            System.out.println("捕捉异常使其不会滚");
        }
    }

}
