package case8;

import java.util.ArrayList;

public class Table<T>
{
	private int totalOfPopulation;
	ArrayList<AttributePercentage<T>> population;
	public Table() 
	{
		this.totalOfPopulation = 0;
		this.population = new ArrayList<AttributePercentage<T>>();
	}
	public int getTotalOfPoblation() 
	{
		return totalOfPopulation;
	}
	public void setTotalOfPoblation(int pTotalOfPopulation) 
	{
		this.totalOfPopulation = pTotalOfPopulation;
	}
	public ArrayList<AttributePercentage<T>> getPoblation() 
	{
		return population;
	}
	public void setPoblation(ArrayList<AttributePercentage<T>> pPopulation) 
	{
		this.population = pPopulation;
	}
}
