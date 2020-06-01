# java小笔记
## 标识符取名规则：
标识符：给类，接口，方法，变量等起名字

### 组成规则
- 英文大小写
- 数字字符
- $和_
### 注意事项
- 不能以数字开头
- 不能是JAVA中的关键字

### 取名规范
    包：package
    全部小写
    单级：one
    多级：one.two
    
    类或接口：
    一个单词组成：首字母大写 One
    多个单词：每个单词首字母大写 OneTwo
    
    方法或变量：
    一个单词组成：首字母小写 one
    多个单词：驼峰法，第二个单词起首字母大写 oneTwo
    
    常量：
    一个单词组成：全部大写 ONE
    多个单词：大写，字母间用_隔开，ONE_TWO

## 原码反码补码
原码是正数时：反码补码与原码相同
原码是负数时：反码等于原码除符号位外按位取反，
补码等于反码+1，等于正数对应的原码按位取反后+1，等于高位减去对应正数的原码+1。
    如原码=1000 1111
    反码=1111 0000
    补码=1 0000 0000-0000 1111=1111 0000+1=1111 0001

## 数据类型
A：基本数据类型
1.整形
    byte short int long
    long数据后要加L或l
2.浮点型（小数）
    float double
    float数据后要加F或f
3.字符型
    char
4.布尔型
    true false
B：引用数据类型
    类、接口、数组
		
## 数据类型转换
当不同类型数据进行数值计算时，会默认转换为更大的存储空间。
    byte=short=char<int<long<float<double
    byte,short,char相互之间不转换，他们参与运算会首先转换成int类型。
强制转换
    目标数据类型 变量名 = （强制转换数据类型）数据
    举例：
        int a;
        byte b;
        byte c = (byte) (a + b)

字符串与任意数据+，都转换成字符串数据，+号为连接符
		
## 键盘输入
首先需要导包：import java.util.Scanner;
然后创建键盘录入对象：Scanner sc = new Scanner(System.in)
调用输入数据函数：int x = sc.nextInt();
	
## Switch分支语句
```
Switch(condition){
    case n1:
        {}
    case n2:
        {}
        ...
    default:
        {}
}
```
注意事项：
    A：case后面只能是常量，且多个case后面的值不能出现相同的
    B：default可以省略，但是通常用于提示错误，不建议省略
    C：default可以出现在任意位置
    D：语句遇到break或语句结束才结束

## 跳转控制语句
break
    跳出单层循环
    跳出多层循环
    需要为循环加标签
        例如：exp1:for(...){
                exp2:for(...){
                    break exp1;
                }
            }
continue
    跳出本次循环，从头再来一次
return
    跳出当前方法（函数）
		
## 方法
创建方法时，方法名相同，参数列表不同，JVM会视作不同的方法。只与参数列表相关。
	
## 数组
数组初始化
```
动态初始化：指定数组长度，由系统给出初始化值
    数据类型[] 数组名 = new 数据类型[长度]
静态初始化：给出初始化值，由系统决定长度
    数组类型[] 数组名 = new 数组类型[]{元素1,,元素2,元素3,...};
    或 
    数组类型[] 数组名 = {元素1,,元素2,元素3,...};
```

## java内存分配 
栈：存放局部变量
    ？存放了地址
堆：存放所有new出来的东西
    存放了内容
方法区：
本地方法区：和系统相关
寄存器：CPU使用
	
## 二维数组
就是元素是一维数组的一维数组
声明方法与一维数组声明方法类似
动态初始化：数据类型[][] 数组名 = new 数据类型[m][n]
            数据类型[] 数组名[] = new 数据类型[m][n]
            数据类型 数组名[][] = new 数据类型[m][n]
            (n可以不给出，表示元素(一维数组)的元素个数不固定)
静态初始化：数据类型[][] 数组名 = new 数据类型[][]{{...},{...},...}
            数据类型[][] 数组名 = {{...},{...},...}
				
## 成员变量和局部变量
成员变量是类中方法外的变量
    内存在堆中，有默认初始化值
    随着对象的产生而产生，随着对象的消失而消失
局部变量是在方法声明时或方法内定义的变量
    内存在栈中，没有默认初始化值
    随着方法的调用而产生，随着调用结束而消失
			
## 构造方法
方法名与类名相同，没有返回值，没有void修饰符
如果自己没有写构造方法，系统会默认给出一个无参的孔构造方法
构造方法一般用于对象的初始化

```java
class Student{
    pubilc Student(){
    
    }
    public Student(int a){
    
    }
}
class Demo{
    public static void main(String[] args){
        Student s = new Student();
        Student s1 = new Student(1);
    }
}
```
		
## main方法格式详解
public:公共的，由于main方法被JVM调用，所以权限要够大
static:静态的，不需要创建对象，通过类名就可以访问，方便调用
void:由于方法的返回值是返回给调用者，main方法时给JVM用的，不需要返回值
main:常见的方法入口
String[] args:main函数的参数,是一个字符串数组参数，在运行java时，可以输入参数
    如java Demo hello 
    hello即为args[0]
		
## 使用JDK提供的帮助文档
1. 打开帮助文档
2. 点击显示，找到索引，用输入框
3. 找到想找的类。以Scanner举例
4. 看包
    java.lang包下的类不用导入，其他的都要导入
5. 再简单的看看类的解释和说明，别忘了看版本
6. 看类的结构
    成员变量	字段摘要
    构造方法	构造方法摘要
    成员方法	方法摘要
