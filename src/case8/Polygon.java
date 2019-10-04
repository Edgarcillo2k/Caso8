package case8;

import java.util.ArrayList;

public class Polygon 
{
	private ArrayList<Point> points;
	
	public Polygon()
	{
		this.points = new ArrayList<Point>();
	}
	public ArrayList<Point> getPoints() 
	{
		return points;
	}
	public void setPoints(ArrayList<Point> pPoints) 
	{
		this.points = pPoints;
	}
	public void addPoint(Point pPoint)
	{
		this.points.add(pPoint);
	}
	public void addPoint(int pX, int pY)
	{
		this.points.add(new Point(pX,pY));
	}
	public void setPoint(Point pPoint,int pIndex)
	{
		this.points.set(pIndex,pPoint);
	}
	public void setPoint(int pX, int pY,int pIndex)
	{
		this.points.add(pIndex,new Point(pX,pY));
	}
	public void removePoint(int pIndex)
	{
		this.points.remove(pIndex);
	}
	public Point getPoint(int pIndex)
	{
		return points.get(pIndex);
	}
	public int numberOfPoints()
	{
		return points.size();
	}
}
