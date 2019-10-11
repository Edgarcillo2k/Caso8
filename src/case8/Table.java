package case8;

import java.util.ArrayList;
import java.util.HashMap;


public class Table<T>
{
	private int totalOfPopulation;
	HashMap<Integer, Double> population;

	public Table() 
	{
		this.totalOfPopulation = 0;
		this.population = new HashMap<>();

	}
	public int getTotalOfPoblation() 
	{
		return totalOfPopulation;
	}
	public void setTotalOfPoblation(int pTotalOfPopulation) 
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
}