7. 学习构造方法
    A:有构造方法	就创建对象
    B:没有构造方法	成员可能都是静态的
8. 看成员方法
    A:左边
        是否静态：如果静态，可以通过类名调用
        返回值类型：人家返回什么，就用什么接收
    B：右边
        看方法名：方法名称不要写错
        参数列表：人家要什么就给什么，要几个就给几个
			
## 代码块
局部代码块：局部位置，用于限定变量的生命周期
构造代码块：在类中，成员的位置。
    class Demo{
        {
            int;
        }
    }
    在每次调用构造方法前都会调用构造代码块。
    作用：可以把多个构造方法中的共同代码放在一起，对对象进行初始化、
静态代码块：在类中的成员位置，不过用static修饰
    只加载一次，并且在其他代码块之前执行。
    作用：一般对类进行初始化
		
## 继承
可以多层继承，不能同时继承多个父类。
子类只能继承父类所有非私有的成员(成员方法和成员变量)
子类不能继承父类的构造方法，但是可以用super关键字去访问父类的构造方法
不要为了部分功能而去继承
当，子类是父类的一种时，才考虑使用继承
			
## this关键字
	代表调用此关键字的对象

## super关键字
类似于this
    代表父类存储空间的标识，可以理解为父类引用，可以操作父类的成员
使用方式
    1、调用成员变量
        this.成员变量 调用本类的成员变量
        super.成员变量 调用父类的成员变量
    2、调用构造方法：必须放在第一行
        this(...) 调用本类的构造方法
        super(...) 在子类构造方法中调用父类的构造方法，...代表父类构造方法的参数列表
    3、调用成员方法
        this.成员方法 调用本类的成员方法
        super.成员方法 调用父类的成员方法

## static关键字
1. 随着类的加载而加载
2. 优先于对象存在
3. 被类的所有对象共享
4. 可以通过类名调用(也可以通过对象名调用)
5. 静态方法不能用this关键字
    因为静态变量静态方法早于对象出现，静态调用this关键字会由于没有对象而出错
6. 静态方法只能访问静态的成员变量和静态的成员方法
	
## final关键字
可以修饰类、方法、变量
    类	无法被继承
    方法	无法被重写
    变量	变成常量，不能修改
		
## 方法重写的注意事项
1、父类的私有方法无法重写
    因为父类的私有方法子类无法继承
2、子类重写父类的方法时，访问权限不能更低

子类重写父类方法时，声明最好一样
	
## 多态
三类
    具体类多态(几乎没有)
    抽象类多态(常用)
    接口类多态(最常用)
前提
    1、要有继承关系
    2、要有方法重写
    3、要有父类引用指向子类对象
        父 f = new 子();
成员访问特点
    1、成员变量	
        编译、运行都看父类
    2、构造方法
        创建子类对象的时候，访问父类的构造方法对父类对象的数据进行初始化
    3、成员方法
        编译看父类，运行看子类(如果引用子类有而父类没有的方法，编译会报错。当父类子类都有时，会运行子类的方法)
    4、静态方法
        编译、运行都看父类
        因为静态变量与类相关，算不上重写，访问的还是父类。
			
## 抽象类(abstract关键字)
特点
    1、用abstract关键字修饰
    2、抽象类内部可以没有抽象方法，但是如果内部有抽象方法，一定要是抽象类
    3、方法体用;代替
        public abstract void one();
    4、抽象类不能实例化(不能创建对象)
    5、抽象类的子类如果不是抽象类，则必须实现抽象类的所有抽象方法
abstract不能与哪些关键字共存
    private 冲突
    final	冲突
    static	无意义 (abstract没有方法体。)
			
## 接口
1、接口用关键字interface表示
    interface 接口名{}
2、类实现接口用implements{}表示
    class 类名 implements 接口名 {}
3、接口不能实例化
    要用多态的方式实现接口
4、接口的子类
    可以是抽象类，不过意义不大
    可以是具体类，要重写接口中的所有抽象方法
与抽象类的区别
    接口
        成员特点
            成员变量：接口中的变量只能是常量，而且是静态的(可以通过接口名访问)
            构造方法：接口没有构造方法
            成员方法：接口方法默认是public权限的抽象方法
        设计理念
            like a 
            接口中定义的是该继承体系的扩展功能
    抽象类
        成员特点
            成员变量：可以是变量也可以是常量
            构造方法：有构造方法
            成员方法：可以抽象也可以非抽象
        设计理念
            is a 子类是父类的一种
            抽象类中定义的是该集成体系的共性功能

## 继承
类与类：继承关系，只能单继承，可以多层继承
类与接口：实现关系，可以多实现，并且还可以再继承一个类的前提下实现多个接口
接口与接口：继承关系，可以单继承，也可以多继承
			
## 权限修饰符
```
            同类	同包	不同包(子类)	不同包(无关类)
public		Y		Y		Y				Y
protected	Y		Y		Y
default		Y		Y
private		Y
```

## 内部类
内部类可以直接使用外部类的成员，但外部类想要使用内部类的成员必须创建对象
成员内部类
    在其他类中使用内部类成员，需要使用如下格式创建对象
        外部类名.内部类名 对象名 = new 外部类对象名().new 内部类对象名)_
    用静态修饰符修饰后的访问方式
        外部类名.内部类名 对象名 = new 外部类名.内部类名()
    从内部类内部访问外部类变量可以用 外部类名.this.变量名访问
