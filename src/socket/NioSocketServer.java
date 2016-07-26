package socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Scanner;

public class NioSocketServer {
    private Selector selector;
    private ServerSocketChannel channel;
    private final InetSocketAddress listenAddress;

    public NioSocketServer(String address, int port) {
        this.listenAddress = new InetSocketAddress(address, port);
    }

    public void start() throws IOException {
        this.selector = Selector.open();
        this.channel = ServerSocketChannel.open();
        this.channel.configureBlocking(false);

        // retrieve server socket and bind to port
        this.channel.socket().bind(listenAddress);
        this.channel.register(this.selector, SelectionKey.OP_ACCEPT);
        System.out.println("Server started...");
    }

    public void stop() {
        try {
            this.channel.close();
            System.out.println("Server stopped...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("usage: NioSocketServer listenAddress listenPort");
            return;
        }
        NioSocketServer server = null;
        try {
            server = new NioSocketServer(args[0], Integer.parseInt(args[1]));
            server.start();
            System.out.println("Hit any key, if you want to stop this server.");
            new Scanner(System.in).nextLine();
        } catch (Throwable th) {
            th.printStackTrace();
        } finally {
            if (server != null) {
                server.stop();
            }
        }
    }

}
