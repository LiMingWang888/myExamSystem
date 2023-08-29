package com.wlm.exam.VO;

import com.wlm.exam.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author WangLiMing
 * @Description: 自定义数据传输
 * @Date 2023/8/14 14:27
 **/
@Data
public class ResultResponse<T> implements Serializable {

    private static final long serialVersionUID = 5787017166520190094L;

    //响应代码
    private Integer code;

    //响应消息
    private String msg;

    //响应结果
    private T data;

    public static <T> ResultResponse<T> success() {
        return success(null);
    }

    public static <T> ResultResponse<T> success(T data) {
        ResultResponse<T> resultResponse = new ResultResponse<>();
        resultResponse.setCode(ResultEnum.SUCCESS.getCode());
        resultResponse.setData(data);
        resultResponse.setMsg(ResultEnum.SUCCESS.getMessage());
        return resultResponse;
    }

    public static <T> ResultResponse<T> success(String message, T data) {
        ResultResponse<T> resultResponse = new ResultResponse<>();
        resultResponse.setCode(ResultEnum.SUCCESS.getCode());
        resultResponse.setData(data);
        resultResponse.setMsg(message);
        return resultResponse;
    }

    public static <T> ResultResponse<T> error(String message) {
        ResultResponse<T> resultResponse = new ResultResponse<>();
        resultResponse.setCode(ResultEnum.SUCCESS.getCode());
        resultResponse.setData(null);
        resultResponse.setMsg(message);
        return resultResponse;
    }

    public static <T> ResultResponse<T> error(String message, Integer code) {
        ResultResponse<T> resultResponse = new ResultResponse<>();
        resultResponse.setCode(code);
        resultResponse.setData(null);
        resultResponse.setMsg(message);
        return resultResponse;
    }
}
