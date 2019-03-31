package cn.restlibs.jvm.jvmload;

public class TestClass {
    private  int m;
    public  int inc(){
        return  m+1;
    }
}


/*    Constant pool:
        #1 = Methodref          #4.#18         // java/lang/Object."<init>":()V
        #2 = Fieldref           #3.#19         // cn/restlibs/jvm/jvmload/TestClass.m:I
        #3 = Class              #20            // cn/restlibs/jvm/jvmload/TestClass
        #4 = Class              #21            // java/lang/Object
        #5 = Utf8               m
        #6 = Utf8               I
        #7 = Utf8               <init>
       #8 = Utf8               ()V
       #9 = Utf8               Code
       #10 = Utf8               LineNumberTable
       #11 = Utf8               LocalVariableTable
       #12 = Utf8               this
       #13 = Utf8               Lcn/restlibs/jvm/jvmload/TestClass;
       #14 = Utf8               inc
       #15 = Utf8               ()I
       #16 = Utf8               SourceFile
       #17 = Utf8               TestClass.java
       #18 = NameAndType        #7:#8          // "<init>":()V
       #19 = NameAndType        #5:#6          // m:I
       #20 = Utf8               cn/restlibs/jvm/jvmload/TestClass
       #21 = Utf8               java/lang/Object
           {*/



      /*  CA FE BA BE 00 00 00 31  00 16 0A 00 04 00 12 09
        00 03 00 13 07 00 14 07  00 15 01 00 01 6D 01 00
        01 49 01 00 06 3C 69 6E  69 74 3E 01 00 03 28 29
        56 01 00 04 43 6F 64 65  01 00 0F 4C 69 6E 65 4E
        75 6D 62 65 72 54 61 62  6C 65 01 00 12 4C 6F 63
        61 6C 56 61 72 69 61 62  6C 65 54 61 62 6C 65 01
        00 04 74 68 69 73 01 00  23 4C 63 6E 2F 72 65 73
        74 6C 69 62 73 2F 6A 76  6D 2F 6A 76 6D 6C 6F 61
        64 2F 54 65 73 74 43 6C  61 73 73 3B 01 00 03 69
        6E 63 01 00 03 28 29 49  01 00 0A 53 6F 75 72 63
        65 46 69 6C 65 01 00 0E  54 65 73 74 43 6C 61 73
        73 2E 6A 61 76 61 0C 00  07 00 08 0C 00 05 00 06
        01 00 21 63 6E 2F 72 65  73 74 6C 69 62 73 2F 6A
        76 6D 2F 6A 76 6D 6C 6F  61 64 2F 54 65 73 74 43
        6C 61 73 73 01 00 10 6A  61 76 61 2F 6C 61 6E 67
        2F 4F 62 6A 65 63 74 00  21 00 03 00 04 00 00 00
        01 00 02 00 05 00 06 00  00 00 02 00 01 00 07 00
        08 00 01 00 09 00 00 00  2F 00 01 00 01 00 00 00
        05 2A B7 00 01 B1 00 00  00 02 00 0A 00 00 00 06             2A B7 00 01 B1方法字节码
        00 01 00 00 00 03 00 0B  00 00 00 0C 00 01 00 00
        00 05 00 0C 00 0D 00 00  00 01 00 0E 00 0F 00 01
        00 09 00 00 00 31 00 02  00 01 00 00 00 07 2A B4
        00 02 04 60 AC 00 00 00  02 00 0A 00 00 00 06 00
        01 00 00 00 06 00 0B 00  00 00 0C 00 01 00 00 00
        07 00 0C 00 0D 00 00 00  01 00 10 00 00 00 02 00


        字节码指令

        2A B7 00 01 B1方法字节码
        第0个字节   2A 指令 aload_0  把第0个本地变量推送到栈顶
        第1个字节   B7  指令 invokespecial 调用对象的私有方法或者构造器   00 01 对应上面指令的方法
        第4个字节   B1 return

        stack=1 深度是1 栈上1个对象

字节码指令表

指令可以带参数
指令一共1个字节的大小 256 最大指令数目


do{
 pc寄存器+1
 读取操作码
 if(存在操作数){
 执行操作码+操作数
 }
}

byte char short boolean 转换int 处理

加载存储指令
栈帧中的局部变量和操作数栈来回转换 load store
常量加载到操作数栈  push

运算指令
add sub mul div 位移 比较

类型转换
_2_
对象创建和访问
new

操作数栈管理

pop dup  swap

控制转移
if

方法调用和返回

invokevitual
invoke interface
invokespecial
invokestatic

异常处理
athrow
同步指令
管程 monitor支持

管程是锁
monitorenter
monexit


class文件 平台中立不依赖操作系统







*/




/*
public cn.restlibs.jvm.jvmload.TestClass();
        descriptor: ()V
        flags: ACC_PUBLIC
        Code:
        stack=1, locals=1, args_size=1
        0: aload_0
        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
        4: return
        LineNumberTable:
        line 3: 0u
        LocalVariableTable:
        Start  Length  Slot  Name   Signature
        0       5     0  this   Lcn/restlibs/jvm/jvmload/TestClass;


   public int inc();
        descriptor: ()I
        flags: ACC_PUBLIC
        Code:
        stack=2, locals=1, args_size=1
        0: aload_0
        1: getfield      #2                  // Field m:I
        4: iconst_1
        5: iadd
        6: ireturn
        LineNumberTable:
        line 6: 0
        LocalVariableTable:
        Start  Length  Slot  Name   Signature
        0       7     0  this   Lcn/restlibs/jvm/jvmload/TestClass;
        }*/
