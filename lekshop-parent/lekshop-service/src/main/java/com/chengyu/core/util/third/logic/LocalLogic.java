package com.chengyu.core.util.third.logic;

import com.chengyu.core.domain.result.GoodsThirdResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.service.system.ConfigService;
import com.chengyu.core.util.third.manager.ThirdUtilFactory;
import com.chengyu.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LeGreen
 * @date 2021/8/26
 */
@Service("localLogic")
public class LocalLogic extends ThirdUtilFactory {

    @Autowired
    private ConfigService configService;

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
        String savePath = configService.getValueByNid("local_img_save_path");
        if(StringUtils.isBlank(savePath)){
            throw new ServiceException("请先配置图片存储地址");
        }
        String url = configService.getValueByNid("local_img_url");
        if(StringUtils.isBlank(url)){
            throw new ServiceException("请先配置图片服务器URL");
        }
        File targetFile=null;
        //先判断文件是否存在
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String fileAdd = sdf.format(new Date());
        //获取文件夹路径
        File file1 =new File(savePath+"/"+fileAdd);
        //如果文件夹不存在则创建
        if(!file1 .exists()  && !file1 .isDirectory()){
            file1 .mkdir();
        }
        //将图片存入文件夹
        targetFile = new File(file1, fileName);
        try {
            //将上传的文件写到服务器上指定的文件。
            file.transferTo(targetFile);
            return url + "/" +fileAdd+"/"+fileName;
        } catch (Exception e) {
            throw new ServiceException("system.upload.error");
        }
    }

    @Override
    public GoodsThirdResult getGoodsDetail(String url) throws ServiceException {
        throw new ServiceException("接口调用错误");
    }

}
