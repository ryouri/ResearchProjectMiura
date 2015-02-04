package researchproject.inputmodule.logical.fourvar;

import java.util.Arrays;

public class Four {
	public Four(String[] f,String sai)
	{
		String s1="",s2="",s3="",s4="";
		int a,b,c,d;
		
		s1=sai.substring(0,1);
		s2=sai.substring(1,2);
		s3=sai.substring(2,3);
		s4=sai.substring(3,4);
		
		a=Integer.valueOf(s1);
		b=Integer.valueOf(s2);
		c=Integer.valueOf(s3);
		d=Integer.valueOf(s4);
		
		int[] y={a,b,c,d};
		Arrays.sort(y);
		a=y[0];
		b=y[1];
		c=y[2];
		d=y[3];
		
		sai=a%2+","+b%2+","+c%2+","+d%2+",";
		SetF setf=new SetF(f,sai);
		
	}

}
