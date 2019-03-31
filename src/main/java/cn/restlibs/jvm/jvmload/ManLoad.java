package cn.restlibs.jvm.jvmload;

public class ManLoad extends User {

 /*class文件结构
 对象结构*/

 /*class常量池
 utf-8 string int long float double  字母量和变量名字，描述符
 class  field_ref  method-ref interface 其他符号引用
 string直接生成string对象*/


 /*运行时常量池
  加载的时候   符号引用转换为方法区内存的直接引用。转换

  方法运行的时候，动态的指向运行时常量池的直接引用。指向
  User user=new Man();
     user.say();    // #71 class字节码是 User say()  public 运行时找动态引用    #60  Man say() public  ox0140 _2


 全局字符串常量池
 哈哈 指向同一个对象。
 */



 /*--类的加载过程 包括类变量
 -----加载 class对象 和验证交叉进行，写入方法区。
 -----验证  文件格式 元数据  字节码  符号引用
 -----准备  分配内存 ，方法表(自己的，父类的内存地址)
 -----解析  符号引用转换为直接引用。指向方法区内存 ，静态解析。class常量池里面的符号引用  解析阶段是虚拟机将常量池内的符号引用替换为直接引用的过程
 -----初始化 clinit方法
 -----解析  动态解析。动态链接。方法里面的符号引用*/


/* 方法区类
 1 运行时常量池 运行时常量池属于每个类的，指向方法区内存的直接引用。
 2 元数据信息，类接口信息，所有方法信息，所有字段信息， (包括继承的吗)
 3 class对象引用，classloader引用
 4 方法表，包括继承父类的
 5 类变量

 栈帧
 1局部变量表，指向堆引用
 2操作数栈
 3动态链接，指向运行时常量池。修改指向不同的直接引用。
 5 计数器
 6方法出口其他信息

 堆
 分代垃圾回收

 */


    //  方法引用 字段引用 静态引用 动态引用
    String s = "哈哈";  //字段引用
    String s2 = "哈哈";  //字段引用

    User u=new User(); //类字段引用

    //静态引用
    private void smoke() {
        System.out.println("我会抽烟");
    }

    //静态引用
    private void smoke(String s) {
        System.out.println("我会抽" + s);
    }

    //动态引用
    public void say() {
        System.out.println("我是Man");
    }

    /*  先看方法，在看实际类型
    如果是私有方法 构造方法 final方法 super方法，直接执行
    其他的就看实际类型
*/
    public static void main(String[] args) throws InterruptedException {

        //方法运行时，动态会解析 #57  #60 等符号引用，指向运行时常量池里解析过的地址。
        ManLoad man = new ManLoad();
        man.start();     // #57 class字节码是 Man start() private 运行时找静态引用    ox0140_3
        man.say();        // #60   class字节码是 Man say() public  运行时找动态引用  ox0140 _2
        man.smoke();      //#62  class字节码是 Man smoke() private 运行时找静态引用  ox0140 _4
        man.smoke("大麻"); // #66  class字节码是 Man smoke(s) private 运行时找静态引用  ox0140 _5
        man.eat();        // #75   class字节码是 Man eat()   public 运行时找动态引用 User  eat()  0x0133_3
        System.out.println(man.name);

        User user = new ManLoad();
        user.say();    // #71 class字节码是 User say()  public 运行时找动态引用    #60  Man say() public  ox0140 _2
        System.out.println(user.name);

        Thread.sleep(100 * 1000);

    }

    private void start() { //方法引用
        System.out.println("开始 ");
    }
}