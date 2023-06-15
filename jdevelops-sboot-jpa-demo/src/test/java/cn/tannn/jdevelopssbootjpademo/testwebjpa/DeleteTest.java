package cn.tannn.jdevelopssbootjpademo.testwebjpa;

import cn.hutool.core.lang.Assert;
import cn.jdevelops.data.jap.enums.SpecBuilderDateFun;
import cn.jdevelops.data.jap.util.JpaUtils;
import cn.tannn.jdevelopssbootjpademo.entity.User;
import cn.tannn.jdevelopssbootjpademo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import javax.validation.constraints.AssertFalse;
import java.util.Arrays;
import java.util.Objects;

/**
 * 删除
 *
 * @author tnnn
 * @version V1.0
 * @date 2023-03-26 15:19
 */
@SpringBootTest
public class DeleteTest {
    @Autowired
    private UserService userService;


    @Test
    void testDeleteBySpec(){
        /*
        INSERT INTO `test`.`sys_user` ( `create_time`, `update_time`, `create_user_name`, `update_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon` )
            VALUES
	        (  '2023-05-30 09:26:29', '2023-05-30 09:26:29', 'tan', 'tan', 'delete', 'test', '123', 'test', '123', '123', '' );

         INSERT INTO `test`.`sys_user` ( `create_time`, `update_time`, `create_user_name`, `update_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon` )
            VALUES
	        (  '2011-05-30 09:26:29', '2023-05-30 09:26:29', 'tan', 'tan', 'delete_spec_1', 'test', '123', 'test', '123', '123', '' );


        INSERT INTO `test`.`sys_user` ( `create_time`, `update_time`, `create_user_name`, `update_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon` )
                VALUES
                (  '2011-05-30 09:26:29', '2023-05-30 09:26:29', 'tan', 'tan', 'delete_spec_2', 'test', '123', 'test', '123', '123', '' );

           INSERT INTO `test`.`sys_user` ( `create_time`, `update_time`, `create_user_name`, `update_user_name`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon` )
                VALUES
                (  '2023-05-30 09:26:29', '2023-05-30 09:26:29', 'tan', 'tan', 'delete_spec_3', 'test', '123', 'test', '123', '123', '' );

         */

        long delete = userService.delete((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userNo"), "delete"));
        System.out.printf("delete_number: %d\n", delete);
        Assert.isTrue(delete>=0);


        //  time eq 2011-05-30 or  userNo eq delete_spec_3
        // delete from sys_user where user_no=? or date_format(create_time, ?)=?
        Specification<User> specification = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("userNo"), "delete_spec_3");
        long delete2 = userService.delete(specification.or((root, query, cb) ->
                cb.equal(JpaUtils.functionTimeFormat(SpecBuilderDateFun.DATE_FORMAT_DATE,
                        root,cb,"createTime"),"2011-05-30")));
        System.out.printf("delete_number: %d\n", delete);
        Assert.isTrue(delete2>=0);
    }





    @Test
    void testDeleteByUniques(){
        // INSERT INTO `test`.`sys_user` (`update_user_name`, `create_user_name`, `create_time`, `update_time`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES ('admin', 'admin', '2021-12-10 15:02:49', '2021-12-10 15:03:00', 'delete7', '111', '222', 'LR-01', '1231', '1312', NULL);
        // INSERT INTO `test`.`sys_user` (`update_user_name`, `create_user_name`, `create_time`, `update_time`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES ('admin', 'admin', '2021-12-10 15:02:49', '2021-12-10 15:03:00', 'delete8', '111', '222', 'LR-01', '1231', '1312', NULL);
        userService.deleteByUnique(Arrays.asList("delete7","delete8"),User::getUserNo);
    }


    @Test
    void testDeleteByUnique(){
        //INSERT INTO `test`.`sys_user` (`update_user_name`, `create_user_name`, `create_time`, `update_time`, `user_no`, `name`, `address`, `login_name`, `login_pwd`, `phone`, `user_icon`) VALUES ('admin', 'admin', '2021-12-10 15:02:49', '2021-12-10 15:03:00', 'delete6', '111', '222', 'LR-01', '1231', '1312', NULL);
        userService.deleteByUnique("delete6", User::getUserNo);
    }
}
