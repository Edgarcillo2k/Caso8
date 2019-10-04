package case8;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageReader 
{
	private static final int SECTOR_SIZE = 1024;
	private static final int DIMENSION = 32;	
	public ImageReader() {};
	public PixelInformation[] getImagePixelsInformation(File pFile, double pSectorPixelsPercentage) throws IOException 
	{
		BufferedImage image = ImageIO.read(pFile);
        int numberOfPixelsPerSector = (int) (pSectorPixelsPercentage * SECTOR_SIZE);
        PixelInformation[] pixelsInformation = new PixelInformation[numberOfPixelsPerSector*SECTOR_SIZE];
        int currentSector = 0;
		int pixelIndex = 0;
		for(int row = 0;row<DIMENSION;row++) {
			for(int column = 0;column<DIMENSION;column++) {
		        for(int currentPixel = 0;currentPixel<numberOfPixelsPerSector;currentPixel++) {
			        int x = (int) (Math.random() * (DIMENSION * row)) + 1;
			        int y = (int) (Math.random() * (DIMENSION * column)) + 1;
			        Color color = new Color(image.getRGB(x, y));
			        pixelsInformation[pixelIndex] = new PixelInformation(x,y,currentSector,color);
			        pixelIndex++;
		        }
		        currentSector++;
			}
		}
        return pixelsInformation;
    }

    public ArrayList<Color> getAVG(ArrayList<PixelInformation> pixelsInformation) {
        //recorrer cada elemento del Array hasta cambiar de sector
        int totalRED = 0;
        int totalGREEN = 0;
        int totalBLUE = 0;
        int cont = 0;
        int numberOfPixelsPerSector = (int) (0.3 * SECTOR_SIZE);
        ArrayList<Color> colorPerSector = new ArrayList<>();

        for (int element = 0; element < pixelsInformation.size(); element++) {
            int red = pixelsInformation.get(element).getColor().getRed();
            totalRED += red;
            int green = pixelsInformation.get(element).getColor().getGreen();
            totalGREEN += green;
            int blue = pixelsInformation.get(element).getColor().getBlue();
            totalBLUE += blue;
            cont++;
            if (cont == numberOfPixelsPerSector) {
                int avgRed = totalRED / numberOfPixelsPerSector;
                int avgGreen = totalGREEN / numberOfPixelsPerSector;
                int avgBlue = totalBLUE / numberOfPixelsPerSector;
                Color sector = new Color(avgRed, avgGreen, avgBlue);
                colorPerSector.add(sector);
                totalRED = 0;
                totalGREEN = 0;
                totalBLUE = 0;
                cont = 0;
            }
        }

        return colorPerSector;
    }
	public SectorInformation[][] getImageSectorInformation(File pFile, double pSectorPixelsPercentage) throws IOException
	{
		SectorInformation[][] imageSectorInformation = new SectorInformation[32][32];
		PixelInformation[] pixelsInformation = getImagePixelsInformation(pFile, pSectorPixelsPercentage);
		int currentSector = 0;
		int numberOfPixelsPerSector = (int) (pSectorPixelsPercentage * SECTOR_SIZE);
		for(int row = 0;row<DIMENSION;row++) {
			for(int column = 0;column<DIMENSION;column++) {
				int startOfSameSectorPixels = currentSector*numberOfPixelsPerSector;
				int endOfSameSectorPixels = (currentSector+1)*numberOfPixelsPerSector;
				imageSectorInformation[row][column] = new SectorInformation(currentSector,pixelsInformation,startOfSameSectorPixels,endOfSameSectorPixels);
				currentSector++;
			}
		}
		return imageSectorInformation;
	}
}
