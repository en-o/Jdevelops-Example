package cn.tannn.jdevelopssbootjpademo.service.impl;

import cn.jdevelops.data.jap.service.impl.J2ServiceImpl;
import cn.tannn.jdevelopssbootjpademo.dao.CompanyDao;
import cn.tannn.jdevelopssbootjpademo.entity.Company;
import cn.tannn.jdevelopssbootjpademo.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 公司
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/1/30 13:47
 */
@Slf4j
@Service
public class CompanyServiceImpl  extends J2ServiceImpl<CompanyDao, Company,Integer> implements CompanyService {
    public CompanyServiceImpl() {
        super(Company.class);
    }
}
