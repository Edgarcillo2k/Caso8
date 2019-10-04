package case8;

import java.awt.Color;

public class SectorInformation 
{
	private int sector;
	private double blankPercentage;
	
	public SectorInformation(int pSector,PixelInformation[] pPixels,int pStart, int pEnd) 
	{
		this.sector = pSector;
		double blanks = 0;
		for(int currentPixel = pStart;currentPixel<pEnd;currentPixel++) {
			if(pPixels[currentPixel].getColor().equals(Color.white)) {
				blanks++;
			}
		}
		this.blankPercentage = blanks/(pEnd-pStart);
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
