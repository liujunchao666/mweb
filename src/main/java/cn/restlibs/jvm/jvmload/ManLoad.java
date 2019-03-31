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
 -----加载 class对象 和验证交叉进行，写入方法区。 各种数据的访问入口  直接引用
      class放在方法区中。不放在堆中
      这个对象作为方法区中类型数据的外部接口
 -----验证  文件格式 元数据  字节码  符号引用
 -----准备  类变量分配内存 ，
           static变量赋值为0  static final 赋值为常量
           虚方法表(自己的，父类的内存地址)

 -----解析  解析阶段是虚拟机将常量池内的符号引用替换为直接引用的过程
           符号引用转换为直接引用。指向方法区内存 ，
           符合引用是class 常量池中的index
           直接引用指向内存。不是常量。
           静态解析。class常量池里面的符号引用

           解析动作
              类 接口   根据常量值加载其他类。或者自己
                字段
                类方法
                接口方法

 -----初始化 clinit方法  按照收集static 字段赋值动作和static 块 按照顺序合并生成。
    静态语句可以赋值给后面的静态变量。但是不能使用
clinit方法可以没有。 同步操作。其他线程阻塞。


 -----解析  动态解析。动态链接。方法里面的符号引用


 类加载器的作用 除了加载动作。
 2个类相等的前提是 同一个class被同一个类加载器加载。不是同一个。不是同一个类
 自定义类加载器。gc回收方便。

 双亲委派模式

 启动类加载
 扩展类
 应用程序类 自定义实现他

 好处是所有的请求都会传送到顶层启动类。层次。基层类被基层类加载器加载。优先级层次。不混乱





 */


/* 方法区类
 1 运行时常量池 运行时常量池属于每个类的，指向方法区内存的class元数据的直接引用。
 2 class对象引用，classloader引用 元数据信息，类接口信息，所有方法信息，所有字段信息。不包括继承的
 3 虚方法表，包括继承父类的
 4 类static变量

 栈帧
 1局部变量表，指向堆引用
 2操作数栈。字节码指向的地方
 3动态链接，指向运行时常量池。修改指向不同的直接引用。
 5 计数器
 6方法出口其他信息


 */



    String s = "哈哈";
    String s2 = "哈哈";

    User u=new User();


    private void smoke() {
        System.out.println(name +"我会抽烟"+age);
    }


    private void smoke(String s) {
        System.out.println(name+"我会抽" + s+age);
    }


    public void say() {
        System.out.println(name+"我是Man"+age);
    }

    /*

      User u=new User();
      User是静态类型
      User() 是实际类型

     先看方法，在看实际类型
    如果是私有方法 构造方法 final方法 super方法，直接执行
    其他的就看实际类型
    先看静态类型。再看实际类型
    对象.方法（参数） 方法的接受者和方法参数叫宗量  宗量.方法(宗量)

    静态编译。宗量根据静态类型。编译器决定。编译很多。变成class
    运行根据。动态类型。jvm决定。运行class文件
    静态多分派。
    动态单分派

*/
    public static void main(String[] args) throws InterruptedException {

        //方法运行时，动态会解析 #57  #60 等符号引用，指向运行时常量池里解析过的地址。
        ManLoad man = new ManLoad();
        man.start();
        man.say();
        man.smoke();
        man.smoke("大麻");

        man.eat();
        man.work();

        User user = new ManLoad();
        user.say();
        System.out.println(user.name);

        Thread.sleep(100 * 1000);

    }

    private void start() { //方法引用
        System.out.println("开始 ");
    }


