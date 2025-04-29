package cn.tannn.test.logslogin.storage;


import cn.tannn.jdevelops.jpa.generator.UuidCustomGenerator;
import cn.tannn.jdevelops.logs.constant.LoginType;
import cn.tannn.jdevelops.logs.context.LoginContext;
import cn.tannn.jdevelops.logs.model.LoginLogRecord;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

/**
 * 登录日志
 *
 * @author <a href="https://t.tannn.cn/">tnnn</a>
 * @version V1.0
 * @date 2025/4/29 21:36
 */
@Entity
@Table(name = "login_log")
@Comment("登录日志")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@DynamicUpdate
@DynamicInsert
public class LoginLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuidCustomGenerator")
    @GenericGenerator(name = "uuidCustomGenerator", type = UuidCustomGenerator.class)
    @Column(columnDefinition = "bigint")
    @Comment("主键，自动生成")
    private Long id;


    /**
     * 登录状态：0[失败],1[成功]
     */
    private Integer status;


    /**
     * true = 退出 ， false = 登录
     */
    private boolean logout;

    /**
     * 备注
     */
    private String description;

    /**
     * 登录类型
     *
     * @see LoginType
     */
    private String type;


    /**
     * 登录平台 (默认冲token里取)
     */
    private String platform;

    /**
     * 登录时间（yyyy-MM-dd HH:mm:ss）
     */
    private LocalDateTime loginTime;

    /**
     * 解析表达式后获取的参数
     */
    private String expression;


    /**
     * 访问设备信息
     */
    private String userAgent;

    /**
     * 登录的IP
     */
    private String ipAddress;

    /**
     * ip归属地[国家-区域-省份-城市-ISP]
     */
    private String ipRegion;

    /**
     * 当前登录名
     */
    private String loginName;


    private String userId;

    private String name;

    private String requestId;


    public LoginLog value(LoginLogRecord logRecord) {
        this.status = logRecord.getStatus();
        this.logout = logRecord.isLogout();
        this.description = logRecord.getDescription();
        this.type = logRecord.getType();

        this.loginTime = logRecord.getLoginTime();
        LoginContext loginContext = logRecord.getLoginContext();
        if (loginContext != null) {
            this.userId = loginContext.getUserId();
            this.name = loginContext.getName();
            this.requestId = loginContext.getRequestId();
            if(loginContext.getLoginName()!=null){
                this.loginName = loginContext.getLoginName();
            }
            if(loginContext.getPlatform()!=null){
                this.platform = loginContext.getPlatform();
            }
        }
        this.expression = logRecord.getExpression();
        this.userAgent = logRecord.getUserAgent();
        this.ipAddress = logRecord.getIpAddress();
        this.ipRegion = logRecord.getIpRegion();
        return this;
    }

}
