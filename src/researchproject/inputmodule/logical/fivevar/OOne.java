package researchproject.inputmodule.logical.fivevar;

public class OOne {
	public OOne(String[] g,String sai)
	{
		String s1="";
		int a;
		int i,j,k,l;
		
		s1=sai.substring(0,1);
		
		a=Integer.valueOf(s1);
		
		if(a>=1&&a<=2)
		{
			for(i=0;i<2;i++)
				for(j=0;j<2;j++)
					for(k=0;k<2;k++)
						for(l=0;l<2;l++)
						{
							sai=a%2+","+i+","+j+","+k+","+l+",";
							SetG setg=new SetG(g,sai);
						}
		}//A
		else if(a>=3&&a<=4)
		{
			for(i=0;i<2;i++)
				for(j=0;j<2;j++)
					for(k=0;k<2;k++)
						for(l=0;l<2;l++)
						{
							sai=i+","+a%2+","+j+","+k+","+l+",";
							SetG setg=new SetG(g,sai);
						}
		}//B
		else if(a>=5&&a<=6)
		{
			for(i=0;i<2;i++)
				for(j=0;j<2;j++)
					for(k=0;k<2;k++)
						for(l=0;l<2;l++)
						{
							sai=i+","+j+","+a%2+","+k+","+l+",";
							SetG setg=new SetG(g,sai);
						}
		}//C
		else if(a>=7&&a<=8)
		{
			for(i=0;i<2;i++)
				for(j=0;j<2;j++)
					for(k=0;k<2;k++)
						for(l=0;l<2;l++)
						{
							sai=i+","+j+","+k+","+a%2+","+l+",";
							SetG setg=new SetG(g,sai);
						}
		}//D
		else if(a==0||a==9)
		{
			for(i=0;i<2;i++)
				for(j=0;j<2;j++)
					for(k=0;k<2;k++)
						for(l=0;l<2;l++)
						{
							sai=i+","+j+","+k+","+l+","+a%2+",";
							SetG setg=new SetG(g,sai);
						}
		}//E
	}

}
