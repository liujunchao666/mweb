package cn.restlibs.proxy;

public class Gmm  implements Start{
    @Override
    public String sing() {
        System.out.print("唱歌");
        return "唱歌";
    }

    @Override
    public void dance() {
        System.out.print("跳舞");

    }

    @Override
    public String say(String s) {
        System.out.print("说话");
        return "说话";
    }
}
