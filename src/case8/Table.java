package case8;

import java.util.ArrayList;
import java.util.HashMap;


public class Table
{
	private int totalOfPopulation;
	HashMap<Integer, Double> population;

	public Table() 
	{
		this.totalOfPopulation = 0;
		this.population = new HashMap<>();

	}
	public int getTotalOfPopulation() 
	{
		return totalOfPopulation;
	}
	public void setTotalOfPopulation(int pTotalOfPopulation) 
	{
		this.totalOfPopulation = pTotalOfPopulation;
	}

	public HashMap<Integer, Double> getPoblation() 
	{
		return population;
	}
	public void setPoblation(HashMap<Integer, Double> pPopulation) 
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
