package cn.tannn.jdevelopssbootjpademo.dao;

import cn.jdevelops.data.jap.repository.JpaBasicsRepository;
import cn.tannn.jdevelopssbootjpademo.entity.Address;

/**
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/1/30 14:20
 */
public interface AddressDao extends JpaBasicsRepository<Address, Integer> {

    Address findByCode(String code);
}
