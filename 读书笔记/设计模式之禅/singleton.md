### 单例模式
让一个类只产生一个实例。spring的默认策略就是单例

#### 懒汉模式
```
// 懒加载，只在使用的时候创建实例
// 双检锁，用于保证安全
public class Singleton{
    private static volatile Singleton singleton;

    private Singleton(){}
    
    public static synchronized getInstance(){
        if(singleton == null){
            synchronized(Singleton.class){
                if (singleton == null) {  
                    singleton = new Singleton();  
                } 
            }
        }else{
            return singleton;
        }
    }
}
```

#### 饿汉模式
```
// 加载class文件时就实例化了，不需要考虑线程问题
public class Singleton{
    private static Singleton singleton = new Singleton();
    
    private Singleton(){};
    
    public static getInstance(){
        return singleton;
    }
}
```

#### 登记式
```
// 采用内部类的方式
public class Singleton {  
    private static class SingletonHolder {  
        private static final Singleton INSTANCE = new Singleton();  
    }  
    private Singleton (){}  
    public static final Singleton getInstance() {  
        return SingletonHolder.INSTANCE;  
    }  
}
```
#### 枚举类
```
// 保证绝对的单例，线程安全
public enum Singleton{
    INSTANCE;
}
```

