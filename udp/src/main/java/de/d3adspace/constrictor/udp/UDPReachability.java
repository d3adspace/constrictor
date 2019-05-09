package de.d3adspace.constrictor.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPReachability {

    private static final int DEFAULT_TIMEOUT = 1500;
    private static final byte[] EMPTY_BYTE_BUFFER = new byte[0];
    private static final DatagramPacket EMPTY_PACKET = new DatagramPacket(EMPTY_BYTE_BUFFER, EMPTY_BYTE_BUFFER.length);

    /**
     * Check if you can reach the given host at the given port via UDP.
     *
     * @param host The host.
     * @param port The port.
     * @return If the host is reachable at the given port.
     */
    public static boolean checkReachability(String host, int port) {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            long now = System.currentTimeMillis();
            datagramSocket.setSoTimeout(DEFAULT_TIMEOUT);
            datagramSocket.connect(new InetSocketAddress(host, port));
            datagramSocket.send(EMPTY_PACKET);
            datagramSocket.receive(EMPTY_PACKET);

            return true;
        } catch (IOException ignore) {
            return false;
        }
    }
}
