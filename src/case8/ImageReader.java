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


	public ArrayList<PixelInformation> getInflectionPoints(ArrayList<PixelInformation> pPoints) {
        PixelInformation max = new PixelInformation(new Point(0, Integer.MIN_VALUE), 0, Color.white);
        PixelInformation min = new PixelInformation(new Point(0, Integer.MAX_VALUE), 0, Color.white);
        ArrayList<PixelInformation> maximos = new ArrayList<PixelInformation>();
        ArrayList<PixelInformation> minimos = new ArrayList<PixelInformation>();
        for (int i = 0; i < pPoints.size(); i++) {
            PixelInformation puntoActual = pPoints.get(i);
            if (puntoActual.getPoint().getY() >= max.getPoint().getY()) {
                if (puntoActual.getPoint().getY() > max.getPoint().getY()) {
                    max = puntoActual;
                    maximos = new ArrayList<PixelInformation>();
                }
                maximos.add(puntoActual);
            }
            if (puntoActual.getPoint().getY() <= min.getPoint().getY()) {
                if (puntoActual.getPoint().getY() < min.getPoint().getY()) {
                    min = puntoActual;
                    minimos = new ArrayList<PixelInformation>();
                }
                minimos.add(puntoActual);
            }
        }
        for (int i = 0; i < maximos.size(); i++) {
            for (int j = 0; j < maximos.size() - 1; j++) {
                PixelInformation actual = maximos.get(j);
                PixelInformation siguiente = maximos.get(j + 1);
                if (actual.getPoint().getX() > siguiente.getPoint().getX()) {
                    maximos.set(j + 1, actual);
                    maximos.set(j, siguiente);
                }
            }
        }
        for (int i = 0; i < minimos.size(); i++) {
            for (int j = 0; j < minimos.size() - 1; j++) {
                PixelInformation actual = minimos.get(j);
                PixelInformation siguiente = minimos.get(j + 1);
                if (actual.getPoint().getX() < siguiente.getPoint().getX()) {
                    minimos.set(j + 1, actual);
                    minimos.set(j, siguiente);
                }
            }
        }
        maximos.addAll(minimos);
        return maximos;
    }

    public ArrayList<PixelInformation> prueba(Point[] pCoordinates, double pPercentage, BufferedImage pImage, int pSector) {
        int arrayLen = pCoordinates.length;
        int numberOfPixelsPerSector = (int) (pPercentage * SECTOR_SIZE);
        boolean flag = false;
        int eleccion = 0;
        ArrayList<PixelInformation> puntos = new ArrayList<PixelInformation>();
        for (int i = 0; i < numberOfPixelsPerSector && i < arrayLen; i++) {
            if (flag) {
                int signo = (Math.random() * 2) % 2 == 0 ? 1 : -1;
                eleccion = eleccion + signo * (int) (Math.random() * 5);
                if (eleccion > arrayLen || eleccion < 0) {
                    eleccion = (int) (Math.random() * arrayLen);
                }
            } else {
                eleccion = (int) (Math.random() * arrayLen);
            }
            Point punto = pCoordinates[eleccion];
            Color color = new Color(pImage.getRGB(punto.getX(), punto.getY()));
            if (color.equals(Color.white)) {
                pPercentage = pPercentage * 0.5;
                numberOfPixelsPerSector = (int) (pPercentage * SECTOR_SIZE);
                flag = false;
            } else {
                puntos.add(new PixelInformation(punto, pSector, color));
                pPercentage += pPercentage * 0.2;
                flag = true;
            }
            arrayLen--;
            for (int j = eleccion; j < arrayLen; j++) {
                pCoordinates[j] = pCoordinates[j + 1];
            }
        }
        return puntos;
    }

    public Point[] formarArray(Point pInitialPoint, Point pFinalPoint) throws InterruptedException {
        Point nombre[] = new Point[SECTOR_SIZE];
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                nombre[i * DIMENSION + j] = new Point(pInitialPoint.getX() + i, pInitialPoint.getY() + j);
            }
        }
        return nombre;
    }

    public ArrayList<ArrayList<PixelInformation>> getImagePixelsInformation(File pFile, double pSectorPixelsPercentage) throws IOException, InterruptedException {

        BufferedImage image = ImageIO.read(pFile);
        int numberOfPixelsPerSector = (int) (pSectorPixelsPercentage * SECTOR_SIZE);
        ArrayList<ArrayList<PixelInformation>> puntosInfleccion = new ArrayList<ArrayList<PixelInformation>>();
        int currentSector = 0;
        for (int row = 0; row < DIMENSION; row++) {
            for (int column = 0; column < DIMENSION; column++) {
                Point initialPoint = new Point(DIMENSION * row, DIMENSION * column);
                Point finalPoint = new Point((DIMENSION * (row + 1)) - 1, (DIMENSION * (column + 1)) - 1);
                ArrayList<PixelInformation> cosa = getInflectionPoints(prueba(formarArray(initialPoint, finalPoint), pSectorPixelsPercentage, image, currentSector));
                puntosInfleccion.add(cosa);
                currentSector++;
            }
        }
        return puntosInfleccion;
    }

    public Color getAVG(ArrayList<PixelInformation> pointPerSector) {
        //recorrer cada elemento del Array hasta cambiar de sector
        int totalRED = 0;
        int totalGREEN = 0;
        int totalBLUE = 0;
        for (int element = 0; element < pointPerSector.size(); element++) {
            //System.out.println(pointPerSector.get(element).getColor());
            int red = pointPerSector.get(element).getColor().getRed();
            totalRED += red;
            int green = pointPerSector.get(element).getColor().getGreen();
            totalGREEN += green;
            int blue = pointPerSector.get(element).getColor().getBlue();
            totalBLUE += blue;
        }
        int avgRed = totalRED / pointPerSector.size();
        int avgGreen = totalGREEN / pointPerSector.size();
        int avgBlue = totalBLUE / pointPerSector.size();
        Color sector = new Color(avgRed, avgGreen, avgBlue);
        return sector;
    }
}
