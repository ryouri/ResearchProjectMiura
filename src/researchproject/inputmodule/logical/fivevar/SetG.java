package researchproject.inputmodule.logical.fivevar;

public class SetG {
	public SetG(String[] g,String sai)
	{
		int i;
		for(i=0;i<32;i++)
		{
			if(g[i].substring(0,10).equals(sai))
			{
				g[i]=sai+"1";
			}
		}
	}
}
