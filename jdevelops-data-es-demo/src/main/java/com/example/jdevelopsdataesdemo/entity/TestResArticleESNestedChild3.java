package com.example.jdevelopsdataesdemo.entity;


import cn.jdevelops.annotation.es.EsField;
import cn.jdevelops.annotation.es.basic.EsFieldBasic;
import cn.jdevelops.annotation.es.constant.EsType;
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
public class TestResArticleESNestedChild3 {



    /**
     * 唯一标识ID
     */
    @EsField(basic = @EsFieldBasic(type = EsType.keyword))
    String fix3;

    /**
     * DOI
     */
    @EsField(basic = @EsFieldBasic(type = EsType.keyword))
    String sex3;


    /**
     * PMID
     */
    @EsField(basic = @EsFieldBasic(type = EsType.keyword))
    String name3;



}
