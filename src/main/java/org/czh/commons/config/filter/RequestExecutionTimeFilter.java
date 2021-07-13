package org.czh.commons.config.filter;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;


/**
 * @author : czh
 * description : 过滤器，打印请求数据、响应结果
 * date : 2021-06-19
 * email 916419307@qq.com
 */
@Slf4j
@SuppressWarnings("unused")
public class RequestExecutionTimeFilter implements Filter {

    public static final ThreadLocal<String> localUuid = new ThreadLocal<>();

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {
        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
        // 跳过请求
        if ("/".equals(requestURI)
                || requestURI.endsWith(".html")) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        String uuid = UUID.randomUUID().toString().replace("-", "");
        localUuid.set(uuid);

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        // 转换请求实体、响应式题，为自定义的实体
        ReplaceRequest replaceRequest = new ReplaceRequest(httpServletRequest);
        ReplaceResponse replaceResponse = new ReplaceResponse(httpServletResponse);

        // 打印 请求 入参
        JSONObject requestParamJsonObj;
        String requestMethod = httpServletRequest.getMethod();
        if ("POST".equals(requestMethod) || "PUT".equals(requestMethod) || "DELETE".equals(requestMethod)) {
            requestParamJsonObj = replaceRequest.getBodyJson();
        } else if ("GET".equals(requestMethod)) {
            requestParamJsonObj = replaceRequest.getParamJson();
        } else {
            requestParamJsonObj = new JSONObject();
        }

        String requestParamJsonString = requestParamJsonObj.toJSONString();
        log.info("\nThe requestURI : {} \nThe UUID : {} \nThe Parameter : {}\n", requestURI, uuid, requestParamJsonString);

        // 过滤链
        chain.doFilter(replaceRequest, replaceResponse);

        // 打印 响应 结果
        String resultJsonString = new String(replaceResponse.getContent());
        resultJsonString = JSONObject.parseObject(resultJsonString).toJSONString();
        log.info("\nThe requestURI : {} \nThe UUID : {} \nThe Response : {}\n", requestURI, uuid, resultJsonString);

        // 输出 响应 结果
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        outputStream.write(replaceResponse.getContent());
        outputStream.flush();
    }
}
