package case8;

import java.awt.Color;
import java.util.ArrayList;

public class Sector 
{
	private int sector;
	private Point initialPoint;
	private Point finalPoint;
	private ArrayList<PixelInformation> pixelsInformation;
	private Color color;
	public Sector(Point pInitialPoint,Point pFinalPoint,int pSector,ArrayList<PixelInformation> pPixelsInformation) 
	{
		this.initialPoint = pInitialPoint;
		this.finalPoint = pFinalPoint;
		this.sector = pSector;
		this.pixelsInformation = pPixelsInformation;
		this.color = getAVG();
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color pColor) 
	{
		this.color = pColor;
	}
	public int getSector() 
	{
		return sector;
	}
	public ArrayList<PixelInformation> getPixelsInformation() 
	{
		return pixelsInformation;
	}
	public void setPixelsInformation(ArrayList<PixelInformation> pPixelsInformation) 
	{
		this.pixelsInformation = pPixelsInformation;
	}
	public void setSector(int pSector) 
	{
		this.sector = pSector;
	}
	public Point getInitialPoint() 
	{
		return initialPoint;
	}
	public void setInitialPoint(Point pInitialPoint) 
	{
		this.initialPoint = pInitialPoint;
	}
	public Point getFinalPoint() 
	{
		return finalPoint;
	}
	public void setFinalPoint(Point pFinalPoint) 
	{
		this.finalPoint = pFinalPoint;
	}
	public Point getRandomPoint()
	{
		int x = (int)(Math.random()*(finalPoint.getX()-initialPoint.getX()) + initialPoint.getX());
		int y = (int)(Math.random()*(finalPoint.getY()-initialPoint.getY()) + initialPoint.getY());
		return new Point(x,y);
	}
	public Color getAVG() {
        int totalRed = 0;
        int totalGreen = 0;
        int totalBlue = 0;
        final int SIZE = pixelsInformation.size();
        for (int element = 0; element < SIZE; element++) {
            int red = pixelsInformation.get(element).getColor().getRed();
            totalRed += red;
            int green = pixelsInformation.get(element).getColor().getGreen();
            totalGreen += green;
            int blue = pixelsInformation.get(element).getColor().getBlue();
            totalBlue += blue;
        }
        int avgRed = totalRed / SIZE;
        int avgGreen = totalGreen / SIZE;
        int avgBlue = totalBlue / SIZE;
        Color sector = new Color(avgRed, avgGreen, avgBlue);
        return sector;
    }
}