局部内部类
    从局部内部类访问局部变量，局部变量必须用final修饰
        因为局部变量会在方法调用结束后消失，而类不会马上消失
        为了还可以使用变量，所以需要使用final修饰变量
匿名内部类
    内部类的简化写法
    前提：存在一个类或者接口
    格式：new 类名或者接口名(){
        重写方法;
    }
    本质是一个继承了该类或者实现了该接口的子类匿名对象
		
## Object类
是所有无父类的类的父类
hashCode方法
    public int hashCode()	
        返回哈希码，是根据地址值换算出来的一个值，不是地址值但是可以理解为地址值
getClass方法
    public final Class getClass()
        返回对象的运行的包名.类名
toString方法
    public String toString()
        返回该对象的字符串表示
equals方法
    public boolean equals(Object obj)
        表示调用方法的对象与参数的对象地址值是否相同
        一般对象使用时是想比较成员变量是否相同，所以可能需要重写equals方法
            可以用 对象名 instanceof 类名 判断对象是否是学生类的对象
            if(obj instanceof Student){}
finalize方法
    protected void finalize()
        当垃圾回收器确定不存在对该对象的更多引用时
        由对象的垃圾回收器调用此方法回收垃圾
clone方法
    protected Object clone()
        创建并返回此对象的一个副本
        若想调用此方法，类需要实现Cloneable接口
        可以得到一个参数相同的对象，但不是在同一地址，改变这个对象不会修改原对象。

## Scanner类
java.util.Scanner
Scanner sc = new Scanner(System.in)
int i = sc.nextInt();
    sc.hasNextInt	如果输入的是int类型的数据，则前式为true否则为false

问题
    当同一个对象，先获取一个基本数据类型，再获取一个字符串时，会出现把换行符给了字符串，使得不能正常运行的错误
解决方式
    1、重新建一个对象
    2、输入时都用String类型输入，再根据需要，转换成相应的数据类型
		
## String类
用String s = new String("hello");方式会在堆和方法区各创建一个对象，有两个地址
用String s = "hello";方式会查询方法区是否有此字符串，如果有，就不创建，没有就创建
boolean equals(Object obj):比较字符串的内容是否相同，区分大小写
boolean equalsIgnoreCase(String str):比较字符串的内容是否相同，忽略大小写
boolean contains(String str):判断大字符串中是否包含小字符串
boolean startsWith(String str):判断字符串是否以某个指定的字符串开头
boolean endsWith(String str):判断字符串是否以某个指定的字符串结尾
boolean isEmpty():判断字符串是否为空
int length();获取字符串的长度
char charAt(int index);返回字符串指定位置的字符
int indexOf(int ch);返回指定字符在此字符串中第一次出现的索引
int indexOf(String str);返回指定字符串在此字符串中第一次出现的索引
int indexOf(int ch,int fromIndex);返回指定字符从指定位置后第一次出现的索引
int indexOf(String str,int fromIndex);返回指定字符串从指定位置后第一次出现的索引
String substring(int start);返回从指定位置开始后的字符串
String substring(int start,int end);返回指定位置的字符串
byte[] getBytes();把字符串转换成字节数组
char[] toCharArray();把字符串转换成字符数组
valueOf()方法可以把任意类型的数组转换成字符串
String toLowerCase();把字符转成小写
String toUpperCase();把字符转成大写
String concat(String str);把字符串拼接
String replace(char old,char new)
String replace(String ole,String new)替换功能
String trim();去除字符串两端的空格
int compareTo(String str);依次比较每一位字符，如果完全相同，返回0。否则可能返回正数或负数
int compareToIgnoreCase(String str);忽略大小写比较
	
## 线程安全(多线程讲解)
安全--同步(一个个做)--数据安全
不安全--不同步--效率高
	
## StringBuffer类
构造方法
    StringBuffer():无参构造方法，默认构造一个其中不带字符的字符串缓冲区，初始容量为 16 个字符。
    StringBuffer(int capacity):置顶容量的字符串缓冲区对象
    StringBuffer(String str):构造一个字符串缓冲区，并将其内容初始化为指定的字符串内容
方法
    StringBuffer.capacity():返回当前容量
    StringBuffer.length():返回长度
    StringBuffer.append(String str):在缓冲区最后追加数据，并返回缓冲区
    StringBuffer.insert(String str,int i):在缓冲区指定位置添加数据并返回缓冲区
    StringBuffer.deleteCharAt(int i):删除指定位置的字符
    StringBuffer.delete(int start,int end):删除指定位置开始到指定位置结束的内容(包括开始位置不包括结束位置)
    StringBuffer.replace(int start,int end,String str):将指定位置的字符串替换为指定的字符串
    StringBuffer.reverse():将缓冲区的数据反转
    StringBuffer.substring(int start):截取从开始位置到最后的字符串。返回String，缓冲区不变
    StringBuffer.substring(int start,int end):截取指定位置的字符串，返回String，缓冲区不变
StringBuffer转String
    1、通过构造方法
        String s = new String(StringBuffer sb);
    2、tostring方法
        String s = sb.tostring();
String转StringBuffer
    1、通过构造方法
        StringBuffer sb = new StringBuffer(String s);
    2、用append()方法
        StringBuffer sb = new StringBuffer();
        sb.append(s);
        
StringBuilder()类与StringBuffer()几乎一样，但是不保证同步，效率更高。用法与StringBuffer相同
	
