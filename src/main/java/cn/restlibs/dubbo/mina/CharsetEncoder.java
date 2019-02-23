package cn.restlibs.dubbo.mina;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caucho.hessian.io.Hessian2Output;
 
/**
 * <b>function:</b> 字符编码
 * @author hoojo
 */
public class CharsetEncoder implements ProtocolEncoder {
	/*
    客户端 发送 编码  -->对象-->字节   ---------------->接受 解码 字节--->对象---->服务端
    客户端 接受解码 <---对象<---字节<---------------  发送编码 字节<---对象<---服务端
*/
    private final static Charset charset = Charset.forName("UTF-8");
    
    @Override
    public void dispose(IoSession session) throws Exception {
    }
 
    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
    	
    	//把Ojbect对象转换为字节
        IoBuffer buff = IoBuffer.allocate(100).setAutoExpand(true);
        
        ByteArrayOutputStream os = new ByteArrayOutputStream();
		Hessian2Output h2o = new Hessian2Output(os);
		h2o.startMessage();
		h2o.writeObject(message);
		h2o.writeString("I am client.");
		h2o.completeMessage();
		h2o.close();
		
		byte[] buffer = os.toByteArray();
		os.close();
		
		buff.put(buffer);
      
        // put 当前系统默认换行符
      //  buff.putString(LineDelimiter.DEFAULT.getValue(), charset.newEncoder());
        // 为下一次读取数据做准备
        buff.flip();
        
        out.write(buff);
    }
}