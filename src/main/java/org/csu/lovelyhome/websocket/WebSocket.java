package org.csu.lovelyhome.websocket;//package org.csu.lovelyhome.websocket;
//
//import javax.servlet.*;
//import java.io.IOException;
//import java.net.UnknownHostException;
//
///**
// * Created by zjx on 2019/8/31.
// */
//public class WebSocket implements Servlet {
//    private static final int PORT = 20818;
//    Server server = null;
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        try {
//            server = new Server(PORT);
//            server.start();
//            System.out.println("Server启动成功，端口为：20818");
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public ServletConfig getServletConfig() {
//        return null;
//    }
//
//    @Override
//    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
//
//    }
//
//    @Override
//    public String getServletInfo() {
//        return null;
//    }
//
//    @Override
//    public void destroy() {
//        try {
//            server.stop();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
