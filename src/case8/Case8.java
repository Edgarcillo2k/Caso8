package case8;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Case8 {

    public static void main(String[] args) throws IOException {
        ImageReader imageReader = new ImageReader();
		imageReader.getImagePixelsInformation(new File("C:\\Users\\Edgar\\Desktop\\Importante\\TEC\\IV SEMESTRE\\ANALISIS DE ALGORITMOS\\pruebaImg.jpg"),0.3);
        //PixelInformation[] pixelsInformation = imageReader.getImagePixelsInformation(new File("C:\\Users\\Edgar\\Desktop\\Importante\\TEC\\IV SEMESTRE\\ANALISIS DE ALGORITMOS\\pruebaImg.jpg"), 0.302);
        //ArrayList<PixelInformation> pixelsInformationArrayList = new ArrayList<>(Arrays.asList(pixelsInformation));
        //ArrayList<Color> colorPerSector = imageReader.getAVG(pixelsInformationArrayList);
        //imageReader.getImageSectorInformation(new File("C:\\Users\\Edgar\\Desktop\\Importante\\TEC\\IV SEMESTRE\\ANALISIS DE ALGORITMOS\\pruebaImg.jpg"), 0.302);
        Point x0 = new Point(-2,3);
        Point x1 = new Point(3,4);
        Point x2 = new Point(-3,-4);
        ArrayList<Point> ejemplo = new ArrayList<>();
        ejemplo.add(x0);
        ejemplo.add(x1);
        ejemplo.add(x2);
        int Area = imageReader.getArea(ejemplo);
        System.out.println(Area);
	}
}
