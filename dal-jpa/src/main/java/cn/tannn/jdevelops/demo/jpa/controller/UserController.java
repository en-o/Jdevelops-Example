package cn.tannn.jdevelops.demo.jpa.controller;

import cn.tannn.jdevelops.demo.jpa.dto.UserFindDTO;
import cn.tannn.jdevelops.demo.jpa.entity.User;
import cn.tannn.jdevelops.demo.jpa.service.UserService;
import cn.tannn.jdevelops.jpa.constant.SQLOperator;
import cn.tannn.jdevelops.jpa.request.Sorteds;
import cn.tannn.jdevelops.jpa.select.EnhanceSpecification;
import cn.tannn.jdevelops.result.response.ResultVO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 测试 J2Service
 *
 * @author tn
 * @version 1
 * @date 2021-12-12 14:48
 */
@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("saves")
    public ResultVO<String> saves(List<User> user) {
        userService.saves(user);
        return ResultVO.success();
    }

    @PostMapping("saveOne")
    public ResultVO<String> saveOne(User user) {
        userService.saveOne(user);
        return ResultVO.success();
    }

    @PostMapping("saveOneByVo")
    public ResultVO<String> saveOneByVo(@RequestBody UserFindDTO user) {
        userService.saveOneByVo(user);
        return ResultVO.success();
    }

    @DeleteMapping("delete")
    public ResultVO<String> delete(@RequestBody UserFindDTO user) {
        Specification<User> spec = EnhanceSpecification.beanWhere(user);
        userService.delete(spec);
        return ResultVO.success();
    }

    @DeleteMapping("delete2")
    public ResultVO<String> delete2(@RequestParam("fieldName") String fieldName
            , @RequestParam(value = "value") String value) {
        userService.delete(fieldName, value);
        return ResultVO.success();
    }

    @DeleteMapping("delete3")
    public ResultVO<String> delete3(@RequestParam("fieldName") String fieldName
            , @RequestParam("operator") SQLOperator operator
            , @RequestParam(value = "value", required = false) String value) {
        userService.delete(fieldName, operator, value);
        return ResultVO.success();
    }

    @DeleteMapping("delete4")
    public ResultVO<String> delete4(@RequestBody UserFindDTO user) {
        userService.delete(user);
        return ResultVO.success();
    }

    @PutMapping("update")
    public ResultVO<String> update(@RequestBody UserFindDTO user, @RequestParam(value = "fieldName", required = false) String fieldName) {
        userService.update(user, SQLOperator.EQ, fieldName);
        return ResultVO.success();
    }


    @GetMapping("findOnly")
    public ResultVO<User> findOnly(@RequestParam("fieldName") String fieldName
            , @RequestParam(value = "value") String value) {
        return ResultVO.success(userService.findOnly(fieldName, value).get());
    }

    @GetMapping("findOnly2")
    public ResultVO<User> findOnly2(@RequestParam("fieldName") String fieldName
            , @RequestParam(value = "value") String value
            , @RequestParam("fieldName2") String fieldName2
            , @RequestParam(value = "value2") String value2) {
        return ResultVO.success(userService.findOnly(fieldName, value, fieldName2, value2).get());
    }

    @GetMapping("findOnly3")
    public ResultVO<User> findOnly3(@RequestBody UserFindDTO user) {
        Specification<User> spec = EnhanceSpecification.beanWhere(user);
        return ResultVO.success(userService.findOnly(spec).get());
    }

    @GetMapping("finds")
    public ResultVO<List<User>> finds() {
        return ResultVO.success(userService.finds());
    }

    @GetMapping("finds2")
    public ResultVO<List<User>> finds2(@RequestParam("fieldName") String fieldName
            , @RequestParam("operator") SQLOperator operator
            , @RequestParam(value = "value", required = false) String value) {
        return ResultVO.success(userService.finds(fieldName, operator, value));
    }

    @PostMapping("finds3")
    public ResultVO<List<User>> finds3(@RequestParam("fieldName") String fieldName
            , @RequestParam("operator") SQLOperator operator
            , @RequestBody Sorteds sort
            , @RequestParam(value = "value", required = false) String value) {
        return ResultVO.success(userService.finds(fieldName, operator, sort, value));
    }


    @PostMapping("finds4")
    public ResultVO<List<User>> finds4(@RequestBody UserFindDTO user, @RequestBody Sorteds sort) {
        Specification<User> spec = EnhanceSpecification.beanWhere(user);
        return ResultVO.success(userService.finds(spec, sort));
    }


    @PostMapping("findComplex")
    public ResultVO<List<User>> findComplex(@RequestBody UserFindDTO user) {
        return ResultVO.success(userService.findComplex(user));
    }

    @PostMapping("findComplex2")
    public ResultVO<List<User>> findComplex(@RequestBody UserFindDTO user, @RequestBody Sorteds sort) {
        List<User> complex = userService.findComplex(user);
        return ResultVO.success(userService.findComplex(user, sort));
    }


//
//    // ============ find page
//
//    /**
//     * 分页-排序
//     *
//     * @param pageable 分页{@link Pagings}
//     * @return Page of B
//     */
//    Page<B> findPage(Pagings pageable);
//
//    /**
//     * 分页-排序
//     *
//     * @param pageable 分页{@link PagingSorteds}
//     * @return Page of B
//     */
//    Page<B> findPage(PagingSorteds pageable);
//
//    /**
//     * 分页-查询
//     *
//     * @param req      查询条件
//     * @param pageable 分页 {@link Pagings}
//     * @param <T>      数据实体的VO TDO BO PO等异形类
//     * @return page  如果想要处理成接口能返回的请使用{@link JpaPageResult#toPage(Page)}
//     */
//    <T> Page<B> findPage(T req, Pagings pageable);
//
//    /**
//     * 分页-查询
//     *
//     * @param req      查询条件
//     * @param pageable 分页 {@link PagingSorteds}
//     * @param <T>      数据实体的VO TDO BO PO等异形类
//     * @return page  如果想要处理成接口能返回的请使用{@link JpaPageResult#toPage(Page)}
//     */
//    <T> Page<B> findPage(T req, PagingSorteds pageable);

}
