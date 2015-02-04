package researchproject.inputmodule.logical.fourvar;

import java.util.Arrays;

public class Three {
	public Three(String[] f,String sai)
	{
		String s1="",s2="",s3="";
		int a,b,c;
		int i;
		
		s1=sai.substring(0,1);
		s2=sai.substring(1,2);
		s3=sai.substring(2,3);
		
		a=Integer.valueOf(s1);
		b=Integer.valueOf(s2);
		c=Integer.valueOf(s3);
		
		int[] y={a,b,c};
		Arrays.sort(y);
		a=y[0];
		b=y[1];
		c=y[2];
		
		if(a>=1&&a<=2)
		{
			if(b>=3&&b<=4)
			{
				if(c>=5&&c<=6)
				{
					for(i=0;i<2;i++)
					{
						sai=a%2+","+b%2+","+c%2+","+i+",";
						SetF setf=new SetF(f,sai);
					}
				}//ABC
				else if(c>=7&&c<=8)
				{
					for(i=0;i<2;i++)
					{
						sai=a%2+","+b%2+","+i+","+c%2+",";
						SetF setf=new SetF(f,sai);
					}
				}//ABD
			}
			else if(b>=5&&b<=6)
			{
				for(i=0;i<2;i++)
				{
					sai=a%2+","+i+","+b%2+","+c%2+",";
					SetF setf=new SetF(f,sai);
				}
			}//ACD
		}
		else
		{
			for(i=0;i<2;i++)
			{
				sai=i+","+a%2+","+b%2+","+c%2+",";
				SetF setf=new SetF(f,sai);
			}
		}//BCD

	}

}
