package cn.tannn.apisign.controller;

import cn.tannn.apisign.entity.UserEntity;
import cn.tannn.apisign.utils.SignUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *  Signature(type = SginEnum.MD5HEADER)
 */
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class SignHeaderMD5ControllerTest {

    /**
     * 模拟mvc测试对象
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * 验证MD5加密 - post json header
     *
     */
    @Test
    void testIsSignMD5BeanPJsonH() throws Exception {
        UserEntity user = UserEntity.builder().age("123").name("谭宁").build();
        // 获取加密串
        String sign = SignUtil.getMd5SignByJson(user);
        // 正确
        HttpHeaders heanders = new HttpHeaders();
        heanders.set("sign",sign);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/header/md5/isSignMD5BeanPJsonH")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .headers(heanders)
                .content(JSONObject.toJSONString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                .andReturn();

        // - 模拟数据中途被篡改
        // 参数顺序错误  加密顺序 age,name  传入参数顺序name,age,
        // 在使用bean json序列化是顺序的差异会被掩盖导致顺序一致判断失效
        UserEntity userParamsSortError = UserEntity.builder().name("谭宁").age("123").build();
        this.mockMvc.perform(MockMvcRequestBuilders.post("/header/md5/isSignMD5BeanPJsonH")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .headers(heanders)
                .content(JSONObject.toJSONString(userParamsSortError)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                .andReturn();

        // 数据更改
        UserEntity userParamsUpdateError = UserEntity.builder().name("谭wq宁").age("12w3").build();
        this.mockMvc.perform(MockMvcRequestBuilders.post("/header/md5/isSignMD5BeanPJsonH")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .headers(heanders)
                .content(JSONObject.toJSONString(userParamsUpdateError)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect((ResultMatcher) jsonPath("$.message", is("签名不正确")))
                .andReturn();
    }

    /**
     * 验证MD5加密 - get bean 拼接 header
     */
    @Test
    void testIsSignMD5BeanH() throws Exception {
        UserEntity user = UserEntity.builder().age("123").name("谭宁").build();
        String userParams = String.format("age=%s&name=%s",user.getAge(), user.getName());
        // 获取加密串
        String sign = SignUtil.getMd5SignByJson(user);
        // 正确
        HttpHeaders heanders = new HttpHeaders();
        heanders.set("sign",sign);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/header/md5/isSignMD5BeanH?"+userParams)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .headers(heanders))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                .andReturn();

        // - 模拟数据中途被篡改
        // 参数顺序错误  加密顺序 age,name  传入参数顺序name,age ()，get方式强制严重参数顺序
        String userParamsSortError = String.format("name=%s&age=%s", user.getName(), user.getAge());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/header/md5/isSignMD5BeanH?"+userParamsSortError)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .headers(heanders))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect((ResultMatcher) jsonPath("$.message", is("签名不正确")))
                .andReturn();

        // 数据更改
        String userParamsUpdateError = String.format("age=%s&name=%s","tan","raa");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/header/md5/isSignMD5BeanH?"+userParamsUpdateError)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .headers(heanders))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect((ResultMatcher) jsonPath("$.message", is("签名不正确")))
                .andReturn();

    }


    /**
     * 验证MD5加密 - post list header
     */
    @Test
    void testIsSignMD5ListBeanPH() throws Exception {

        ArrayList<UserEntity> userArrayList = new ArrayList<>();
        UserEntity user = UserEntity.builder().age("123").name("谭宁").build();
        userArrayList.add(user);
        user = UserEntity.builder().age("456").name("谭宁2").build();
        userArrayList.add(user);
        user = UserEntity.builder().age("789").name("谭宁3").build();
        userArrayList.add(user);
        // 获取加密串
        String jsonString = JSONArray.toJSONString(userArrayList);
        String sign = SignUtil.getMd5SignByJson(jsonString);
        // 正确
        HttpHeaders heanders = new HttpHeaders();
        heanders.set("sign",sign);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/header/md5/isSignMD5ListBeanPH")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .headers(heanders)
                .content(jsonString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                .andReturn();

        // - 模拟数据中途被篡改
        // 参数顺序错误
        ArrayList<UserEntity> sortUser = new ArrayList<>(userArrayList);
        Collections.reverse(sortUser);
        String userParamsSortError = JSONArray.toJSONString(sortUser);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/header/md5/isSignMD5ListBeanPH")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .headers(heanders)
                .content(userParamsSortError))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect((ResultMatcher) jsonPath("$.message", is("签名不正确")))
                .andReturn();

        // 数据更改
        user = UserEntity.builder().age("789").name("谭宁3").build();
        userArrayList.add(user);
        // 获取加密串
        String userParamsUpdateError = JSONArray.toJSONString(userArrayList);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/header/md5/isSignMD5ListBeanPH")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .headers(heanders)
                .content(userParamsUpdateError))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect((ResultMatcher) jsonPath("$.message", is("签名不正确")))
                .andReturn();

    }

}