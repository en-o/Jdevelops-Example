package cn.tannn.demo.jdevelops.logsp6spy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LogsP6spyApplicationTests {

    @Autowired
    public SpyDao spyDao;

    @Test
    void insert() {
        spyDao.save(new Spy("tan"));
        spyDao.save(new Spy("ning"));
        spyDao.save(new Spy("星期天"));
        spyDao.save(new Spy("早上好"));
    }

    @Test
    void select() {
        System.out.println("select: " + spyDao.findByName("tan"));
    }

    @Test
    void delete() {
        System.out.println("delete: " + spyDao.deleteByName("ning"));
    }

    @Test
    void update() {
        List<Spy> s = spyDao.findByName("星期天");
        s.forEach(x -> {
            x.setName("update 星期天");
            spyDao.save(x);
        });
    }

}