## Arrays类
要import java.util Arrays;
public static String toString(int[] a);该方法可以把所有数组转换成字符串
public static void sort(int[] a);对数组进行升序排列
public static int binarySearch(int[] a,int key);二分法进行查找

## 包装类类型
每个基本类型都有对应的封装类型，封装了对应的方法。
Byte类
Short类
Integer类
    包装了一个基本类型int的值。该类提供多个方法能在int类和String类
    之间互相转换，还提供了处理int类型时非常有用的其他一些常量和方法
    static String toBinaryString(int i);以二进制无符号整数形式返回一个整数参数的字符串表示形式
    static String toHexString(int i);以十六进制无符号整数形式返回一个整数参数的字符串表示形式
    static String toOctalString(int i);以八进制无符号整数形式返回一个整数参数的字符串表示形式
    
    将字符串转换为int类型
        用构造方法的方式
            Integer i = new Integer(String s);//s的内容必须是数字
        Integer i = new Integer(str);int ii = i.intValue;
        int i = Integer.parseInt(s);
    int转字符串
        1、String s = ""+number;
        2、String s = String.valueOf(int i);
        3、Integer i = new Integer(number); String s = i.toString();
        4、String s = Integer.toString(number);
    可以用Integer.toString(int number,int x);将number转化为x进制数，x取值范围在2~36之间，否则为十进制。
    可以用Integer.parseInt(int number,int x);将x进制数转化为十进制数。
Long类
Character类
    public static boolean isUpperCase(char ch):判断是否是大写字符
    public static boolean isLowerCase(char ch):判断是否是小写字符
    public static boolean isDigit(char ch):判断是否是数字
    public static char toUpperCase(char ch):小写转大写
    public static char toLowerCase(char ch):大写转小写
Float类
Double类
Boolean类
	
## 正则表达式
boolean matches(String regex):返回字符串是否匹配给定的正则表达式
String[] split(String regex):根据给定的regex拆分字符串，并添加到字符串数组中
String replaceAll(String regex, String replacement):用replacement替换字符串中的regex
	
## Math类
public static int abs(int a):绝对值
public static double ceil(double a):向上取整
public static double floor(double a):向下取整
public static int max(int a,int b):
public static int min(int a,int b):
public static double pow(double a,double b):a的b次幂
public static double random():0到1的随机数
public static int round(float a):四舍五入
public static double sqrt(double a):正平方根

## System类
public static void gc():强行执行垃圾回收器，回收垃圾。调用回收的对象的finalize()函数
public static void exit(int status): 终止当前正在运行的Java虚拟机，status = 0表示正常退出
public static long currentTimeMillis():获得当前以毫秒为单位的时间
public static void arraycopy(Object src,int srcPos,Object dest,int destPos,int length)
    从指定源数组复制一个数组，从指定源数组的指定位置，复制到目标数组的指定位置。
	
## BigInteger类
可以存储比Integer更大的数
public BigInteger add(BigInteger val):加
public BigInteger subtract(BigInteger val):减
public BigInteger multiply(BigInteger val):乘
public BigInteger divide(BigInteger val):除
public BigInteger[] divideAndRemainder(BigInteger val):返回商和余数的数组
	
## BigDecimal类
为了避免float类型数据存储出现误差。用于解决精度丢失的问题
构造方法
    public BigDecimal(String str):如果用double类型作为参数，容易出现不可预知的误差
算法
    public BigDecimal add(BigDecimal val):加
    public BigDecimal subtract(BigDecimal val):减
    public BigDecimal multiply(BigDecimal val):乘
    public BigDecimal divide(BigDecimal val):除
    public BigDecimal divide(BigDecimal val,int scale,int roundingMode):商，保留小数位数由scale决定
		
## Date类
java.util.Date
构造方法
    Date():根据当前的默认毫秒值创建日期对象
    Date(long date):根据给定的毫秒值创建日期对象
成员方法
    getTime():获取当前时间(毫秒)
    setTime(long time):设置时间(以毫秒为单位)
		
## abstract DateFormat类
由于是抽象类，想使用要使用具体子类simpleDateFormat
java.text.simpleDateFormat
 创建对象
    SimpleDateFormat sdf = new SimpleDateFormat();(默认模式)
    SimpleDateFormat sdf = new SimpleDateFormat(yyyy年MM月dd日 HH:mm:ss)
使用format方法
    String s = sdf.format(Date d);格式化日期
使用prase方法
    Date d = sdf2.parse(s);把字符串转成日期
		
## Calendar类
是一个抽象类，它为特定瞬间与一组诸如
YEAR、MONTH、DAY_OF_MONTH、HOUR 等 
日历字段之间的转换提供了一些方法，
并为操作日历字段（例如获得下星期的日期）
提供了一些方法。瞬间可用毫秒值来表示
    用Calendar c = Calendar.getInstance()获得一个对象(子类对象)
    int year = c.get(Calendar.YEAR)
    int month = c.get(Calendar.MONTH)
    int day = c.get(Calendar.DAY_OF_MONTH)
    
    public void add(int field,int i):修改时间
    public void set(int year,int month,int date)设置当前日历的年月日，月从0开始
		
## 集合
由于数组不可变，为了方便使用，提出集合
集合和数组的区别
    1、长度区别
        数组长度固定
        集合长度可变
    2、内容不同
        数组存储的是同一种类型的元素
        集合可以存储不同类型的元素
    3、元素的数据类型
        数组可以存储基本类型和引用数据类型
        集合只能存储引用数据类型
