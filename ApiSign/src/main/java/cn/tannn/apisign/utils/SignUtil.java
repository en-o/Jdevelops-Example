package cn.tannn.apisign.utils;

import cn.jdevelops.apisign.bean.ApiSignBean;
import cn.jdevelops.encryption.core.SignMD5Util;
import cn.jdevelops.result.util.SpringBeanUtils;
import cn.tannn.apisign.entity.UserEntity;
import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;


/**
 * 验证工具
 *
 * @author tn
 * @version 1
 * @date 2020/12/22 10:03
 */
public class SignUtil extends cn.jdevelops.apisign.util.SignUtil {


    public static String getMd5SignByJson(UserEntity userEntity) {
        ApiSignBean apiSignBean = SpringBeanUtils.getInstance().getBean(ApiSignBean.class);
        String encrypt1 = SignMD5Util.encrypt(JSON.toJSONString(userEntity), true);
        return SignMD5Util.encrypt(encrypt1 + apiSignBean.getSalt(), true);
    }

    public static String getMd5SignByBean2LinkedHashMap2Str(UserEntity userEntity) {
        ApiSignBean apiSignBean = SpringBeanUtils.getInstance().getBean(ApiSignBean.class);
        LinkedHashMap<String, Object> stringObjectLinkedHashMap = TempUtil.beanToLinkedHashMap(userEntity);
        String encrypt1 = SignMD5Util.encrypt(map2Str(stringObjectLinkedHashMap), true);
        return SignMD5Util.encrypt(encrypt1 + apiSignBean.getSalt(), true);
    }

}
