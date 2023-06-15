package cn.tannn.jdevelopssbootjpademo.testwebjpa;

import cn.tannn.jdevelopssbootjpademo.entity.User;
import cn.tannn.jdevelopssbootjpademo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
public class TransactionalTest {

    @Autowired
    private UserService userService;

    /**
     * 异常回滚（错误跟delete在同一个事务里）
     */
    @Test
    @Transactional(rollbackFor = Exception.class)
    void testDeleteTransactional(){
        //INSERT INTO `test`.`sys_user` (`update_user_name`, `create_user_name`, `create_time`, `update_time`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES ('admin', 'admin', '2021-12-10 15:02:49', '2021-12-10 15:03:00', 'delete6', '111', '222', 'LR-01', '1231', '1312', NULL);
        userService.deleteByUnique("delete6", User::getUserNo);
        System.out.println(1/0);
    }

    /**
     * 不加事务注解会不回滚，应为 错误跟 delete不在同一个事务里
     */
    @Test
    void testDeleteTransactional1(){
        //INSERT INTO `test`.`sys_user` (`update_user_name`, `create_user_name`, `create_time`, `update_time`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES ('admin', 'admin', '2021-12-10 15:02:49', '2021-12-10 15:03:00', 'delete6', '111', '222', 'LR-01', '1231', '1312', NULL);
        userService.deleteByUnique("delete6", User::getUserNo);
        System.out.println(1/0);
    }


    @Test
    @Transactional(rollbackFor = Exception.class)
    @Rollback(value = false)// 事务自动回滚，默认是true。可以不写
    void testDeleteTransactional2(){
        //INSERT INTO `test`.`sys_user` (`update_user_name`, `create_user_name`, `create_time`, `update_time`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES ('admin', 'admin', '2021-12-10 15:02:49', '2021-12-10 15:03:00', 'delete6', '111', '222', 'LR-01', '1231', '1312', NULL);
        userService.deleteByUnique("delete6", User::getUserNo);
        try {
            System.out.println(1/0);
        }catch (Exception e){
//            e.printStackTrace();
            System.out.println("捕捉异常使其不会滚");
        }
    }


    /**
     * 会滚回滚
     */
    @Test
    void testDeleteTransactional3(){
        //INSERT INTO `test`.`sys_user` (`update_user_name`, `create_user_name`, `create_time`, `update_time`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES ('admin', 'admin', '2021-12-10 15:02:49', '2021-12-10 15:03:00', 'delete6', '111', '222', 'LR-01', '1231', '1312', NULL);
        new TransactionalTest().transactional();
    }


    /**
     * 测试异常捕捉之后会不会回滚（在同一个事务，默认是要回滚的）
     */
    @Transactional(rollbackFor = Exception.class)
    public void transactional(){
        userService.deleteByUnique("delete6", User::getUserNo);
        System.out.println(1/0);
    }
}
