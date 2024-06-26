package cn.tannn.demo.jdevelops.logsp6spy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spy")
public class SpyController {

    @Autowired
    private SpyDao spyDao;

    @PostMapping
    public ResponseEntity<Spy> insert(@RequestBody Spy spy) {
        return ResponseEntity.ok(spyDao.save(spy));
    }

    @GetMapping("/select/{name}")
    public ResponseEntity<List<Spy>> select(@PathVariable String name) {
        return ResponseEntity.ok(spyDao.findByName(name));
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Integer> delete(@PathVariable String name) {
        return ResponseEntity.ok(spyDao.deleteByName(name));
    }

    @PutMapping("/updateWeekend")
    public ResponseEntity<Void> update() {
        List<Spy> spies = spyDao.findByName("星期天");
        spies.forEach(spy -> {
            spy.setName("update 星期天");
            spyDao.save(spy);
        });
        return ResponseEntity.ok().build();
    }

    @PostMapping("/insertTestData")
    public ResponseEntity<Void> insertTestData() {
        spyDao.save(new Spy("tan"));
        spyDao.save(new Spy("ning"));
        spyDao.save(new Spy("星期天"));
        spyDao.save(new Spy("早上好"));
        return ResponseEntity.ok().build();
    }
}
