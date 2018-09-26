package com.niu;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QrCodeCreateUtil {

	
	/**
	    * ���ɰ����ַ�����Ϣ�Ķ�ά��ͼƬ
	      * @param outputStream �ļ������·��
	     * @param content ��ά��Я����Ϣ
	      * @param qrCodeSize ��ά��ͼƬ��С
      * @param imageFormat ��ά��ĸ�ʽ
	      * @throws WriterException 
	      * @throws IOException 
	     */
	     @SuppressWarnings("unchecked")
		public static boolean createQrCode(OutputStream outputStream, String content, int qrCodeSize, String imageFormat) throws WriterException, IOException{  
	            //���ö�ά�������ͣ���
	            Hashtable<EncodeHintType, Object> hintMap = new Hashtable<EncodeHintType, Object>();  
	             hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  // �ô��� 
	             hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");//֧������
	             
	            @SuppressWarnings("rawtypes")
				Map hints = new HashMap<>();
	             hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
	             hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
	            QRCodeWriter qrCodeWriter = new QRCodeWriter();  
	             //�������ؾ���(λ����)��QR�������ַ���  
	             BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hints);  
	            // ʹBufferedImage����QRCode  (matrixWidth ���ж�ά�����ص�)
	             int matrixWidth = byteMatrix.getWidth();  
	             BufferedImage image = new BufferedImage(matrixWidth-200, matrixWidth-200, BufferedImage.TYPE_INT_RGB);  
	             image.createGraphics();  
	             Graphics2D graphics = (Graphics2D) image.getGraphics();  
	             graphics.setColor(Color.WHITE);  
	            graphics.fillRect(0, 0, matrixWidth, matrixWidth);  
	             // ʹ�ñ��ؾ��󻭲�����ͼ��
	             graphics.setColor(Color.BLACK);  
	             for (int i = 0; i < matrixWidth; i++){
	                 for (int j = 0; j < matrixWidth; j++){
	                    if (byteMatrix.get(i, j)){
	                         graphics.fillRect(i-100, j-100, 1, 1);  
	                    }
	                 }
	             }
	             return ImageIO.write(image, imageFormat, outputStream);  
	     }  
       
	     /**
	      * ����ά�벢���Я������Ϣ
	      */
	     public static void readQrCode(InputStream inputStream) throws IOException{  
	         //���������л�ȡ�ַ�����Ϣ
	         BufferedImage image = ImageIO.read(inputStream);  
	         //��ͼ��ת��Ϊ������λͼԴ
	         LuminanceSource source = new BufferedImageLuminanceSource(image);  
	         BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));  
	         QRCodeReader reader = new QRCodeReader();  
	         Result result = null ;  
	         try {
	         result = reader.decode(bitmap);  
	         } catch (ReaderException e) {
	             e.printStackTrace();  
	         }
	         System.out.println(result.getText());  
	     }
     /**
      * ���Դ���
	      * @throws WriterException 
	      */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			createQrCode(new FileOutputStream(new File("d:\\qrcode.jpg")),"С���������ɶ",900,"JPEG");
			readQrCode(new FileInputStream(new File("d:\\qrcode.jpg")));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (WriterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
