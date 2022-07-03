package org.example;

import cn.jdevelops.exception.annotation.DisposeException;
import cn.jdevelops.result.result.ResultVO;
import org.springframework.stereotype.Service;

/**
 * 测试
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-07-03 19:36
 */
@Service
@DisposeException(messages ={"错误的零","下标越界"}, codes = {500,501}, exceptions = {
        ArithmeticException.class,
        ArrayIndexOutOfBoundsException.class} )
public class IService {

    /**
     * 单个异常
     * @return
     */
    public ResultVO<String> test1(){
        int zero = 1 / 0;
        return ResultVO.success();
    }
    /**
     * 多个异常处理
     * @return
     */

    public ResultVO<String>  test2(){
        int[] indexs = {1,2,3};
        System.out.println(indexs[4]);
        return ResultVO.success();
    }
}
