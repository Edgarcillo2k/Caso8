package case8;

public class AttributePercentage<T> 
{
	private T atributte;
	private double percentage;
	private int totalOfPoblation;
	private int volumeOfPoblation;
	private Short genotype[];
	private final short GENOTYPE_LIMIT = Short.MAX_VALUE;
	public AttributePercentage(T pAttribute,int pTotalOfPoblation,int pVolumeOfPoblation)
	{
		this.atributte = pAttribute;
		this.totalOfPoblation = pTotalOfPoblation;
		this.volumeOfPoblation = pVolumeOfPoblation;
		this.percentage = pVolumeOfPoblation/(double)(pTotalOfPoblation);
		this.genotype = new Short[2];
	}
	public int getGENOTYPE_LIMIT() 
	{
		return GENOTYPE_LIMIT;
	}
	public T getAtributte() 
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
	public void setAtributte(T pAtributte) 
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
	public Short[] getGenotype() {
		return genotype;
	}
	public void setGenotype(Short[] pGenotype) 
	{
		this.genotype = pGenotype;
	}
	public void calculateGenotype(int pInitialRank)
	{
		this.genotype[0] = (short)pInitialRank;
		this.genotype[1] = (short)(genotype[0] + percentage*GENOTYPE_LIMIT);
	}
	public void setFinalRank(short pFinalRank)
	{
		this.genotype[1] = pFinalRank;
	}
}
