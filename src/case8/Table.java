package case8;

import java.util.ArrayList;

public class Table<T>
{
	private int totalOfPoblation;
	ArrayList<AttributePercentage<T>> poblation;
	public Table() 
	{
		this.totalOfPoblation = 0;
		this.poblation = new ArrayList<AttributePercentage<T>>();
	}
	public int getTotalOfPoblation() 
	{
		return totalOfPoblation;
	}
	public void setTotalOfPoblation(int pTotalOfPoblation) 
	{
		this.totalOfPoblation = pTotalOfPoblation;
	}
	public ArrayList<AttributePercentage<T>> getPoblation() 
	{
		return poblation;
	}
	public void setPoblation(ArrayList<AttributePercentage<T>> pPoblation) 
	{
		this.poblation = pPoblation;
	}
}
