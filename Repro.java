import java.net.*;
import java.io.File;

public class Repro {
    public static void main(String [] args) throws Exception {
        var path = java.nio.file.Path.of(System.getProperty("java.io.tmpdir")).resolve("server.socket");
        var addr = java.net.UnixDomainSocketAddress.of(path);
        var runnable = new Runnable(){
                public void run() {
                    try {
                        var chan = java.nio.channels.ServerSocketChannel.open(java.net.StandardProtocolFamily.UNIX);
                        chan.bind(addr);
                        chan.accept();
                    }
                    catch (java.io.IOException e) {
                        System.err.println(e);
                    }
                }
            };
        var thread = new Thread(runnable);
        thread.start();
        Thread.sleep(100);
        var chan = java.nio.channels.SocketChannel.open(java.net.StandardProtocolFamily.UNIX);
        chan.connect(addr);
        System.out.println(chan.toString());
        java.nio.file.Files.delete(path);
    }
}
