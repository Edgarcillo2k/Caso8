package case8;

import java.util.ArrayList;

public class Table
{
	private int totalOfPopulation;
	ArrayList<AttributePercentage> population;
	AttributePercentage[] populationArray;
	public Table() 
	{
		this.totalOfPopulation = 0;
		this.population = new ArrayList<AttributePercentage>();
	}
	public int getTotalOfPopulation() 
	{
		return totalOfPopulation;
	}
	public void setTotalOfPopulation(int pTotalOfPopulation) 
	{
		this.totalOfPopulation = pTotalOfPopulation;
	}
	public ArrayList<AttributePercentage> getPopulation() 
	{
		return population;
	}
	public void setPopulation(ArrayList<AttributePercentage> pPopulation) 
	{
		this.population = pPopulation;
	}
	public AttributePercentage[] getPopulationArray() 
	{
		return populationArray;
	}
	public void setPopulationArray(AttributePercentage[] pPopulation) 
	{
		this.populationArray = pPopulation;
	}
}
