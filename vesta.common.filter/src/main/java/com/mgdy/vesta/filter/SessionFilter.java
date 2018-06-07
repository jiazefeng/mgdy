package com.mgdy.vesta.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ZhangBailiang on 2016/3/16.
 * 登陆过滤
 */
public class SessionFilter extends OncePerRequestFilter {
    /*
    * 登陆过滤
    * @see
    * org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(
    * javax.servlet.http.HttpServletRequest,
    * javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
    */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 不过滤的uri
        String[] notFilter = new String[]{"index.html", "loginCheck.html", "LoginLight.aspx",
                "/static/editer/jsp/controller.jsp", "/property/addPropertyAnnouncement", "static", "/user/ownerManage.html", "/errors", "/Basic", "/webNews","/weixinapi"};
        // 请求的uri
        String uri = request.getRequestURI();
        try {
            // 是否过滤
            boolean doFilter = true;
            for (String s : notFilter) {
                if (uri.indexOf(s) != -1) {
                    // 如果uri中包含不过滤的uri，则不进行过滤
                    doFilter = false;
                    break;
                }
            }
            if (uri.equals("/")) {
                doFilter = false;
            }

            if (doFilter) {
                // 执行过滤
                // 从session中获取登录者实体
                Object obj = request.getSession().getAttribute("propertystaff");
                if (null == obj) {
                    // 如果session中不存在登录者实体，则弹出框提示重新登录
                    // 设置request和response的字符集，防止乱码
                    request.setCharacterEncoding("gbk");
                    response.setContentType("text/html;charset=gbk");
                    PrintWriter out = response.getWriter();
                    String loginPage = request.getContextPath() + "/errors/404.jsp";
                    StringBuilder builder = new StringBuilder();
                    builder.append("<script type=\"text/javascript\">");
                    /*   builder.append("alert('网页过期，请重新登录！');");*/
                    builder.append("window.top.location.href='");
                    builder.append(loginPage);
                    builder.append("';");
                    builder.append("</script>");
                    out.print(builder.toString());
                } else {
                    // 如果session中存在登录者实体，则继续
                    filterChain.doFilter(request, response);
                }
            } else {
                // 如果不执行过滤，则继续
                filterChain.doFilter(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
