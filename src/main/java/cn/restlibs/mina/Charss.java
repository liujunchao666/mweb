package cn.restlibs.mina;


import java.io.FileOutputStream;

import org.junit.Test;


public class Charss {


	//右键 复制  为一个hex流  tcp.port==59393  tcp.port==59353
	@Test
	public  void ttt2()   throws Exception {
		
		String string= "4500006745204000800600007f0000017f0000010d80e801bb278edd27a5ed2e501800ff5d5e0000323031382d30312d31312031313a33353a303009e4b88de5aea2e6b094e4ba86e38082e592b1e4b8a4e4b8aae585b3e7b3bbe5a5bde38082e68b9ce68b9c0a";
	
		
		byte b[]=new byte[string.length()/2];
		for (int i=0;i<string.length()/2;i++) {
			int begin=i*2;
			int end=i*2+2;
			String string2 = string.substring(begin, end);
			
			int intt = Integer.valueOf(string2,16) ;
			byte bb=(byte) intt;
			b[i]=bb;
	
		}
		
		System.out.println(new String(b,"utf-8"));
		 
	}
	
	@Test
	public  void ttt()   throws Exception {
		
		String string= "e8 bf 9e e6 8e a5 e6 9c 8d e5 8a a1 e5 99 a8 e6 88 90 e5 8a 9f ef bc 81";
		
		String[] split = string.split(" ");
		byte b[]=new byte[split.length];
		for (int i=0;i<split.length;i++) {
			String string2 = split[i];
			int intt = Integer.valueOf(string2,16) ;
			byte bb=(byte) intt;
			System.out.println(bb);
			b[i]=bb;
			/*232
			191
			158
			230*/
		/*	
			-24
			-65
			-98
			-26
			-114
			-91
			-26
			-100
			-115
			-27
			-118
			-95
			-27
			-103
			-88
			-26
			-120
			-112
			-27
			-118
			-97
			-17
			-68
			-127*/
			
		}
		
		System.out.println(new String(b,"utf-8"));
		 
		 
	}
	
	
	public static void main(String[] args) throws Exception {
		
		String s= "EF BB BF "+ //txt开头 utf8
             "e8 bf 9e e6 8e a5 e6 9c 8d e5 8a a1 e5 99 a8 e6 88 90 e5 8a 9f ef bc 81";

		        //                       e4 b8 ad e5 9b bd e4 b8 9c 
		        //e6 96 b9 e8 88 aa e7 a9 ba e5 85 ac e5 8fb8                                                           
		
		//2种方法 1 把字节读取到记事本上，utf8打开
		// 2 自己解析


		
		  String path2="d://3png.txt";
		  //字节流把字节输入到文件中。
		  FileOutputStream  out=new FileOutputStream(path2); //也可传入file,out.write(接受) 写入数据到哪里
		//  OutputStreamWriter outs=new OutputStreamWriter(out,"utf-8");
		  String[] split = s.split(" ");
		  for (String string : split) {
			  //16进制转换成字节。字节转换成int
			int code1=Integer.parseInt(string, 16);
			out.write(code1);   //把字节放到输出流里
		  }
		  out.close();
		 


		
		
		
	
          
	}
}

