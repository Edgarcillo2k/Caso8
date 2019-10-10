package case8;

public class Individual<T>
{
	private T object;
	private short genotype;
	public Individual(T pObject,short pGenotype)
	{
		object = pObject;
		genotype = pGenotype;
	}
	public T getObject() 
	{
		return object;
	}
	public void setObject(T pObject) 
	{
		this.object = pObject;
	}
	public short getGenotype() 
	{
		return genotype;
	}
	public void setGenotype(short pGenotype) 
	{
		this.genotype = pGenotype;
	}
}
