package cn.tannn.jdevelops.demo.jpa.delete.dto;

import cn.tannn.jdevelops.annotations.jpa.JpaSelectOperator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDelete {
    @JpaSelectOperator
    private String address;
}
