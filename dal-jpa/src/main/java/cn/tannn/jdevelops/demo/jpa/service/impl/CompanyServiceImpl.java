package cn.tannn.jdevelops.demo.jpa.service.impl;

import cn.tannn.jdevelops.demo.jpa.dao.CompanyDao;
import cn.tannn.jdevelops.demo.jpa.entity.Company;
import cn.tannn.jdevelops.demo.jpa.service.CompanyService;
import cn.tannn.jdevelops.jpa.service.J2ServiceImpl;
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
