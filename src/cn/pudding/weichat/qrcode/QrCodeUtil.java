package cn.pudding.weichat.qrcode;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import org.apache.commons.codec.digest.DigestUtils;

import com.swetake.util.Qrcode;

public class QrCodeUtil {

	
	
	/**
	 * 生成二维码操作
	 * 长:200
	 * 宽:200
	 * 
	 * */
	public static byte[] createQRCode(String content) 
	{
	    byte[] result = null;
	    try 
	    {
	        Qrcode qrcodeHandler = new Qrcode();
	        qrcodeHandler.setQrcodeErrorCorrect('M');
	        qrcodeHandler.setQrcodeEncodeMode('B');
	        qrcodeHandler.setQrcodeVersion(10);
	        byte[] contentBytes = content.getBytes("utf-8");
	        BufferedImage bufferImgage = new BufferedImage(280, 280, BufferedImage.TYPE_INT_RGB);
	        Graphics2D graphics2D = bufferImgage.createGraphics();
	        graphics2D.setBackground(Color.WHITE);
	        graphics2D.clearRect(0, 0, 280, 280);
	        graphics2D.setColor(Color.BLACK);
	        int pixoff = 10;
	        if (contentBytes.length > 0 && contentBytes.length < 240) 
	        {
	            boolean[][] matrix = qrcodeHandler.calQrcode(contentBytes);
	            for (int i = 0; i < matrix.length; i++) 
	            {
	                for (int j = 0; j < matrix.length; j++) 
	                {
	                    if (matrix[j][i]) 
	                    {
	                        graphics2D.fillRect(j * 4 + pixoff, i * 4 + pixoff, 4, 4);
	                    }
	                }
	            }
	        } 
	        else 
	        {
	            //
	        }
	        graphics2D.dispose();
	        bufferImgage.flush();
	        ByteArrayOutputStream output = new ByteArrayOutputStream();
	        ImageIO.write(bufferImgage, "png", output);
	        result = output.toByteArray();
	        output.close();
	    } 
	    catch (Exception e) 
	    {
	    	e.printStackTrace();
	    	
	    }
	    return result;
	}
	
	  
	public static void main(String[] args)
	{
		System.out.println(QrCodeUtil.createQRCode("你好!"));
	}
	
}
