package cn.tannn.demo.jdevelops.logsp6spy;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

/**
 * 用户表 sys_user
 * @author : tn
 * @date : 2021-9-10
 */
@Entity
@Table(name = "test_spy")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Spy {

	/**
	 * 用户编号
	 */
	@Id
	private String userNo;

	/**
	 * 姓名
	 */
	private String name;

	public Spy(String userNo, String name) {
		this.userNo = userNo;
		this.name = name;
	}

	public Spy(String name) {
		this.userNo = UUID.randomUUID().toString();
		this.name = name;
	}
}
