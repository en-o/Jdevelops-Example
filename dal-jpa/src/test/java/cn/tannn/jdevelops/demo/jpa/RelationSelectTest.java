package cn.tannn.jdevelops.demo.jpa;

import cn.tannn.jdevelops.demo.jpa.dao.AddressDao;
import cn.tannn.jdevelops.demo.jpa.dto.CompanyFind;
import cn.tannn.jdevelops.demo.jpa.entity.Address;
import cn.tannn.jdevelops.demo.jpa.entity.Company;
import cn.tannn.jdevelops.demo.jpa.service.CompanyService;
import cn.tannn.jdevelops.jpa.constant.SQLOperator;
import cn.tannn.jdevelops.jpa.request.Pagings;
import cn.tannn.jdevelops.jpa.select.EnhanceSpecification;
import cn.tannn.jdevelops.result.bean.ColumnUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;

/**
 * 级联查询
 *
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
    void initData() {
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
    void testRelationSelect_general() {
        CompanyFind companyFind = new CompanyFind();
        companyFind.setName("开");
        // from sys_company company0_
        // left outer join relation_company_address company0_1_ on company0_.id=company0_1_.id
        // where company0_.name like ?
        companyService.finds(companyFind).forEach(System.out::println);
    }

    /**
     * 查询通过地址查询 - JpaSelectOperator.fileName
     */
    @Test
    void testRelationSelect_address() {
        // 根据 地址查询 （地址是级联参数
        CompanyFind companyFind = new CompanyFind();
        companyFind.setAddressCode("101");
        // from sys_company company0_
        // left outer join relation_company_address company0_1_ on company0_.id=company0_1_.id cross
        // join sys_address address1_
        // where company0_1_.address_id=address1_.id and address1_.code=?
        companyService.finds(companyFind).forEach(System.out::println);


        // 根据 地址查询 （地址是级联参数
        CompanyFind companyFind2 = new CompanyFind();
        companyFind2.setAddressCode("101");
        companyFind2.setAddressPath("重");
        // FROM sys_company company0_
        // LEFT OUTER JOIN relation_company_address company0_1_ ON company0_.id = company0_1_.id
        // CROSS JOIN sys_address address1_
        // WHERE company0_1_.address_id = address1_.id
        // AND ( address1_.CODE =? OR address1_.path LIKE ?)
        companyService.finds(companyFind2).forEach(System.out::println);
    }


    /**
     * 分页查询通过地址查询 - JpaSelectOperator.fileName
     */
    @Test
    void testRelationSelect_address_page() {
        // 根据 地址查询 （地址是级联参数  + 分页
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
        companyService.findPage(companyFind2, new Pagings()).forEach(System.out::println);
    }

//    /**
//     * 通过级联的实体查询 - Specifications.where Bean
//     */
//    @Test
//    void testRelationSelect_address2() {
//        // 通过级联的实体查询
//        // from sys_address address0_ where address0_.code=?
//        Address byCode = addressDao.findByCode("100");
//        companyService.finds(ColumnUtil.getFieldName(Address::getCode), SQLOperator.EQ, byCode).forEach(System.out::println);
//    }

    /**
     *  查询通过地址查询 - Specifications.where attribute
     */
    @Test
    void testRelationSelect_address3(){
//        Address byCode = new Address();
//        // 只能通过id查询，其他的不行,如果能值得得到id就不用像上面那种先查询一次了
//        byCode.setId(2L);
//        // from sys_company company0_
//        // left outer join relation_company_address company0_1_
//        // on company0_.id=company0_1_.id where company0_1_.address_id=?
//        companyService.finds("Company::getAddress",byCode).forEach(System.out::println);



        // 使用  EnhanceSpecification 通过级联字段查询  selectKey: 要注意 = Company里的属性名.本身字段的属性名
        // from sys_company company0_
        // left outer join relation_company_address company0_1_
        // on company0_.id=company0_1_.id
        // left outer join sys_address address1_
        // on company0_1_.address_id=address1_.id
        // where address1_.path=?
        Specification<Company> where = EnhanceSpecification.where(e -> e.eq(true,
                "address.path",
                "重庆"));
        companyService.getJpaBasicsDao().findAll(where).forEach(System.out::println);
    }

}
