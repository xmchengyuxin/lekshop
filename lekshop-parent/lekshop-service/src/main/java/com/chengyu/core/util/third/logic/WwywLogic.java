package com.chengyu.core.util.third.logic;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.chengyu.core.domain.result.GoodsThirdResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.SysApproveConfig;
import com.chengyu.core.service.system.ApproveConfigService;
import com.chengyu.core.util.third.manager.ThirdUtilFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @title  万维易网
 * @author LeGreen
 * @date   2021/1/29
 */
@Service("wwywLogic")
public class WwywLogic extends ThirdUtilFactory {

    @Autowired
    private ApproveConfigService approveConfigService;


    @Override
    public String approveRealname(String idCard, String name) throws ServiceException {
        SysApproveConfig approveConfig = this.getConfig();
        String res= "";
        /*new ShowApiRequest(approveConfig.getApiUrl()+"/1072-1",approveConfig.getAppId(),approveConfig.getSecret())
                .addTextPara("idcard", idCard)
                .addTextPara("name", name)
                .post();*/
        System.out.println(res);
        JSONObject json = JSONUtil.parseObj(res);
        int showapiResCode = json.getInt("showapi_res_code");
        if(showapiResCode == 0){
            JSONObject jsonBody = JSONUtil.parseObj(json.get("showapi_res_body"));
            int code = jsonBody.getInt("code");
            if(code == 0){
                return null;
            }else if(code == 1){
                return "身份证与姓名不匹配";
            }else if(code == 2){
                return "无此身份证号码";
            }else if(code == 12){
                return "身份证号码不合法";
            }else if(code == 100){
                return "渠道异常，请稍后再试";
            }else if(code == 101){
                return "验证信息重复输入，避免恶意验证请间隔60秒以上再次核验";
            }else if(code == 103){
                return "24小时内相同姓名或卡号核验次数超限";
            }
        }else{
            return "实名认证调用失败";
        }
        return null;
    }

    @Override
    public String approveBank(String accountNo, String accountName, String idCard) throws ServiceException {
        SysApproveConfig approveConfig = this.getConfig();
        String res= "";
        /*new ShowApiRequest(approveConfig.getApiUrl()+"/1072-4",approveConfig.getAppId(),approveConfig.getSecret())
                .addTextPara("acct_pan",accountNo)
                .addTextPara("acct_name",accountName)
                .addTextPara("cert_type","01")
                .addTextPara("cert_id",idCard)
                .addTextPara("needBelongArea","true")
                .post();*/
        System.out.println(res);
        JSONObject json = JSONUtil.parseObj(res);
        int showapiResCode = json.getInt("showapi_res_code");
        if(showapiResCode == 0){
            JSONObject jsonBody = JSONUtil.parseObj(json.get("showapi_res_body"));
            int code = jsonBody.getInt("code");
            if(code == 0){
                return null;
            }else if(code == 4){
                return "此卡被没收,请于发卡方联系";
            }else if(code == 5){
                return "持卡人认证失败";
            }else if(code == 14){
                return "无效卡号";
            }else if(code == 15){
                return "此卡无对应发卡方";
            }else if(code == 21){
                return "该卡未初始化或睡眠卡";
            }else if(code == 34){
                return "作弊卡,吞卡";
            }else if(code == 40){
                return "发卡方不支持的交易";
            }else if(code == 41){
                return "此卡已经挂失";
            }else if(code == 43){
                return "此卡被没收";
            }else if(code == 54){
                return "该卡已过期";
            }else if(code == 57){
                return "发卡方不允许此交易";
            }else if(code == 62){
                return "受限制的卡";
            }else if(code == 75){
                return "密码错误次数超限";
            }else if(code == 82){
                return "身份证号码有误";
            }else if(code == 83){
                return "银行卡号码有误";
            }else if(code == 84){
                return "手机号码输入格式有误";
            }else if(code == 86){
                return "持卡人信息有误";
            }else if(code == 87){
                return "未开通无卡支付";
            }else if(code == 96){
                return "交易失败请重试";
            }else if(code == 100){
                return "渠道异常，请稍后再试";
            }else if(code == 101){
                return "验证信息重复输入，避免恶意验证请间隔60秒以上再次核验";
            }else if(code == 103){
                return "24小时内相同姓名或卡号核验次数超限";
            }
        }else{
            return "银行卡认证调用失败";
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

    private SysApproveConfig getConfig(){
        return approveConfigService.getApproveConfig();
    }
}
