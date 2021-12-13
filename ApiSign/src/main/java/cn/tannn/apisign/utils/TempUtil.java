package cn.tannn.apisign.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 零时工具类
 * @author tn
 * @date 2020/12/22 11:50
 */
public class TempUtil {
    /**
     * 将对象装换为map
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 实体转Map
     */
    @SuppressWarnings("unchecked")
    public static MultiValueMap<String, Object> beanToLinkedMultiValueMap(Object obj) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                if (!"class".equals(name)) {
                    params.add(name, propertyUtilsBean.getNestedProperty(obj, name));
                }
            }
            if(obj!=null&&params.containsKey("empty") ){
                params = (MultiValueMap<String, Object>) obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }


    /**
     * 实体转Map
     * @param obj
     * @return
     */
    @SuppressWarnings("unchecked")
    public static MultiValueMap<String, String> beanToLinkedMultiValueMap2(Object obj) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                if (!"class".equals(name)) {
                    params.add(name, JSONObject.toJSONString(propertyUtilsBean.getNestedProperty(obj, name)));
                }
            }
            if(obj!=null&&params.containsKey("empty") ){
                params = (MultiValueMap<String, String>) obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }


    /**
     * 实体转Map
     * @param obj
     * @return
     */
    @SuppressWarnings("unchecked")
    public static LinkedHashMap<String, Object> beanToLinkedHashMap(Object obj) {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                if (!"class".equals(name)) {
                    params.put(name, propertyUtilsBean.getNestedProperty(obj, name));
                }
            }
            if(obj!=null&&params.containsKey("empty") ){
                params = (LinkedHashMap<String, Object>) obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }

}
