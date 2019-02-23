package cn.restlibs.dubbo.mina;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caucho.hessian.io.Hessian2Input;
import cn.restlibs.dubbo.*;
/**
 * <b>function:</b> 字符解码
 * @author hoojo
 */
public class CharsetDecoder implements ProtocolDecoder {
 
	/*
    客户端 发送 编码  -->对象-->字节   ---------------->接受 解码 字节--->对象---->服务端
    客户端 接受解码 <---对象<---字节<---------------  发送编码 字节<---对象<---服务端
*/
    
    private final static Charset charset = Charset.forName("UTF-8");    
    // 可变的IoBuffer数据缓冲区
    private IoBuffer buff = IoBuffer.allocate(100).setAutoExpand(true);
    
    @Override
    public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
    	
    	//把字节转换为对象Ojbect
    	
    	int i=0;
        // 如果有消息
        while (in.hasRemaining()) {
            // 判断消息是否是结束符，不同平台的结束符也不一样；
            // windows换行符（\r\n）就认为是一个完整消息的结束符了； UNIX 是\n；MAC 是\r
            byte b = in.get();
            buff.put(b);
           // if (b == '\n') {
          
                //log.info("message: " + message);
            /*} else {
                buff.put(b);
            }*/
        }
        
        
        buff.flip();
        byte[] bytes = new byte[buff.limit()];
        buff.get(bytes);
        
        ByteArrayInputStream inputStream=new ByteArrayInputStream(bytes);
        Hessian2Input h2i = new Hessian2Input(inputStream);  
        h2i.startMessage();  
        
    	Object obj=null;
		try {
			obj = (Object) h2i.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}  

        h2i.completeMessage();  
        h2i.close();  
        
        buff = IoBuffer.allocate(100).setAutoExpand(true);
        
        // 如果结束了，就写入转码后的数据
        out.write(obj);
        
    }
 
    @Override
    public void dispose(IoSession session) throws Exception {
    	System.out.println("dispose");
    }
 
    @Override
    public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {
    	System.out.println("完成解码");
    }
}