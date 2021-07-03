package org.czh.commons.entity.resp;

/**
 * @author : czh
 * description :
 * date : 2021-07-03
 * email 916419307@qq.com
 */
public class BaseResponseTest {

    public static void main(String[] args) {
        // BaseResponse(code=0, msg=SUCCESS, data=null)
        System.out.println(BaseResponse.newSuccessResp());
        // BaseResponse(code=-99, msg=FAIL, data=null)
        System.out.println(BaseResponse.newFailResp());
        // BaseResponse(code=-99, msg=fail, data=null)
        System.out.println(BaseResponse.newFailResp("fail"));
    }

}
