package de.d3adspace.constrictor.netty;

import de.d3adspace.constrictor.core.thread.NamedThreadFactory;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

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
}
