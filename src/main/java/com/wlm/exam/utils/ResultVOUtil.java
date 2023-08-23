package com.wlm.exam.utils;

import com.wlm.exam.VO.ResultResponse;

public class ResultVOUtil {
    public static ResultResponse success(Object object) {
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setData(object);
        resultResponse.setMsg("成功");
        resultResponse.setCode(0);
        return resultResponse;
    }

    public static ResultResponse success() {
        return success(null);
    }

    public static ResultResponse error(Integer code, String msg) {
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setData(null);
        resultResponse.setCode(code);
        resultResponse.setMsg(msg);
        return resultResponse;
    }
}
