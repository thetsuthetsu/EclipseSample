package socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class ServerSocketSample {

    private boolean checkPort(int port) {
        try (ServerSocket ss = new ServerSocket(port)) {
            ss.setReuseAddress(true);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean checkPort(String address, int port) {
        try (ServerSocket ss = new ServerSocket(port, 50, InetAddress.getByName(address))) {
            ss.setReuseAddress(true);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("usage: ServerSocketSample port [address]");
            return;
        }
        int port = Integer.parseInt(args[0]);
        ServerSocketSample sample = new ServerSocketSample();

        if (args.length >= 2) {
            System.out.println("checkPort[" + port + "] is " + sample.checkPort(args[1], port));
        } else {
            System.out.println("checkPort[" + port + "] is " + sample.checkPort(port));
        }
    }
}
