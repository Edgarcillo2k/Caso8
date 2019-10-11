package case8;

public class Individual<T>
{
	private T object;
	private int genotype;
	private boolean fit;
	public Individual(T pObject,int pGenotype)
	{
		object = pObject;
		genotype = pGenotype;
		fit = false;
	}
	public boolean isFit() 
	{
		return fit;
	}
	public void setFit(boolean pFit) 
	{
		this.fit = pFit;
	}
	public T getObject() 
	{
		return object;
	}
	public void setObject(T pObject) 
	{
		this.object = pObject;
	}
	public int getGenotype() 
	{
		return genotype;
	}
	public void setGenotype(int pGenotype) 
	{
		this.genotype = pGenotype;
	}
}
