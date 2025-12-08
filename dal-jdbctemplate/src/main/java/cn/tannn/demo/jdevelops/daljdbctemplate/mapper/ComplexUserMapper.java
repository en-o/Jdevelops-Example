package cn.tannn.demo.jdevelops.daljdbctemplate.mapper;

import cn.tannn.demo.jdevelops.daljdbctemplate.mapper.example.ComplexUserInterface;
import cn.tannn.demo.jdevelops.daljdbctemplate.mapper.example.ComplexUserRecord;
import cn.tannn.jdevelops.annotations.jdbctemplate.xml.XmlInsert;
import cn.tannn.jdevelops.annotations.jdbctemplate.xml.XmlMapper;
import cn.tannn.jdevelops.annotations.jdbctemplate.xml.XmlSelect;

import java.util.List;
import java.util.Map;

/**
 * 复杂用户 Mapper
 * 用于测试 Interface 和 Record 返回类型以及复杂属性映射
 *
 * @author tnnn
 */
@XmlMapper(namespace = "cn.tannn.demo.jdevelops.daljdbctemplate.mapper.ComplexUserMapper")
public interface ComplexUserMapper {

    // ==================== 插入数据（用于测试前准备） ====================

    /**
     * 批量插入测试数据
     */
    @XmlInsert("batchInsert")
    int batchInsert(List<Map<String, Object>> users);

    /**
     * 清空表数据
     */
    @XmlInsert("deleteAll")
    int deleteAll();


    // ==================== Interface 返回类型测试 ====================

    /**
     * 查询单个用户 - Interface返回类型
     * 测试：基本类型、枚举、List、Map、嵌套对象、JSON字符串
     */
    @XmlSelect("findByIdInterface")
    ComplexUserInterface findByIdInterface(Long id);

    /**
     * 查询用户列表 - Interface返回类型
     */
    @XmlSelect("findAllInterface")
    List<ComplexUserInterface> findAllInterface();

    /**
     * 根据状态查询 - Interface返回类型
     */
    @XmlSelect("findByStatusInterface")
    List<ComplexUserInterface> findByStatusInterface(Integer status);


    // ==================== Record 返回类型测试 ====================

    /**
     * 查询单个用户 - Record返回类型
     * 测试：基本类型、枚举、List、Map、嵌套Record、JSON字符串
     */
    @XmlSelect("findByIdRecord")
    ComplexUserRecord findByIdRecord(Long id);

    /**
     * 查询用户列表 - Record返回类型
     */
    @XmlSelect("findAllRecord")
    List<ComplexUserRecord> findAllRecord();

    /**
     * 根据状态查询 - Record返回类型
     */
    @XmlSelect("findByStatusRecord")
    List<ComplexUserRecord> findByStatusRecord(Integer status);


    // ==================== 特殊场景测试 ====================

    /**
     * 测试NULL值处理 - Interface
     */
    @XmlSelect("findByIdInterface")
    ComplexUserInterface findWithNullValues(Long id);

    /**
     * 测试空集合处理 - Interface
     */
    @XmlSelect("findByIdInterface")
    ComplexUserInterface findWithEmptyCollections(Long id);

    /**
     * 测试枚举转换 - Interface
     * 验证 status -> UserStatus 的正确转换
     */
    @XmlSelect("findByIdInterface")
    ComplexUserInterface findForEnumConversion(Long id);
}
