package org.czh.commons.utils;

import org.czh.commons_core.asserts.EmptyAssert;
import org.czh.commons_core.validate.EmptyValidate;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * @author : czh
 * description :
 * date : 2021-07-02
 * email 916419307@qq.com
 */
public final class IpUtil {

    public static final String INTRANET_IP = getIntranetIp(); // 内网IP
    public static final String INTERNET_IP = getInternetIp(); // 外网IP

    public static String getIntranetIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getInternetIp() {
        try {
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            Enumeration<InetAddress> address;
            while (networks.hasMoreElements()) {
                address = networks.nextElement().getInetAddresses();
                while (address.hasMoreElements()) {
                    ip = address.nextElement();
                    if (ip instanceof Inet4Address && !ip.isSiteLocalAddress()
                            && !ip.getHostAddress().equals(INTRANET_IP)) {
                        return ip.getHostAddress();
                    }
                }
            }

            // 如果没有外网IP，就返回内网IP
            return INTRANET_IP;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getRealClientIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (EmptyValidate.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (EmptyValidate.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (EmptyValidate.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inetAddress;
                try {
                    inetAddress = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    throw new RuntimeException(e);
                }
                EmptyAssert.isNotNull(inetAddress);
                ipAddress = inetAddress.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    public static String getIpFromAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!EmptyValidate.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }

        ip = request.getHeader("X-Forwarded-For");
        if (EmptyValidate.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (EmptyValidate.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (EmptyValidate.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (EmptyValidate.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (EmptyValidate.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static void main(String[] args) {
        System.out.println(getIntranetIp());
        System.out.println(getInternetIp());
    }
}
