package cn.tannn.demo.jdevelops.dalddss.controller;

import cn.tannn.demo.jdevelops.dalddss.classloader.DynamicClassLoader;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 驱动
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/26 14:58
 */
@Tag(name = "驱动")
@RestController
@RequestMapping("/driver")
public class DriverController {

    /**
     * 添加驱动
     * @param driverJarPath 驱动路径[e.g G:/tool/maven/repo/com/microsoft/sqlserver/sqljdbc4/4.0/sqljdbc4-4.0.jar]
     * @return
     */
    @GetMapping("add")
    @Parameter(name = "driverJarPath",description = "G:/tool/maven/repo/com/microsoft/sqlserver/sqljdbc4/4.0/sqljdbc4-4.0.jar")
    public String addDriver(String driverJarPath){
//        String filePath2 = "G:/tool/maven/repo/com/microsoft/sqlserver/sqljdbc4/4.0/sqljdbc4-4.0.jar";
        DynamicClassLoader.AppClassLoaderAddDriver(driverJarPath);
        return "添加成功";
    }
}
