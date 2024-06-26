package cn.tannn.demo.jdevelops.apissign.utils;

import cn.tannn.demo.jdevelops.apissign.entity.UserEntity;
import cn.tannn.jdevelops.result.spring.SpringContextUtils;
import cn.tannn.jdevelops.sign.config.ApiSignConfig;
import cn.tannn.jdevelops.sign.util.SignMD5Util;
import com.alibaba.fastjson2.JSON;

import java.util.LinkedHashMap;


/**
 * 验证工具
 *
 * @author tn
 * @version 1
 * @date 2020/12/22 10:03
 */
public class SignUtil extends cn.tannn.jdevelops.sign.util.SignUtil {


    public static String getMd5SignByJson(UserEntity userEntity) {
        ApiSignConfig apiSignBean = SpringContextUtils.getInstance().getBean(ApiSignConfig.class);
        String encrypt1 = SignMD5Util.encrypt(JSON.toJSONString(userEntity), true);
        return SignMD5Util.encrypt(encrypt1 + apiSignBean.getSalt(), true);
    }

    public static String getMd5SignByBean2LinkedHashMap2Str(UserEntity userEntity) {
        ApiSignConfig apiSignBean = SpringContextUtils.getInstance().getBean(ApiSignConfig.class);
        LinkedHashMap<String, Object> stringObjectLinkedHashMap = TempUtil.beanToLinkedHashMap(userEntity);
        String encrypt1 = SignMD5Util.encrypt(map2Str(stringObjectLinkedHashMap), true);
        return SignMD5Util.encrypt(encrypt1 + apiSignBean.getSalt(), true);
    }

}
