package de.d3adspace.constrictor.tcp;

import de.d3adspace.constrictor.core.thread.NamedThreadFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class TCPReachability {

    private static final int THREAD_COUNT = 4;
    private static final String THREAD_NAME_PREFIX = "TCP Reachability Check Thread";
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(THREAD_COUNT, new NamedThreadFactory(THREAD_NAME_PREFIX));
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

    /**
     * Check if you can reach the given host at the given port via TCP.
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
     * Check if you can reach the given host at the given port via TCP.
     *
     * @param host The host.
     * @param port The port.
     * @return The future of the result.
     */
    public static Future<Boolean> checkReachabilityWithFuture(String host, int port) {
        return EXECUTOR_SERVICE.submit(() -> checkReachability(host, port));
    }
}