### Collection：是集合的顶层接口。
1、添加
    boolean add(Object obj):添加一个元素
    boolean addAll(Collection c):添加一个集合的元素
2、删除
    void clear():删除所有元素
    boolean remove(Object o):移除一个元素
    boolean removeAl(Collection c):移除一个集合的元素
3、判断
    boolean contains(Object obj):判断集合中是否包含指定的元素
    boolean containsAll(Collection c):判断集合中是否包含集合元素
    boolean isEmpty():判断集合是否为空
4、获取
    迭代器
        Iterator<E> iterator()(重点)
        Iterator it = c.iterator();
        Object next():返回下一个元素
        boolean hasNext():返回是否还有下一个元素
        void remove():从迭代器指向的集合中移除迭代器返回的最后一个元素（可选操作）。
5、查看长度
    int size():返回元素的个数
6、交集
    boolean retainAll(Collection c):调用方法的集合变为两个集合都有的元素，集合变化则返回true
7、把集合转换为数组
    Object[] toArray():返回包含此 collection 中所有元素的数组。

### 列表List
可以存放相同的对象
特有功能
    void add(int index, Object element):在指定位置添加元素//索引不能超过已有的元素数量
    Object remove(index):移除指定位置的元素，并返回被删除的元素
    Object get(int index):获取指定位置的元素
    Object set(int index,Object element):在指定的位置添加元素
    List subList(int fromIndex,int toIndex):返回列表中指定位置之间的list
    ListIterator	List特有的迭代器
        Object previous():返回上一个元素
        boolean hasPrevious():判断是否有上一个元素
List子类特点
    ArrayList
        底层数据结构是数组，查询快，增删慢
        线程不安全，效率高
    Vector
        底层数据结构是数组，查询快，增删慢
        线程安全，效率低
    LinkedList
        底层数据结构是链表，查询慢，增删快
        线程不安全，效率高
LinkedList特有功能
    public void addFirst(Object e ):添加在第一个位置
    public void addLast(Object e):添加在最后一个位置
    public Object getFirst():获取第一个
    public Object getLast():获取最后一个
    public Object removeFirst():移除第一个
    public Object removeLast():移除最后一个
ArrayList类
    Collection c = new ArrayList();

### 集Set
不可以存放相同的对象
HashSet
    底层由hashCode()和equals()实现。
    如果想实现对象元素相同即为相同对象，需要重写以上两个方法
TreeSet
    TreeSet会进行排序
    1.值的类中实现comparable，进行自然排序
    2.在实例化TreeSet时，通过有参构造的方式，
      用 TreeSet<T> t = new TreeSet<T>(new comparator<T>(){
        @override
        public int compare(T t){
            int num = t == this ? 0 : 1;
            return num;
        }
      });

### 映射Map
HashMap
    由哈希表保证键唯一，无序
LinkedHashMap
    由哈希表键唯一，由链表保证有序
TreeMap
    基于红黑树的Map接口实现
    键唯一，自动排序
1、添加功能
    V put(K key,V value);添加元素
2、删除功能
    void clear();移除所有键值对元素
    V remove(Object key);根据键删除键值对，并返回相应的值
3、判断功能
    boolean containKey(Object key);判断集合是否包含指定的键
    boolean containValue(Object value);判断集合是否包含指定的值
    boolean isEmpty();判断集合是否为空
4、获取功能
    V get (Object key);根据键获取值
    Set<Map.Entry<K,V>> entrySet();返回键值对对象集合
        再使用增强for(Map.Entry<K,V>entry:set)获取集合中的数据
        getKey();
        getValue();
    Set<K> keySet();获取集合中所有键的集合
    Collection<V> values();获取集合中所有值的集合
5、长度功能
    int size();返回集合中的键值对对数
	
## Collections类概述
针对集合操作的工具类 都是静态方法
Collections成员方法
    public static<T> void sort(List<T> list) 排序
    public static<T> int binarySearch(List<?> list) 二分查找
    public static<T> T max(Collection<?> coll) 最大值
    public static<T> void reverse(List<?> list) 反转
    public static<T> void shuffle(List<?> list) 随机置换
		
## 泛型
### 泛型类
在类名后添加<类名>来决定集合可以使用的对象，可以加多个类名
List<String> list = new ArrayList<String>();

### 泛型方法
public <T> void show(T t){}
定义一个方法，就可以接收类型的对象作为参数

### 泛型接口:泛型定义在接口上
public interface InterImp<T>{}
实现时
    已知泛型类型public class Inter implements InterImp<String>{}
    未知类型	public class Inter<T> implements InterImp<T>{}
创建对象时
    InterImp<String> i = new Inter();
    InterImp<String> i = new Inter<String>();
			
## 静态导入
import static 包名.类名.方法名
要求 方法必须是静态方法
导入后，可以直接使用方法名使用方法。
如果有多个同名的静态方法，容易不知道用谁，需要谨慎使用。

## 异常处理
### Error
重大异常

### Exception
编译期异常
运行期异常RunningTimeException
处理方式
    try..catch..finally
    try(){
        可能出问题的代码
    }catch(异常名 变量名){
        针对问题的处理
    }finally{
        释放资源
    }
    finally控制的语句体一定会执行
    可以使用多个catch语句
    在JDK7后可以在一个catch里匹配多个异常，用|隔开
    
