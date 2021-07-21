package org.czh.commons.config.filter;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiPredicate;


/**
 * @author : czh
 * description : 过滤器，打印请求数据、响应结果
 * date : 2021-06-19
 * email 916419307@qq.com
 */
@Slf4j
@SuppressWarnings("unused")
public class RequestExecutionTimeFilter implements Filter {

    @Getter
    private static final ThreadLocal<String> localUuid = new ThreadLocal<>();

    private static final Map<String, BiPredicate<String, String>> filterMap = new HashMap<>();

    static {
        add("/", String::equals);
        add("swagger-ui", String::contains);
        add(".css", String::endsWith);
        add(".js", String::endsWith);
        add(".html", String::endsWith);
        add(".png", String::endsWith);
        add(".woff", String::endsWith);
        add(".woff2", String::endsWith);
        add(".map", String::endsWith);
    }

    public static void reset() {
        filterMap.clear();
    }

    public static void remove(@NotBlankTag String uri) {
        EmptyAssert.isNotBlank(uri);
        filterMap.remove(uri);
    }

    public static void add(@NotBlankTag String uri, @NotNullTag BiPredicate<String, String> filter) {
        EmptyAssert.isNotBlank(uri);
        EmptyAssert.isNotNull(filter);

        filterMap.put(uri, filter);
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {
        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
        // 跳过请求
        if (EmptyValidate.isNotEmpty(filterMap)) {
            for (Map.Entry<String, BiPredicate<String, String>> entry : filterMap.entrySet()) {
                if (entry.getValue().test(requestURI, entry.getKey())) {
                    chain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
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
