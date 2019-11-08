### 模板方法模式  
抽取共通方法，就类似于基本的继承的用法  

```java
public abstract class Animal{
    public void breath(){
        System.out.println("animal need to breath");
    }
    
    // animal need eat food
    public abstract void eat();
    
    // animal will move in different way
    public abstract void move();
}

public class Cat extends Animal{
    @Override
    public void eat(){
        System.out.println("cats like eating fishes");
    }
    
    @Override
    public void move(){
        System.out.println("cats like walking after eating");
    }
}

public class Bird extends Animal{
    @Override
    public void eat(){
        System.out.println("birds like eating worms");
    }
    
    @Override
    public void move(){
        System.out.println("birds like flying after eating");
    }
}
```
场景类  
```java
public class Scene{
    public static void main(String[] args){
        Animal cat = new Cat();
        Animal bird = new Bird();
        
        cat.breath();
        cat.eat();
        cat.move();
        
        bird.breath();
        bird.eat();
        bird.move();
    }
}

```
