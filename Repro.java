import java.net.*;
import java.io.File;

public class Repro {
    public static void main(String [] args) throws java.io.IOException, MalformedURLException {
        var chan = java.nio.channels.SocketChannel.open(java.net.StandardProtocolFamily.UNIX);
        System.out.println(out.toString());
    }
}
