package cn.tannn.jdevelops.demo.jpa.dao;


import cn.tannn.jdevelops.demo.jpa.entity.Address;
import cn.tannn.jdevelops.jpa.repository.JpaBasicsRepository;

/**
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/1/30 14:20
 */
public interface AddressDao extends JpaBasicsRepository<Address, Integer> {

    Address findByCode(String code);
}
