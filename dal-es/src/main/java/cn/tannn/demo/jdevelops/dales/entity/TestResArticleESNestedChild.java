package cn.tannn.demo.jdevelops.dales.entity;



import cn.tannn.jdevelops.annotations.es.EsField;
import cn.tannn.jdevelops.annotations.es.basic.EsFieldBasic;
import cn.tannn.jdevelops.annotations.es.basic.EsFieldMultiType;
import cn.tannn.jdevelops.annotations.es.constant.EsType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



/**
 * 文献文章表
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/21 9:07
 */
@Getter
@Setter
@ToString
public class TestResArticleESNestedChild {


    /**
     * 唯一标识ID
     */
    @EsField(basic = @EsFieldBasic(type = EsType.keyword))
    String fix;

    /**
     * DOI
     */
    @EsField(basic = @EsFieldBasic(type = EsType.keyword))
    String sex;


    /**
     * PMID
     */
    @EsField(basic = @EsFieldBasic(type = EsType.keyword),
            fields = {
                    @EsFieldMultiType(
                            alias = "nickname",
                            basic = @EsFieldBasic(
                                    type = EsType.keyword
                            )
                    )
            }
    )
    String name;


}
