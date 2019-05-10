package de.d3adspace.constrictor.core.thread;

import com.google.common.base.Preconditions;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A {@link ThreadFactory} that creates named threads with the format {prefix + ' #' + counter}
 *
 * @author Ruby Hale
 */
public class NamedThreadFactory implements ThreadFactory {

    /**
     * The delimiter between prefix and counter.
     */
    private static final String DELIMITER = " #";

    /**
     * The counter for the amount and the ids of threads.
     */
    private final AtomicLong threadCounter = new AtomicLong(0);

    /**
     * The prefix for the thread name.
     */
    private final String threadNamePrefix;

    /**
     * Creates a new named thread factory.
     *
     * @param threadNamePrefix The prefix of the names of the created threads.
     *
     */
    public NamedThreadFactory(String threadNamePrefix) {
        Preconditions.checkNotNull(threadNamePrefix, "Thread name prefix may not be null.");
        Preconditions.checkArgument(!threadNamePrefix.isBlank(), "Thread name prefix may not be blank.");

        this.threadNamePrefix = threadNamePrefix;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Preconditions.checkNotNull(runnable, "Runnable may not be null.");

        String name = threadNamePrefix + DELIMITER + threadCounter.getAndIncrement();
        return new Thread(runnable, name);
    }
}
