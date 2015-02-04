package fourvariable;

import java.util.Arrays;

public class Two {
	public Two(String[] f,String sai)
	{
		String s1="",s2="";
		int a,b;
		int i,j;

		s1=sai.substring(0,1);
		s2=sai.substring(1,2);
		
		a=Integer.valueOf(s1);
		b=Integer.valueOf(s2);
		
		int[] y={a,b};
		Arrays.sort(y);
		a=y[0];
		b=y[1];
		
		if(a>=1&&a<=2)
		{
			if(b>=3&&b<=4)
			{
				for(i=0;i<2;i++)
					for(j=0;j<2;j++)
					{
						sai=a%2+","+b%2+","+i+","+j+",";
						SetF setf=new SetF(f,sai);				        
					}
			}//AB
			else if(b>=5&&b<=6)
			{
				for(i=0;i<2;i++)
					for(j=0;j<2;j++)
					{
						sai=a%2+","+i+","+b%2+","+j+",";
						SetF setf=new SetF(f,sai);				        
					}
			}//AC
			else if(b>=7&&b<=8)
			{
				for(i=0;i<2;i++)
					for(j=0;j<2;j++)
					{
						sai=a%2+","+i+","+j+","+b%2+",";
						SetF setf=new SetF(f,sai);				        
					}
			}//AD
		}
		else if(a>=3&&a<=4)
		{
			if(b>=5&&b<=6)
			{
				for(i=0;i<2;i++)
					for(j=0;j<2;j++)
					{
						sai=i+","+a%2+","+b%2+","+j+",";
						SetF setf=new SetF(f,sai);     
					}
			}//BC
			else if(b>=7&&b<=8)
			{
				for(i=0;i<2;i++)
					for(j=0;j<2;j++)
					{
						sai=i+","+a%2+","+j+","+b%2+",";
						SetF setf=new SetF(f,sai);				        
					}
			}//BD
		}
		else if(a>=5&&a<=6)
		{
			for(i=0;i<2;i++)
				for(j=0;j<2;j++)
				{
					sai=i+","+j+","+a%2+","+b%2+",";
					SetF setf=new SetF(f,sai);
				}
		}//CD
	}

}
