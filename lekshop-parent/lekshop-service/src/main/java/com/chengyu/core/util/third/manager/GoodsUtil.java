package com.chengyu.core.util.third.manager;

import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.utils.StringUtils;

public class GoodsUtil {

    public static String getTbGoodsIdByUrl(String url){
        if(StringUtils.isBlank(url)){
            return "";
        }
        //https://detail.tmall.com/item.htm?spm=a21ag.12100465.0.0.1a0450a5gbYj5D&id=641929645819
        //https://detail.tmall.com/item.htm?spm=a230r.1.14.1.39b57c7592zygm&id=617013751039&cm_id=140105335569ed55e27b&abbucket=15&skuId=4520672083972
        //http://s.m.taobao.com/h5?q=%E4%BB%8A%E5%B9%B4%E6%B5%81%E8%A1%8C%E7%B4%AB%E8%89%B2%E8%A3%99%E5%AD%90%E5%A4%8F%E6%B3%95%E5%BC%8F%E8%B6%85%E4%BB%99&spm=a2141.1.searchbar.searchbox&layeredSrp=true&_navigation_params=%7B%22needdismiss%22%3A%220%22%2C%22animated%22%3A%220%22%2C%22needpoptoroot%22%3A%220%22%7D&search_action=initiative&ad_type=1.0&from=input&scm=1007.home_topbar.searchbox.d&datasource_token=1624357773256&sourceType=other&suid=139f243f-d510-4a0d-9a51-5c1921049f80&ut_sk=1.YMAr2cCf3RoDANovzB5apvYW_21646297_1624357734446.Copy.shoutaosearch&un=91bb18bcb443f655a05d16ed04ccadc8&share_crt_v=1&sp_tk=N29RY1hTQm56elg=&bxsign=tcd9OpcyqFDeHD356Jq5HwKlU3bfOjIdTqWov5qHLYgs6hInQHovAsCCm6Y26zfet20VxtYfqac1B9Gq5EmfnqN_ArLm70la2jzL3UhKjQ4HaM
        //https://item.taobao.com/item.htm?spm=a211oj.20087502.6500797680.ditem1.66c22a7bOnWdd8&id=652876058350&utparam=null
        String url0 = url.split("\\?")[1];
        String[] params = url0.split("&");
        String id= "";
        for(String param : params){
            if(param.startsWith("id=")){
                id = param.replace("id=", "");
                break;
            }
        }
        if(StringUtils.isNotBlank(id)){
            return id;
        }

        String url1 = url.split("\\?")[0];
        params = url1.split("\\/");
        for(String param : params){
            if(param.endsWith(".htm")){
                id = param.replace(".htm", "").replace("i", "");
                break;
            }
        }
        return StringUtils.isNotBlank(id) ? id.trim() : id;
    }

    public static String getJdGoodsId(String url) throws ServiceException {
        //https://item.m.jd.com/product/11435453591.html?wxa_abtest=o&utm_source=iosapp&utm_medium=appshare&utm_campaign=t_335139774&utm_term=CopyURL&ad_od=share&utm_user=plusmember&gx=RnFixjRRbDaPw9RP--txD2cJu6ejbR2MctQv
        String params1 = url.split("\\?")[0];
        for(String params : params1.split("/")){
            if(params.contains(".html")){
                return params.replaceAll(".html", "");
            }
        }
        throw new ServiceException("商品识别失败, 请手动填写");
    }

    public static String getPddGoodsId(String url) throws ServiceException {
        //https://mobile.yangkeduo.com/goods1.html?_wvx=10&refer_share_uid=6107798832&share_uin=6BVPUWUMQA45KHFB7IOIK56HNI_GEXDA&page_from=0&_wv=41729&refer_share_channel=copy_link&refer_share_id=ULDs5TOYuiSDnYXgZ2RgEPs9xbGWoyQ4&share_uid=6107798832&pxq_secret_key=JQV6ZE4ELIWJMU4AKFJHY2XCRRE6RSRZB3QFV7LEYUQMWYOUTW5Q&goods_id=166915394901
        String params1 = url.split("\\?")[1];
        for(String params : params1.split("&")){
            if(params.contains("goods_id")){
                return params.split("=")[1];
            }
        }
        throw new ServiceException("商品识别失败, 请手动填写");
    }

    public static String getGoodsTypeByUrl(String url) {
        if(url.contains("tmall.com") || url.contains("taobao.com")){
            return CommonConstant.TAOBAO;
        }else if(url.contains("jd.com")){
            return CommonConstant.JINGDONG;
        }else if(url.contains("yangkeduo.com")){
            return CommonConstant.PINDUODUO;
        }else{
            return CommonConstant.TAOBAO;
        }
    }
}
