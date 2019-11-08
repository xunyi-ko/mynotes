### 中介者模式
充当类与类之间的中介，减少类的耦合。  
#### 优点
1、降低了类的复杂度，可以将多对多转化成了多对一。 2、各个类之间的解耦。 3、符合迪米特原则。
#### 缺点
中介者会庞大，变得复杂难以维护。  

```
// 学校类
public class School{
    // 
}

// 老师类
public class Teacher{

}

// 学生类
public class Student{

}
// 中介者类
public class Broker{
    
}

```
