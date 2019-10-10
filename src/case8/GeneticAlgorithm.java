package case8;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

public class GeneticAlgorithm 
{
	private Table<Sector> table;
	private ArrayList<Individual<PixelInformation>> population;
	private Integer[] populationQuantityOfTable;
	private Double[] populationPercentageOfTable;
	public GeneticAlgorithm(Table<Sector> pTable)
	{
		this.table = pTable;
		this.population = new ArrayList<Individual<PixelInformation>>();
		this.populationQuantityOfTable = new Integer[table.getPoblation().size()];
		this.populationPercentageOfTable = new Double[table.getPoblation().size()];
		Arrays.fill(populationQuantityOfTable,0);
		Arrays.fill(populationPercentageOfTable,0.0);
	}
	public Table<Sector> getTable() 
	{
		return table;
	}
	public void setTable(Table<Sector> pTable) 
	{
		this.table = pTable;
	}
	public ArrayList<Individual<PixelInformation>> getPopulation() 
	{
		return population;
	}
	public void setPopulation(ArrayList<Individual<PixelInformation>> pPopulation) 
	{
		this.population = pPopulation;
	}
	public void createPopulation(int pNumberOfIndividuals) 
	{
		for(int individual = 0;individual<pNumberOfIndividuals;individual++) {
			short genotype = (short)(Math.random()*Short.MAX_VALUE);
			for(int sector = 0;sector<table.getPoblation().size();sector++) {
				AttributePercentage<Sector> currentSectorAttribute = table.getPoblation().get(sector);
				Short[] currentGenotype = currentSectorAttribute.getGenotype();
				if(genotype >= currentGenotype[0] && genotype<currentGenotype[1]) {					
					Sector currentSector = currentSectorAttribute.getAtributte();
					population.add(new Individual<PixelInformation>(new PixelInformation(currentSector.getRandomPoint(),currentSector.getSector(),currentSector.getColor()),genotype));
					populationQuantityOfTable[sector]++;
					populationPercentageOfTable[sector] = populationQuantityOfTable[sector]/(double)population.size();
					break;
				}
			}
		}
	}
	public ArrayList<Individual<PixelInformation>> fitnessFunction()
	{
		ArrayList<Individual<PixelInformation>> fits = new ArrayList<Individual<PixelInformation>>();
		for(int individual = 0;individual<population.size();individual++) {
			for(int sector = 0;sector<table.getPoblation().size();sector++) {
				Individual<PixelInformation> currentIndividual = population.get(individual);
				AttributePercentage<Sector> currentSectorAttribute = table.getPoblation().get(sector);
				Sector currentSector = currentSectorAttribute.getAtributte();
				double percentageOfSimilarity = percentageOfEquality(currentIndividual.getObject().getColor(),currentSector.getColor());
				if(percentageOfSimilarity == 100 && populationPercentageOfTable[sector]<currentSectorAttribute.getPercentage()) {
					fits.add(currentIndividual);
				}
				else {
					mutate(currentIndividual,currentSector);
				}
			}
		}
		return fits;
	}
	public void mutate(Individual<PixelInformation> pIndividual,Sector currentSector) 
	{
		//hacerle not a un bit random segun quien sabe que xd
	}
	public double[] rgbToLab(int R, int G, int B) {

	    double r, g, b, X, Y, Z, xr, yr, zr;

	    // D65/2°
	    double Xr = 95.047;  
	    double Yr = 100.0;
	    double Zr = 108.883;


	    // --------- RGB to XYZ ---------//

	    r = R/255.0;
	    g = G/255.0;
	    b = B/255.0;

	    if (r > 0.04045)
	        r = Math.pow((r+0.055)/1.055,2.4);
	    else
	        r = r/12.92;

	    if (g > 0.04045)
	        g = Math.pow((g+0.055)/1.055,2.4);
	    else
	        g = g/12.92;

	    if (b > 0.04045)
	        b = Math.pow((b+0.055)/1.055,2.4);
	    else
	        b = b/12.92 ;

	    r*=100;
	    g*=100;
	    b*=100;

	    X =  0.4124*r + 0.3576*g + 0.1805*b;
	    Y =  0.2126*r + 0.7152*g + 0.0722*b;
	    Z =  0.0193*r + 0.1192*g + 0.9505*b;


	    // --------- XYZ to Lab --------- //

	    xr = X/Xr;
	    yr = Y/Yr;
	    zr = Z/Zr;

	    if ( xr > 0.008856 )
	        xr =  (float) Math.pow(xr, 1/3.);
	    else
	        xr = (float) ((7.787 * xr) + 16 / 116.0);

	    if ( yr > 0.008856 )
	        yr =  (float) Math.pow(yr, 1/3.);
	    else
	        yr = (float) ((7.787 * yr) + 16 / 116.0);

	    if ( zr > 0.008856 )
	        zr =  (float) Math.pow(zr, 1/3.);
	    else
	        zr = (float) ((7.787 * zr) + 16 / 116.0);


	    double[] lab = new double[3];

	    lab[0] = (116*yr)-16;
	    lab[1] = 500*(xr-yr); 
	    lab[2] = 200*(yr-zr); 

	    return lab;

	}
	public double percentageOfEquality(Color pColor1, Color pColor2)
	{
		double[] color1 = rgbToLab(pColor1.getRed(),pColor1.getGreen(),pColor1.getBlue());
		double[] color2 = rgbToLab(pColor2.getRed(),pColor2.getGreen(),pColor2.getBlue());
		return 100 - Math.sqrt(Math.pow((color1[0] - color2[0]),2) + Math.pow((color1[1] - color2[1]),2) + Math.pow((color1[2] - color2[2]),2));
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
