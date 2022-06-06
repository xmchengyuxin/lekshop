package com.chengyu.core.util.third.logic;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.chengyu.core.domain.result.GoodsThirdResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.util.approve.AliHttpsUtil;
import com.chengyu.core.util.third.manager.ThirdUtilFactory;
import com.chengyu.core.utils.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LeGreen
 * @date 2021/8/26
 */
@Service("aliLogic")
public class AliLogic extends ThirdUtilFactory {


    @Override
    public String approveRealname(String idCard, String name) throws ServiceException {
        //https://market.aliyun.com/products/57124001/cmapi00035880.html?spm=5176.2020520132.101.3.466b7218h9mL8S#sku=yuncode2988000001
        String ALI_APP_CODE = "6701d95086bb44fdb187a1e2083d7896";

        String host = "https://dfidveri.market.alicloudapi.com";
        String path = "/verify_id_name";
        String method = "POST";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + ALI_APP_CODE);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("id_number", idCard);
        bodys.put("name", name);


        try {
            HttpResponse response = AliHttpsUtil.doPost(host, path, method, headers, querys, bodys);
            //获取response的body
            JSONObject json = JSONUtil.parseObj(EntityUtils.toString(response.getEntity()));
            if("OK".equals(json.get("status"))){
                System.out.println("身份认证通过");
                return null;
            }else{
                return json.getStr("result_message");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String approveBank(String accountNo, String accountName, String idCard) throws ServiceException {
        //https://market.aliyun.com/products/57000002/cmapi00043974.html?spm=5176.2020520132.101.3.5e697218SjAhH5#sku=yuncode3797400001
        String ALI_APP_CODE = "6701d95086bb44fdb187a1e2083d7896";

        String host = "https://b3bzxv1.market.alicloudapi.com";
        String path = "/bank/3bzxv1";
        String method = "POST";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + ALI_APP_CODE);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        //需要给X-Ca-Nonce的值生成随机字符串，每次请求不能相同
        headers.put("X-Ca-Nonce", StringUtils.genenrateInd());
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("bankcard", accountNo);
        bodys.put("idcard", idCard);
        bodys.put("realname",accountName);


        try {
            HttpResponse response = AliHttpsUtil.doPost(host, path, method, headers, querys, bodys);
            //获取response的body
            JSONObject json = JSONUtil.parseObj(EntityUtils.toString(response.getEntity()));
            if("00000".equals(json.get("errcode"))){
                System.out.println("银行卡认证通过");
                return null;
            }else{
                return json.getStr("errmsg");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String upload(MultipartFile file, String fileName, int type) throws ServiceException {
        throw new ServiceException("接口调用错误");
    }

    @Override
    public GoodsThirdResult getGoodsDetail(String url) throws ServiceException {
        throw new ServiceException("接口调用错误");
    }

}
