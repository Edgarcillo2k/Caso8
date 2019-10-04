package case8;

import java.io.File;
import java.io.IOException;

public class Case8 
{
    public static void main(String[] args) throws IOException 
    {
    	ImageReader imageReader = new ImageReader();
        imageReader.getImageSectorInformation(new File("C:\\Users\\Edgar\\Desktop\\Importante\\TEC\\IV SEMESTRE\\ANALISIS DE ALGORITMOS\\pruebaImg.jpg"),0.3);
    }
}
