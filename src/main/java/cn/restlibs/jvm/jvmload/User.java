package cn.restlibs.jvm.jvmload;

public class User {
    String name = "张三";
    int age = 18;
    Integer maxage = 130;


    public void wuwu() {
        int i=0;
        System.out.println("我是user");
    }



/*
  this i 2个locals solt
  descriptor: ()V
    flags: ACC_PUBLIC
    Code:
    stack=2, locals=2, args_size=1
            0: iconst_0
         1: istore_1
         2: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
            5: ldc           #8                  // String 我是user
            7: invokevirtual #9                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
            10: return
    LineNumberTable:
    line 10: 0
    line 11: 2
    line 12: 10
    LocalVariableTable:
    Start  Length  Slot  Name   Signature
            0      11     0  this   Lcn/restlibs/jvm/jvmload/User;
            2       9     1     i   I
            */
    public void sleepp() {
        long l=123;
        System.out.println("我是user");
    }

/*
this l   3 slot
    public void sleepp();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
    stack=2, locals=3, args_size=1
            0: ldc2_w        #10                 // long 123l
            3: lstore_1
         4: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
            7: ldc           #8                  // String 我是user
            9: invokevirtual #9                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
            12: return
    LineNumberTable:
    line 15: 0
    line 16: 4
    line 17: 12
    LocalVariableTable:
    Start  Length  Slot  Name   Signature
            0      13     0  this   Lcn/restlibs/jvm/jvmload/User;
            4       9     1     l   J
            */

    public void say() {
        int i=0;
        long l=123;
        System.out.println("我是user");
    }


/*
    this i l 4个slot
    public void say();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
    stack=2, locals=4, args_size=1
            0: iconst_0
         1: istore_1
         2: ldc2_w        #10                 // long 123l
            5: lstore_2
         6: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
            9: ldc           #8                  // String 我是user
            11: invokevirtual #9                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
            14: return
    LineNumberTable:
    line 20: 0
    line 21: 2
    line 22: 6
    line 23: 14
    LocalVariableTable:
            Start  Length  Slot  Name   Signature
            0      15     0  this   Lcn/restlibs/jvm/jvmload/User;
            2      13     1     i   I
            6       9     2     l   J
*/


    public void work() {
        long l=123;
        int i=0;
        System.out.println("我是user");
    }



/*this l i  4ge slot

    public void work();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
    stack=2, locals=4, args_size=1
            0: ldc2_w        #10                 // long 123l
            3: lstore_1
         4: iconst_0
         5: istore_3
         6: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
            9: ldc           #8                  // String 我是user
            11: invokevirtual #9                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
            14: return
    LineNumberTable:
    line 26: 0
    line 27: 4
    line 28: 6
    line 29: 14
    LocalVariableTable:
    Start  Length  Slot  Name   Signature
    0      15     0  this   Lcn/restlibs/jvm/jvmload/User;
    4      11     1     l   J
    6       9     3     i   I             long 占用2个slot*/



    public void eat() {
        int i=10;
        try{
            long l=23;
            System.out.println("我是user eat");
        }catch (Exception e){
          //  int ii=22;
        }
        int j=20;
    }
/*


this i l 可以使用long的slot
    public void eat();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
    stack=2, locals=4, args_size=1
            0: bipush        10
            2: istore_1
         3: ldc2_w        #12                 // long 23l
            6: lstore_2
         7: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
            10: ldc           #14                 // String 我是user eat
            12: invokevirtual #9                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
            15: goto          19
            18: astore_2
        19: bipush        20
            21: istore_2
        22: return
    Exception table:
    from    to  target type
             3    15    18   Class java/lang/Exception
    LineNumberTable:
    line 35: 0
    line 37: 3
    line 38: 7
    line 41: 15
    line 39: 18
    line 42: 19
    line 43: 22
    LocalVariableTable:
    Start  Length  Slot  Name   Signature
    7       8     2     l   J
    0      23     0  this   Lcn/restlibs/jvm/jvmload/User;
    3      20     1     i   I
   22       1     2     j   I        //l的slot作用域出来。可以被j使用
*/


    public void eat2() {
        int i=10;
        try{
            long l=23;
            System.out.println("我是user eat");
        }catch (Exception e){
             int h=22;
        }
        int j=20;
    }


}


