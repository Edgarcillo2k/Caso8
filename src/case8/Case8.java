package case8;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Case8 
{
    public static void main(String[] args) throws IOException 
    {
    	ImageReader imageReader = new ImageReader();
        ArrayList<PixelInformation> pixelsInformation = imageReader.getImagePixelsInformation(new File("C:\\Users\\Edgar\\Desktop\\No importante\\Fondos de pantalla ajustados\\goku_ui.png"),0.3);    
        ArrayList<Color> colorPerSector = imageReader.getAVG(pixelsInformation);
    }
}