### throws
异常的常用方法
public String getMessage();异常的消息字符串
public String toString();返回异常的简单信息描述
    此对象的类的name(全路径名)
    : 
    调用此对象getLocalizedMessage()方法的结果(默认是getMessage()方法)
void printStackTrace()；获得异常类名和异常信息，以及异常出现在程序中的位置
    把信息输出在控制台(相当于JVM默认处理方式)

### 自定义异常
写一个异常类，需要继承自Exception或RuntimeException
	
## File类
文件和目录路径名的抽象表示形式
构造方法：
    File(String pathname);根据一个路径得到File对象
    File(String parent,String child);根据一个目录和一个子文件得到File对象
    File(File parent,String child);根据一个父File对象和一个子文件路径得到File对象
方法：
    创建功能
        public boolean createNewFile();
            创建文件
        public boolean mkdir();
            创建文件夹
        public boolean mkdirs();
            可一次性创建多层文件夹
    删除功能
        public boolean delete()
            删除文件
    重命名功能
        public boolean renameTo(File dest)
            路径相同就是改名，否则就是改名并剪切
    判断功能
        public boolean isDirectory();
            判断是否是目录
        public boolean isFile()；
            判断是否是文件
        public boolean exists()；
            判断是否存在
        public boolean canRead();
            判断是否可读
        public boolean canWrite();
            判断是否可写
        public boolean isHidden();
            判断是否隐藏
    获取功能
        public String getAbsoolutePath();
            获取绝对路径
        public String getPath();
            获取相对路径
        public String getName();
            获取名称
        public long length();
            获取长度，字节数
        public long lastModified();
            获取最后一次修改的时间，毫秒值
        public String[] list() 
            返回一个字符串数组，
            这些字符串指定此抽象路径名表示的目录中的文件和目录 

## IO流
流向：
    输入流	读取数据
    输出流	写出数据
数据类型：
    字节流
        字节输入流	读取数据	InputStream
        字节输出流	写出数据	OutputStream
    字符流(字符流写入后需要调用flush()方法才会真正写入，或者close后)
        字符输入流	读取数据	Reader
        字符输出流	写出数据	Writer
InputStream 和 OutputStream 都是抽象类，所以要是用实现类

### FileOutPutStream
void write(byte[] b);
    将 b.length 个字节从指定 byte 数组写入此文件输出流中
void write(byte[] b,int off,int len);
    将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此文件输出流
void write(int b);
    将指定字节写入此文件输出流
void close();
    释放输出流
实现输入数据换行
    写入换行符
        不同的系统换行符不同
        windows	\r\n
        linux	\n
        mac		\r
通过不同的构造方法，可以实现文件重新写或追加写入
    FileOutPutStream(File file);
    FileOutPutStream(String name);
        重新写
    FileOutPutStream(File file,boolean append);
    FileOutPutStream(String name,boolean append);
        追加写入

### FileInputStream
int read();
    读取一个字节
int read(byte[] b)
    读取b.length个字节的数据，返回读取的个数。如果没有了，返回-1

### BufferedInputStream
缓冲区输入流

### BufferedOutputStream
缓冲区输出流

### OutputStreamWriter
构造方法
    OutputStreamWriter(OutputStream out) 
        创建使用默认字符编码的 OutputStreamWriter
    OutputStreamWriter(OutputStream out, String charsetName) 
        创建使用指定字符集的 OutputStreamWriter
方法
    public void write(int c)写入单个字符
    public void write(char[] cbuf)写入字符数组
    public void write(char[] cbuf,int off,int len)写入
    public void write(String s)写入字符串
    public void write(String s,int off,int len)写入字符串
        
### InputStreamReader
构造方法
    InputStreamReader(InputStream in) 
        创建使用默认字符编码的 InputStreamReader
    InputStreamReader(InputStream in, String charsetName) 
        创建使用指定字符集的 InputStreamReader
方法
    read(char c);
    read(char[] chs);	用法和之前的一样

### FileReader
构造方法
    FileReader(File file) 
        在给定从中读取数据的 File 的情况下创建一个新 FileReader。 
    FileReader(FileDescriptor fd) 
        在给定从中读取数据的 FileDescriptor 的情况下创建一个新 FileReader。 
    FileReader(String fileName) 
        在给定从中读取数据的文件名的情况下创建一个新 FileReader。 

### FileWriter
构造方法
    FileWriter(File file) 
        根据给定的 File 对象构造一个 FileWriter 对象。 
    FileWriter(File file, boolean append) 
        根据给定的 File 对象构造一个 FileWriter 对象。 
    FileWriter(FileDescriptor fd) 
        构造与某个文件描述符相关联的 FileWriter 对象。 
    FileWriter(String fileName) 
        根据给定的文件名构造一个 FileWriter 对象。 
    FileWriter(String fileName, boolean append) 
        根据给定的文件名以及指示是否附加写入数据的 boolean 值来构造 FileWriter 对象。 

### BufferedWriter
public void newLine();写入换行符

### BufferedReader
public String readLine();读取一个文本行,不包含换行符。如果没了就返回null

### 数据输入输出流
DataInputStreamD
    构造方法
        DataInputStream(InputStream in)
    可以读取各种基本数据类型
    readInt();等
DataOutputStreamD
    构造方法
        DataOutputStream(OutputStream out)
    writeInt();等

### 内存操作流
ByteArrayInputStream
    构造方法
        ByteArrayInputStream(byte[] buf) 
