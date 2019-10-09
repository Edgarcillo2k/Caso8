package case8;

import java.awt.Color;
import java.util.ArrayList;

public class Polygon 
{
	private ArrayList<PixelInformation> points;
	private int sector;
	private Color color;
	
	public Polygon(int pSector)
	{
		this.points = new ArrayList<PixelInformation>();
		this.sector = pSector;
		this.color = null;
	}
	public Polygon(int pSector,ArrayList<PixelInformation> pPoints)
	{
		this.points = pPoints;
		this.sector = pSector;
		this.color = getAVG(pPoints);
	}
	public Color getColor() 
	{
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
	public void setSector(int pSector) 
	{
		this.sector = pSector;
	}
	public ArrayList<PixelInformation> getPoints() 
	{
		return points;
	}
	public void setPoints(ArrayList<PixelInformation> pPoints) 
	{
		this.points = pPoints;
	}
	public PixelInformation getPoint(int pIndex)
	{
		return points.get(pIndex);
	}
	public int numberOfPoints()
	{
		return points.size();
	}
	public Color getAVG(ArrayList<PixelInformation> pointsPerSector) {
        int totalRed = 0;
        int totalGreen = 0;
        int totalBlue = 0;
        final int SIZE = pointsPerSector.size();
        for (int element = 0; element < SIZE; element++) {
            int red = pointsPerSector.get(element).getColor().getRed();
            totalRed += red;
            int green = pointsPerSector.get(element).getColor().getGreen();
            totalGreen += green;
            int blue = pointsPerSector.get(element).getColor().getBlue();
            totalBlue += blue;
        }
        int avgRed = totalRed / SIZE;
        int avgGreen = totalGreen / SIZE;
        int avgBlue = totalBlue / SIZE;
        Color sector = new Color(avgRed, avgGreen, avgBlue);
        return sector;
    }
}
