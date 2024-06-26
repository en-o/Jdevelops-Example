package cn.tannn.demo.jdevelops.logsp6spy;

import com.p6spy.engine.common.P6Util;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

/**
 * now + "|" + elapsed + "|" + category + "|connection " + connectionId + "|url " + url + "|" + P6Util.singleLine(prepared) + "|" + P6Util.singleLine(sql);
 * #01-07-20 14:37:38:871 | took 1ms | statement | connection 0| url jdbc:p6spy:mysql://localhost:3306/data?serverTimezone=CTT&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=true
 * 自定义日志打印
 * @author tn
 * @version 1
 * @date 2020/7/3 9:19
 */
public class CustomP6SpyLoggerFormat implements MessageFormattingStrategy {

    static final String SELECT_1 = "SELECT 1";

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return "=====================================================\n" +
               "最终执行的sql：" + P6Util.singleLine(sql) +
               "\n=====================================================\n";
    }
}
