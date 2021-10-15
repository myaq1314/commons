package org.czh.commons.utils.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.pool.PoolStats;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author : czh
 * description :
 * date : 2021-06-30
 * email 916419307@qq.com
 */
@Slf4j
public final class HttpPoolUtil {

    private static final PoolingHttpClientConnectionManager connectionManager;
    private static final ScheduledExecutorService monitorExecutor;
    private static final CloseableHttpClient httpClient;
    private static final int maxTotal = 100;// 连接池最大连接数
    private static final int maxPerRoute = 10;// 每个主机的并发
    //    private static final int maxRoute = 50;// 目标主机的最大并发，如果只有一台，可以和maxTotal一样
    private static final int timeout = 5000;
    private static boolean isShowUsePoolLog = true;

    static {
        connectionManager = configConnectionManager();

        monitorExecutor = Executors.newScheduledThreadPool(1);
        openMonitor();

        httpClient = createHttpClient();
    }

    public static void shutdown() {
        monitorExecutor.shutdown();
    }

    public static CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    private static PoolingHttpClientConnectionManager configConnectionManager() {
        final Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        // 将最大连接数增加
        connectionManager.setMaxTotal(maxTotal);
        // 将每个路由基础的连接增加
        connectionManager.setDefaultMaxPerRoute(maxPerRoute);
        return connectionManager;
    }

    private static void openMonitor() {
        monitorExecutor.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // 关闭异常连接
                connectionManager.closeExpiredConnections();
                // 关闭空闲的连接
                connectionManager.closeIdleConnections(timeout, TimeUnit.MILLISECONDS);
                final PoolStats poolStats = connectionManager.getTotalStats();
                final int usePoolNum = poolStats.getAvailable() + poolStats.getLeased() + poolStats.getPending();
                if (isShowUsePoolLog) {
                    log.info("***********》关闭异常+空闲连接！ 空闲连接:" + poolStats.getAvailable()
                                     + " 持久连接:" + poolStats.getLeased()
                                     + " 最大连接数:" + poolStats.getMax()
                                     + " 阻塞连接数:" + poolStats.getPending());
                }
                isShowUsePoolLog = usePoolNum != 0;
            }
        }, timeout, timeout, TimeUnit.MILLISECONDS);
    }

    private static CloseableHttpClient createHttpClient() {
        return HttpClients.custom()
                          .setConnectionManager(connectionManager)
                          .setRetryHandler(configRetryHandler())
                          .build();
    }

    private static HttpRequestRetryHandler configRetryHandler() {
        return (exception, executionCount, context) -> {
            if (executionCount >= 2) {
                log.info("*******》重试了2次，就放弃");
                return false;
            }
            if (exception instanceof NoHttpResponseException) {
                log.info("*******》服务器丢掉连接，重试");
                return true;
            }
            if (exception instanceof SSLHandshakeException) {
                log.info("*******》不要重试SSL握手异常");
                return false;
            }
            if (exception instanceof ConnectTimeoutException) {
                log.info("*******》连接超时被拒绝");
                return false;
            }
            if (exception instanceof InterruptedIOException) {
                log.info("*******》 中断");
                return false;
            }
            if (exception instanceof UnknownHostException) {
                log.info("*******》目标服务器不可达");
                return false;
            }
            if (exception instanceof SSLException) {
                log.info("*******》SSL握手异常");
                return false;
            }
            final HttpClientContext clientContext = HttpClientContext.adapt(context);
            final HttpRequest request = clientContext.getRequest();
            // 如果请求是幂等的，就再次尝试
            return !(request instanceof HttpEntityEnclosingRequest);
        };
    }
}
