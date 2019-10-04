package case8;

import java.awt.Color;

public class PixelInformation 
{
	private Point point;
	private int sector;
	private Color color;
	
	public PixelInformation(int pX,int pY,int pSector,Color pColor)
	{
		this.point = new Point(pX,pY);
		this.sector = pSector;
		this.color = pColor;
	}
	public PixelInformation(Point pPoint,int pSector,Color pColor)
	{
		this.point = pPoint;
		this.sector = pSector;
		this.color = pColor;
	}
	public Point getPoint()
	{
		return point;
	}
	public void setPoint(Point pPoint)
	{
		this.point = pPoint;
	}
	public void setPoint(int pX,int pY)
	{
		this.point = new Point(pX,pY);
	}
	public int getSector() 
	{
		return sector;
	}
	public void setSector(int pSector) 
	{
		this.sector = pSector;
	}
	public Color getColor() 
	{
		return color;
	}
	public void setColor(Color pColor) 
	{
		this.color = pColor;
	}
	public String toString()
	{
		return "Point: " + point.toString() + " sector: " + sector + " color: " + color.toString();
	}
}
