package researchproject.inputmodule.logical.fivevar;

import java.util.Arrays;

public class FFour {
	public FFour(String[] g,String sai)
	{
		String s1="",s2="",s3="",s4="";
		int a,b,c,d;
		int i;
		int t;
		
		s1=sai.substring(0,1);
		s2=sai.substring(1,2);
		s3=sai.substring(2,3);
		s4=sai.substring(3,4);
		
		a=Integer.valueOf(s1);
		b=Integer.valueOf(s2);
		c=Integer.valueOf(s3);
		d=Integer.valueOf(s4);
		
		int[] r={a,b,c,d};
		for(t=0;t<4;t++)
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
		d=r[3];
		
		if(a>=1&&a<=2)
		{
			if(b>=3&&b<=4)
			{
				if(c>=5&&c<=6)
				{
					if(d>=7&&d<=8)
					{
						for(i=0;i<2;i++)
						{
							sai=a%2+","+b%2+","+c%2+","+d%2+","+i+",";
							SetG setg=new SetG(g,sai);
						}
					}//ABCD
					else if(d==0||d==9)
					{
						for(i=0;i<2;i++)
						{
							sai=a%2+","+b%2+","+c%2+","+i+","+d%2+",";
							SetG setg=new SetG(g,sai);
						}
					}//ABCE
				}
				else if(d>=7&&c<=8)
				{
					for(i=0;i<2;i++)
					{
						sai=a%2+","+b%2+","+i+","+c%2+","+d%2+",";
						SetG setg=new SetG(g,sai);
					}
				}//ABDE
			}
			else if(b>=5&&b<=6)
			{
				for(i=0;i<2;i++)
				{
					sai=a%2+","+i+","+b%2+","+c%2+","+d%2+",";
					SetG setg=new SetG(g,sai);
				}
			}//ACDE
		}
		else if(a>=3&&a<=4)
		{
			for(i=0;i<2;i++)
			{
				sai=i+","+a%2+","+b%2+","+c%2+","+d%2+",";
				SetG setg=new SetG(g,sai);
			}
		}//BCDE
	}
}
