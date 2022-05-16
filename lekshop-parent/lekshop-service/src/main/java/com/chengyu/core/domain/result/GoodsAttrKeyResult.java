package com.chengyu.core.domain.result;

import com.chengyu.core.model.PmsGoodsAttrVal;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GoodsAttrKeyResult {

    private String attrKey;

    /**
     * 字符串格式
     */
    private String attrValueList;

    /**
     * 对象格式
     */
    private List<PmsGoodsAttrVal> valList;
}
