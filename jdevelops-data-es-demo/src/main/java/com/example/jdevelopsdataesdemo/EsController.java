package com.example.jdevelopsdataesdemo;

import cn.jdevelops.api.result.response.ResultVO;
import cn.jdevelops.api.result.util.uuid.UUIDUtils;
import cn.jdevelops.data.es.core.ElasticService;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import com.example.jdevelopsdataesdemo.entity.TestResArticleES;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;


/**
 * es 测试
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/11/6 11:10
 */
@RestController
public class EsController {

    private final ElasticService elasticService;

    public EsController(ElasticService elasticService) {
        this.elasticService = elasticService;
    }

    /**
     * 添加测试数据
     */
    @GetMapping("/add")
    public ResultVO<String> addTest() throws IOException {
        TestResArticleES testResArticleES = new TestResArticleES();
        testResArticleES.setUid(UUIDUtils.getInstance().generateShortUuid());
        testResArticleES.setDoi("test");
        testResArticleES.setPmid("test");
        testResArticleES.setPmcid("test");
        testResArticleES.setTitle("test");
        testResArticleES.setAuthors("test");
        testResArticleES.setOrganizations("test");
        testResArticleES.setPubDate("2023");
        testResArticleES.setJournal("test");
        testResArticleES.setIssn("test");
        testResArticleES.setYear(2023);
        testResArticleES.setVolume("test");
        testResArticleES.setIssue("test");
        testResArticleES.setPage(1);
        testResArticleES.setType("test");
        testResArticleES.setAbstractStr("test");
        testResArticleES.setKeywords("test");
        testResArticleES.setState(1);
        testResArticleES.setCreatorName("test");
        testResArticleES.setEditorName("test");
        testResArticleES.setCreateTime(LocalDateTime.now());
        testResArticleES.setCreateUserName("test");
        testResArticleES.setUpdateTime(LocalDateTime.now());
        testResArticleES.setUpdateUserName("test");
        Long id = UUIDUtils.getInstance().generateShortUuidLong();
        testResArticleES.setId(id);
        elasticService.addDocument("jdevelops_test_res_article",
                id.toString(), testResArticleES);
        return ResultVO.successMessage("测试数据新增成功");
    }

    @GetMapping("/update/{title}/{uid}")
    public ResultVO<String> updateValue(@PathVariable("title") String title, @PathVariable("uid") String uid) throws IOException {
        Query query = TermQuery.of(t -> t.field("uid").value(uid))._toQuery();
        elasticService.updateFieldValue("jdevelops_test_res_article", query, "title", title);
        return ResultVO.successMessage("修改title成功");
    }
}
