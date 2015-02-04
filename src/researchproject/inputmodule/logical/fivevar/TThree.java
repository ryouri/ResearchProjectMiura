package researchproject.inputmodule.logical.fivevar;

import java.util.Arrays;

public class TThree {
	public TThree(String[] g,String sai)
	{
		String s1="",s2="",s3="";
		int a,b,c;
		int i,j;
		int t;
		
		s1=sai.substring(0,1);
		s2=sai.substring(1,2);
		s3=sai.substring(2,3);
		
		a=Integer.valueOf(s1);
		b=Integer.valueOf(s2);
		c=Integer.valueOf(s3);
		
		int[] r={a,b,c};
		for(t=0;t<3;t++)
		{
			if(r[t]==0)
			{
				r[t]=r[t]+10;
			}
		}
		Arrays.sort(r);
		a=r[0];
		b=r[1];
		c=r[2];
		
		if(a>=1&&a<=2)
		{
			if(b>=3&&b<=4)
			{
				if(c>=5&&c<=6)
				{
					for(i=0;i<2;i++)
						for(j=0;j<2;j++)
						{
							sai=a%2+","+b%2+","+c%2+","+i+","+j+",";
							SetG setg=new SetG(g,sai);
						}
				}//ABC
				else if(c>=7&&c<=8)
				{

					for(i=0;i<2;i++)
						for(j=0;j<2;j++)
						{
							sai=a%2+","+b%2+","+i+","+c%2+","+j+",";
							SetG setg=new SetG(g,sai);
						}
				}//ABD
				else if(c==0||c==9)
				{
					for(i=0;i<2;i++)
						for(j=0;j<2;j++)
						{
							sai=a%2+","+b%2+","+i+","+j+","+c%2+",";
							SetG setg=new SetG(g,sai);
						}
				}//ABE
			}
			else if(b>=5&&b<=6)
			{
				if(c>=7&&c<=8)
				{
					for(i=0;i<2;i++)
						for(j=0;j<2;j++)
						{
							sai=a%2+","+i+","+b%2+","+c%2+","+j+",";
							SetG setg=new SetG(g,sai);
						}
				}//ACD
				else if(c==0||c==9)
				{
					for(i=0;i<2;i++)
						for(j=0;j<2;j++)
						{
							sai=a%2+","+i+","+b%2+","+j+","+c%2+",";
							SetG setg=new SetG(g,sai);
						}
				}//ACE
			}
			else if(b>=7&&b<=8)
			{
				for(i=0;i<2;i++)
					for(j=0;j<2;j++)
					{
						sai=a%2+","+i+","+j+","+b%2+","+c%2+",";
						SetG setg=new SetG(g,sai);
					}
			}//ADE
		}
		else if(a>=3&&a<=4)
		{
			if(b>=5&&b<=6)
			{
				if(c>=7&&c<=8)
				{
					for(i=0;i<2;i++)
						for(j=0;j<2;j++)
						{
							sai=i+","+a%2+","+b%2+","+c%2+","+j+",";
							SetG setg=new SetG(g,sai);
						}
				}//BCD
				else if(c==0||c==9)
				{
					for(i=0;i<2;i++)
						for(j=0;j<2;j++)
						{
							sai=i+","+a%2+","+b%2+","+j+","+c%2+",";
							SetG setg=new SetG(g,sai);
						}
				}//BCE
			}
			else if(b>=7&&b<=8)
			{
				for(i=0;i<2;i++)
					for(j=0;j<2;j++)
					{
						sai=i+","+a%2+","+j+","+b%2+","+c%2+",";
						SetG setg=new SetG(g,sai);
					}
			}//BDE
		}
		else if(a>=5&&a<=6)
		{
			for(i=0;i<2;i++)
				for(j=0;j<2;j++)
				{
					sai=i+","+j+","+a%2+","+b%2+","+c%2+",";
					SetG setg=new SetG(g,sai);
				}
		}//CDE
	}

}
