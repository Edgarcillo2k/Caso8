package case8;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Case8 {

    public static void main(String[] args) throws IOException {
        ImageReader imageReader = new ImageReader();
        PixelInformation[] pixelsInformation = imageReader.getImagePixelsInformation(new File("C:\\Users\\Edgar\\Desktop\\Importante\\TEC\\IV SEMESTRE\\ANALISIS DE ALGORITMOS\\pruebaImg.jpg"), 0.302);
        ArrayList<PixelInformation> pixelsInformationArrayList = new ArrayList<>(Arrays.asList(pixelsInformation));
        ArrayList<Color> colorPerSector = imageReader.getAVG(pixelsInformationArrayList);
        imageReader.getImageSectorInformation(new File("C:\\Users\\Edgar\\Desktop\\Importante\\TEC\\IV SEMESTRE\\ANALISIS DE ALGORITMOS\\pruebaImg.jpg"), 0.302);
    }
}
