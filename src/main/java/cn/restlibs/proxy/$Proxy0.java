package cn.restlibs.proxy;

import cn.restlibs.proxy.Start;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

public final class $Proxy0 extends Proxy implements Start {
    private static Method m1;
    private static Method m4;
    private static Method m2;
    private static Method m3;
    private static Method m5;
    private static Method m0;

    public $Proxy0(InvocationHandler var1) throws RuntimeException {
        super(var1);
    }



    public final void dance() throws  RuntimeException{
        try {
            super.h.invoke(this, m4, (Object[])null);
        } catch (RuntimeException  var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }


    public final String sing() throws  RuntimeException {
        try {
            return (String)super.h.invoke(this, m3, (Object[])null);
        } catch (RuntimeException  var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final String say(String var1) throws RuntimeException  {
        try {
            return (String)super.h.invoke(this, m5, new Object[]{var1});
        } catch (RuntimeException var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }


    static {
        try {
            m0 = Class.forName("java.lang.Object").getMethod("hashCode");
            m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
            m2 = Class.forName("java.lang.Object").getMethod("toString");

            m4 = Class.forName("com.proxy.Start").getMethod("dance");
            m3 = Class.forName("com.proxy.Start").getMethod("sing");
            m5 = Class.forName("com.proxy.Start").getMethod("say", Class.forName("java.lang.String"));
        } catch (NoSuchMethodException var2) {
            throw new NoSuchMethodError(var2.getMessage());
        } catch (ClassNotFoundException var3) {
            throw new NoClassDefFoundError(var3.getMessage());
        }
    }
}
