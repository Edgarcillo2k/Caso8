package case8;

import java.util.ArrayList;

public class GeneticAlgorithm 
{
	private Table<Sector> table;
	private ArrayList<PixelInformation> population;
	public GeneticAlgorithm(Table<Sector> pTable)
	{
		this.table = pTable;
		this.population = new ArrayList<PixelInformation>();
	}
	public Table<Sector> getTable() 
	{
		return table;
	}
	public void setTable(Table<Sector> pTable) 
	{
		this.table = pTable;
	}
	public ArrayList<PixelInformation> getPopulation() 
	{
		return population;
	}
	public void setPopulation(ArrayList<PixelInformation> pPopulation) 
	{
		this.population = pPopulation;
	}
	public void createPopulation(int pNumberOfIndividuals) 
	{
		for(int individual = 0;individual<pNumberOfIndividuals;individual++) {
			boolean flag = false;
			short genotype = (short)(Math.random()*Short.MAX_VALUE);
			for(int sector = 0;sector<table.getPoblation().size();sector++) {
				AttributePercentage<Sector> currentSectorAttribute = table.getPoblation().get(sector);
				Short[] currentGenotype = currentSectorAttribute.getGenotype();
				if(genotype >= currentGenotype[0] && genotype<currentGenotype[1]) {
					Sector currentSector = currentSectorAttribute.getAtributte();
					population.add(new PixelInformation(currentSector.getRandomPoint(),currentSector.getSector(),currentSector.getColor()));
					flag = true;
					break;
				}
			}
		}
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
		cross();
	}
}
