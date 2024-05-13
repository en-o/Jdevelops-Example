package cn.tannn.jdevelops.demo.jpa.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 公司
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/1/30 13:45
 */
@Entity
@Table(name = "sys_company")
@org.hibernate.annotations.Table(appliesTo = "sys_company", comment = "公司")
@Getter
@Setter
@RequiredArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Company extends CommonBean<Company>{
    String name;
    String des;

    /**
     * 地址
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "relation_company_address")
    @ToString.Exclude
    private Address address;

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", des='" + des + '\'' +
                ", address=" + address +
                '}';
    }

}
