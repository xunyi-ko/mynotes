### 原型模式
创建模式之一，用于复制对象  
在java中，是通过clone完成的  
由于java的clone是通过二进制流完成的，因此不会调用构造方法  
优点是自由，缺点是太自由  
速度比new要快不少


```java
// 浅克隆，基本类型和String是克隆来的，但其他对象还是原来的句柄
public class Pattern{
    @Test
    public void testPrototype(){
        Prototype o1 = new Prototype();
        o1.setValue = "object1";
        
        o1.doSomething();
        
        Prototype o2 = o1.clone();
        o2.doSomething();
        
        // 两者不是同一个对象
        System.out.println(o1 == o2);
    }
}
class Prototype implements Cloneable{
    private String value;
    
    public void setValue(String _value){
        this.value = _value;
    }
    public String getValue(){
        return this.value;
    }
    public void doSomething(){
        System.out.println(this.value);
    }
    public Prototype clone(){
        Prototype cloneObject = null;
        try{
            cloneObject = (Prototype) super.clone();
        }catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cloneObject;
    }
}
```
