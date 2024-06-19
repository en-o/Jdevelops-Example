package cn.tannn.demo.jdevelops.dalddss.classloader;


import cn.tannn.demo.jdevelops.dalddss.controller.DriverController;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 *   建议在真正需要时谨慎使用此方法，并确保了解其潜在影响[如果需要更高级的模块化或隔离，可以考虑使用 OSGi 或其他类似的技术]
 *          - 在运行时修改类加载路径可能导致不稳定性和安全问题。
 *          - 如果加载的 JAR 文件包含与应用程序中已加载的类相同的类，可能会引发类冲突。
 *          - 在某些环境中，可能会出现类加载器隔离问题，导致无法访问或使用新加载的类
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/26 14:56
 */
public class DynamicClassLoader {


    /**
     *
     * 往 AppClassLoader 中添加[不会重复添加]  数据库驱动 [无法删除，还有重启失效]
     * @param fileJarPath 驱动JAR路径 e.g  {@link DriverController#addDriver(String)}
     */
    public static void AppClassLoaderAddDriver(String fileJarPath){

        try {
            // 获取 AppClassLoader
            ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
            if (appClassLoader instanceof URLClassLoader) {
                URLClassLoader urlClassLoader = (URLClassLoader) appClassLoader;

                // 创建一个 URL 对象，表示要加载的 JAR 文件的路径
                File jarFile = new File(fileJarPath);
                URL jarUrl = jarFile.toURI().toURL();
                // 使用反射获取 addURL 方法
                Method addUrlMethod = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
                addUrlMethod.setAccessible(true);
                // 调用 addURL 方法将 JAR 文件添加到类加载路径中
                addUrlMethod.invoke(urlClassLoader, jarUrl);
                // 现在 JAR 文件中的类应该可以被 AppClassLoader 加载了
                // 你可以使用 Class.forName() 或其他方式来加载 JAR 文件中的类
            } else {
                System.out.println("AppClassLoader is not a URLClassLoader.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
