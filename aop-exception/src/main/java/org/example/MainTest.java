package org.example;

import cn.jdevelops.exception.annotation.DisposeException;
import cn.jdevelops.result.result.ResultVO;

/**
 * 普通方法上不生效，目前测试web可行，因为用的是aop
 * @author tnnn
 * @version V1.0
 * @date 2022-06-24 17:53
 */
public class MainTest {

    public static void main(String[] args) {
            test1();
//            test2(1);
//            test2(2);
//            test3(1);
//            test3(2);
    }


    @DisposeException(messages ="错误的零", codes = 500, exceptions = ArithmeticException.class )
    public static ResultVO<String> test1(){
        int zero = 1 / 0;
        return ResultVO.success();
    }




    /**
     * 多个异常处理
     * @param i
     * @return
     */
    @DisposeException(messages ={"错误的零","下标越界"}, codes = {500,501}, exceptions = {
            ArithmeticException.class,
            ArrayIndexOutOfBoundsException.class} )
    public static ResultVO<String>  test2(int i){
        if(i ==  1){
            int zero = 1 / 0;
        }else {
            int[] indexs = {1,2,3};
            System.out.println(indexs[4]);
        }
        return ResultVO.success();
    }


    /**
     * 全部公用默认code
     * @param i
     * @return
     */
    @DisposeException(messages ={"错误的零","下标越界"}, exceptions = {
            ArithmeticException.class,
            ArrayIndexOutOfBoundsException.class} )
    public static ResultVO<String>  test3(int i){
        if(i ==  1){
            int zero = 1 / 0;
        }else {
            int[] indexs = {1,2,3};
            System.out.println(indexs[4]);
        }
        return ResultVO.success();
    }
}
