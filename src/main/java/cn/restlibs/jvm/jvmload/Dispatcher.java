package cn.restlibs.jvm.jvmload;

public class Dispatcher {
    static class QQ {}
    static class _360 {}

    public static class Father {
        public void hardChoice(QQ arg) {
            System.out.println("father choose QQ");
        }

        public void hardChoice(_360 arg) {
            System.out.println("father choose _360");
        }
    }

    public static class Son extends Father {
        @Override
        public void hardChoice(QQ arg) {
            System.out.println("son choose QQ");
        }

        @Override
        public void hardChoice(_360 arg) {
            System.out.println("son choose 360");
        }
    }

  /*  public static void main(String[] args) {
        Father father = new Father();
        Father son = new Son();
        father.hardChoice(new _360());
        son.hardChoice(new QQ());
    }
*/

 /*   public static void main(String[] args) {
        byte[] pase=new byte[64*1024*1024];
        System.gc();// 手动gc 作用域内不可回收  此刻gc 后台线程执行gc 方法也正在执行中。也回收不了
    }
*/
/*

    public static void main(String[] args) {
        {
            byte[] pase = new byte[64 * 1024 * 1024];
        }
        System.gc();// 手动gc 不在作用域内也回收不了。  pase的slot 局部变量表。还没释放。没有其他复用他的slot
    }
*/



 /*   public static void main(String[] args) {
        {
            byte[] pase = new byte[64 * 1024 * 1024];
        }
        int a=10;
        System.gc();// 手动gc 回收
    }

*/



/*

    虚拟机想对于物理机的
    物理机的执行引擎建立在处理器 硬件 操作系统 指令集的
            虚拟机执行引擎自己实现。
    方法的调用和字节码执行

    栈帧 方法调用和方法执行的数据结构
            方法局部变量 操作数栈 动态链接 方法返回地址

    多大的局部变量和操作数栈深度都已经确定了。再code属性中
    一个栈帧的需要的内存。不受程序运行数据影响。确定的
    只有栈顶的栈帧才有效。
    一个线程包括一个栈
    一个栈包括多个栈帧

    局部变量表
    max local 分配局部变量表的最大容量
    long dubbo 2 slot 其他1个slot 1个slot 4个字节
    局部变量表对gc影响



    操作栈同局部变量一样。操作栈的最大深度编译阶段就放放在code中了


    动态链接
*/


/*            解析阶段
    静态方法 私有方法 实例构造器 父类方法 final方法 非虚方法

            编译器再编译阶段不知道一个对象是什么类型
    重载是编译器决定的。参数
    方法的接受者。有些是必须运行期决定。 重写

    编译器字节码一样。执行结果可以不一样。

    对象（看实际类型） .方法（对象 看静态类型）
    编译器决定 字节码    对象（静态类型） .方法（对象 看静态类型）
    运行期决定           对象（看实际类型） .方法（对象 看静态类型）

    动态分派耗时
    虚方法表  包括父类的方法*/



/*


栈的最大深度是2

    操作栈      局部变量表
    200         0  值  1 值     2  值    3 值
    100

相加出栈。结果放到栈顶

    操作栈      局部变量表
    3000     0 值  1 值     2  值    3 值


    相加出栈。结果放到栈顶

    操作栈      局部变量表
    300         0  值  1 值     2  值    3 值
    300


    相乘结果放栈顶
    操作栈      局部变量表
    90000
*/


}