package case8;

public class Point 
{
	private int x;
	private int y;
	
	public Point(int pX,int pY)
	{
		this.x = pX;
		this.y = pY;
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
	public String toString()
	{
		return "x: " + x + " y: " + y;
	}
}
