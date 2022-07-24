package cn.jdevelops.build.util;
import cn.jdevelops.jwt.util.JwtUtil;
import cn.jdevelops.jwtweb.util.JwtWebUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;



/**
 * 用户token相关的工具
 *
 * @author tn
 * @date 2021-09-13 14:36
 */
public class UserTokenUtil {


	/**
	 * 获取消息头中 的token
	 *  默认返回admin
	 * @param request request
	 * @return com.detabes.evc.vo.user.UserVO
	 * @author lxw
	 * @date 2021/11/17 11:28
	 */
	public static String getLoginNameByToke(HttpServletRequest request) {
		String token = JwtWebUtil.getToken(request);
		if (StringUtils.isBlank(token)) {
			return null;
		}
		try {
			return JwtUtil.getClaim(token,null);
		} catch (Exception e) {
			return null;
		}
	}

}
