package org.czh.commons.config.filter;

import com.alibaba.fastjson.JSONObject;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author : czh
 * description : 替换请求流
 * date : 2021-06-19
 * email 916419307@qq.com
 */
public class ReplaceRequest extends HttpServletRequestWrapper {

    private final Map<String, String[]> parameterMap;
    private byte[] body;

    public ReplaceRequest(HttpServletRequest request) throws IOException {
        super(request);

        ServletInputStream is = request.getInputStream();
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(1024)) {
            byte[] bytes = new byte[1024];
            int len;
            while ((len = is.read(bytes)) != -1) {
                baos.write(bytes, 0, len);
            }
            this.body = baos.toByteArray();
        }
        this.parameterMap = request.getParameterMap();
    }

    @Override
    public String getParameter(String name) {
        EmptyAssert.isNotNull(name);
        String[] result = this.parameterMap.get(name);
        return EmptyValidate.isNotEmpty(result) ? result[0] : null;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return this.parameterMap;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(this.parameterMap.keySet());
    }

    @Override
    public String[] getParameterValues(String name) {
        return this.parameterMap.get(name);
    }

    @Override
    public ServletInputStream getInputStream() {
        byte[] temp = body;
        if (body == null) {
            temp = new byte[0];
        }
        final ByteArrayInputStream bais = new ByteArrayInputStream(temp);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }

            @Override
            public int read() {
                return bais.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    public JSONObject getBodyJson() {
        if (EmptyValidate.isEmpty(this.body)) {
            return new JSONObject();
        }
        return JSONObject.parseObject(new String(this.body));
    }

    public void setBodyJson(JSONObject jsonObject) {
        EmptyAssert.isNotNull(jsonObject);
        this.body = jsonObject.toJSONString().getBytes();
    }

    public JSONObject getParamJson() {
        JSONObject jsonObject = new JSONObject();
        if (EmptyValidate.isEmpty(this.parameterMap)) {
            return jsonObject;
        }

        Enumeration<String> parameterNames = this.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            jsonObject.put(parameterName, this.getParameter(parameterName));
        }
        return jsonObject;
    }
}
