package com.xbz.vpase.response;

import java.util.List;

/**
 * 获取result结果的方式
 * <p>
 * 注： 命名而已并没有用到工厂
 */

public class ResultFactory {

    /**
     * 判断单个对象
     * @param param
     * @return
     */
    public static <T> ResultJson getResultJson(T param) {
        ResultJson result = new ResultJson();
        if (param == null) {
            result.ResultJson(RetCode.ERROR);
            return result;
        }

        result.ResultJson(RetCode.OK);
        result.setData(param);
        return result;
    }

    /**
     * 判断集合对象
     * @param list
     * @param <T>
     * @return
     */
    public static <T> ResultJson getResultJson(List<T> list) {
        ResultJson result = new ResultJson();
        if (list == null) {
            result.ResultJson(RetCode.ERROR);
            return result;
        }

        if (list.isEmpty()) {
            result.ResultJson(RetCode.OK);
            result.setMessage("没有查找到相关记录");
            return result;
        }

        result.ResultJson(RetCode.OK);
        result.setData(list);
        return result;
    }

    /**
     * 获取空异常
     * @return
     */
    public static ResultJson getNullParamResultJson() {
        ResultJson resultJson = new ResultJson();
        resultJson.ResultJson(RetCode.PARAM_NULL);
        return resultJson;
    }

    /**
     * 获取错误对象
     * @return
     */
    public static ResultJson getErrorResultJson(){
        ResultJson resultJson = new ResultJson();
        resultJson.ResultJson(RetCode.ERROR);
        return resultJson;
    }

    /**
     * 获取成功对象
     * @return
     */
    public static ResultJson getSuccessResultJson(){
        ResultJson resultJson = new ResultJson();
        resultJson.ResultJson(RetCode.OK);
        return resultJson;
    }
}
