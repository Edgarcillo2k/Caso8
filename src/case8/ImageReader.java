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
	public ArrayList<PixelInformation> getInflectionPoints(ArrayList<PixelInformation> pPoints) {
        PixelInformation max = new PixelInformation(new Point(0, Integer.MIN_VALUE), 0, Color.white);
        PixelInformation min = new PixelInformation(new Point(0, Integer.MAX_VALUE), 0, Color.white);
        ArrayList<PixelInformation> maxs = new ArrayList<PixelInformation>();
        ArrayList<PixelInformation> mins = new ArrayList<PixelInformation>();
        for (int i = 0; i < pPoints.size(); i++) {
            PixelInformation currentPoint = pPoints.get(i);
            if (currentPoint.getPoint().getY() >= max.getPoint().getY()) {
                if (currentPoint.getPoint().getY() > max.getPoint().getY()) {
                    max = currentPoint;
                    maxs = new ArrayList<PixelInformation>();
                }
                maxs.add(currentPoint);
            }
            if (currentPoint.getPoint().getY() <= min.getPoint().getY()) {
                if (currentPoint.getPoint().getY() < min.getPoint().getY()) {
                    min = currentPoint;
                    mins = new ArrayList<PixelInformation>();
                }
                mins.add(currentPoint);
            }
        }
        for (int i = 0; i < maxs.size(); i++) {
            for (int j = 0; j < maxs.size() - 1; j++) {
                PixelInformation current = maxs.get(j);
                PixelInformation next = maxs.get(j + 1);
                if (current.getPoint().getX() > next.getPoint().getX()) {
                    maxs.set(j + 1, current);
                    maxs.set(j, next);
                }
            }
        }
        for (int i = 0; i < mins.size(); i++) {
            for (int j = 0; j < mins.size() - 1; j++) {
                PixelInformation current = mins.get(j);
                PixelInformation next = mins.get(j + 1);
                if (current.getPoint().getX() < next.getPoint().getX()) {
                    mins.set(j + 1, current);
                    mins.set(j, next);
                }
            }
        }
        maxs.addAll(mins);
        return maxs;
    }

    public ArrayList<PixelInformation> getPixelsInformation(Point[] pCoordinates, double pPercentage, BufferedImage pImage, int pSector) {
        int arrayLen = pCoordinates.length;
        final int NUMBER_OF_SCAN = 10;
        double probability = 1;
        final double REDUCTION = 1-(1.0/(arrayLen*pPercentage));
        final int NUMBER_OF_PIXELS = (int) (arrayLen * (pPercentage/NUMBER_OF_SCAN));
        ArrayList<PixelInformation> pixelsInformation = new ArrayList<PixelInformation>();
        for(int scan = 0;scan<NUMBER_OF_SCAN;scan++) {
        	double random = Math.random();
        	if(random < probability) {
        		for(int pixel = 0;pixel<NUMBER_OF_PIXELS;pixel++) {
	        		int election = (int)(Math.random()*arrayLen);
	        		Color color = new Color(pImage.getRGB(pCoordinates[election].getX(), pCoordinates[election].getY()));
		            if(color.equals(Color.white)) {
		            	probability *= REDUCTION;
		            }
		            else {
		            	pixelsInformation.add(new PixelInformation(pCoordinates[election],pSector,color));
		            }
	        		arrayLen--;
		            pCoordinates[election] = pCoordinates[arrayLen];	            
	        	}
        	}
        }
        return pixelsInformation;
    }

    public Point[] createImageArray(Point pInitialPoint, Point pFinalPoint) {
        Point imageArray[] = new Point[SECTOR_SIZE];
        for (int row = 0; row < DIMENSION; row++) {
            for (int column = 0; column < DIMENSION; column++) {
                imageArray[row * DIMENSION + column] = new Point(pInitialPoint.getX() + row, pInitialPoint.getY() + column);
            }
        }
        return imageArray;
    }

    public ArrayList<Polygon> getImagePolygons(File pFile, double pSectorPixelsPercentage) throws IOException {

        BufferedImage image = ImageIO.read(pFile);
        ArrayList<Polygon> polygons = new ArrayList<Polygon>();
        int currentSector = 0;
        for (int row = 0; row < DIMENSION; row++) {
            for (int column = 0; column < DIMENSION; column++) {
                Point initialPoint = new Point(DIMENSION * row, DIMENSION * column);
                Point finalPoint = new Point((DIMENSION * (row + 1)) - 1, (DIMENSION * (column + 1)) - 1);
                ArrayList<PixelInformation> pixelsInformation = getPixelsInformation(createImageArray(initialPoint, finalPoint), pSectorPixelsPercentage, image, currentSector);
                if(pixelsInformation.size()>0) {
                	ArrayList<PixelInformation> inflectionPoints = getInflectionPoints(pixelsInformation);
                	polygons.add(new Polygon(currentSector,inflectionPoints));
                }
                currentSector++;
            }
        }
        return polygons;
    }
}
