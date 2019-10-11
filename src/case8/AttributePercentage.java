package case8;

public class AttributePercentage 
{
	private Sector atributte;
	private double percentage;
	private int totalOfPoblation;
	private int volumeOfPoblation;
	private Integer genotype[];
	private final int GENOTYPE_LIMIT = (int)Math.pow(2,16);
	public AttributePercentage(Sector pAttribute,int pTotalOfPoblation,int pVolumeOfPoblation)
	{
		this.atributte = pAttribute;
		this.totalOfPoblation = pTotalOfPoblation;
		this.volumeOfPoblation = pVolumeOfPoblation;
		this.percentage = pVolumeOfPoblation/(double)(pTotalOfPoblation);
		this.genotype = new Integer[2];
	}
	public int getGENOTYPE_LIMIT() 
	{
		return GENOTYPE_LIMIT;
	}
	public Sector getAtributte() 
	{
		return atributte;
	}
	public int getTotalOfPoblation() 
	{
		return totalOfPoblation;
	}
	public void setTotalOfPoblation(int pTotalOfPoblation) 
	{
		this.totalOfPoblation = pTotalOfPoblation;
	}
	public int getVolumeOfPoblation() {
		return volumeOfPoblation;
	}
	public void setVolumeOfPoblation(int pVolumeOfPoblation) 
	{
		this.volumeOfPoblation = pVolumeOfPoblation;
	}
	public void setAtributte(Sector pAtributte) 
	{
		this.atributte = pAtributte;
	}
	public double getPercentage() 
	{
		return percentage;
	}
	public void setPercentage(double pPercentage) 
	{
		this.percentage = pPercentage;
	}
	public void calculatePercentage()
	{
		this.percentage = volumeOfPoblation/(double)(totalOfPoblation);
	}
	public Integer[] getGenotype() {
		return genotype;
	}
	public void setGenotype(Integer[] pGenotype) 
	{
		this.genotype = pGenotype;
	}
	public void calculateGenotype(int pInitialRank)
	{
		this.genotype[0] = pInitialRank;
		this.genotype[1] = (int)(genotype[0] + percentage*GENOTYPE_LIMIT);
	}
	public void setFinalRank(int pFinalRank)
	{
		this.genotype[1] = pFinalRank;
	}
}
