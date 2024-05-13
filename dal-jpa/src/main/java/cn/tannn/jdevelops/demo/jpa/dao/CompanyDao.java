package cn.tannn.jdevelops.demo.jpa.dao;

import cn.tannn.jdevelops.demo.jpa.entity.Company;
import cn.tannn.jdevelops.jpa.repository.JpaBasicsRepository;

/**
 * 公司
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/1/30 13:46
 */
public interface CompanyDao extends JpaBasicsRepository<Company, Integer> {
}
