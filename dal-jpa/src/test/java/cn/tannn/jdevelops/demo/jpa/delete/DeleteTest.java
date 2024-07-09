package cn.tannn.jdevelops.demo.jpa.delete;

import cn.tannn.jdevelops.demo.jpa.delete.dto.UserDelete;
import cn.tannn.jdevelops.demo.jpa.entity.User;
import cn.tannn.jdevelops.demo.jpa.service.UserService;
import cn.tannn.jdevelops.jpa.constant.SQLOperator;
import cn.tannn.jdevelops.jpa.utils.SpecificationUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 测试 j2service 删除
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/5/16 下午2:23
 */
@SpringBootTest
public class DeleteTest {
    @Autowired
    private UserService userService;

    // 准备数据
    @Test
    void add(){
        userService.saves(Arrays.asList(
                User.create("tan1","cq1","tan1","1231"),
                User.create("tan2","cq2","tan2","1232"),
                User.create("tan3","cq3","tan3","1233"),
                User.create("tan4","cq4","tan4","1234"),
                // LIKE DELETE
                User.create("delete_tan2","cq2","delete_tan2","1232"),
                User.create("delete_tan3","cq3","delete_tan3","1233"),
                User.create("delete_tan4","cq4","delete_tan4","1234"),

                // in DELETE
                User.create("in_tan2","cq2","in_tan2","1232"),
                User.create("in_tan3","cq3","in_tan3","1233"),
                User.create("in_tan4","cq4","in_tan4","1234"),

                // is null DELETE
                User.create("tan5",null,"tan5","1232"),
                User.create("tan6",null,"tan6","1233"),
                User.create("tan7",null,"tan7","1234")
        ));
    }


    @Test
    void deleteParameter(){
        userService.deleteEq("name","tan2");
    }

    @Test
    void deleteParameter_LIKE(){
        // delete from sys_user where name like ?
        userService.delete("name", SQLOperator.LIKE,"delete_");
    }
    @Test
    void deleteParameter_IN(){
        // delete from sys_user where name in (? , ?)
        // delete from sys_user where name in ('in_tan2' , 'in_tan3')
        userService.delete("name", SQLOperator.IN,"in_tan2","in_tan3");
        // delete from sys_user where name in ('in_tan4' , 'in_tan5')
        userService.delete("name", SQLOperator.IN, Arrays.asList("in_tan4","in_tan5"));

        // sys_user where id in (10 , 20)
        List<Long> ids = new ArrayList<>();
        ids.add(10L);
        ids.add(20L);
        userService.delete("id", SQLOperator.IN, ids);

        // delete from sys_user where name in ('in_tan8' , 'in_tan7')
        List<String> idss = new ArrayList<>();
        idss.add("in_tan8");
        idss.add("in_tan7");
        userService.delete("name", SQLOperator.IN, idss);
    }
    @Test
    void deleteParameter_ISNULL(){
        userService.delete("address", SQLOperator.ISNULL);
    }

    @Test
    void deleteParameter_Bean(){
        userService.delete(new UserDelete("cq3"));
    }

    @Test
    void deleteParameter_Specification(){
        SpecificationUtil<User> instance = SpecificationUtil.getInstance();
        Specification<User> eq = instance.eq("address", "cq4", true, false);
        userService.delete(eq);
    }

}
