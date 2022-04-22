package com.chengyu.core.domain.result;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuResult implements Serializable{

	private static final long serialVersionUID = 754476682411611363L;

	private Integer id;
	
	private String name;
	
	private String url;
	
	private List<MenuResult> children;
	
}
