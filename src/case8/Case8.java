package case8;

import java.io.File;
import java.io.IOException;

public class Case8 
{
    public static void main(String[] args) throws IOException 
    {
    	ImageReader imageReader = new ImageReader();
        imageReader.getImagePixelsInformation(new File("C:\\Users\\Edgar\\Desktop\\No importante\\Fondos de pantalla ajustados\\goku_ui.png"),0.3);    
    }
}
