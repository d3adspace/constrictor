package de.d3adspace.constrictor.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPReachability {

    private static final int DEFAULT_TIMEOUT = 4000;

    /**
     * Check if you can reach the given host at the given port via TCP.
     *
     * @param host The host.
     * @param port The port.
     * @return If the host is reachable at the given port.
     */
    public static boolean checkReachability(String host, int port) {

        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), DEFAULT_TIMEOUT);
            return true;
        } catch (IOException ignore) {
            return false;
        }
    }
}
