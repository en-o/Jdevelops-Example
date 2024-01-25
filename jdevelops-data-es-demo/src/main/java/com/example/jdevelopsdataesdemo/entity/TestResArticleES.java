package com.example.jdevelopsdataesdemo.entity;

import cn.jdevelops.annotation.es.EsField;
import cn.jdevelops.annotation.es.EsIndex;
import cn.jdevelops.annotation.es.basic.EsFieldBasic;
import cn.jdevelops.annotation.es.basic.EsFieldMultiType;
import cn.jdevelops.annotation.es.constant.EsDdlAuto;
import cn.jdevelops.annotation.es.constant.EsType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static cn.jdevelops.annotation.es.constant.EsTypeDataFormat.*;


import java.time.LocalDateTime;


/**
 * 文献文章表
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/21 9:07
 */
@Getter
@Setter
@ToString
@EsIndex(name = "jdevelops_test_res_article", ddlAuto = EsDdlAuto.UPDATE)
public class TestResArticleES {


    @EsField(basic = @EsFieldBasic(type = EsType.LONG, index = false))
    private Long id;

    /**
     * 唯一标识ID
     */
    String uid;

    /**
     * DOI
     */
    String doi;


    /**
     * PMID
     */
    @EsField(basic = @EsFieldBasic(type = EsType.text, analyzer = "english"))
    String pmid;


    /**
     * PMCID
     */
    @EsField(
            basic = @EsFieldBasic(type = EsType.text),
            fields = {
                    @EsFieldMultiType(
                            alias = "PMCIDnickname",
                            basic = @EsFieldBasic(
                                    type = EsType.text,
                                    analyzer = "english")
                    )
            }
    )
    String pmcid;


    /**
     * 题名
     */
    @EsField(basic = @EsFieldBasic(type = EsType.text))
    String title;


    /**
     * 发文作者
     */
    @EsField(basic = @EsFieldBasic(type = EsType.text))
    String authors;

    /**
     * 发文机构
     */
    @EsField(basic = @EsFieldBasic(type = EsType.text))
    String organizations;

    /**
     * 发布日期
     */

    @EsField(
            basic = @EsFieldBasic(
                    type = EsType.date,
                    format = {YYYYMMDDHHMMSS, YYYYMMDD, YYYYMM, YYYY, EPOCH_MILLIS, STRICT_DATE_OPTIONAL_TIME}
            ),
            fields = {
                    @EsFieldMultiType(
                            alias = "pubDate2",
                            basic = @EsFieldBasic(
                                    type = EsType.date,
                                    format = {YYYYMMDDHHMMSS, YYYYMMDD, YYYYMM, YYYY, EPOCH_MILLIS, STRICT_DATE_OPTIONAL_TIME}
                            )
                    ),
                    @EsFieldMultiType(
                            alias = "pubDate3",
                            basic = @EsFieldBasic(
                                    type = EsType.date,
                                    format = {YYYYMMDDHHMMSS, YYYYMMDD, YYYYMM, YYYY, EPOCH_MILLIS, STRICT_DATE_OPTIONAL_TIME}
                            )
                    )
            }
    )
    String pubDate;

    /**
     * 期刊
     */
    @EsField(basic = @EsFieldBasic(type = EsType.text))
    String journal;


    /**
     * ISSN
     */
    @EsField(basic = @EsFieldBasic(type = EsType.keyword))
    String issn;

    /**
     * 年
     */
    @EsField(basic = @EsFieldBasic(type = EsType.integer))
    Integer year;

    /**
     * 卷
     */
    @EsField(basic = @EsFieldBasic(type = EsType.keyword))
    String volume;

    /**
     * 期
     */
    @EsField(basic = @EsFieldBasic(type = EsType.keyword))
    String issue;

    /**
     * 页码
     */
    @EsField(basic = @EsFieldBasic(type = EsType.keyword))
    Integer page;

    /**
     * 文章类型
     */
    @EsField(basic = @EsFieldBasic(type = EsType.keyword))
    String type;


    /**
     * 摘要(abstract是关键字)
     */
    @EsField(basic = @EsFieldBasic(type = EsType.text))
    String abstractStr;

    /**
     * 关键词
     */
    @EsField(basic = @EsFieldBasic(type = EsType.text))
    String keywords;


    /**
     * 状态
     */
    @EsField(basic = @EsFieldBasic(type = EsType.integer))
    Integer state;

    /**
     * 创建者姓名
     */
    @EsField(basic = @EsFieldBasic(type = EsType.keyword))
    String creatorName;

    /**
     * 编辑者姓名
     */
    @EsField(basic = @EsFieldBasic(type = EsType.keyword))
    String editorName;


    /**
     * 表示该字段为创建时间字段，在这个实体被insert的时候，会自动为其赋值
     */
    @EsField(basic = @EsFieldBasic(type = EsType.date,
            format = {YYYYMMDDHHMMSS, YYYYMMDD, YYYYMM, YYYY, EPOCH_MILLIS, STRICT_DATE_OPTIONAL_TIME}))
    private LocalDateTime createTime;

    /**
     * 表示该字段为创建人，在这个实体被insert的时候，会自动为其赋值
     */
    @EsField(basic = @EsFieldBasic(type = EsType.keyword))
    private String createUserName;

    /**
     * 表示该字段为修改时间字段，在这个实体被update的时候，会自动为其赋值
     */
    @EsField(basic = @EsFieldBasic(type = EsType.date,
            format = {YYYYMMDDHHMMSS, YYYYMMDD, YYYYMM, YYYY, EPOCH_MILLIS, STRICT_DATE_OPTIONAL_TIME}))
    private LocalDateTime updateTime;

    /**
     * 表示该字段为修改人，在这个实体被update的时候，会自动为其赋值
     */
    @EsField(basic = @EsFieldBasic(type = EsType.keyword))
    private String updateUserName;

    /**
     * 测试嵌套
     */
    @EsField(basic = @EsFieldBasic(type = EsType.nested))
    private TestResArticleESNestedChild nestedChild;

    /**
     * 测试嵌套
     */
    @EsField(basic = @EsFieldBasic(type = EsType.nested))
    private TestResArticleESNestedChild2 nestedChild21;
}
