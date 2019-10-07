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
	public PixelInformation[] getInflectionPoints(ArrayList<PixelInformation> pPoints)
	{
		PixelInformation max = new PixelInformation(new Point(0,Integer.MIN_VALUE),0,Color.white);
		PixelInformation min = new PixelInformation(new Point(0,Integer.MAX_VALUE),0,Color.white);
		for(int i = 0;i<pPoints.size();i++) {
			PixelInformation puntoActual = pPoints.get(i);
			if(puntoActual.getPoint().getY() > max.getPoint().getY()) {
				max = puntoActual;
			}
			if(puntoActual.getPoint().getY() < min.getPoint().getY()) {
				min = puntoActual;
			}
		}
		PixelInformation resp[] = {max,min};
		return resp;
	}
	public ArrayList<PixelInformation> prueba(Point[] pCoordinates,double pPercentage, BufferedImage pImage,int pSector)
	{
		int arrayLen = pCoordinates.length;
		int numberOfPixelsPerSector = (int) (pPercentage * SECTOR_SIZE);
		boolean flag = false;
		int eleccion = 0;
		ArrayList<PixelInformation> puntos = new ArrayList<PixelInformation>();
		for(int i = 0;i<numberOfPixelsPerSector && i<arrayLen;i++) {
			if(flag) {
				int signo = (Math.random()*2)%2 == 0?1:-1;
				eleccion = eleccion +  signo * (int) (Math.random() * 5);
				if(eleccion > arrayLen || eleccion < 0) {
					eleccion = (int) (Math.random() * arrayLen);
				}
			}
			else {
				eleccion = (int) (Math.random() * arrayLen);
			}
	        Point punto = pCoordinates[eleccion];
	        Color color = new Color(pImage.getRGB(punto.getX(),punto.getY()));
	        if(color.equals(Color.white)) {
	        	pPercentage = pPercentage*0.5;
	        	numberOfPixelsPerSector = (int) (pPercentage * SECTOR_SIZE);
	        	flag = false;
	        }
	        
	        else {
	        	puntos.add(new PixelInformation(punto,pSector,color));
	        	pPercentage += pPercentage*0.2;
	        	flag = true;
	        }
	        arrayLen--;
	        for(int j = eleccion;j<arrayLen;j++) {
	        	pCoordinates[j] = pCoordinates[j+1];
	        }
		}
		return puntos;   
	}
	public Point[] formarArray(Point pInitialPoint,Point pFinalPoint) throws InterruptedException
	{
		Point nombre[] = new Point[SECTOR_SIZE];
		for(int i = 0;i<DIMENSION;i++) {
			for(int j = 0;j<DIMENSION;j++) {
				nombre[i*DIMENSION + j] = new Point(pInitialPoint.getX()+i,pInitialPoint.getY()+j);
			}
		}
		return nombre;
	}
	public void getImagePixelsInformation(File pFile, double pSectorPixelsPercentage) throws IOException, InterruptedException 
	{
		BufferedImage image = ImageIO.read(pFile);
        int numberOfPixelsPerSector = (int) (pSectorPixelsPercentage * SECTOR_SIZE);
        ArrayList<PixelInformation> puntosInfleccion = new ArrayList<PixelInformation>(); 
        int currentSector = 0;
		for(int row = 0;row<DIMENSION;row++) {
			for(int column = 0;column<DIMENSION;column++) {
				Point initialPoint = new Point(DIMENSION * row,DIMENSION * column);
				Point finalPoint = new Point((DIMENSION * (row+1))-1,(DIMENSION * (column+1))-1);
		        PixelInformation[] cosa = getInflectionPoints(prueba(formarArray(initialPoint,finalPoint),pSectorPixelsPercentage,image,currentSector));
		        if(cosa[0].getPoint().getY() < Integer.MAX_VALUE && cosa[0].getPoint().getY() > Integer.MIN_VALUE ) {
		        puntosInfleccion.add(cosa[0]);
		        }
		        if(cosa[1].getPoint().getY() < Integer.MAX_VALUE && cosa[1].getPoint().getY() > Integer.MIN_VALUE ) {
		        puntosInfleccion.add(cosa[1]);
				}
		        currentSector++;
			}
		}
		for(int i = 0;i<puntosInfleccion.size();i++) {
			System.out.println(puntosInfleccion.get(i).toString());
		}
    }
	/*
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
    */

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
    /*
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
				Point initialPoint = new Point(DIMENSION * row,DIMENSION * column);
				Point finalPoint = new Point((DIMENSION * (row+1))-1,(DIMENSION * (column+1))-1);
				imageSectorInformation[row][column] = new SectorInformation(currentSector,pixelsInformation,startOfSameSectorPixels,endOfSameSectorPixels,initialPoint,finalPoint);
				currentSector++;
			}
		}
		return imageSectorInformation;
	}
    
    public int getArea(ArrayList<Point> polygon){
        Point firstItem = polygon.get(0);
        polygon.add(firstItem);
        float constant = (float) (1.0/2.0);
        int positiveAnswer = 0;
        int negativeAnswer = 0;
        int changeSign = -1;
        for (int firstDiagonal = 0; firstDiagonal<polygon.size()-1; firstDiagonal++){
            Point xDiagonal = polygon.get(firstDiagonal);
            Point yDiagonal = polygon.get(firstDiagonal+1);
            positiveAnswer+= xDiagonal.getX() * yDiagonal.getY();            
        }
        for (int seconfDiagonal = 0; seconfDiagonal < polygon.size()-1; seconfDiagonal++) {
            Point yDiagonal = polygon.get(seconfDiagonal);
            Point xDiagonal = polygon.get(seconfDiagonal+1);
            negativeAnswer+= yDiagonal.getY() * xDiagonal.getX(); 
        }
        negativeAnswer = negativeAnswer * changeSign;
        int Area = Math.abs((int) ((positiveAnswer + negativeAnswer) * constant));
        return Area;
    }
           
	*/
}
