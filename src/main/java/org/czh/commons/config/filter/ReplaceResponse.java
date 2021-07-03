package org.czh.commons.config.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author : czh
 * description : 替换响应流
 * date : 2021-06-19
 * email 916419307@qq.com
 */
public class ReplaceResponse extends HttpServletResponseWrapper {

    private final ByteArrayOutputStream buffer;
    private final ServletOutputStream out;

    public ReplaceResponse(HttpServletResponse response) {
        super(response);
        buffer = new ByteArrayOutputStream();
        out = new ReplaceOutputStream(buffer);
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return this.out;
    }

    @Override
    public void flushBuffer() throws IOException {
        this.out.flush();
    }

    public byte[] getContent() throws IOException {
        flushBuffer();
        return buffer.toByteArray();
    }

    static class ReplaceOutputStream extends ServletOutputStream {

        private final ByteArrayOutputStream baos;

        public ReplaceOutputStream(ByteArrayOutputStream baos) {
            this.baos = baos;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setWriteListener(WriteListener listener) {

        }

        @SuppressWarnings("RedundantThrows")
        @Override
        public void write(int b) throws IOException {
            this.baos.write(b);
        }
    }
}
