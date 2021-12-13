package cn.tannn.apisign.utils;

import cn.jdevelops.encryption.constant.SignConstant;
import cn.jdevelops.encryption.core.SignMD5Util;
import cn.tannn.apisign.entity.UserEntity;
import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;


/**
 * 验证工具
 * @author tn
 * @version 1
 * @date 2020/12/22 10:03
 */
public class SignUtil extends cn.jdevelops.apisign.util.SignUtil {


    public static String getMd5SignByJson(UserEntity userEntity){
        String encrypt1 = SignMD5Util.encrypt(JSON.toJSONString(userEntity),true);
        return SignMD5Util.encrypt(encrypt1+ SignConstant.MD5_PRIVATE_KEY, true);
    }

    public static String getMd5SignByBean2LinkedHashMap2Str(UserEntity userEntity){
        LinkedHashMap<String, Object> stringObjectLinkedHashMap = TempUtil.beanToLinkedHashMap(userEntity);
        String encrypt1 = SignMD5Util.encrypt(map2Str(stringObjectLinkedHashMap),true);
        return SignMD5Util.encrypt(encrypt1+ SignConstant.MD5_PRIVATE_KEY, true);
    }

}
