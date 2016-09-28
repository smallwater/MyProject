package com.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;

/**
 * Created by Administrator on 16-9-21.
 */
public class URLTest {
    public static void main(String[] args) {
        try{
            URL url = new URL("http://localhost:2000/customStockIndex?code=300059.SZ&index=8002|8044");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.connect();         //获取连接
            InputStream is = conn.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
            StringBuffer bs = new StringBuffer();
            String l = null;
            while((l=buffer.readLine())!=null){
                bs.append(l);
            }
            System.out.println(bs.toString());
            System.out.println(getLinuxLocalIp());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static String getLinuxLocalIp() {

        String ipAddr = null;

        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;

            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) netInterfaces
                        .nextElement();

                if ("eth0".equals(ni.getName())) {
                    Enumeration<InetAddress> ips = ni.getInetAddresses();
                    while (ips.hasMoreElements()) {
                        ip = ips.nextElement();
                        if (ip != null && ip instanceof Inet4Address && ip.getHostAddress().indexOf(".") != -1) {
                            ipAddr = ip.getHostAddress();
                            System.out.println("本服务器ip地址是: {} "+ipAddr);
                            return ipAddr;
                        } else {
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
