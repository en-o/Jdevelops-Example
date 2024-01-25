package cn.tan.authorizationserverusermanage.dao;

import cn.tan.authorizationserverusermanage.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/1/25 13:25
 */
public interface UserInfoDao extends JpaRepository<UserInfo,Long> {

    Optional<UserInfo> findByLoginName(String loginName);

    Optional<UserInfo> findByPhone(String phone);
}