/*

            方法解析
            #23 = Methodref          #16.#79        // cn/restlibs/jvm/jvmload/ManLoad.eat:()V
            符号引用编译器是ManLoad.eat  直接引用是父类的eat方法。本类没有eat方法
            #25 = Methodref          #5.#75         // cn/restlibs/jvm/jvmload/User.say:()V                       父类的方法
             符号引用编译器是User.say  直接引用是User.say



              字段引用
            #26 = Fieldref           #5.#80         // cn/restlibs/jvm/jvmload/User.name:Ljava/lang/String;    父类的字段
             符号引用编译器是User.name  直接引用是User.name
            #24 = Fieldref           #16.#80        // cn/restlibs/jvm/jvmload/ManLoad.name:Ljava/lang/String;   父类的字段
           符号引用编译器是ManLoad.name  解析直接引用是User.name



方法解析
解析阶段解析
或者运行时解析

 public static void main(java.lang.String[]) throws java.lang.InterruptedException;
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=3, args_size=1
         0: new           #19                 // class cn/restlibs/jvm/jvmload/ManLoad
         3: dup
         4: invokespecial #20                 // Method "<init>":()V           //invokespecial 解析阶段解析
         7: astore_1
         8: aload_1
         9: invokespecial #21                 // Method start:()V                //invokespecial 解析阶段解析
        12: aload_1
        13: invokevirtual #22                 // Method say:()V                 //动态解析 运行时解析 。找父类say方法。实际类型的
        16: aload_1
        17: invokespecial #23                 // Method smoke:()V            //invokespecial 解析阶段解析
        20: aload_1
        21: ldc           #24                 // String 大麻
        23: invokespecial #25                 // Method smoke:(Ljava/lang/String;)V           //invokespecial 解析阶段解析
        26: aload_1
        27: invokevirtual #26                 // Method eat:()V                        //动态解析 运行时解析 。找父类say方法。实际类型的
        30: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;  //getstatic  解析阶段解析
        33: aload_1
        34: getfield      #10                 // Field name:Ljava/lang/String;
        37: invokevirtual #16                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V      //动态解析 运行时解析 。找父类say方法。实际类型的
        40: new           #19                 // class cn/restlibs/jvm/jvmload/ManLoad
        43: dup
        44: invokespecial #20                 // Method "<init>":()V         /invokespecial 解析阶段解析
        47: astore_2
        48: aload_2
        49: invokevirtual #27                 // Method cn/restlibs/jvm/jvmload/User.say:()V    //动态解析 运行时解析 。找自己的say方法。实际类型的
        52: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
        55: aload_2
        56: getfield      #28                 // Field cn/restlibs/jvm/jvmload/User.name:Ljava/lang/String;
        59: invokevirtual #16                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        62: ldc2_w        #29                 // long 100000l
        65: invokestatic  #31                 // Method java/lang/Thread.sleep:(J)V          //invokestatic 解析阶段解析
        68: return
      LineNumberTable:
        line 102: 0
        line 103: 8
        line 104: 12
        line 105: 16
        line 106: 20
        line 107: 26
        line 108: 30
        line 110: 40
        line 111: 48
        line 112: 52
        line 114: 62
        line 116: 68
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      69     0  args   [Ljava/lang/String;
            8      61     1   man   Lcn/restlibs/jvm/jvmload/ManLoad;
           48      21     2  user   Lcn/restlibs/jvm/jvmload/User;
    Exceptions:
      throws java.lang.InterruptedException
}








    Constant pool:
            #1 = Methodref          #5.#57         // cn/restlibs/jvm/jvmload/User."<init>":()V     父类的构造方法
            #2 = String             #58            // 哈哈
            #3 = Fieldref           #16.#59        // cn/restlibs/jvm/jvmload/ManLoad.s:Ljava/lang/String;
            #4 = Fieldref           #16.#60        // cn/restlibs/jvm/jvmload/ManLoad.s2:Ljava/lang/String;
            #5 = Class              #61            // cn/restlibs/jvm/jvmload/User
            #6 = Fieldref           #16.#62        // cn/restlibs/jvm/jvmload/ManLoad.u:Lcn/restlibs/jvm/jvmload/User;
            #7 = Fieldref           #63.#64        // java/lang/System.out:Ljava/io/PrintStream;
            #8 = String             #65            // 我会抽烟
            #9 = Methodref          #66.#67        // java/io/PrintStream.println:(Ljava/lang/String;)V
            #10 = Class              #68            // java/lang/StringBuilder
            #11 = Methodref          #10.#57        // java/lang/StringBuilder."<init>":()V
            #12 = String             #69            // 我会抽
            #13 = Methodref          #10.#70        // java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            #14 = Methodref          #10.#71        // java/lang/StringBuilder.toString:()Ljava/lang/String;
            #15 = String             #72            // 我是Man
            #16 = Class              #73            // cn/restlibs/jvm/jvmload/ManLoad
            #17 = Methodref          #16.#57        // cn/restlibs/jvm/jvmload/ManLoad."<init>":()V   本类的构造方法
            #18 = Methodref          #16.#74        // cn/restlibs/jvm/jvmload/ManLoad.start:()V
            #19 = Methodref          #16.#75        // cn/restlibs/jvm/jvmload/ManLoad.say:()V
            #20 = Methodref          #16.#76        // cn/restlibs/jvm/jvmload/ManLoad.smoke:()V
            #21 = String             #77            // 大麻
            #22 = Methodref          #16.#78        // cn/restlibs/jvm/jvmload/ManLoad.smoke:(Ljava/lang/String;)V
            #23 = Methodref          #16.#79        // cn/restlibs/jvm/jvmload/ManLoad.eat:()V                   父类的方法
            #24 = Fieldref           #16.#80        // cn/restlibs/jvm/jvmload/ManLoad.name:Ljava/lang/String;   父类的字段
            #25 = Methodref          #5.#75         // cn/restlibs/jvm/jvmload/User.say:()V                       父类的方法
            #26 = Fieldref           #5.#80         // cn/restlibs/jvm/jvmload/User.name:Ljava/lang/String;    父类的字段
            #27 = Long               100000l
            #29 = Methodref          #81.#82        // java/lang/Thread.sleep:(J)V
            #30 = String             #83            // 开始
            #31 = Utf8               s
              #32 = Utf8               Ljava/lang/String;
              #33 = Utf8               s2
              #34 = Utf8               u
              #35 = Utf8               Lcn/restlibs/jvm/jvmload/User;
              #36 = Utf8               <init>
              #37 = Utf8               ()V
              #38 = Utf8               Code
              #39 = Utf8               LineNumberTable
              #40 = Utf8               LocalVariableTable
              #41 = Utf8               this
            #42 = Utf8               Lcn/restlibs/jvm/jvmload/ManLoad;
          #43 = Utf8               smoke
          #44 = Utf8               (Ljava/lang/String;)V
          #45 = Utf8               say
          #46 = Utf8               main
          #47 = Utf8               ([Ljava/lang/String;)V
          #48 = Utf8               args
          #49 = Utf8               [Ljava/lang/String;
          #50 = Utf8               man
          #51 = Utf8               user
          #52 = Utf8               Exceptions
          #53 = Class              #84            // java/lang/InterruptedException
            #54 = Utf8               start
              #55 = Utf8               SourceFile
              #56 = Utf8               ManLoad.java
              #57 = NameAndType        #36:#37        // "<init>":()V
            #58 = Utf8               哈哈
            #59 = NameAndType        #31:#32        // s:Ljava/lang/String;
            #60 = NameAndType        #33:#32        // s2:Ljava/lang/String;
            #61 = Utf8               cn/restlibs/jvm/jvmload/User
            #62 = NameAndType        #34:#35        // u:Lcn/restlibs/jvm/jvmload/User;
            #63 = Class              #85            // java/lang/System
            #64 = NameAndType        #86:#87        // out:Ljava/io/PrintStream;
            #65 = Utf8               我会抽烟
            #66 = Class              #88            // java/io/PrintStream
            #67 = NameAndType        #89:#44        // println:(Ljava/lang/String;)V
            #68 = Utf8               java/lang/StringBuilder
          #69 = Utf8               我会抽
          #70 = NameAndType        #90:#91        // append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            #71 = NameAndType        #92:#93        // toString:()Ljava/lang/String;
            #72 = Utf8               我是Man
          #73 = Utf8               cn/restlibs/jvm/jvmload/ManLoad
          #74 = NameAndType        #54:#37        // start:()V
            #75 = NameAndType        #45:#37        // say:()V
            #76 = NameAndType        #43:#37        // smoke:()V
            #77 = Utf8               大麻
             #78 = NameAndType        #43:#44        // smoke:(Ljava/lang/String;)V
            #79 = NameAndType        #94:#37        // eat:()V
            #80 = NameAndType        #95:#32        // name:Ljava/lang/String;
            #81 = Class              #96            // java/lang/Thread
            #82 = NameAndType        #97:#98        // sleep:(J)V
            #83 = Utf8               开始
              #84 = Utf8               java/lang/InterruptedException
              #85 = Utf8               java/lang/System
              #86 = Utf8               out
              #87 = Utf8               Ljava/io/PrintStream;
              #88 = Utf8               java/io/PrintStream
              #89 = Utf8               println
              #90 = Utf8               append
              #91 = Utf8               (Ljava/lang/String;)Ljava/lang/StringBuilder;
              #92 = Utf8               toString
              #93 = Utf8               ()Ljava/lang/String;
              #94 = Utf8               eat
              #95 = Utf8               name
              #96 = Utf8               java/lang/Thread
              #97 = Utf8               sleep
              #98 = Utf8               (J)V*/













}