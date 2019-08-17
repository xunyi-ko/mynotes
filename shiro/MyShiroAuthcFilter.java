package com.jscwf.boss.shiro;

import com.alibaba.fastjson.JSONObject;
import com.jscwf.boss.exception.ExceptionEnum;
import com.jscwf.boss.util.Result;
import com.jscwf.boss.util.ResultUtil;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("rawtypes")
public class MyShiroAuthcFilter extends FormAuthenticationFilter {
	
	
	 /**
     * 在访问controller前判断是否登录，返回json，不进行重定向。
     * @param request
     * @param response
     * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (isAjax(request)) {
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json");
			Result result = ResultUtil.error(ExceptionEnum.SESSION_TIME_OUT);
            httpServletResponse.getWriter().write(JSONObject.toJSON(result).toString());
        } else {
            //saveRequestAndRedirectToLogin(request, response);
            /**
             * @Mark 非ajax请求重定向为登录页面
             */
            httpServletResponse.sendRedirect("/login");
        }
        return false;
    }

    private boolean isAjax(ServletRequest request){
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if("XMLHttpRequest".equalsIgnoreCase(header)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
		
}
