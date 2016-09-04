

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageWriteTest {

/**
 * 
 */

	public ImageWriteTest() {
		super();
		BufferedImage image = null;
		
		try {

			// Read from a file
			File file = new File("salt2.jpg");
			image = ImageIO.read(file);
		} catch (IOException e) {

			System.err.println("Cannot open the file.");
		}

		System.out.println("Width: " + image.getWidth());
		System.out.println("Height: " + image.getHeight());

		//���ҧ BufferedImage �������������Ѻ Save �Ҿ�������������ͧ�к� size ��� type ����
		BufferedImage image2 = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
		
		//���ҧ array �ͧ�ԵԢ������������Ѻ�纤��㹡�äӹǳ
		int rgb[][] = new int[image.getWidth()][image.getHeight()];
		
		//�֧��� RGB �͡����зӡ���ŧ���������ٻ�ͧ integer ����դ�Ҫ�ǧ Gray Scale ����� 0-255
		//��Ф��� array ���ӡ���ŧ�� gray scale ��������ö��令ӹǳ��ҵ�ҧ � �� �� filter noise
		for(int i=0;i<image.getWidth();i++)
			for(int j=0;j<image.getHeight();j++){
				rgb[i][j] = averageRGB(image.getRGB(i,j));
				System.out.println(rgb[i][j]);
			}
		
		//�ŧ��Ҩҡ integer ��Ѻ��� Hex ���͹ӡ�Ѻ����ҧ�Ҿ��������
		for(int i=0;i<image.getWidth();i++)
			for(int j=0;j<image.getHeight();j++){
				image2.setRGB(i,j,averageRGB2(rgb[i][j]));
			}
		
		//���¡ method writeImage �������ҧ����Ҿ���������
		try {
			writeImage(image2);
		} catch (ImageFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	//�ŧ��� Hex �� integer �ѧ�� 0-255
	public int averageRGB(int rgb)
	{
		int r = (rgb >> 16) & 0xff;
		int g = (rgb >>  8) & 0xff;
		int b = (rgb >>  0) & 0xff;
	
		int nIntensity = (int) (r+g+b)/3;
		r = g = b = nIntensity;
		return nIntensity;
	}	
	
	//�ŧ��Ҩҡ integer ��Ѻ�� Hex ���ͧ�ҡ��͹���ҧ�Ҿ���������� ��ҷ���� setRGB
	//ŧ� BufferedImage �е�ͧ��Ѻ��� Hex ����͹���
	public int averageRGB2(int rgb)
	{

		int r = (rgb  & 0xff ) << 16;
		int g = (rgb  & 0xff) << 8;
		int b = (rgb  & 0xff) ;

		int nIntensity = (int) (r+g+b);
		r = g = b = nIntensity;
		return (rgb & 0xff000000)| (r<<16) | (g<<8) | (b<<0);

	}
	
	//	method �������Ѻ���ҧ����ٻ�Ҿ����������觨����ҧ����� png ���ͧ�ҡ
	// ��� png ����س�Ҿ�ͧ�Ҿ�ա��� ��觶�� save �� jpg �з�����Ҿᵡ
	public void writeImage(BufferedImage img){
		File f = new File("myimage.png");
	     try {
			ImageIO.write(img, "png", f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

	public static void main(String[] args) {
		new ImageWriteTest();
	}
}