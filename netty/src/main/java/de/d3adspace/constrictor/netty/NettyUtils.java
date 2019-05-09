package de.d3adspace.constrictor.netty;

import io.netty.channel.epoll.Epoll;

public class NettyUtils {

    private static final boolean EPOLL_AVAILABLE = Epoll.isAvailable();

    /**
     * Check if epoll is available on this platform.
     *
     * @return If epoll is available.
     */
    public static boolean isEpollAvailable() {
        return EPOLL_AVAILABLE;
    }
}
