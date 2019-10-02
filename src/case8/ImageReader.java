package case8;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ImageReader 
{
	private static final int SECTOR_SIZE = 1024;
	private static final int DIMENSION = 32;	
	public ImageReader() {};
	public ArrayList<PixelInformation> getImagePixelsInformation(File pFile, double pSectorPixelsPercentage) throws IOException 
	{
		BufferedImage image = ImageIO.read(pFile);
        ArrayList<PixelInformation> pixelsInformation = new ArrayList<PixelInformation>();
        int numberOfPixelsPerSector = (int) (pSectorPixelsPercentage * SECTOR_SIZE);
        int currentSector = 0;
		for(int row = 0;row<DIMENSION;row++) {
			for(int column = 0;column<DIMENSION;column++) {
		        for(int currentPixel = 0;currentPixel<numberOfPixelsPerSector;currentPixel++) {
			        int x = (int) (Math.random() * (DIMENSION * row)) + 1;
			        int y = (int) (Math.random() * (DIMENSION * column)) + 1;
			        Color color = new Color(image.getRGB(x, y));
			        pixelsInformation.add(new PixelInformation(x,y,currentSector,color));
		        }
		        currentSector++;
			}
		}
        return pixelsInformation;
    }
}
