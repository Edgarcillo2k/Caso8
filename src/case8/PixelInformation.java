package case8;

import java.awt.Color;

public class PixelInformation 
{
	private int x;
	private int y;
	private int sector;
	private Color color;
	public PixelInformation(int pX,int pY,int pSector,Color pColor)
	{
		this.x = pX;
		this.y = pY;
		this.sector = pSector;
		this.color = pColor;
	}
	public int getX() 
	{
		return x;
	}
	public void setX(int pX) 
	{
		this.x = pX;
	}
	public int getY() 
	{
		return y;
	}
	public void setY(int pY) 
	{
		this.y = pY;
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
		return "x: " + x + " y: " + y + " sector: " + sector + " color: " + color.toString();
	}
}
