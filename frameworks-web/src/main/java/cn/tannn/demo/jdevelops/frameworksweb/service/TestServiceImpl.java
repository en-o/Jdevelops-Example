package cn.tannn.demo.jdevelops.frameworksweb.service;

import cn.tannn.demo.jdevelops.frameworksweb.dao.TestDao;
import cn.tannn.demo.jdevelops.frameworksweb.entity.TestWeb;
import cn.tannn.jdevelops.jpa.service.J2ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://t.tannn.cn/">tnnn</a>
 * @version V1.0
 * @date 2024/6/30 上午1:29
 */
@Service
public class TestServiceImpl extends J2ServiceImpl<TestDao, TestWeb, Long> implements TestService {
    public TestServiceImpl() {
        super(TestWeb.class);
    }
}
