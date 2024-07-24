package cn.tannn.jdevelops.demo.jpa.entity;

import cn.tannn.jdevelops.jpa.convert.JsonObjectConverter;
import cn.tannn.jdevelops.jpa.convert.ListStringConverter;
import com.alibaba.fastjson2.JSONObject;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;


/**
 * 测试内置的 convert
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/1/30 13:40
 */
@Entity
@Table(name = "test_convert")
@Comment("测试内置的convert")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@DynamicUpdate
@DynamicInsert
public class ConvertTest extends CommonBean<ConvertTest> {

    @Comment("Json")
    @Column(columnDefinition = "json not null")
    @Convert(converter = JsonObjectConverter.class)
    private JSONObject config;

    @Comment("liststr")
    @Column(columnDefinition = "varchar(100)")
    @Convert(converter = ListStringConverter.class)
    private List<String> strs;
}
