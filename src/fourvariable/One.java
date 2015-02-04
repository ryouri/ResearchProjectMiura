package fourvariable;

import java.util.Arrays;

public class One {
	public One(String[] f,String sai)
	{
		String s1="";
		int a;
		int i,j,k;
		
		s1=sai.substring(0,1);
		
		a=Integer.valueOf(s1);
		
		if(a>=1&&a<=2)
		{
			for(i=0;i<2;i++)
				for(j=0;j<2;j++)
					for(k=0;k<2;k++)
					{
						sai=a%2+","+i+","+j+","+k+",";
						SetF setf=new SetF(f,sai);
					}
		}//A
		else if(a>=3&&a<=4)
		{
			for(i=0;i<2;i++)
				for(j=0;j<2;j++)
					for(k=0;k<2;k++)
					{
						sai=i+","+a%2+","+j+","+k+",";
						SetF setf=new SetF(f,sai);
					}
		}//B
		else if(a>=5&&a<=6)
		{
			for(i=0;i<2;i++)
				for(j=0;j<2;j++)
					for(k=0;k<2;k++)
					{
						sai=i+","+j+","+a%2+","+k+",";
						SetF setf=new SetF(f,sai);
					}
		}//C
		else if(a>=7&&a<=8)
		{
			for(i=0;i<2;i++)
				for(j=0;j<2;j++)
					for(k=0;k<2;k++)
					{
						sai=i+","+j+","+k+","+a%2+",";
						SetF setf=new SetF(f,sai);
					}
		}//D
	}

}
