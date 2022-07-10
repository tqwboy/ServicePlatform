package com.hohenheim.java.serviceplatform.core.web;


import com.hohenheim.java.serviceplatform.core.define.CoreResultCodes;
import com.hohenheim.java.serviceplatform.core.define.IResultCode;
import com.hohenheim.java.serviceplatform.core.model.FailRespModel;
import com.hohenheim.java.serviceplatform.core.model.SuccessRespModel;

/**
 * @author Hohenheim
 * @date 2019/12/22
 * @description 响应数据封装
 */
public class ResponsePack {
    public static SuccessRespModel<Object> reqSuccess() {
        return reqSuccess(null);
    }

    public static SuccessRespModel<Object> reqSuccess(Object data) {
        return reqSuccess(data, CoreResultCodes.RETURN_SUCCESS.getMsg());
    }

    public static SuccessRespModel<Object> reqSuccess(Object data, String successMsg) {
        SuccessRespModel<Object> resultResp = new SuccessRespModel<>();
        resultResp.setResultCode(CoreResultCodes.RETURN_SUCCESS.getCode());
        resultResp.setMsg(successMsg);
        resultResp.setReqSuccess(true);

        if(null != data) {
            resultResp.setData(data);
        }

        return resultResp;
    }

    public static FailRespModel<Object> reqFail(IResultCode resultCode) {
        return reqFail(resultCode.getCode(), resultCode.getMsg());
    }

    public static FailRespModel<Object> reqFail(String errCode, String msg) {
        FailRespModel<Object> resultResp = new FailRespModel<>();
        resultResp.setResultCode(errCode);
        resultResp.setMsg(msg);
        resultResp.setReqSuccess(false);

        return resultResp;
    }
}