ByteArrayOutputStream
    构造方法
        ByteArrayOutputStream() 
    创建对象后，用toByteArray()方法得到byte数组
    给ByteArrayInputStream用于创建对象
    由于是内存流，没有具体文件。
同理，有字符流的CharArrayReader和CharArrayWriter
                StringReader和StringWriter

### PrintWriter
构造方法
    可以用File、Stream、OutputStream、Writer等作为参数进行构造
    当参数为Writer,true或OutputStream true 作为参数时
        可以构造自动刷新的PrintWriter
可以写入任何类型的数据
可以开启自动刷新。如果开启自动刷新
会在调用println、format、printf方法时刷新

### PrintStream
基本输入流
    System.Out本质上等同于PrintStream
            
### 随机访问流RandomAccessFile
此类的实例支持对随机访问文件的读取和写入
构造方法
    RandomAccessFile(File file,String mode)
        创建从中读取和向其中写入的随机访问文件流
        文件由File参数决定
    RandomAccessFile(String name,String mode)
    mode可选，一般用rw
    'r'  以只读方式打开，如果调用write方法，将导致IOException
    'rw' 可读写，如果不存在，则创建文件
    'rws'可读写
    'rwd'可读写
方法
    writeBoolean(boolean v)
    writeByte(int v)
    writeBytes(String s)
    writeChar(int v)
    writeChars(String s)
    writeDouble(double v)
    writeInt(int v)
    writeUTF(String str)
    long getFilePointer();
        返回此文件的当前偏移量
    seek(long pos);
        设置文件指针偏移量，之后的读写操作从此开始
    int readInt()等
    String readLine()
        读取文本的下一行

