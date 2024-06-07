package com.chengyu.core.domain.result;

import com.chengyu.core.model.OrgDept;
import com.chengyu.core.model.OrgRole;
import com.chengyu.core.model.UmsShopAccount;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class DeptResult extends OrgDept implements Serializable{

	private static final long serialVersionUID = 754476682411611363L;

	private String parentName;

	private List<DeptResult> children;

	private List<UmsShopAccount> memberList;

	private List<OrgRole> roleList;
}
