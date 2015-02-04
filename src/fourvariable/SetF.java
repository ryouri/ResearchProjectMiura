package fourvariable;

public class SetF {
	public SetF(String[] f,String sai)
	{
		int i;
		for(i=0;i<16;i++)
		{
			if(f[i].substring(0,8).equals(sai))
			{
				f[i]=sai+"1";
			}
		}
	}

}
