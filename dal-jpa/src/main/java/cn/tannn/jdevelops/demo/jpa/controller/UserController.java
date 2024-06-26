package cn.tannn.jdevelops.demo.jpa.controller;

import cn.tannn.jdevelops.annotations.jpa.JpaSelectOperator;
import cn.tannn.jdevelops.demo.jpa.controller.pojo.*;
import cn.tannn.jdevelops.demo.jpa.entity.User;
import cn.tannn.jdevelops.demo.jpa.service.UserService;
import cn.tannn.jdevelops.jpa.constant.SQLOperator;
import cn.tannn.jdevelops.jpa.request.PagingSorteds;
import cn.tannn.jdevelops.jpa.request.Pagings;
import cn.tannn.jdevelops.jpa.request.Sorteds;
import cn.tannn.jdevelops.jpa.result.JpaPageResult;
import cn.tannn.jdevelops.jpa.select.EnhanceSpecification;
import cn.tannn.jdevelops.result.response.ResultPageVO;
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


    /**
     * 批量save
     *
     * @param user User
     * @return User
     */
    @PostMapping("saves")
    public ResultVO<List<User>> saves(@RequestBody List<User> user) {
        return ResultVO.success(userService.saves(user));
    }


    /**
     * 单个save
     *
     * @param user User
     * @return User
     */
    @PostMapping("saveOne")
    public ResultVO<User> saveOne(User user) {
        return ResultVO.success(userService.saveOne(user));
    }

    /**
     * 利用异体DTO保存
     *
     * @param user RegisterUser
     * @return User
     */
    @PostMapping("saveOneByVo")
    public ResultVO<User> saveOneByVo(@RequestBody RegisterUser user) {
        return ResultVO.success(userService.saveOneByVo(user));
    }

    /**
     * 利用异体DTO删除
     *
     * @param user DeleteUser
     * @return String
     */
    @DeleteMapping("delete")
    public ResultVO<String> delete(@RequestBody DeleteUser user) {
        Specification<User> spec = EnhanceSpecification.beanWhere(user);
        userService.delete(spec);
        return ResultVO.success();
    }

    /**
     * 指定 key=value删除
     *
     * @param fieldName 字段
     * @param value     值
     * @return String
     */
    @DeleteMapping("delete2")
    public ResultVO<String> delete2(@RequestParam("fieldName") String fieldName
            , @RequestParam(value = "value") String value) {
        userService.deleteEq(fieldName, value);
        return ResultVO.success();
    }


    /**
     * 指定 key operator value删除
     *
     * @param fieldName 字段
     * @param operator  运算符
     * @param value     值
     * @return String
     */
    @DeleteMapping("delete3")
    public ResultVO<String> delete3(@RequestParam("fieldName") String fieldName
            , @RequestParam("operator") SQLOperator operator
            , @RequestParam(value = "value", required = false) String[] value) {
        userService.delete(fieldName, operator, value);
        return ResultVO.success();
    }


    /**
     * 利用异体DTO删除
     *
     * @param user DeleteUser
     * @return String
     */
    @DeleteMapping("delete4")
    public ResultVO<String> delete4(@RequestBody DeleteUser user) {
        userService.delete(user);
        return ResultVO.success();
    }


    /**
     * 更新
     *
     * @param user      UpdateUser
     * @param fieldName 指定主键名（实体字段名
     * @return String
     */
    @PutMapping("update")
    public ResultVO<String> update(@RequestBody UpdateUser user, @RequestParam(value = "fieldName", required = false) String fieldName) {
        userService.update(user, SQLOperator.EQ, fieldName);
        return ResultVO.success();
    }

    /**
     * 根据唯一值查询
     *
     * @param fieldName 字段
     * @param value     值
     * @return User
     */
    @GetMapping("findOnly")
    public ResultVO<User> findOnly(@RequestParam("fieldName") String fieldName
            , @RequestParam(value = "value") String value) {
        return ResultVO.success(userService.findOnly(fieldName, value).get());
    }


    /**
     * 组合唯一值查询
     *
     * @param fieldName  字段
     * @param value      字段
     * @param fieldName2 值
     * @param value2     值
     * @return User
     */
    @GetMapping("findOnly2")
    public ResultVO<User> findOnly2(@RequestParam("fieldName") String fieldName
            , @RequestParam(value = "value") String value
            , @RequestParam("fieldName2") String fieldName2
            , @RequestParam(value = "value2") String value2) {
        return ResultVO.success(userService.findOnly(fieldName, value, fieldName2, value2).get());
    }

    /**
     * 复杂唯一值查询
     *
     * @param user 异体DTO查询
     * @return User
     */
    @GetMapping("findOnly3")
    public ResultVO<User> findOnly3(@RequestBody FindOnly3 user) {
        Specification<User> spec = EnhanceSpecification.beanWhere(user);
        return ResultVO.success(userService.findOnly(spec).get());
    }

    /**
     * 查询所有
     *
     * @return User
     */
    @GetMapping("finds")
    public ResultVO<List<User>> finds() {
        return ResultVO.success(userService.finds());
    }

    /**
     * 根据条件查询所有
     *
     * @param fieldName 字段
     * @param operator  运算符
     * @param value     值
     * @return User
     */
    @GetMapping("finds2")
    public ResultVO<List<User>> finds2(@RequestParam("fieldName") String fieldName
            , @RequestParam("operator") SQLOperator operator
            , @RequestParam(value = "value", required = false) String... value) {
        return ResultVO.success(userService.finds(fieldName, operator, value));
    }


    /**
     * 根据条件查询所有并排序
     *
     * @param fieldName 字段
     * @param operator  运算符
     * @param sort      排序
     * @param value     值
     * @return User
     */
    @PostMapping("finds3")
    public ResultVO<List<User>> finds3(@RequestParam("fieldName") String fieldName
            , @RequestParam("operator") SQLOperator operator
            , @RequestBody Sorteds sort
            , @RequestParam(value = "value", required = false) String... value) {
        return ResultVO.success(userService.finds(fieldName, operator, sort, value));
    }


    /**
     * 异体条件查询所有并排序 {@link UserService#finds(Specification, Sorteds)}
     *
     * @param user UserFindDTO
     * @return User
     */
    @PostMapping("finds4")
    public ResultVO<List<User>> finds4(@RequestBody UserFindDTO user) {
        Specification<User> spec = EnhanceSpecification.beanWhere(user);
        return ResultVO.success(userService.finds(spec, user.getSort()));
    }

    /**
     * 异体条件查询所有 {@link UserService#finds(Object)}
     *
     * @param user UserFindDTO
     * @return User
     */
    @PostMapping("finds5")
    public ResultVO<List<User>> finds5(@RequestBody UserFindDTO user) {
        return ResultVO.success(userService.finds(user));
    }

    /**
     * 异体条件查询所有并排序 {@link UserService#finds(Object, Sorteds)}
     *
     * @param user UserFindDTO
     * @return User
     */
    @PostMapping("finds6")
    public ResultVO<List<User>> finds6(@RequestBody UserFindDTO user) {
        return ResultVO.success(userService.finds(user, user.getSort()));
    }

    /**
     * 分页查询
     *
     * @param page Pagings
     * @return User
     */
    @PostMapping("findPage")
    public ResultPageVO<User, JpaPageResult<User>> findPage(@RequestBody Pagings page) {
        return ResultPageVO.success(JpaPageResult.toPage(userService.findPage(page)));
    }

    /**
     * 分页排序查询
     *
     * @param page PagingSorteds
     * @return User
     */
    @PostMapping("findPage2")
    public ResultPageVO<User, JpaPageResult<User>> findPage2(@RequestBody PagingSorteds page) {
        return ResultPageVO.success(JpaPageResult.toPage(userService.findPage(page)));
    }

    /**
     * 异体条件分页查询 {@link UserService#findPage(Pagings)}
     *
     * @param find PageUser
     * @return User
     */
    @PostMapping("findPage3")
    public ResultPageVO<User, JpaPageResult<User>> findPage3(@RequestBody Page2User find) {
        return ResultPageVO.success(JpaPageResult.toPage(userService.findPage(find, find.getPage())));
    }

    /**
     * 异体条件分页排序查询 {@link UserService#findPage(PagingSorteds)}
     *
     * @param find PageUser
     * @return User
     */

    @PostMapping("findPage4")
    public ResultPageVO<User, JpaPageResult<User>> findPage4(@RequestBody PageUser find) {
        return ResultPageVO.success(JpaPageResult.toPage(userService.findPage(find, find.getPage())));
    }




    /**
     * 测试 {@link JpaSelectOperator 组合}
     *
     * @param user UserFindDTO
     * @return User
     */
    @PostMapping("jpaSelectOperator")
    public ResultVO<List<User>> jpaSelectOperator(@RequestBody UserFindDTO user) {
        return ResultVO.success(userService.finds(user));
    }

}
