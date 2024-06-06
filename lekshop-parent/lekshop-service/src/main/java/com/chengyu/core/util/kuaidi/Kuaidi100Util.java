package com.chengyu.core.util.kuaidi;

import cn.hutool.json.JSONUtil;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.utils.StringUtils;
import com.google.gson.Gson;
import com.kuaidi100.sdk.api.QueryTrack;
import com.kuaidi100.sdk.api.Subscribe;
import com.kuaidi100.sdk.contant.ApiInfoConstant;
import com.kuaidi100.sdk.core.IBaseClient;
import com.kuaidi100.sdk.pojo.HttpResult;
import com.kuaidi100.sdk.request.*;
import com.kuaidi100.sdk.response.BaseResponse;
import com.kuaidi100.sdk.utils.SignUtils;

public class Kuaidi100Util {

    static String key = "MlywewRS7534";//PropertiesReader.get("key");
    static String customer = "8F8243C126DE7B0D38B186DB7BC936FA";//PropertiesReader.get("customer");
    static String secret = PropertiesReader.get("secret");
    static String siid = PropertiesReader.get("siid");
    static String userid = PropertiesReader.get("userid");
    static String tid = PropertiesReader.get("tid");
    static String secret_key = PropertiesReader.get("secret_key");
    static String secret_secret = PropertiesReader.get("secret_secret");


    /**
     * 实时快递查询接口
     * @author LeGreen
     * @date   2023/11/10
     * @param  num 快递单号
     * @param  phone 手机
     * @param  company 快递公司
     * @return BaseResponse
     */
    public static QueryDeliveryResponse queryTrack(String num, String phone, String company) throws Exception{
        if(StringUtils.isBlank(company)) {
            throw new ServiceException("快递公司不在查询范围内");
        }
        QueryTrackReq queryTrackReq = new QueryTrackReq();
        QueryTrackParam queryTrackParam = new QueryTrackParam();
        queryTrackParam.setCom(company);
        queryTrackParam.setNum(num);
        queryTrackParam.setPhone(phone);
        String param = new Gson().toJson(queryTrackParam);

        queryTrackReq.setParam(param);
        queryTrackReq.setCustomer(customer);
        queryTrackReq.setSign(SignUtils.querySign(param ,key,customer));

        IBaseClient baseClient = new QueryTrack();
        HttpResult result = baseClient.execute(queryTrackReq);
        return JSONUtil.toBean(result.getBody(), QueryDeliveryResponse.class);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(queryTrack("JD0145976299959", "13666011848","jd"));
    }

    /**
     * 订阅接口(订阅后, 快递100有物流变化会通知到回调链接)
     * @author LeGreen
     * @date   2023/11/10
     * @param  callbackUrl 回调链接
     * @param  phone 手机
     * @param  num 快递单号
     * @param  company 快递公司
     */
    public static BaseResponse subscribe(String callbackUrl, String phone, String num, String company) throws Exception{
        SubscribeParameters subscribeParameters = new SubscribeParameters();
        subscribeParameters.setCallbackurl(callbackUrl);
        subscribeParameters.setPhone(phone);

        SubscribeParam subscribeParam = new SubscribeParam();
        subscribeParam.setParameters(subscribeParameters);
        subscribeParam.setCompany(company);
        subscribeParam.setNumber(num);
        subscribeParam.setKey(key);

        SubscribeReq subscribeReq = new SubscribeReq();
        subscribeReq.setSchema(ApiInfoConstant.SUBSCRIBE_SCHEMA);
        subscribeReq.setParam(new Gson().toJson(subscribeParam));

        IBaseClient subscribe = new Subscribe();
        return subscribe.executeToObject(subscribeReq);
    }
}
