package com.chengyu.core.domain.result;

import com.chengyu.core.model.SysMenu;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MenuSingleResult implements Serializable{

	private static final long serialVersionUID = -5204158333453443403L;

	private SysMenu menu;
	
	private String topName;
	
}
