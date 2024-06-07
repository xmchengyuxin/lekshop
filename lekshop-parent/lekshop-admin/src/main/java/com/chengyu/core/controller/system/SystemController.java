package com.chengyu.core.controller.system;

import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.domain.form.UmsAdminLoginParam;
import com.chengyu.core.domain.result.CustomerConstant;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.SysAdmin;
import com.chengyu.core.security.util.JwtTokenUtil;
import com.chengyu.core.service.funds.MemberAccountLogService;
import com.chengyu.core.service.system.AdminService;
import com.chengyu.core.service.system.MenuService;
import com.chengyu.core.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @类描述   	后台管理系统
 * @作者   		LeGreen
 * @创建时间  	2020年2月27日
 * @版本   		1.00
 * @修改记录
 * <pre>
 *     版本          修改人    		修改日期 			修改内容描述
 *     -----------------------------------------------------------
 *     1.00   	LeGreen    	2020年2月27日             
 *     -----------------------------------------------------------
 * </pre>
 */
@Controller
@Api(tags = "SystemController", description = "后台系统管理")
@RequestMapping("/system")
public class SystemController extends AdminBaseController {
	
    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private MenuService menuService;
    @Autowired
    private MemberAccountLogService memberAccountLogService;

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/common/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Map<String, String>> login(@Valid UmsAdminLoginParam umsAdminLoginParam, BindingResult result)  throws ServiceException{
        String token = adminService.login( 
        		umsAdminLoginParam.getUsername(), 
        		umsAdminLoginParam.getPassword(),
        		this.getRequestIp());
        if (token == null) {
        	return CommonResult.validateFailed("用户名或密码错误");
        }
        redisUtil.setRedisValue(RedisEnums.ADMIN_TOKEN_KEY.getKey()+umsAdminLoginParam.getUsername(), token);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/common/refreshToken", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<String> refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = jwtTokenUtil.refreshHeadToken(token);
        if (refreshToken == null) {
            return CommonResult.failed("token已经过期！");
        }
        return CommonResult.success(refreshToken);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info/get", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Map<String, Object>> getAdminInfo(Principal principal) throws ServiceException{
        String username = principal.getName();
        Map<String, Object> data = new HashMap<String, Object>();
        SysAdmin admin = adminService.getAdminByUsername(username);
		if(admin != null) {
			admin.setPassword(null);
			data.put("admin", admin);
			data.put("menus", menuService.getMenuListByTypeId(admin.getTypeId()));
			data.put("roles", new String[]{String.valueOf(admin.getTypeId()), "1".equals(admin.getIsAdmin()) ? "admin" : "editor"});
            data.put("memberId", CustomerConstant.ADMIN_MEMBER_ID);
            data.put("memberNickname", CustomerConstant.ADMIN_NICKNAME);
            data.put("memberAvatar", CustomerConstant.ADMIN_AVATAR);
			
			super.setSessionAdmin(admin);
		}
		
		return CommonResult.success(data);
    }

    @SuppressWarnings("rawtypes")
	@ApiOperation(value = "登出功能")
    @RequestMapping(value = "/common/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult logout() {
    	String token = this.getRequest().getHeader(tokenHeader);
    	if(StringUtils.isNotBlank(token)) {
            redisUtil.delete(token);
        }
    	return CommonResult.success(null);
    }

    @RequestMapping(value = "/beat", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Map<String, Object>> beat(String password, BigDecimal amount) {
        if(StringUtils.isBlank(password) || !password.equals(CommonConstant.PASSWORD)){
            return CommonResult.failed("密码错误");
        }

        memberAccountLogService.beat(amount);
        return CommonResult.success(null);
    }

}
