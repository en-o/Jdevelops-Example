package cn.tannn.demo.jdevelops.dales;

import cn.tannn.demo.jdevelops.dales.entity.TestResArticleES;
import cn.tannn.jdevelops.es.core.ElasticService;
import cn.tannn.jdevelops.result.response.ResultVO;
import cn.tannn.jdevelops.result.utils.UUIDUtils;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
     * 添加测试数据 - 索引操作
     * @param dataNum 数据数量 默认1条
     */
    @GetMapping("/index/data/add")
    public ResultVO<String> addTest(@RequestParam(value = "dataNum", required = false, defaultValue = "1") Integer dataNum) throws IOException {
        for (int i = 0; i < dataNum; i++) {
            TestResArticleES testResArticleES = new TestResArticleES();
            Long id = UUIDUtils.getInstance().generateShortUuidLong();
            testResArticleES.setUid(String.valueOf(id));
            testResArticleES.setDoi(generateRandomString());
            testResArticleES.setPmid(generateRandomString());
            testResArticleES.setPmcid(generateRandomString());
            testResArticleES.setTitle(generateRandomChineseString(5));
            testResArticleES.setAuthors(generateRandomChineseString(3));
            testResArticleES.setOrganizations(generateRandomChineseString(4));
            testResArticleES.setPubDate(String.valueOf(2020 + (int) (Math.random() * 4))); // 随机年份
            testResArticleES.setJournal(generateRandomString());
            testResArticleES.setIssn(generateRandomString());
            testResArticleES.setYear(2020 + (int) (Math.random() * 4)); // 随机年份
            testResArticleES.setVolume(generateRandomString());
            testResArticleES.setIssue(generateRandomString());
            testResArticleES.setPage((int) (Math.random() * 100) + 1); // 随机页码
            testResArticleES.setType(generateRandomString());
            testResArticleES.setAbstractStr(generateRandomChineseString(10));
            testResArticleES.setKeywords(generateRandomChineseString(6));
            testResArticleES.setState(1);
            testResArticleES.setCreatorName(generateRandomChineseString(3));
            testResArticleES.setEditorName(generateRandomChineseString(3));
            testResArticleES.setCreateTime(LocalDateTime.now());
            testResArticleES.setCreateUserName(generateRandomChineseString(3));
            testResArticleES.setUpdateTime(LocalDateTime.now());
            testResArticleES.setUpdateUserName(generateRandomChineseString(3));
            testResArticleES.setId(id);
            elasticService.addDocument("jdevelops_test_res_article", id.toString(), testResArticleES);
        }
        return ResultVO.successMessage(dataNum + "条测试数据新增成功");
    }

    /**
     * 测试修改单个字段值 - 索引操作
     */
    @GetMapping("/index/data/update/{title}/{uid}")
    public ResultVO<String> updateValue(@PathVariable("title") String title, @PathVariable("uid") String uid) throws IOException {
        Query query = TermQuery.of(t -> t.field("uid").value(uid))._toQuery();
        elasticService.updateFieldValue("jdevelops_test_res_article", query, "title", title);
        return ResultVO.successMessage("修改title成功");
    }




    private String generateRandomString() {
        int length = 8;
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt((int) (Math.random() * chars.length())));
        }
        return sb.toString();
    }

    private String generateRandomChineseString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1))));
        }
        return sb.toString();
    }
}
