import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class ImageConverter {
	static String imageFile = "humanAndZomF";
	static String outputFile = "data2.txt";

    public static void main(String[] args) throws IOException {
        for(int i=0;i<6;i++){
            writeData(getImageData( imageFile+i+".png" ), "humanAndZomF"+i+".txt");
        }
    }

    static int[][][] getImageData(String fileName) throws IOException {
        BufferedImage img = ImageIO.read(new File(fileName));
        int width = img.getWidth();
        int height = img.getHeight();
        int[][][] data = new int[height][width][3];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color color = new Color(img.getRGB(j, i));
                data[i][j][0] = color.getRed();
                data[i][j][1] = color.getGreen();
                data[i][j][2] = color.getBlue();
            }
        }
        return data;
    }

    static void writeData(int[][][] data,String outputFileName) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(new File(outputFileName));
        out.println("Image size : " + data[0].length + " x " +data.length);
        out.print("{");
        for (int i = 0; i < data.length; i++) {
            out.print("{");
            for (int j = 0; j < data[i].length; j++) {
                out.print("{" + data[i][j][0] + "," + data[i][j][1] + "," + data[i][j][2] + "}");
                if (j < data[i].length - 1)
                    out.print(",");
            }
            out.print("}");
            if (i < data.length - 1)
                out.println(",");
        }
        out.print("};");
        out.close();
    }
}
