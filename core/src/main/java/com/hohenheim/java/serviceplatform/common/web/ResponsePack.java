package com.hohenheim.java.serviceplatform.common.web;


import com.hohenheim.java.serviceplatform.common.define.CommonResultCodes;
import com.hohenheim.java.serviceplatform.common.define.IResultCode;
import com.hohenheim.java.serviceplatform.common.model.FailRespModel;
import com.hohenheim.java.serviceplatform.common.model.SuccessRespModel;

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
        return reqSuccess(data, CommonResultCodes.RETURN_SUCCESS.getMsg());
    }

    public static SuccessRespModel<Object> reqSuccess(Object data, String successMsg) {
        SuccessRespModel<Object> resultResp = new SuccessRespModel<>();
        resultResp.setResultCode(CommonResultCodes.RETURN_SUCCESS.getCode());
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

    public static FailRespModel<Object> reqFail(int errCode, String msg) {
        FailRespModel<Object> resultResp = new FailRespModel<>();
        resultResp.setResultCode(errCode);
        resultResp.setMsg(msg);
        resultResp.setReqSuccess(false);

        return resultResp;
    }
}