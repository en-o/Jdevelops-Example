
package cn.tannn.demo.jdevelops.dalddss.controller;

import cn.tannn.jdevelops.ddss.controller.DynamicDatasourceController;
import cn.tannn.jdevelops.ddss.service.DynamicDatasourceService;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据源管理
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/15 15:20
 */
@RestController
public class DynamicDatasourceManageController extends DynamicDatasourceController {

    public final DynamicDatasourceService dynamicDatasourceService;

    public DynamicDatasourceManageController(DynamicDatasourceService dynamicDatasourceService) {
        super(dynamicDatasourceService);
        this.dynamicDatasourceService = dynamicDatasourceService;
    }
}
