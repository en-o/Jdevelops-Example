package com.example.jdevelopsdataddssdemo.controller;

import cn.hutool.core.map.MapUtil;
import cn.jdevelops.api.result.response.ResultVO;
import cn.jdevelops.data.ddss.annotation.DbName;
import cn.jdevelops.data.ddss.annotation.DbNamed;
import cn.jdevelops.data.ddss.annotation.DyDS;
import com.example.jdevelopsdataddssdemo.controller.dto.TestVO;
import com.example.jdevelopsdataddssdemo.dao.UserDao;
import com.example.jdevelopsdataddssdemo.entity.nonExistentListener.User;
import com.google.common.collect.Multimap;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.collections4.MultiMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * classloader
 *
 * @author tn
 * @version 1
 * @date 2021-12-12 14:48
 */
@Tag(name = "classloader")
@RestController
@RequestMapping("/classloader")
public class TestController {


    @GetMapping("classLoaderTree")
    public Object classLoaderTree(){
        ClassLoader loader = TestController.class.getClassLoader();
        // 因为Bootstrap ClassLoader不是一个普通的Java类，所以ExtClassLoader的parent=null
        List<Map<String,Object>> maps = new ArrayList<>();
        while (loader != null) {
            maps.add(MapUtil.of("classLoader",loader.getClass()));
            if (loader instanceof URLClassLoader) {
                URL[] urls = ((URLClassLoader) loader).getURLs();
                List<String> lists = new ArrayList<>();
                for (URL url : urls) {
                    lists.add(url.toExternalForm());
                }
                maps.add(MapUtil.of("jar",lists));
            }
            loader = loader.getParent();
        }
        return maps;
    }

}

