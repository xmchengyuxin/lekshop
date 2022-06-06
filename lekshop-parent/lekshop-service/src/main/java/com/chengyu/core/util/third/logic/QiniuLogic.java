package com.chengyu.core.util.third.logic;

import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.result.GoodsThirdResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.service.system.QiniuService;
import com.chengyu.core.util.third.manager.ThirdUtilFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author LeGreen
 * @date 2021/8/26
 */
@Service("qiniuLogic")
public class QiniuLogic extends ThirdUtilFactory {

    @Autowired
    private QiniuService qiniuService;

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
        try {
            if(type == CommonConstant.IMG){
                return qiniuService.uploadByImg(file.getBytes(), fileName);
            }else{
                return qiniuService.uploadByVideo(file.getBytes(), fileName);
            }
        } catch (IOException e) {
            throw new ServiceException("上传失败");
        }
    }

    @Override
    public GoodsThirdResult getGoodsDetail(String url) throws ServiceException {
        throw new ServiceException("接口调用错误");
    }

}
