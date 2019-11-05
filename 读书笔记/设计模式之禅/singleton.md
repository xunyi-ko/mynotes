### 单例模式
让一个类只产生一个实例。spring的默认策略就是单例

#### 懒汉模式
```
public class Singleton{
    private static Singleton singleton;

    private Singleton(){}
    
    public static synchronized getInstance(){
        if(singleton == null){
            return new Singleton();
        }else{
            return singleton;
        }
    }
    
}
```

#### 饿汉模式
```
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

