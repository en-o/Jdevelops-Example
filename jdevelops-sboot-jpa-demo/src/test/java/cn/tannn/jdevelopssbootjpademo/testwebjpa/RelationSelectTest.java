package cn.tannn.jdevelopssbootjpademo.testwebjpa;

import cn.jdevelops.api.result.request.PageDTO;
import cn.jdevelops.api.result.request.SortPageDTO;
import cn.jdevelops.api.result.util.bean.ColumnUtil;
import cn.jdevelops.data.jap.core.Specifications;
import cn.jdevelops.data.jap.util.IObjects;
import cn.tannn.jdevelopssbootjpademo.dao.AddressDao;
import cn.tannn.jdevelopssbootjpademo.dto.CompanyFind;
import cn.tannn.jdevelopssbootjpademo.entity.Address;
import cn.tannn.jdevelopssbootjpademo.entity.Company;
import cn.tannn.jdevelopssbootjpademo.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;

/**
 * 存在记录的的查询测试
 * @author tnnn
 * @version V1.0
 * @date 2023-03-26 17:20
 */
@SpringBootTest
public class RelationSelectTest {


    @Autowired
    private CompanyService companyService;
    @Autowired
    private AddressDao addressDao;



    @Test
    void initData(){
        Address address = new Address();
        address.setCode("100");
        address.setPath("重庆");
        Address address2 = new Address();
        address2.setCode("101");
        address2.setPath("四川");
        List<Address> addresses = addressDao.saveAll(Arrays.asList(address, address2));
        Company company = new Company();
        company.setDes("开发");
        company.setName("开发");
        company.setAddress(addresses.get(0));
        Company company2 = new Company();
        company2.setDes("测试");
        company2.setName("测试");
        company2.setAddress(addresses.get(1));
        companyService.getJpaBasicsDao().saveAll(Arrays.asList(company, company2));
    }

    /**
     * 一般查询
     */
    @Test
    void testRelationSelect_general(){
        CompanyFind companyFind = new CompanyFind();
        companyFind.setName("开");
        // from sys_company company0_
        // left outer join relation_company_address company0_1_ on company0_.id=company0_1_.id
        // where company0_.name like ?
        companyService.findComplex(companyFind).forEach(System.out::println);
    }

    /**
     *  查询通过地址查询 - JpaSelectOperator.fileName
     */
    @Test
    void testRelationSelect_address(){
        CompanyFind companyFind = new CompanyFind();
        companyFind.setAddressCode("101");
        // from sys_company company0_
        // left outer join relation_company_address company0_1_ on company0_.id=company0_1_.id cross
        // join sys_address address1_
        // where company0_1_.address_id=address1_.id and address1_.code=?
        companyService.findComplex(companyFind).forEach(System.out::println);

        CompanyFind companyFind2 = new CompanyFind();
        companyFind2.setAddressCode("101");
        companyFind2.setAddressPath("重");
        // FROM sys_company company0_
        // LEFT OUTER JOIN relation_company_address company0_1_ ON company0_.id = company0_1_.id
        // CROSS JOIN sys_address address1_
        // WHERE company0_1_.address_id = address1_.id
        // AND ( address1_.CODE =? OR address1_.path LIKE ?)
        companyService.findComplex(companyFind2).forEach(System.out::println);
    }



    /**
     *  查询通过地址查询PAGE  - JpaSelectOperator.fileName
     */
    @Test
    void testRelationSelect_address_page(){
        CompanyFind companyFind2 = new CompanyFind();
        companyFind2.setAddressCode("101");
        companyFind2.setAddressPath("重");
        // from sys_company company0_
        // left outer join relation_company_address company0_1_
        // on company0_.id=company0_1_.id
        // cross join sys_address address1_
        // where company0_1_.address_id=address1_.id
        // and (address1_.code=? or address1_.path like ?)
        // limit ?
        companyService.findByBean(companyFind2, new PageDTO()).getRows().forEach(System.out::println);
    }

    /**
     *  查询通过地址查询 - Specifications.where Bean
     */
    @Test
    void testRelationSelect_address2(){
        // 通过真实实体查询
        // from sys_address address0_ where address0_.code=?
        Address byCode = addressDao.findByCode("100");
        companyService.findBeanList(Company::getAddress,byCode).forEach(System.out::println);
    }

    /**
     *  查询通过地址查询 - Specifications.where attribute
     */
    @Test
    void testRelationSelect_address3(){
        Address byCode = new Address();
        // 只能通过id查询，其他的不行,如果能值得得到id就不用像上面那种先查询一次了
        byCode.setId(2);
        // from sys_company company0_
        // left outer join relation_company_address company0_1_
        // on company0_.id=company0_1_.id where company0_1_.address_id=?
        companyService.findBeanList(Company::getAddress,byCode).forEach(System.out::println);

        // 自定义模拟 findBeanList
        // from sys_company company0_
        // left outer join relation_company_address company0_1_
        // on company0_.id=company0_1_.id
        // left outer join sys_address address1_
        // on company0_1_.address_id=address1_.id
        // where address1_.path=?
        Specification<Company> where = Specifications.where(e -> e.eq(true,
                "address.path",
                "重庆"));
        companyService.getJpaBasicsDao().findAll(where).forEach(System.out::println);
    }

}
