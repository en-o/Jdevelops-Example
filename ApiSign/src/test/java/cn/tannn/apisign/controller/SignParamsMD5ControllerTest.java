package cn.tannn.apisign.controller;

import cn.tannn.apisign.entity.UserEntity;
import cn.tannn.apisign.entity.UserEntity2;
import cn.tannn.apisign.utils.SignUtil;
import cn.tannn.apisign.utils.TempUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Signature(type = SginEnum.MD5)
 */
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class SignParamsMD5ControllerTest {

    /**
     * 模拟mvc测试对象
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * 不签名
     */
    @Test
    void isSign() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/params/md5/isSign")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andReturn();
    }

    /**
     * 验证MD5加密 - get 拼接
     */
    @Test
    void isSignMD5() throws Exception {

        String userParams = String.format("name=%s&age=%s", "谭宁", "123");
        // 获取加密串
        String sign = SignUtil.getMd5SignBymap2Str(userParams);
        String userParamsOK =  String.format(userParams+"&sign=%s", sign);
        // 正确
        this.mockMvc.perform(MockMvcRequestBuilders.get("/params/md5/isSignMD5?"+userParamsOK))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                .andReturn();

        // - 模拟数据中途被篡改
        // 参数顺序错误  加密顺序 age,name  传入参数顺序name,age ()，get方式强制严重参数顺序
        String userParamsSortError = String.format("age=%s&name=%s", "123", "谭宁");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/params/md5/isSignMD5?"+userParamsSortError))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.message", is("接口签名不正确")))
                .andReturn();

        // 数据更改
        String userParamsUpdateError = String.format("name=%s&age=%s", "谭宁", "13123");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/params/md5/isSignMD5?"+userParamsUpdateError))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.message", is("接口签名不正确")))
                .andReturn();
    }

    /**
     * 验证MD5加密 - get bean 拼接
     */
    @Test
    void isSignMD5Bean() throws Exception {
        UserEntity user = UserEntity.builder().age("123").name("谭宁").build();
        Map<String, Object> beanToMap = TempUtil.beanToMap(user);
        // 获取加密串
        String sign = SignUtil.getMd5SignByMap2Str(beanToMap);
        String userParams = SignUtil.map2Str(beanToMap);
        String userParamsOK =  String.format(userParams+"&sign=%s", sign);
        // 正确
        this.mockMvc.perform(MockMvcRequestBuilders.get("/params/md5/isSignMD5Bean?"+userParamsOK)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                .andReturn();

        // - 模拟数据中途被篡改
//        // 数据更改
        UserEntity userUpdate = UserEntity.builder().age("123").name("谭宁77").build();
        Map<String, Object> beanToUpdateMap = TempUtil.beanToMap(userUpdate);
        String userUpdateParams = SignUtil.map2Str(beanToUpdateMap);
        String userParamsUpdateError =  String.format(userUpdateParams+"&sign=%s", sign);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/params/md5/isSignMD5Bean?"+userParamsUpdateError)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect( jsonPath("$.message", is("接口签名不正确")))
                .andReturn();
    }

    /**
     * 验证MD5加密 - post bean
     */
    @Test
    void isSignMD5BeanP() throws Exception {
        UserEntity user = UserEntity.builder().age("123").name("谭宁").build();
        // 获取加密串
        String sign = SignUtil.getMd5SignBymap2Str(SignUtil.map2Str(TempUtil.beanToLinkedHashMap(user)));
        // 正确
        this.mockMvc.perform(MockMvcRequestBuilders.post("/params/md5/isSignMD5BeanP")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .param("name","谭宁")
                .param("age","123")
                .param("sign",sign))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                .andReturn();

        // - 模拟数据中途被篡改
        // 参数顺序错误 加密顺序name,age,   传入参数顺序 age,name
        this.mockMvc.perform(MockMvcRequestBuilders.post("/params/md5/isSignMD5BeanP")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .param("age","123")
                .param("name","谭宁")
                .param("sign",sign))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.message", is("接口签名不正确")))
                .andReturn();

        // 数据更改
        this.mockMvc.perform(MockMvcRequestBuilders.post("/params/md5/isSignMD5BeanP")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .param("name","谭E宁")
                .param("age","123")
                .param("sign",sign))
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.message", is("接口签名不正确")))
                .andReturn();
    }

    /**
     * 验证MD5加密 - post json
     * 注意 传入的bean要用 LinkedHashMap 装，因为要强制验证顺序
     */
    @Test
    void isSignMD5BeanPJson() throws Exception {
        UserEntity user = UserEntity.builder().age("123").name("谭宁").build();
        UserEntity2 userEntity2 = new UserEntity2();
        BeanUtils.copyProperties(userEntity2, user);
        // 获取加密串
        String sign = SignUtil.getMd5SignByBean2LinkedHashMap2Str(user);
        userEntity2.setSign(sign);
        // 正确
        this.mockMvc.perform(MockMvcRequestBuilders.post("/params/md5/isSignMD5BeanPJson")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                // 注意 传入的bean要用 LinkedHashMap 装，因为要强制验证顺序
                .content(JSONObject.toJSONString(TempUtil.beanToLinkedHashMap(userEntity2))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                .andReturn();

        // - 模拟数据中途被篡改
        // 参数顺序错误 加密顺序name,age,   传入参数顺序 age,name
        this.mockMvc.perform(MockMvcRequestBuilders.post("/params/md5/isSignMD5BeanPJson")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                // 无序传入
                .content(JSONObject.toJSONString(userEntity2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.message", is("接口签名不正确")))
                .andReturn();

//        // 数据更改
        userEntity2.setName("tatt");
        this.mockMvc.perform(MockMvcRequestBuilders.post("/params/md5/isSignMD5BeanPJson")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                // 注意 传入的bean要用 LinkedHashMap 装，因为要强制验证顺序
                .content(JSONObject.toJSONString(TempUtil.beanToLinkedHashMap(userEntity2))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.message", is("接口签名不正确")))
                .andReturn();
    }
}