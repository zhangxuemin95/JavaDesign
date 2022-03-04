import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

public class  C extends HttpsServer{
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream ps = new PrintStream("d;\\工作文档\\practice.txt");
        PrintWriter pw = new PrintWriter("d;\\工作文档\\practice.txt");
    }

    @Override
    public void setHttpsConfigurator(HttpsConfigurator config) {

    }

    @Override
    public HttpsConfigurator getHttpsConfigurator() {
        return null;
    }

    @Override
    public void bind(InetSocketAddress addr, int backlog) throws IOException {

    }

    @Override
    public void start() {

    }

    @Override
    public void setExecutor(Executor executor) {

    }

    @Override
    public Executor getExecutor() {
        return null;
    }

    @Override
    public void stop(int delay) {

    }

    @Override
    public HttpContext createContext(String path, HttpHandler handler) {
        return null;
    }

    @Override
    public HttpContext createContext(String path) {
        return null;
    }

    @Override
    public void removeContext(String path) throws IllegalArgumentException {

    }

    @Override
    public void removeContext(HttpContext context) {

    }

    @Override
    public InetSocketAddress getAddress() {
        return null;
    }
}
