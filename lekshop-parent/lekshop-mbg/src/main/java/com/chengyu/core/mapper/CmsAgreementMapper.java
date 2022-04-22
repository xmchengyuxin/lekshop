package com.chengyu.core.mapper;

import com.chengyu.core.model.CmsAgreement;
import com.chengyu.core.model.CmsAgreementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsAgreementMapper {
    long countByExample(CmsAgreementExample example);

    int deleteByExample(CmsAgreementExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsAgreement record);

    int insertSelective(CmsAgreement record);

    List<CmsAgreement> selectByExampleWithBLOBs(CmsAgreementExample example);

    List<CmsAgreement> selectByExample(CmsAgreementExample example);

    CmsAgreement selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsAgreement record, @Param("example") CmsAgreementExample example);

    int updateByExampleWithBLOBs(@Param("record") CmsAgreement record, @Param("example") CmsAgreementExample example);

    int updateByExample(@Param("record") CmsAgreement record, @Param("example") CmsAgreementExample example);

    int updateByPrimaryKeySelective(CmsAgreement record);

    int updateByPrimaryKeyWithBLOBs(CmsAgreement record);

    int updateByPrimaryKey(CmsAgreement record);
}