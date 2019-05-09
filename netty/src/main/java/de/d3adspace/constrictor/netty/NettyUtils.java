package de.d3adspace.constrictor.netty;

import de.d3adspace.constrictor.core.thread.NamedThreadFactory;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollDatagramChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyUtils {

    private static final int BOSS_EVENT_LOOG_GROUP_THREAD_COUNT = 1;
    private static final String BOSS_EVENT_LOOP_GROUP_THREAD_NAME_PREFIX = "Boss Group Thread";
    private static final int WORKER_EVENT_LOOG_GROUP_THREAD_COUNT = 4;
    private static final String WORKER_EVENT_LOOP_GROUP_THREAD_NAME_PREFIX = "Worker Group Thread";
    private static final boolean EPOLL_AVAILABLE = Epoll.isAvailable();

    /**
     * Check if epoll is available on this platform.
     *
     * @return If epoll is available.
     */
    public static boolean isEpollAvailable() {
        return EPOLL_AVAILABLE;
    }

    /**
     * Create a default boss group with {@link #BOSS_EVENT_LOOG_GROUP_THREAD_COUNT} threads and the default thread
     * name prefix {@link #BOSS_EVENT_LOOP_GROUP_THREAD_NAME_PREFIX}.
     *
     * @return The event loop group.
     */
    public static EventLoopGroup createBossGroup() {
        return createBossGroup(BOSS_EVENT_LOOG_GROUP_THREAD_COUNT);
    }

    /**
     * Create a default boss group with the thread name prefix {@link #BOSS_EVENT_LOOP_GROUP_THREAD_NAME_PREFIX}.
     *
     * @return The event loop group.
     */
    public static EventLoopGroup createBossGroup(int threadAmount) {
        return createEventLoopGroup(threadAmount, BOSS_EVENT_LOOP_GROUP_THREAD_NAME_PREFIX);
    }

    /**
     * Create a default boss group with {@link #WORKER_EVENT_LOOG_GROUP_THREAD_COUNT} threads and the default thread
     * name prefix {@link #WORKER_EVENT_LOOP_GROUP_THREAD_NAME_PREFIX}.
     *
     * @return The event loop group.
     */
    public static EventLoopGroup createWorkerGroup() {
        return createWorkerGroup(WORKER_EVENT_LOOG_GROUP_THREAD_COUNT);
    }

    /**
     * Create a default boss group with the thread name prefix {@link #WORKER_EVENT_LOOP_GROUP_THREAD_NAME_PREFIX}.
     *
     * @return The event loop group.
     */
    public static EventLoopGroup createWorkerGroup(int threadAmount) {
        return createEventLoopGroup(threadAmount, WORKER_EVENT_LOOP_GROUP_THREAD_NAME_PREFIX);
    }

    /**
     * Create a new event look group with a specific thread amount and a prefix for its threads names.
     *
     * @param threadAmount The amount of threads.
     * @param threadNamePrefix The prefix of the thread names.
     * @return The event look groups.
     */
    public static EventLoopGroup createEventLoopGroup(int threadAmount, String threadNamePrefix) {
        NamedThreadFactory namedThreadFactory = new NamedThreadFactory(threadNamePrefix);
        return EPOLL_AVAILABLE ? new EpollEventLoopGroup(threadAmount, namedThreadFactory) : new NioEventLoopGroup(threadAmount, namedThreadFactory);
    }

    /**
     * Get the appropriate socket channel class based on the fact if epoll is available.
     *
     * @return The class of the socket channel.
     */
    public static Class<? extends SocketChannel> getSocketChannel() {
        return isEpollAvailable() ? EpollSocketChannel.class : NioSocketChannel.class;
    }

    /**
     * Get the appropriate server socket channel class based on the fact if epoll is available.
     *
     * @return The class of the server socket channel.
     */
    public static Class<? extends ServerSocketChannel> getServerSocketChannel() {
        return isEpollAvailable() ? EpollServerSocketChannel.class : NioServerSocketChannel.class;
    }

    /**
     * Get the appropriate datagram channel class based on the fact if epoll is available.
     *
     * @return The class of the datagram socket channel.
     */
    public static Class<? extends DatagramChannel> getDatagramChannel() {
        return isEpollAvailable() ? EpollDatagramChannel.class : NioDatagramChannel.class;
    }
}
