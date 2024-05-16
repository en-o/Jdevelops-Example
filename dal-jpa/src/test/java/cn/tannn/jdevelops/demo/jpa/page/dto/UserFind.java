package cn.tannn.jdevelops.demo.jpa.page.dto;

import cn.tannn.jdevelops.annotations.jpa.JpaSelectOperator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFind {
    @JpaSelectOperator
    private String address;
}
