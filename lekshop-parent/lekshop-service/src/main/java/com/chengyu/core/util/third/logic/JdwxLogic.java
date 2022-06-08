package com.chengyu.core.util.third.logic;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.InterfaceEnums;
import com.chengyu.core.domain.result.GoodsThirdResult;
import com.chengyu.core.domain.result.InterfaceConfig;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.service.system.InterfaceConfigService;
import com.chengyu.core.util.third.manager.GoodsUtil;
import com.chengyu.core.util.third.manager.ThirdUtilFactory;
import com.chengyu.core.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title  京东万象工具类
 * @author LeGreen
 * @date   2021/11/23
 */
@Log4j2
@Service("jdwxLogic")
public class JdwxLogic extends ThirdUtilFactory {

    @Autowired
    private InterfaceConfigService interfaceConfigService;

    private static final String SUS_CODE = "10000";

    public InterfaceConfig getConfig(){
        return interfaceConfigService.getParams(InterfaceEnums.JDWX.getKey());
    }

    @Override
    public String approveRealname(String idCard, String name) throws ServiceException {
        throw new ServiceException("接口调用错误");
    }

    @Override
    public String approveBank(String accountNo, String accountName, String idCard) throws ServiceException {
        throw new ServiceException("接口调用错误");
    }

    @Override
    public String upload(MultipartFile file, String fileName, int type) throws ServiceException {
        throw new ServiceException("接口调用错误");
    }

    @Override
    public GoodsThirdResult getGoodsDetail(String url) throws ServiceException {
        if(StringUtils.isBlank(url)){
            throw new ServiceException("请输入正确的商品详情链接");
        }
        String type = GoodsUtil.getGoodsTypeByUrl(url);
        String message = HttpUtil.get(this.getGoodsGetUrl(url, type));
        return this.parseGoodsDetail(message, type);
    }

    private GoodsThirdResult parseGoodsDetail(String message, String type) throws ServiceException {
        JSONObject jsonObject = JSONUtil.parseObj(message);
        String code = jsonObject.getStr("code");
        log.info(message);
        if(SUS_CODE.equals(code)){
            String data = jsonObject.getStr("result");
            JSONObject json = JSONUtil.parseObj(data);
            if(json.getInt("code") != 0){
                throw new ServiceException("商品识别失败, 请手动填写");
            }
            JSONObject bodyJson = JSONUtil.parseObj(json.getStr("ret_body"));
            GoodsThirdResult goods = new GoodsThirdResult();
            switch (type) {
                case CommonConstant.TAOBAO:
                    JSONObject taobaoJson = JSONUtil.parseObj(bodyJson.getStr("item"));
                    goods.setTitle(taobaoJson.getStr("title"));
                    JSONArray jsonArray = taobaoJson.getJSONArray("images");
                    goods.setMainImg((String) jsonArray.get(0));
                    goods.setGoodsImg(jsonArray.join("|"));
                    goods.setDetail(taobaoJson.getStr("desc"));

                    JSONArray attrKeyArray = taobaoJson.getJSONArray("props_list");
                    List<Map> attrKeys = attrKeyArray.toList(Map.class);
                    break;
                case CommonConstant.JINGDONG:
                    goods.setTitle(bodyJson.getStr("skuName"));
                    goods.setMainImg(bodyJson.getStr("image"));
                    break;
                case CommonConstant.PINDUODUO:
                    goods.setTitle(bodyJson.getStr("goods_name"));
                    goods.setMainImg(bodyJson.getStr("thumb_url"));
                    break;
                default:
            }
            return goods;
        }else{
            throw new ServiceException("商品识别失败, 请手动填写");
        }
    }

    private String getGoodsGetUrl(String goodsUrl, String type) throws ServiceException {
        InterfaceConfig config = getConfig();
        String url = null;
        String goodsId;
        Map<String,Object> params = new HashMap<>();
        params.put("appkey", config.getAppId());

        switch (type) {
            case CommonConstant.TAOBAO:
                goodsId = GoodsUtil.getTbGoodsIdByUrl(goodsUrl);
                params.put("num_iid", goodsId);
                url = HttpUtil.urlWithForm(config.getApiUrl()+"/idata/taobaoItemDetailGet", params, StandardCharsets.UTF_8, false);
                break;
            case CommonConstant.JINGDONG:
                goodsId = GoodsUtil.getJdGoodsId(goodsUrl);
                params.put("product_id", goodsId);
                url = HttpUtil.urlWithForm(config.getApiUrl()+"/TONGLI/ProductSkuViewGet", params, StandardCharsets.UTF_8, false);
                break;
            case CommonConstant.PINDUODUO:
                goodsId = GoodsUtil.getPddGoodsId(goodsUrl);
                params.put("goods_id", goodsId);
                url = HttpUtil.urlWithForm(config.getApiUrl()+"/TONGLI/GoodsDetail", params, StandardCharsets.UTF_8, false);
                break;
            default:
        }
        log.info("京东万象请求参数>>"+url);
        return url;
    }

    /**
     * 查询余额
     * @author LeGreen
     * @date   2021/6/5
     * @return int
     * @throws Exception 业务异常
     */
    public int getLeftBalance() throws Exception{
        throw new ServiceException("查询剩余余额失败");
    }

}
