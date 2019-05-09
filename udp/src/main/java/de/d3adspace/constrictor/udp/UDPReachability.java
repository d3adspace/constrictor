package de.d3adspace.constrictor.udp;

import de.d3adspace.constrictor.core.thread.NamedThreadFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class UDPReachability {

    private static final int THREAD_COUNT = 4;
    private static final String THREAD_NAME_PREFIX = "UDP Reachability Check Thread";
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(THREAD_COUNT, new NamedThreadFactory(THREAD_NAME_PREFIX));
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
            datagramSocket.setSoTimeout(DEFAULT_TIMEOUT);
            datagramSocket.connect(new InetSocketAddress(host, port));
            datagramSocket.send(EMPTY_PACKET);
            datagramSocket.receive(EMPTY_PACKET);

            return true;
        } catch (IOException ignore) {
            return false;
        }
    }

    /**
     * Check if you can reach the given host at the given port via UDP.
     *
     * @param host The host.
     * @param port The port.
     * @param consumer The consumer for the result.
     */
    public static void checkReachability(String host, int port, Consumer<Boolean> consumer) {
        EXECUTOR_SERVICE.execute(() -> {
            boolean isReachable = checkReachability(host, port);
            consumer.accept(isReachable);
        });
    }

    /**
     * Check if you can reach the given host at the given port via UDP.
     *
     * @param host The host.
     * @param port The port.
     * @return The future of the result.
     */
    public static Future<Boolean> checkReachabilityWithFuture(String host, int port) {
        return EXECUTOR_SERVICE.submit(() -> checkReachability(host, port));
    }
}
