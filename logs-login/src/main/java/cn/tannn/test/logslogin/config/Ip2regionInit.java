package cn.tannn.test.logslogin.config;

import cn.tannn.ip2region.sdk.config.SearcherConfig;
import org.lionsoul.ip2region.xdb.Searcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;

/**
 * ip region 数据初始化加载到内存里
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/4/3 14:19
 */
@Component
public class Ip2regionInit implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(Ip2regionInit.class);


    /**
     * 在服务启动时，将 ip2region 加载到内存中
     * @see <a href="https://gitee.com/lionsoul/ip2region/tree/master/binding/java#%E7%BC%93%E5%AD%98%E6%95%B4%E4%B8%AA-xdb-%E6%95%B0%E6%8D%AE">缓存整个-xdb-数据</a>
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        LOGGER.info("加载ip2region文件 starting");
        try {
            InputStream inputStream = new ClassPathResource("/ipdb/ip2region.xdb").getInputStream();
            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
            SearcherConfig.searcher = Searcher.newWithBuffer(bytes);
        } catch (Exception exception) {
            LOGGER.error("加载ip2region文件失败 ===> 请仔细阅读 resources/ipdb/readme.txt ", exception);
        }
    }
}
