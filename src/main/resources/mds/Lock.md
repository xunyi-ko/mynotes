## 锁
锁按是否可重入可分为可重入锁和不可重入锁
按竞争策略可分为公平锁和非公平锁

### 不可重入锁
当一个锁被使用后，即使是当前线程，再次访问这把锁时（递归或者不同方法用同一个锁），也会等待，导致死锁的问题
比如
```java
public class Lock{
    private boolean isLocked;
    private Thread currentThread;
    public void lock(){
        while(isLocked){
            wait();
        }
        isLocked = true;
        currentThread = Thread.currentThread();
    }
    public void unlock(){
        // 确定是当前的线程才可以解锁
        if(currentThread != Thread.currentThread()){
            throw new RuntimeException();
        }
        isLocked = false;
        currentThread = null;
    }
}
```

### 可重入锁
同一个线程可以重复进入锁，而不会等待
```java
public class Lock{
    // 计数，加锁次数。为0时代表没有锁。不为0时，只有加锁的线程可以访问
    private boolean isLocked;
    private int locks;
    private Thread currentThread;
    public void lock(){
        while(currentThread != Thread.currentThread() && isLocked){
            wait();
        }
        locks++;
        isLocked = true;
        currentThread = Thread.currentThread();
    }
    public void unlock(){
        if(currentThread != Thread.currentThread()){
            throw new RuntimeException();
        }
        locks--;
        if(locks == 0){
            currentThread = null;
            isLocked = false;
        }
    }
    
}
```

### 公平锁
按照先进先出的原则，先尝试加锁的可以先获取到锁。
因此，需要在加锁的方法里加入请求队列，保证先进先出。
```java
public class Lock{
    private boolean isLocked;
    private int locks;
    private Thread currentThread;
    private LinkedList<Thread> threadList = new LinkedList<>();
    public void lock(){
        while(currentThread != Thread.currentThread() && isLocked){
            if(!set.contains(Thread.currentThread()){
                threadList.add(Thread.currentThread());
            }
            wait();
        }
        locks++;
        isLocked = true;
        currentThread = Thread.currentThread();
    }
    public void unlock(){
        if(currentThread != Thread.currentThread()){
            throw new RuntimeException();
        }
        locks--;
        if(locks == 0){
            if(threadList.isEmpty()){
                // 如果set中没有值，说明没有正在等待的线程，则打开锁
                currentThread = null;
                isLocked = false;
            }else{
                // 有正在等待的线程，就把第一个取出来，作为当前线程，并从等待列表中去除
                Iterator<Thread> iterator = threadList.iterator();
                currentThread = iterator.next();
                iterator.remove();
            }
        }
    }
}
```

### 非公平锁
随机获取锁。少了请求队列，因此速度会比公平锁略快
代码同可重入锁

### ReentrantLock
并发包的可重入锁
```java
// 不公平锁
// private Lock lock = new ReentrantLock();
// 公平锁
private Lock lock = new ReentrantLock(true);

public void doSomething(){
    // 上锁
    lock.lock();
    try{
        // 做点事
    }catch(Exception e){
        e.printStackTrace();
    }finally{
        // 解锁
        lock.unlock();
    }
}
```