### 合并流 SequenceInputStream
可以实现用一个对象读取多个文件的内容
构造方法
    SequenceInputStream(InputStream s1, InputStream s2) 
    SequenceInputStream((Enumeration<? extends InputStream> e);
        将流存入Vector集合中，使用elements()方法得到Enumeration对象，
        用于构造SequenceInputStream

### 序列化流	反序列化
操作对象，序列化数据就是讲对象写到文本文件
写入对象时，需要实现序列化接口Serializable
ObjectOutputStream
    创建序列化对象，
    用writeIObject(Object obj)写入
ObjectInputStream
    创建反序列化对象
    用readObject(Object obj)读取
    返回Object类对象
如果有些元素不想被序列化，可以使用transient关键字修饰

### Properties类
属性集合类，是一个可以和IO流相结合使用的集合类
    可保存在流中或从流中加载，属性列表中每个键及其对应的值都是字符串
构造方法
    无参构造
        创建一个无默认值的空属性列表
方法
    setProperty(String key,String value)
        添加键值对
    getProperty(String key)
        获取元素，键原本对应的值，如果不存在返回null
    stringPropertyNames()
        获取所有键的集合
    结合IO流的方法
        public void load(Reader reader)
            把文件中的数据加载到集合中
        public void store(Writer writer,String comments)
            把集合中的数据写入文件中
				
## 多线程
Thread类实现方法
    需要继承Thread类
    重写run()方法
    然后创建类的对象，调用start()方法
方法
    getName()获得线程名字
    setName()设置线程名字
    currentThread()获得当前运行线程的引用
    
线程优先级
    getPriority()获取线程优先级
    setPriority()设置线程优先级
        优先级为1-10
线程控制
    public static void sleep(long millis)线程休眠
    public final void join()线程加入。当前线程执行完，其他线程才能进行
    public static void yield()线程礼让
    public final void setDaemon(boolean on)后台线程
    public final void stop()中断线程
    public void interrupt()中断线程

同步关键字synchronized
	用于对多线程安全问题，进行同步
		出现问题的原因：
			1.多线程
			2.操作同一个数据
			3.多次操作
	用于加锁，解决第三个问题 格式
		synchronized(Object){}Object可以是任意对象，但必须是同一个对象
	同步关键字还可以用于方法体上。这时的锁对象是this
	当方法为静态方法时，锁对象是class文件(class文件名.class)
Lock接口
	创建Lock实现类对象，在代码块前使用lock()方法，
	结束后使用unlock()方法
wait()与notify()
	在多线程程序中，锁对象可以使用wait()方法与notify()方法
	锁对象调用wait()方法时，可以使当前线程停止
	调用notify()方法可以唤醒这把锁的单个线程
	notifyAll()方法可以唤醒这把锁的所有线程。
		
## Socket类
构造方法
    Socket(String host,int port)创建一个流套接字并将其连接到指定主机上的指定端口号
成员方法
    OutputStream getOutputStream()	返回此套接字的输出流
    InputStream getInputStream()	返回此套接字的输入流
    void close()	关闭此套接字
    setSoTimeout(int mills)
        设置等待时间。
        如果read方法超过一定时间没有读取到数据，就抛出异常
客户端代码实现
    Socket s = new Socket("127.0.0.1",8080);
    OutputStream os= s.getOutputStream;
    os.write("hello world");
    s.close();

## ServerSocket类
实现步骤
    1.创建服务器ServerSocket对象和系统要指定的端口号
    2.使用ServerSocket对象中的方法accept，获取到请求的客户端对象Socket
    3.使用Socket对象中的方法getInputStream()获取网络字节输入流
    4.使用网络字节输入流InputStream对象中的方法read，读取客户端发送的数据
    5.使用getOutputStream方法，获取OutputStream对象，使用write方法，向客户端发送数据
构造方法
    ServerSocket(int port)
方法
    Socket accept()
        返回客户端的Socket对象
服务器端代码实现
    ServerSocket ss = new ServerSocket(8080);
    Socket s = ss.accept();
    InputStream is = s.getInputStream;
    byte[] bytes = new byte[1024];
    int len = is.read(bytes);
    System.out.println(new String(bytes,0,len));
    ss.close;

## BS服务器
同样通过ServerSocket的accept方法，等待浏览器访问
浏览器会对服务器传入一些数据，类似如下
GET /bs/web/index.html HTTP/1.1
Accept: text/html, application/xhtml+xml, image/jxr, */*
Accept-Language: zh-Hans-CN,zh-Hans;q=0.5
User-Agent: Mozilla/5.0 (Windows NT 10.0; Trident/7.0; rv:11.0) like Gecko
Accept-Encoding: gzip, deflate
Host: 127.0.0.1:8080
DNT: 1
Connection: Keep-Alive
第一行中间部分即是所访问的网址所在地。
可以用BufferedReader br = new BufferedReader(new InputStreamReader(is));
String s = br.readLine();
Sting[] arr = s.split(" ");
String htmlPath = arr[1].subString(1);
获取地址。再将地址中的内容回写到浏览器。
首先必须写入如下三行
    os.write("HTTP/1.1 200 OK\r\n".getBytes());
    os.write("Content-Type:text/html\r\n".getBytes());
    os.write("\r\n".getBytes));

		
## Junit测试规范
步骤
    1.定义一个测试类
        *建议：
            *测试类名：	被测试类名+Test
            *包名:		xxx.xxx.x.test
    2.定义测试方法：可以独立运行
        *建议：
            *方法名：test+测试方法名
            *返回值：void
            *参数列表：无参
    3.给方法加@Test注解
    4.导入Junit依赖环境(导包)
判定结果
    红色失败，绿色成功
可以使用断言来测试
    Assert.assertEquals(int a,int b)
    需要导包。如果a,b相等，则顺利通过，否则抛出异常。

## 反射
框架：半成品软件。可以在框架的基础上进行软件开发，简化编码
反射：将类的各个组成部分封装为其他对象，这就是反射机制
Class类对象
    获取class对象的方式：
        Class.forName("全类名")，将字节码文件加载进内存，返回class对象
            多用于配置文件
        类名.class:通过类名的属性class获取
            多用于参数的传递
        对象.getClass()；此方法在Object类中定义，也就是所有对象都有此方法
            多用于对象的获取字节码的方式
        *用不同方式获得的相同的class文件对象，是相同的。
    方法
        1.获取成员变量
            *Field[] getFields():获取所有public变量
            *Field getField(String name)获取指定名称的public变量
                获取到变量后，可以用
                变量.get(对象)方式，获取对象的该变量值
                或用
                变量.set(对象,值)设置对象的变量值
            *Field[] getDeclaredFields()	不考虑修饰符
            *Field getDeclaredField(String name)
                如果不是public变量，想要像public一样set get
                使用	变量.setAccessible(true)(暴力反射)
                即可和上述相同的方式进行set get
        2.获取构造方法们
            *Constructor<?>[] getConstructors()
            *Constructor<T>[] getConstructor(类<?>... parameterTypes)
                通过相应参数，创建构造器。参数是参数类型.class,如String.class
                使用newInstance(类<?>... parameterTypes)方法创建对象
            *Constructor<T> getDeclaredConstructor(类<?>... parameterTypes)
            *Constructor<?> getDeclaredConstructors()
        3.获取成员方法们
            *Method[] getMethods()
            *Method getMethod(String name,类<?>... parameterTypes)
                同理，创建Method对象。
                可以使用invoke(对象)方式，使对象调用无参方法
                用invoke(对象，参数...)方式使对象调用方法
            *Method[] getDeclaredMethods()
            *Method getDeclaredMethod(String name,类<?>... parameterTypes)
        4.获取类名
            *String getName()
                获得全类名(包括包名)
反射案例
    public class ReflectDemo {
        public static void main(String[] args) throws Exception {
            Properties pro = new Properties();//创建properties对象
            InputStream is = new FileInputStream("pro.properties");//读取properties文件
            pro.load(is);//载入字节流，
            String className = pro.getProperty("ClassName");//读取配置文件
            String methodName = pro.getProperty("MethodName");
            Class<?> cl1 = Class.forName(className);//创建字节码文件对象
            Object obj = cl1.newInstance();//创建对象
            Method method = cl1.getMethod(methodName);//创建方法对象
            method.invoke(obj);//调用方法
        }
    }

## 注解
作用分类
    1.编写文档：通过代码里标识的注解生成文档
    2.代码分析：通过代码里标识的注解对代码进行分析
    3.编译检查：通过代码里标识的注解让编译器能够实现基本的编译检查
学习内容
    1.JDK中预定义的一些注解
        @Override	重写
        @SuppressWarnings("all")	压制警告
        @Deprecated	已过时
    2.自定义注解
        格式
            元注解：用于描述注解的注解(放在定义注解的上方)
                @Target()：描述注解能够作用的位置
                    Element.TYPE	可以定义在类上
                    Element.METHOD	定义在方法上
                    Element.FIELD	定义在变量上
                @Retention()：描述注解被保留的阶段
                    RetentionPolicy.RUNTIME	保留到运行期
                @Documented：描述注解是否被抽取到API中
                @Inherited：描述注解是否被子类继承
            public @interface Name
    3.在程序中使用注解