package case8;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GeneticAlgorithm 
{
	private Table table;
	private HashMap<Integer,Integer> populationQuantityOfTable;
	private HashMap<Integer,Double> populationPercentageOfTable;
	private HashMap<Integer,ArrayList<Individual<PixelInformation>>> population;
	private final int GENOTYPE_LIMIT = (int)Math.pow(2,16);
	private Color avgColorOfSectors;
	public JFrame pruebaFrame;
	public JPanel panel;
	public Graphics g;
	public int poblacionTotal;
	public int fitsTotal;
	public GeneticAlgorithm(Table pTable)
	{
		pruebaFrame = new JFrame();
		pruebaFrame.setVisible(true);
        pruebaFrame.setBounds(0, 0, 1100, 1100);
        panel = new JPanel();
        panel.setVisible(true);
        panel.setBounds(0, 0, 1024, 1024);
        pruebaFrame.add(panel);
        g = panel.getGraphics();
        poblacionTotal = 0;
        
		this.table = pTable;
		this.population = new HashMap<Integer,ArrayList<Individual<PixelInformation>>>();
		this.populationQuantityOfTable = new HashMap<Integer,Integer>(table.getPopulation().size());
		this.populationPercentageOfTable = new HashMap<Integer,Double>(table.getPopulation().size());
		for(int sector = 0;sector<table.getPopulation().size();sector++) {
			this.populationQuantityOfTable.put(table.getPopulation().get(sector).getAtributte().getSector(),0);
			this.populationPercentageOfTable.put(table.getPopulation().get(sector).getAtributte().getSector(),0.0);
			this.population.put(table.getPopulation().get(sector).getAtributte().getSector(),new ArrayList<Individual<PixelInformation>>());
		}
		this.avgColorOfSectors = avgColorOfSectors();
	}
	public Table getTable() 
	{
		return table;
	}
	public void setTable(Table pTable) 
	{
		this.table = pTable;
	}
	public void createPopulation(int pNumberOfIndividuals) 
	{
		for(int individual = 0;individual<pNumberOfIndividuals;individual++) {
			poblacionTotal++;
			int genotype = (int)(Math.random()*GENOTYPE_LIMIT);
			Sector currentSector = table.getPopulationArray()[genotype].getAtributte();
			ArrayList<Individual<PixelInformation>> sectorInformation = population.get(currentSector.getSector());
			sectorInformation.add(new Individual<PixelInformation>(new PixelInformation(currentSector.getRandomPoint(),currentSector.getSector(),currentSector.getColor()),genotype));
			populationQuantityOfTable.put(currentSector.getSector(),populationQuantityOfTable.get(currentSector.getSector())+1);
			populationPercentageOfTable.put(currentSector.getSector(),populationQuantityOfTable.get(currentSector.getSector())/(double)population.size());
			g.setColor(sectorInformation.get(sectorInformation.size()-1).getObject().getColor());
	        g.fillOval(sectorInformation.get(sectorInformation.size()-1).getObject().getPoint().getX(), sectorInformation.get(sectorInformation.size()-1).getObject().getPoint().getY(), 5, 5);
		}
	}
	public HashMap<Integer,ArrayList<Individual<PixelInformation>>> fitnessFunction()
	{
		fitsTotal = 0;
		HashMap<Integer,ArrayList<Individual<PixelInformation>>> fits = new HashMap<Integer,ArrayList<Individual<PixelInformation>>>();
		for(int sector = 0;sector<table.getPopulation().size();sector++) {
			AttributePercentage currentSector = table.getPopulation().get(sector);
			fits.put(currentSector.getAtributte().getSector(),new ArrayList<Individual<PixelInformation>>());
			for (Map.Entry<Integer, ArrayList<Individual<PixelInformation>>> entry : population.entrySet()) {
			    ArrayList<Individual<PixelInformation>> value = entry.getValue();
			    if(value.size()>0) {
			    	Individual<PixelInformation> currentIndividual = value.get(0);
					double percentageOfSimilarity = percentageOfEquality(currentIndividual.getObject().getColor(),currentSector.getAtributte().getColor());
					//System.out.println(percentageOfSimilarity);
					if(percentageOfSimilarity<50) {
						ArrayList<Individual<PixelInformation>> currentFitSector = fits.get(currentSector.getAtributte().getSector());
						currentFitSector.add(currentIndividual);
						fitsTotal++;						
					}
				}
			}
		}
		return fits;
	}
	public Color avgColorOfSectors() {
        int totalRed = 0;
        int totalGreen = 0;
        int totalBlue = 0;
        final int SIZE = table.getPopulation().size();
        for (int element = 0; element < SIZE; element++) {
            int red = table.getPopulation().get(element).getAtributte().getColor().getRed();
            totalRed += red;
            int green = table.getPopulation().get(element).getAtributte().getColor().getGreen();
            totalGreen += green;
            int blue = table.getPopulation().get(element).getAtributte().getColor().getBlue();
            totalBlue += blue;
        }
        int avgRed = totalRed / SIZE;
        int avgGreen = totalGreen / SIZE;
        int avgBlue = totalBlue / SIZE;
        Color sector = new Color(avgRed, avgGreen, avgBlue);
        return sector;
    }
	public void cross(Individual<PixelInformation> pIndividual1,Individual<PixelInformation> pIndividual2)
	{	final String WORD_ZEROS = "0000000000000000";
		String[] chromosomes = {Integer.toBinaryString(pIndividual1.getGenotype()),Integer.toBinaryString(pIndividual2.getGenotype())};
		chromosomes[0] = (WORD_ZEROS + chromosomes[0]).substring(chromosomes[0].length());
		chromosomes[1] = (WORD_ZEROS + chromosomes[1]).substring(chromosomes[1].length());
		String childChromosome = "";
		for(int currentBit = 0;currentBit<WORD_ZEROS.length();currentBit++) {
			int currentChromosome = (int)(Math.random()*100)%2;
			childChromosome += chromosomes[currentChromosome].charAt(currentBit);
		}		
		int random = (int)(Math.random()*10);
		if(random>=7) {
			childChromosome = mutate(childChromosome);
		}
		int childGenotype = Integer.parseInt(childChromosome, 2);
		Sector currentSector = table.getPopulationArray()[childGenotype].getAtributte();	
		//System.out.println(childGenotype + "," + pIndividual1.getGenotype() + "," + pIndividual2.getGenotype());
		ArrayList<Individual<PixelInformation>> sectorInformation = population.get(currentSector.getSector());
		sectorInformation.add(new Individual<PixelInformation>(new PixelInformation(currentSector.getRandomPoint(),currentSector.getSector(),currentSector.getColor()),childGenotype));
		populationQuantityOfTable.put(currentSector.getSector(),populationQuantityOfTable.get(currentSector.getSector())+1);
		populationPercentageOfTable.put(currentSector.getSector(),populationQuantityOfTable.get(currentSector.getSector())/(double)population.size());
		poblacionTotal++;
		g.setColor(sectorInformation.get(sectorInformation.size()-1).getObject().getColor());
        g.fillOval(sectorInformation.get(sectorInformation.size()-1).getObject().getPoint().getX(), sectorInformation.get(sectorInformation.size()-1).getObject().getPoint().getY(), 5, 5);
	}
	public String mutate(String pGenotype) 
	{
		int random = (int)Math.random()*pGenotype.length();
		pGenotype = pGenotype.substring(0, random) + pGenotype.charAt(random) == "1"?"0":"1" + pGenotype.substring(random + 1); 
		return pGenotype;
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
	public void run(int pGenerations)
	{
		for(int currentGeneration = 1;currentGeneration<pGenerations;currentGeneration++) {
			//if(population.size()>=500000) {
				//break;
			//}
			HashMap<Integer,ArrayList<Individual<PixelInformation>>> fits = fitnessFunction();
			System.out.println("Gen:"+currentGeneration + ",Fits:" + fitsTotal + ",Population:" + poblacionTotal+",Sectors:"+table.getPopulation().size());
			for (Map.Entry<Integer,ArrayList<Individual<PixelInformation>>> entry : fits.entrySet()) {	
				ArrayList<Individual<PixelInformation>> currentFitSector = entry.getValue();
				while(currentFitSector.size()>1) {
					int random1 = (int)Math.random()*fits.size();
					Individual<PixelInformation> individual1 = currentFitSector.get(random1);
					currentFitSector.remove(random1);
					int random2 = (int)Math.random()*fits.size();
					Individual<PixelInformation> individual2 = currentFitSector.get(random2);
					currentFitSector.remove(random2);
					for(int children = 0;children<2;children++) {
						cross(individual1,individual2);
					}
				}
				//if(population.size()>=500000) {
					//break;
				//}
			}
			fitsTotal = 0;
		}
		/*
		for (Map.Entry<Integer, Double> entry : populationPercentageOfTable.entrySet()) {
			Integer key = entry.getKey();
		    Double value = entry.getValue();
		    for(int i = 0;i<table.getTotalOfPopulation();i++) {
		    	if(key == table.getPopulationArray()[i].getAtributte().getSector()) {
		    		System.out.println("key:" + key + " Valor:" + value + "," + table.getPopulationArray()[i].getPercentage());
		    		break;
		    	}
		    }
		}
		*/
		
	}
}
