package case8;

import java.util.ArrayList;

public class GeneticAlgorithm 
{
	private ArrayList<Polygon> population;

	public GeneticAlgorithm() 
	{
		this.population = new ArrayList<Polygon>();
	}
	public ArrayList<Polygon> getPopulation() {
		return population;
	}
	public void setPopulation(ArrayList<Polygon> population) {
		this.population = population;
	}
	public void fitnessFunction()
	{
		//haga algo
	}
	public void cross()
	{
		/*
		String[] chromosomes = {Integer.toBinaryString(puntoPoligono),Integer.toBinaryString(puntoPoligono2)};
		int[] chromosomesLength = {chromosomes[0].length(), chromosomes[1].length()};
		bool smallest =  chromosomesLength[0]>chromosomesLength[1];
		String childChromosome = "";
		int half = chromosomes[(!smallest)].length/2;
		int i = 0;
		half = half>chromosomesLength[smallest]?chromosomesLength[smallest]:half;
		for(int j = 0;j<2;j++){
			for(;i<half-1;i++){
				childChromosome += chromosomes[smallest][i];
			}
			half = childChromosome.length;
			smallest = !smallest;
		}
		int childPoint = Integer.parseInt(childChromosome, 2);
        */
	}
	public void run()
	{
		fitnessFunction();
		cross();
	}
}
