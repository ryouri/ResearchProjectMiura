package researchproject.inputmodule.logical.fivevar;

import java.util.Arrays;

import researchproject.inputmodule.logical.fourvar.SetF;

public class FFive {
	public FFive(String[] g,String sai)
	{
		String s1="",s2="",s3="",s4="",s5="";
		int a,b,c,d,e;
		int t;
		
		s1=sai.substring(0,1);
		s2=sai.substring(1,2);
		s3=sai.substring(2,3);
		s4=sai.substring(3,4);
		s5=sai.substring(4,5);
		
		a=Integer.valueOf(s1);
		b=Integer.valueOf(s2);
		c=Integer.valueOf(s3);
		d=Integer.valueOf(s4);
		e=Integer.valueOf(s5);
		
		int[] r={a,b,c,d,e};
		for(t=0;t<5;t++)
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
		e=r[4];
		
		sai=a%2+","+b%2+","+c%2+","+d%2+","+e%2+",";
		SetG setg=new SetG(g,sai);
	}
}
