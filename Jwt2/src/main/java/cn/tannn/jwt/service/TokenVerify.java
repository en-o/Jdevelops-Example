package cn.tannn.jwt.service;

import org.springframework.stereotype.Service;

/**
 * token验证
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-07-24 16:31
 */
@Service
public class TokenVerify {

    /**
     * 验证token
     * @param token
     * @return
     */
    public boolean verity(String token) {
        return true;
    }

    /**
     * 刷新token
     * @param userCode
     */
    public void refreshUserToken(String userCode) {
        return;
    }
}