/*
-------------
每一个常量值是一个表

        基本的
        标志  长度  byte  utf-8
        标志  byte  Integer Float Long Double

        指向基本的
        标志  index      Class String

        指向基本表或者表
        标志  index index     Field  Method  InterfaceMethod  NameAndType

*/
/*

  Constant pool:   class常量池

  顺序       标志          index 或者值
   #1 = Methodref          #12.#30        // java/lang/Object."<init>":()V                   --  Class和NameAndtype
   #2 = String             #31            // 张三                                            --  index
   #3 = Fieldref           #11.#32        // cn/restlibs/jvm/jvmload/User.name:Ljava/lang/String; -- Class和NameAndtype
   #4 = Fieldref           #11.#33        // cn/restlibs/jvm/jvmload/User.age:I                      -- Class和NameAndtype
   #5 = Methodref          #34.#35        // java/lang/Integer.valueOf:(I)Ljava/lang/Integer;  -- Class和NameAndtype
   #6 = Fieldref           #11.#36        // cn/restlibs/jvm/jvmload/User.maxage:Ljava/lang/Integer; --
   #7 = Fieldref           #37.#38        // java/lang/System.out:Ljava/io/PrintStream;
   #8 = String             #39            // 我是user
   #9 = Methodref          #40.#41        // java/io/PrintStream.println:(Ljava/lang/String;)V
  #10 = String             #42            // 我是user eat
  #11 = Class              #43            // cn/restlibs/jvm/jvmload/User
  #12 = Class              #44            // java/lang/Object  //    index
  #13 = Utf8               name
  #14 = Utf8               Ljava/lang/String;      //--字段类型
  #15 = Utf8               age              //--字段名字
  #16 = Utf8               I               //--字段类型 int
  #17 = Utf8               maxage
  #18 = Utf8               Ljava/lang/Integer;      //--字段类型 Integer  如果是方法 （参数）反回类型
  #19 = Utf8               <init>          //方法名字
  #20 = Utf8               ()V           //方法的nameAndType
  #21 = Utf8               Code          //方法属性
  #22 = Utf8               LineNumberTable
  #23 = Utf8               LocalVariableTable
  #24 = Utf8               this
  #25 = Utf8               Lcn/restlibs/jvm/jvmload/User;    //字段的类型
  #26 = Utf8               say        //方法名字
  #27 = Utf8               eat        //方法名字
  #28 = Utf8               SourceFile
  #29 = Utf8               User.java     //文件名
  #30 = NameAndType        #19:#20        // "<init>":()V
  #31 = Utf8               张三                                               //utf-8
  #32 = NameAndType        #13:#14        // name:Ljava/lang/String;       --index index 字段的
  #33 = NameAndType        #15:#16        // age:I          字段的
  #34 = Class              #45            // java/lang/Integer
  #35 = NameAndType        #46:#47        // valueOf:(I)Ljava/lang/Integer;  方法的
  #36 = NameAndType        #17:#18        // maxage:Ljava/lang/Integer;  字段的
  #37 = Class              #48            // java/lang/System        类
  #38 = NameAndType        #49:#50        // out:Ljava/io/PrintStream;  字段的
  #39 = Utf8               我是user
  #40 = Class              #51            // java/io/PrintStream     类
  #41 = NameAndType        #52:#53        // println:(Ljava/lang/String;)V    方法的
  #42 = Utf8               我是user eat
  #43 = Utf8               cn/restlibs/jvm/jvmload/User    //类的全路径名字
  #44 = Utf8               java/lang/Object                   //utf-8    //类的全路径名字
  #45 = Utf8               java/lang/Integer            //类的全路径名字
  #46 = Utf8               valueOf
  #47 = Utf8               (I)Ljava/lang/Integer;     //--参数和返回类型
  #48 = Utf8               java/lang/System          //类的全路径名字
  #49 = Utf8               out
  #50 = Utf8               Ljava/io/PrintStream;    //字段的nameAndType
  #51 = Utf8               java/io/PrintStream
  #52 = Utf8               println
  #53 = Utf8               (Ljava/lang/String;)V      //方法的的nameAndType

-------------------

字段表 没有值的
访问标志
   name (name)
  type(AndType)
private  age   i
private  age   i
private   maxage  Integer

方法表

        访问标志
        name (name)
        type(AndType)
        属性个数
        属性表


属性表
属性表index--->code
属性的属性长度
自定义

code属性表
属性表index--->code
属性长度
max stack   操作数栈深度的最大值 最大深度
max locals  slots
code length
code            字节码
异常表数量
异常表
属性的属性数量
属性的属性表    local variable table


异常表
start
end
hand
cath type

本地变量表
start 作用范围
length
name
type
index slot 位置



*/





