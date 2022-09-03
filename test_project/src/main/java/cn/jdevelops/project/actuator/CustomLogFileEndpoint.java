package cn.jdevelops.project.actuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 *
 * @author tn
 * @date  2022-07-15 09:37
 */
@Configuration
@WebEndpoint(id = "ilog")
public class CustomLogFileEndpoint {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private File externalFile;

    /**
     * 文件夹
     */
        @Value("${log.fileFolder:logs/${spring.application.name}}")
    private String fileFolder;


    /**
     * LogFileWebEndpoint logFileWebEndpoint
     * @param date 日期 yyyy-MM-dd
     * @param logName 日志名
     * @return List
     */
    @ReadOperation(
            produces = {"text/plain; charset=UTF-8"}
    )
    public Resource loadFile(String date,String logName) {
        try {
            String path = StringUtils.cleanPath(fileFolder + File.separator + date + File.separator + logName);
            externalFile = new File(".", path);
            Resource logFileResource = this.getLogFileResource();
            // && logFileResource.isReadable()
            return logFileResource != null  ? logFileResource : null;
        } catch (Exception e) {
            this.logger.error("自定义SpringBootAdmin的LogFileEndpoint日志文件失败", e);
        }
        return null;
    }


    private Resource getLogFileResource() {
        if (this.externalFile != null) {
            return new FileSystemResource(this.externalFile);
        } else  {
            logger.debug("Missing files");
            return null;
        }
    }

}
