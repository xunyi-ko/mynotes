package mynotes.com.xunyi_ko.mynotes.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class AtomicIntegerTest {
    @Test
    public void testAdd() {
        AtomicInteger integer = new AtomicInteger();
        A a = new A();
        
        ThreadFactory factory = new ThreadFactory() {
            private final AtomicInteger threadNumber = new AtomicInteger(1);
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r, "test-atomic" + threadNumber.getAndIncrement());
                
                if (t.isDaemon()) {
                    t.setDaemon(false);
                }
                if (t.getPriority() != Thread.NORM_PRIORITY) {
                    t.setPriority(Thread.NORM_PRIORITY);
                }

                return t;
            }
        };
        ThreadPoolExecutor pool1 = new ThreadPoolExecutor(10, 100, 60, TimeUnit.NANOSECONDS, new ArrayBlockingQueue<Runnable>(16), factory);
        ThreadPoolExecutor pool2 = new ThreadPoolExecutor(10, 100, 60, TimeUnit.NANOSECONDS, new ArrayBlockingQueue<Runnable>(16), factory);
        
        for(int i = 0; i < 10; i++) {
            pool1.execute(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i < 10000; i++)
                        integer.getAndIncrement();
                }
            });
        }
        for(int i = 0; i < 10; i++) {
            pool2.execute(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i < 10000; i++)
                        a.i++;
                }
            });
        }
        try {
            Thread.sleep(1000);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("integer: " + integer.get());
            System.out.println("A.integer: " + a.i);
        }
    }
}

class A{
    public int i;
}
