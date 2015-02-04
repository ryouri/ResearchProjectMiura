package researchproject.inputmodule.logical.fivevar;

import java.util.Arrays;

public class TTwo {
	public TTwo(String[] g,String sai)
	{
		String s1="",s2="";
		int a,b;
		int i,j,k;
		int t;
		
		s1=sai.substring(0,1);
		s2=sai.substring(1,2);
		
		a=Integer.valueOf(s1);
		b=Integer.valueOf(s2);
		
		int[] r={a,b};
		for(t=0;t<2;t++)
		{
			if(r[t]==0)
			{
				r[t]=r[t]+10;
			}
		}
		Arrays.sort(r);
		a=r[0];
		b=r[1];
		
		if(a>=1&&a<=2)
		{
			if(b>=3&&b<=4)
			{
				for(i=0;i<2;i++)
					for(j=0;j<2;j++)
						for(k=0;k<2;k++)
						{
							sai=a%2+","+b%2+","+i+","+j+","+k+",";
							SetG setg=new SetG(g,sai);
						}
			}//AB
			else if(b>=5&&b<=6)
			{
				for(i=0;i<2;i++)
					for(j=0;j<2;j++)
						for(k=0;k<2;k++)
						{
							sai=a%2+","+i+","+b%2+","+j+","+k+",";
							SetG setg=new SetG(g,sai);
						}
			}//AC
			else if(b>=7&&b<=8)
			{
				for(i=0;i<2;i++)
					for(j=0;j<2;j++)
						for(k=0;k<2;k++)
						{
							sai=a%2+","+i+","+j+","+b%2+","+k+",";
							SetG setg=new SetG(g,sai);
						}
			}//AD
			else if(b==0||b<=9)
			{
				for(i=0;i<2;i++)
					for(j=0;j<2;j++)
						for(k=0;k<2;k++)
						{
							sai=a%2+","+i+","+j+","+k+","+b%2+",";
							SetG setg=new SetG(g,sai);
						}
			}//AE
		}
		else if(a>=3&&a<=4)
		{
			if(b>=5&&b<=6)
			{
				for(i=0;i<2;i++)
					for(j=0;j<2;j++)
						for(k=0;k<2;k++)
						{
							sai=i+","+a%2+","+b%2+","+j+","+k+",";
							SetG setg=new SetG(g,sai);
						}
			}//BC
			else if(b>=7&&b<=8)
			{
				for(i=0;i<2;i++)
					for(j=0;j<2;j++)
						for(k=0;k<2;k++)
						{
							sai=i+","+a%2+","+j+","+b%2+","+k+",";
							SetG setg=new SetG(g,sai);
						}
			}//BD
			else if(b==0||b<=9)
			{
				for(i=0;i<2;i++)
					for(j=0;j<2;j++)
						for(k=0;k<2;k++)
						{
							sai=i+","+a%2+","+j+","+k+","+b%2+",";
							SetG setg=new SetG(g,sai);
						}
			}//BE
		}
		else if(a>=5&&a<=6)
		{
			if(b>=7&&b<=8)
			{
				for(i=0;i<2;i++)
					for(j=0;j<2;j++)
						for(k=0;k<2;k++)
						{
							sai=i+","+j+","+a%2+","+b%2+","+k+",";
							SetG setg=new SetG(g,sai);
						}
			}//CD
			else if(b==0||b<=9)
			{
				for(i=0;i<2;i++)
					for(j=0;j<2;j++)
						for(k=0;k<2;k++)
						{
							sai=i+","+j+","+a%2+","+k+","+b%2+",";
							SetG setg=new SetG(g,sai);
						}
			}//CE
		}
		else if(a>=7&&a<=8)
		{
			for(i=0;i<2;i++)
				for(j=0;j<2;j++)
					for(k=0;k<2;k++)
					{
						sai=i+","+j+","+k+","+a%2+","+b%2+",";
						SetG setg=new SetG(g,sai);
					}
		}//DE
	}

}
