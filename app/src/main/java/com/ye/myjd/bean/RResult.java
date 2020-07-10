package com.ye.myjd.bean;

/**
 * @author : WoDong
 * @date : 2020/3/15 18:57
 * @desc :
 */
public class RResult {
    /*
     "success": true,
   "errorMsg": "",
   "result": {
       "id": "用户id",
       "userName": "用户名",
       "userIcon": "头像路径",
       "waitPayCount": 待付款数,
       "waitReceiveCount": 待收货数,
       "userLevel": 用户等级（1注册会员2铜牌会员3银牌会员4金牌会员5钻石会员）
   }
     */
    private boolean success;
    private String errorMsg;
    private String result;

    @Override
    public String toString() {
        return "RResult{" +
                "success=" + success +
                ", errorMsg='" + errorMsg + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
