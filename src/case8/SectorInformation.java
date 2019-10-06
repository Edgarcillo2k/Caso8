package case8;

import java.awt.Color;

public class SectorInformation 
{
	private int sector;
	private double blankPercentage;
	private Point initialPoint;
	private Point finalPoint;
	
	public SectorInformation(int pSector,PixelInformation[] pPixels,int pStart, int pEnd,Point pInitialPoint,Point pFinalPoint) 
	{
		this.sector = pSector;
		double blanks = 0;
		for(int currentPixel = pStart;currentPixel<pEnd;currentPixel++) {
			if(pPixels[currentPixel].getColor().equals(Color.white)) {
				blanks++;
			}
		}
		this.blankPercentage = blanks/(pEnd-pStart);
		this.initialPoint = pInitialPoint;
		this.finalPoint = pFinalPoint;
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
	public int getSector() 
	{
		return sector;
	}
	public void setSector(int pSector) 
	{
		this.sector = pSector;
	}
	public double getBlankPercentage() 
	{
		return blankPercentage;
	}
	public void setBlankPercentage(double pBlankPercentage) 
	{
		this.blankPercentage = pBlankPercentage;
	}
}
