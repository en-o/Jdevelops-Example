package cn.tannn.jdevelops.demo.jpa.page;

import cn.tannn.jdevelops.demo.jpa.entity.User;
import cn.tannn.jdevelops.demo.jpa.page.dto.UserFind;
import cn.tannn.jdevelops.demo.jpa.service.UserService;
import cn.tannn.jdevelops.jpa.constant.SQLOperator;
import cn.tannn.jdevelops.jpa.request.Sorteds;
import cn.tannn.jdevelops.jpa.utils.SpecificationUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 测试排序
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/5/16 上午10:09
 */
@SpringBootTest
public class SortTest {

    @Autowired
    private UserService userService;


    /**
     * 排序查询
     */
    @Test
    void sortParams(){
        userService.finds("address", SQLOperator.EQ, new Sorteds( 0, "phone"), "重庆")
                .forEach( d -> System.out.println("phone:asc: "+d));

        userService.finds("address", SQLOperator.EQ, new Sorteds( 1, "phone"), "重庆")
                .forEach( d -> System.out.println("phone:desc: "+d));

        // 测试 Sorteds.fix order by user0_.phone asc
        userService.finds("address", SQLOperator.EQ
                        , new Sorteds( 0, "phone").fixSort("id")
                        , "重庆")
                .forEach( d -> System.out.println("fixSort:phone:asc: "+d));
        // 测试 Sorteds.fix order by user0_.id asc
        userService.finds("address", SQLOperator.EQ
                        , new Sorteds()
                        , "重庆")
                .forEach( d -> System.out.println("fixSort:id:asc: "+d));
        // 测试 Sorteds.fix order by user0_.phone asc
        userService.finds("address", SQLOperator.EQ
                        , new Sorteds( ).fixSort("phone")
                        , "重庆")
                .forEach( d -> System.out.println("fixSort:phone:asc: "+d));
    }

    /**
     *  Specification排序查询
     */
    @Test
    void sortSpecification(){
        SpecificationUtil<User> spec = SpecificationUtil.getInstance();
        userService.finds(spec.eq("address", "重庆", true, false)
                        , new Sorteds( 0, "phone"))
                .forEach( d -> System.out.println("phone:asc: "+d));

        userService.finds(spec.eq("address", "重庆", true, false)
                        , new Sorteds( 1, "phone"))
                .forEach( d -> System.out.println("phone:desc: "+d));

        userService.finds(spec.eq("address", "重庆", true, false)
                        , new Sorteds( 1, "phone"))
                .forEach( d -> System.out.println("phone:desc: "+d));
    }

    /**
     *  异体bean排序查询
     */
    @Test
    void sortDto(){

        userService.finds(new UserFind("重庆")
                        , new Sorteds( 0, "phone"))
                .forEach( d -> System.out.println("phone:asc: "+d));

        userService.finds(new UserFind("重庆")
                        , new Sorteds( 1, "phone"))
                .forEach( d -> System.out.println("phone:desc: "+d));
    }



}
