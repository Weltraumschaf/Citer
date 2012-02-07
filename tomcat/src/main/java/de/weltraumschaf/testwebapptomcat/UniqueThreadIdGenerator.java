package de.weltraumschaf.testwebapptomcat;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @see http://docs.oracle.com/javase/6/docs/api/java/lang/ThreadLocal.html
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 * @license http://www.weltraumschaf.de/the-beer-ware-license.txt THE BEER-WARE LICENSE
 */
public class UniqueThreadIdGenerator {

    private static final AtomicInteger uniqueId = new AtomicInteger(0);
    private static final ThreadLocal< Integer> uniqueNum = new ThreadLocal< Integer>() {

        @Override protected Integer initialValue() {
            return uniqueId.getAndIncrement();
        }
    };

    public static int getCurrentThreadId() {
        return uniqueId.get();
    }
}