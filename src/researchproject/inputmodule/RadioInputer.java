package researchproject.inputmodule;
import java.io.PrintWriter;

import processing.core.PApplet;
import processing.core.PFont;
import researchproject.drawmodule.DrawManager;

public class RadioInputer extends PApplet {

	StartInput startInput;
	public void setStartInput(StartInput startInput) {
		this.startInput = startInput;
	}



class SRadio
{
  int beforeSquareColor=0xff03263D;
  int beforeFillSquareColor=0xff03263D;
  int hoverSquareColor=0xff00578E;
  int hoverFillSquareColor=0xff00578E;
  int afterSquareColor=0xff00A3E8;
  int afterFillSquareColor=0xff00A3E8;

  boolean fillBeforeSquare=false;

  PFont rText;
  int textColor=0xff000000;
  int textPoints=14;

  int squareLength=13;
  float squareLineWidth=0;
  int squareSpacing=10;

  String[] radioText;
  boolean[] radioChoose;

  int over=0;
  int nC;

  int rX, rY;

  float maxTextWidth=0;

  String radioLabel;

  boolean debug=false;
  boolean pressOnlyOnce=true;
  int deb=0;

////////////////////////////////////////////////////////
  SRadio(int x, int y, String[] op, String id)
  {
    rX=x;
    rY=y;
    radioText=op;

    nC=op.length;
    radioChoose = new boolean[nC];

    rText = createFont("Arial",16,true);
    textFont(rText,textPoints);
    textAlign(LEFT);

    for (int i=0; i<nC; i++)
    {
      if (textWidth(radioText[i]) > maxTextWidth) maxTextWidth=textWidth(radioText[i]);
      radioChoose[i]=false;
    }

    radioChoose[over]=true;

    radioLabel=id;
  }
////////////////////////////////////////////////////////
  public void setValue(int n)
  {
    if (n<0) n=0;
    if (n>(nC-1)) n=nC-1;

   for (int i=0; i<nC; i++) radioChoose[i]=false;
   radioChoose[n]=true;
   over=n;
  }
////////////////////////////////////////////////////////
  public void deBounce(int n)
  {
    if (pressOnlyOnce)
      return;
    else

    if (deb++ > n)
    {
      deb=0;
      pressOnlyOnce=true;
    }

  }
////////////////////////////////////////////////////////
  public boolean mouseOver()
  {
    boolean result=false;

    for (int i=0; i<nC; i++)
    {
      if ((mouseX>=(rX+(i*(squareSpacing+squareLength)))) && (mouseX<=(rX+(i*(squareSpacing+squareLength))+squareLength)) && (mouseY>=(rY)) && (mouseY<=(rY+squareLength)))
      {
        result=true;
        fill(hoverFillSquareColor);
        stroke(hoverSquareColor);
        rect(rX+i*(squareSpacing+squareLength), rY, squareLength, squareLength);
        if (radioChoose[i])
        fill(afterFillSquareColor);
        stroke(afterSquareColor);
        rect(rX+i*(squareSpacing+squareLength), rY, squareLength, squareLength);

        if (mousePressed && mouseButton==LEFT && pressOnlyOnce)
        {
          over=i;
          setValue(over);
          pressOnlyOnce=false;
        }
        deBounce(3);
        i=nC;
      }
      else
      {
        result=false;
      }
    }
    return result;
  }
////////////////////////////////////////////////////////
  public void drawRect()
  {
    strokeWeight(squareLineWidth);
   for (int i=0; i<nC; i++)
    {
      fill(beforeFillSquareColor);
      stroke(beforeSquareColor);
      rect(rX+i*(squareSpacing+squareLength), rY, squareLength, squareLength);

      fill(afterFillSquareColor);
      stroke(afterSquareColor);

      if (radioChoose[i])
      rect(rX+i*(squareSpacing+squareLength), rY, squareLength, squareLength);
    }
    mouseOver();
  }

  ///////////////////////////////////////////////////////
  public int update()
  {
    drawRect();
    return over;

  }

///////////////////////////////////////////////////////
  public int getValue()
  {
    return over;
  }

///////////////////////////////////////////////////////
  public void setDebugOn()
  {
    debug=true;
  }
///////////////////////////////////////////////////////
  public void setDebugOff()
  {
    debug=false;
  }
///////////////////////////////////////////////////////
  public void printGeometry()
  {
    println("radio = new ADradio("+rX+", "+rY+", arrayOfOptions"+", \""+radioLabel+"\");");

  }
///////////////////////////////////////////////////////
  public void setBeforeSquareColor(int c)
  {
    beforeSquareColor=c;
  }
///////////////////////////////////////////////////////
  public void setBeforeFillSquareColor(int c)
  {
    beforeFillSquareColor=c;
  }
///////////////////////////////////////////////////////
  public void setAfterSquareColor(int c)
  {
    afterSquareColor=c;
  }
///////////////////////////////////////////////////////
  public void setAfterFillSquareColor(int c)
  {
    afterFillSquareColor=c;
  }
///////////////////////////////////////////////////////
  public void setTextColor(int c)
  {
    textColor=c;
  }

///////////////////////////////////////////////////////
  public void setTextSize(int s)
  {
    textPoints=s;
  }
///////////////////////////////////////////////////////
  public void setSquareLength(int s)
  {
    squareLength=s;
  }
///////////////////////////////////////////////////////
  public void setLabel(String l)
  {
    radioLabel=l;
  }

}

///////////////////////////////////////////////////////
String[] options = {"0", "1","d"};
SRadio radioButton0;
SRadio radioButton1;
SRadio radioButton2;
SRadio radioButton3;
SRadio radioButton4;
SRadio radioButton5;
SRadio radioButton6;
SRadio radioButton7;
SRadio radioButton8;
SRadio radioButton9;
SRadio radioButton10;
SRadio radioButton11;
SRadio radioButton12;
SRadio radioButton13;
SRadio radioButton14;
SRadio radioButton15;
SRadio radioButton16;
SRadio radioButton17;
SRadio radioButton18;
SRadio radioButton19;
SRadio radioButton20;
SRadio radioButton21;
SRadio radioButton22;
SRadio radioButton23;
SRadio radioButton24;
SRadio radioButton25;
SRadio radioButton26;
SRadio radioButton27;
SRadio radioButton28;
SRadio radioButton29;
SRadio radioButton30;
SRadio radioButton31;

///////////////////////////////////////////////////////
int radio;
PrintWriter writer;
PFont output;
int n;
int bboxcolor=0xff03263D;
int hboxcolor=0xff00578E;
int aboxcolor=0xff00A3E8;

int stat=1;
int ytop=130;          //change position
int xlab=85;           //change position
int ylab=ytop+30;      //0000(x)
int Slab=182;          //S
int Sx=xlab+98;        //S(x)
int xbutton=Sx+27;     //button(x)
int ybutton=ytop+17;   //button(y)
int xsep=250;          //ABCDE(x)
int ysep=25;           //0000(y)

///////////////////////////////////////////////////////
public void setup()
{
  size(600,600);
  smooth();
  output = createFont("Arial",20,true);

  radioButton0 = new SRadio(xbutton, ybutton, options, "radioButton0");
  radioButton0.setDebugOn();
  radioButton0.setValue(0);

  radioButton1 = new SRadio(xbutton, ybutton+ysep, options, "radioButton1");
  radioButton1.setDebugOn();
  radioButton1.setValue(0);

  radioButton2 = new SRadio(xbutton, ybutton+ysep*2, options, "radioButton2");
  radioButton2.setDebugOn();
  radioButton2.setValue(0);

  radioButton3 = new SRadio(xbutton, ybutton+ysep*3, options, "radioButton3");
  radioButton3.setDebugOn();
  radioButton3.setValue(0);

  radioButton4 = new SRadio(xbutton, ybutton+ysep*4, options, "radioButton4");
  radioButton4.setDebugOn();
  radioButton4.setValue(0);

  radioButton5 = new SRadio(xbutton, ybutton+ysep*5, options, "radioButton5");
  radioButton5.setDebugOn();
  radioButton5.setValue(0);

  radioButton6 = new SRadio(xbutton, ybutton+ysep*6, options, "radioButton6");
  radioButton6.setDebugOn();
  radioButton6.setValue(0);

  radioButton7 = new SRadio(xbutton, ybutton+ysep*7, options, "radioButton7");
  radioButton7.setDebugOn();
  radioButton7.setValue(0);

  radioButton8 = new SRadio(xbutton, ybutton+ysep*8, options, "radioButton8");
  radioButton8.setDebugOn();
  radioButton8.setValue(0);

  radioButton9 = new SRadio(xbutton, ybutton+ysep*9, options, "radioButton9");
  radioButton9.setDebugOn();
  radioButton9.setValue(0);

  radioButton10 = new SRadio(xbutton, ybutton+ysep*10, options, "radioButton10");
  radioButton10.setDebugOn();
  radioButton10.setValue(0);

  radioButton11 = new SRadio(xbutton, ybutton+ysep*11, options, "radioButton11");
  radioButton11.setDebugOn();
  radioButton11.setValue(0);

  radioButton12 = new SRadio(xbutton, ybutton+ysep*12, options, "radioButton12");
  radioButton12.setDebugOn();
  radioButton12.setValue(0);

  radioButton13 = new SRadio(xbutton, ybutton+ysep*13, options, "radioButton13");
  radioButton13.setDebugOn();
  radioButton13.setValue(0);

  radioButton14 = new SRadio(xbutton, ybutton+ysep*14, options, "radioButton14");
  radioButton14.setDebugOn();
  radioButton14.setValue(0);

  radioButton15 = new SRadio(xbutton, ybutton+ysep*15, options, "radioButton15");
  radioButton15.setDebugOn();
  radioButton15.setValue(0);

  radioButton16 = new SRadio(xbutton+xsep, ybutton, options, "radioButton0");
  radioButton16.setDebugOn();
  radioButton16.setValue(0);

  radioButton17 = new SRadio(xbutton+xsep, ybutton+ysep, options, "radioButton1");
  radioButton17.setDebugOn();
  radioButton17.setValue(0);

  radioButton18 = new SRadio(xbutton+xsep, ybutton+ysep*2, options, "radioButton2");
  radioButton18.setDebugOn();
  radioButton18.setValue(0);

  radioButton19 = new SRadio(xbutton+xsep, ybutton+ysep*3, options, "radioButton3");
  radioButton19.setDebugOn();
  radioButton19.setValue(0);

  radioButton20 = new SRadio(xbutton+xsep, ybutton+ysep*4, options, "radioButton4");
  radioButton20.setDebugOn();
  radioButton20.setValue(0);

  radioButton21 = new SRadio(xbutton+xsep, ybutton+ysep*5, options, "radioButton5");
  radioButton21.setDebugOn();
  radioButton21.setValue(0);

  radioButton22 = new SRadio(xbutton+xsep, ybutton+ysep*6, options, "radioButton6");
  radioButton22.setDebugOn();
  radioButton22.setValue(0);

  radioButton23 = new SRadio(xbutton+xsep, ybutton+ysep*7, options, "radioButton7");
  radioButton23.setDebugOn();
  radioButton23.setValue(0);

  radioButton24 = new SRadio(xbutton+xsep, ybutton+ysep*8, options, "radioButton8");
  radioButton24.setDebugOn();
  radioButton24.setValue(0);

  radioButton25 = new SRadio(xbutton+xsep, ybutton+ysep*9, options, "radioButton9");
  radioButton25.setDebugOn();
  radioButton25.setValue(0);

  radioButton26 = new SRadio(xbutton+xsep, ybutton+ysep*10, options, "radioButton10");
  radioButton26.setDebugOn();
  radioButton26.setValue(0);

  radioButton27 = new SRadio(xbutton+xsep, ybutton+ysep*11, options, "radioButton11");
  radioButton27.setDebugOn();
  radioButton27.setValue(0);

  radioButton28 = new SRadio(xbutton+xsep, ybutton+ysep*12, options, "radioButton12");
  radioButton28.setDebugOn();
  radioButton28.setValue(0);

  radioButton29 = new SRadio(xbutton+xsep, ybutton+ysep*13, options, "radioButton13");
  radioButton29.setDebugOn();
  radioButton29.setValue(0);

  radioButton30 = new SRadio(xbutton+xsep, ybutton+ysep*14, options, "radioButton14");
  radioButton30.setDebugOn();
  radioButton30.setValue(0);

  radioButton31 = new SRadio(xbutton+xsep, ybutton+ysep*15, options, "radioButton15");
  radioButton31.setDebugOn();
  radioButton31.setValue(0);
}

///////////////////////////////////////////////////////
public void draw()
{
  background(0xff000000);
  if(mouseX>=80 && mouseX<=180 && mouseY>=30 && mouseY<=67){
    fill(hboxcolor);
  }else{
  fill(bboxcolor);
  }
  rect(80,30,100,37,7);
  if(mouseX>=width-170 && mouseX<=width-170+100 && mouseY>=30 && mouseY<=67){
  fill(hboxcolor);
  }else{
  fill(bboxcolor);
  }
  rect(width-170,30,100,37,7);

  int m = 255*(millis()%3000)/3000;
  if(m<125){
   n=m+125;
  }else if(m>=125){
   n=350-m;
  }
///////////////////////////////////////////////////////
  textSize(34);
  fill(0xffFFFFFF);
  text("input",280,60);
  textSize(24);
  text("Formula",87,55);
  text("K-map",445,55);
///////////////////////////////////////////////////////
  switch(stat){
    case 0:
    radioButton0.update();
    radioButton1.update();
    radioButton2.update();
    radioButton3.update();
    radioButton4.update();
    radioButton5.update();
    radioButton6.update();
    radioButton7.update();

    noStroke();
    fill(0xff00A3E8,n);
    triangle(259,17,269,27,249,27);

    textSize(34);
    fill(0xffFFFFFF);
    text("3",250,60);

    textSize(18);
    fill(0xffFFFFFF);
    text("      A B C",xlab,ytop);
    text("S   0   1   d",Slab,ytop);

    textSize(20);
    fill(0xffFFFFFF);
    text("      0 0 0",xlab,ylab);
    text("      0 0 1",xlab,ylab+ysep);
    text("      0 1 0",xlab,ylab+ysep*2);
    text("      0 1 1",xlab,ylab+ysep*3);
    text("      1 0 0",xlab,ylab+ysep*4);
    text("      1 0 1",xlab,ylab+ysep*5);
    text("      1 1 0",xlab,ylab+ysep*6);
    text("      1 1 1",xlab,ylab+ysep*7);

    textSize(20);
    fill(0xffFFFFFF);
    text(options[radioButton0.getValue()], Sx,ylab);
    text(options[radioButton1.getValue()], Sx,ylab+ysep);
    text(options[radioButton2.getValue()], Sx,ylab+ysep*2);
    text(options[radioButton3.getValue()], Sx,ylab+ysep*3);
    text(options[radioButton4.getValue()], Sx,ylab+ysep*4);
    text(options[radioButton5.getValue()], Sx,ylab+ysep*5);
    text(options[radioButton6.getValue()], Sx,ylab+ysep*6);
    text(options[radioButton7.getValue()], Sx,ylab+ysep*7);
    break;
///////////////////////////////////////////////////////
    case 1:
    radioButton0.update();
    radioButton1.update();
    radioButton2.update();
    radioButton3.update();
    radioButton4.update();
    radioButton5.update();
    radioButton6.update();
    radioButton7.update();
    radioButton8.update();
    radioButton9.update();
    radioButton10.update();
    radioButton11.update();
    radioButton12.update();
    radioButton13.update();
    radioButton14.update();
    radioButton15.update();

    noStroke();
    fill(0xff00A3E8,n);
    triangle(259,17,269,27,249,27);
    triangle(259,80,269,70,249,70);


    textSize(34);
    fill(0xffFFFFFF);
    text("4",250,60);

    textSize(18);
    fill(0xffFFFFFF);
    text("   A B C D",xlab,ytop);
    text("S   0   1   d",Slab,ytop);
    textSize(20);
    fill(0xffFFFFFF);

    text("   0 0 0 0",xlab,ylab);
    text("   0 0 0 1",xlab,ylab+ysep);
    text("   0 0 1 0",xlab,ylab+ysep*2);
    text("   0 0 1 1",xlab,ylab+ysep*3);
    text("   0 1 0 0",xlab,ylab+ysep*4);
    text("   0 1 0 1",xlab,ylab+ysep*5);
    text("   0 1 1 0",xlab,ylab+ysep*6);
    text("   0 1 1 1",xlab,ylab+ysep*7);
    text("   1 0 0 0",xlab,ylab+ysep*8);
    text("   1 0 0 1",xlab,ylab+ysep*9);
    text("   1 0 1 0",xlab,ylab+ysep*10);
    text("   1 0 1 1",xlab,ylab+ysep*11);
    text("   1 1 0 0",xlab,ylab+ysep*12);
    text("   1 1 0 1",xlab,ylab+ysep*13);
    text("   1 1 1 0",xlab,ylab+ysep*14);
    text("   1 1 1 1",xlab,ylab+ysep*15);

    textSize(20);
    fill(0xffFFFFFF);
    text(options[radioButton0.getValue()], Sx,ylab);
    text(options[radioButton1.getValue()], Sx,ylab+ysep);
    text(options[radioButton2.getValue()], Sx,ylab+ysep*2);
    text(options[radioButton3.getValue()], Sx,ylab+ysep*3);
    text(options[radioButton4.getValue()], Sx,ylab+ysep*4);
    text(options[radioButton5.getValue()], Sx,ylab+ysep*5);
    text(options[radioButton6.getValue()], Sx,ylab+ysep*6);
    text(options[radioButton7.getValue()], Sx,ylab+ysep*7);
    text(options[radioButton8.getValue()], Sx,ylab+ysep*8);
    text(options[radioButton9.getValue()], Sx,ylab+ysep*9);
    text(options[radioButton10.getValue()], Sx,ylab+ysep*10);
    text(options[radioButton11.getValue()], Sx,ylab+ysep*11);
    text(options[radioButton12.getValue()], Sx,ylab+ysep*12);
    text(options[radioButton13.getValue()], Sx,ylab+ysep*13);
    text(options[radioButton14.getValue()], Sx,ylab+ysep*14);
    text(options[radioButton15.getValue()], Sx,ylab+ysep*15);
    break;
///////////////////////////////////////////////////////
    case 2:
    radioButton0.update();
    radioButton1.update();
    radioButton2.update();
    radioButton3.update();
    radioButton4.update();
    radioButton5.update();
    radioButton6.update();
    radioButton7.update();
    radioButton8.update();
    radioButton9.update();
    radioButton10.update();
    radioButton11.update();
    radioButton12.update();
    radioButton13.update();
    radioButton14.update();
    radioButton15.update();
    radioButton16.update();
    radioButton17.update();
    radioButton18.update();
    radioButton19.update();
    radioButton20.update();
    radioButton21.update();
    radioButton22.update();
    radioButton23.update();
    radioButton24.update();
    radioButton25.update();
    radioButton26.update();
    radioButton27.update();
    radioButton28.update();
    radioButton29.update();
    radioButton30.update();
    radioButton31.update();

    noStroke();
    fill(0xff00A3E8,n);
    triangle(259,80,269,70,249,70);

    textSize(34);
    fill(0xffFFFFFF);
    text("5",250,60);

    textSize(18);
    fill(0xffFFFFFF);
    text("A B C D E",xlab,ytop);
    text("A B C D E",xlab+xsep,ytop);
    text("S   0   1   d",Slab,ytop);
    text("S   0   1   d",Slab+xsep,ytop);

    textSize(20);
    fill(0xffFFFFFF);

    text("0 0 0 0 0",xlab,ylab);
    text("0 0 0 0 1",xlab,ylab+ysep);
    text("0 0 0 1 0",xlab,ylab+ysep*2);
    text("0 0 0 1 1",xlab,ylab+ysep*3);
    text("0 0 1 0 0",xlab,ylab+ysep*4);
    text("0 0 1 0 1",xlab,ylab+ysep*5);
    text("0 0 1 1 0",xlab,ylab+ysep*6);
    text("0 0 1 1 1",xlab,ylab+ysep*7);
    text("0 1 0 0 0",xlab,ylab+ysep*8);
    text("0 1 0 0 1",xlab,ylab+ysep*9);
    text("0 1 0 1 0",xlab,ylab+ysep*10);
    text("0 1 0 1 1",xlab,ylab+ysep*11);
    text("0 1 1 0 0",xlab,ylab+ysep*12);
    text("0 1 1 0 1",xlab,ylab+ysep*13);
    text("0 1 1 1 0",xlab,ylab+ysep*14);
    text("0 1 1 1 1",xlab,ylab+ysep*15);

    text("1 0 0 0 0",xlab+xsep,ylab);
    text("1 0 0 0 1",xlab+xsep,ylab+ysep);
    text("1 0 0 1 0",xlab+xsep,ylab+ysep*2);
    text("1 0 0 1 1",xlab+xsep,ylab+ysep*3);
    text("1 0 1 0 0",xlab+xsep,ylab+ysep*4);
    text("1 0 1 0 1",xlab+xsep,ylab+ysep*5);
    text("1 0 1 1 0",xlab+xsep,ylab+ysep*6);
    text("1 0 1 1 1",xlab+xsep,ylab+ysep*7);
    text("1 1 0 0 0",xlab+xsep,ylab+ysep*8);
    text("1 1 0 0 1",xlab+xsep,ylab+ysep*9);
    text("1 1 0 1 0",xlab+xsep,ylab+ysep*10);
    text("1 1 0 1 1",xlab+xsep,ylab+ysep*11);
    text("1 1 1 0 0",xlab+xsep,ylab+ysep*12);
    text("1 1 1 0 1",xlab+xsep,ylab+ysep*13);
    text("1 1 1 1 0",xlab+xsep,ylab+ysep*14);
    text("1 1 1 1 1",xlab+xsep,ylab+ysep*15);

    textSize(20);
    fill(0xffFFFFFF);
    text(options[radioButton0.getValue()], Sx,ylab);
    text(options[radioButton1.getValue()], Sx,ylab+ysep);
    text(options[radioButton2.getValue()], Sx,ylab+ysep*2);
    text(options[radioButton3.getValue()], Sx,ylab+ysep*3);
    text(options[radioButton4.getValue()], Sx,ylab+ysep*4);
    text(options[radioButton5.getValue()], Sx,ylab+ysep*5);
    text(options[radioButton6.getValue()], Sx,ylab+ysep*6);
    text(options[radioButton7.getValue()], Sx,ylab+ysep*7);
    text(options[radioButton8.getValue()], Sx,ylab+ysep*8);
    text(options[radioButton9.getValue()], Sx,ylab+ysep*9);
    text(options[radioButton10.getValue()], Sx,ylab+ysep*10);
    text(options[radioButton11.getValue()], Sx,ylab+ysep*11);
    text(options[radioButton12.getValue()], Sx,ylab+ysep*12);
    text(options[radioButton13.getValue()], Sx,ylab+ysep*13);
    text(options[radioButton14.getValue()], Sx,ylab+ysep*14);
    text(options[radioButton15.getValue()], Sx,ylab+ysep*15);
    text(options[radioButton16.getValue()], Sx+xsep,ylab);
    text(options[radioButton17.getValue()], Sx+xsep,ylab+ysep);
    text(options[radioButton18.getValue()], Sx+xsep,ylab+ysep*2);
    text(options[radioButton19.getValue()], Sx+xsep,ylab+ysep*3);
    text(options[radioButton20.getValue()], Sx+xsep,ylab+ysep*4);
    text(options[radioButton21.getValue()], Sx+xsep,ylab+ysep*5);
    text(options[radioButton22.getValue()], Sx+xsep,ylab+ysep*6);
    text(options[radioButton23.getValue()], Sx+xsep,ylab+ysep*7);
    text(options[radioButton24.getValue()], Sx+xsep,ylab+ysep*8);
    text(options[radioButton25.getValue()], Sx+xsep,ylab+ysep*9);
    text(options[radioButton26.getValue()], Sx+xsep,ylab+ysep*10);
    text(options[radioButton27.getValue()], Sx+xsep,ylab+ysep*11);
    text(options[radioButton28.getValue()], Sx+xsep,ylab+ysep*12);
    text(options[radioButton29.getValue()], Sx+xsep,ylab+ysep*13);
    text(options[radioButton30.getValue()], Sx+xsep,ylab+ysep*14);
    text(options[radioButton31.getValue()], Sx+xsep,ylab+ysep*15);
    break;
}
}

///////////////////////////////////////////////////////
public void mouseClicked(){
	//formulaが押された時
	if (mouseX >= 80 && mouseX <= 180 && mouseY >= 30 && mouseY <= 67) {
		startInput.changeLogical();
	}

  switch(stat){
    case 2:
      if(mouseX>=width-170 && mouseX<=width-170+100 && mouseY>=30 && mouseY<=67){
        writer =  createWriter("input5.csv");

        writer.println("A,B,C,D,E,S");
        writer.println("0,0,0,0,0,"+options[radioButton0.getValue()]);
        writer.println("0,0,0,0,1,"+options[radioButton1.getValue()]);
        writer.println("0,0,0,1,0,"+options[radioButton2.getValue()]);
        writer.println("0,0,0,1,1,"+options[radioButton3.getValue()]);
        writer.println("0,0,1,0,0,"+options[radioButton4.getValue()]);
        writer.println("0,0,1,0,1,"+options[radioButton5.getValue()]);
        writer.println("0,0,1,1,0,"+options[radioButton6.getValue()]);
        writer.println("0,0,1,1,1,"+options[radioButton7.getValue()]);
        writer.println("0,1,0,0,0,"+options[radioButton8.getValue()]);
        writer.println("0,1,0,0,1,"+options[radioButton9.getValue()]);
        writer.println("0,1,0,1,0,"+options[radioButton10.getValue()]);
        writer.println("0,1,0,1,1,"+options[radioButton11.getValue()]);
        writer.println("0,1,1,0,0,"+options[radioButton12.getValue()]);
        writer.println("0,1,1,0,1,"+options[radioButton13.getValue()]);
        writer.println("0,1,1,1,0,"+options[radioButton14.getValue()]);
        writer.println("0,1,1,1,1,"+options[radioButton15.getValue()]);
        writer.println("1,0,0,0,0,"+options[radioButton16.getValue()]);
        writer.println("1,0,0,0,1,"+options[radioButton17.getValue()]);
        writer.println("1,0,0,1,0,"+options[radioButton18.getValue()]);
        writer.println("1,0,0,1,1,"+options[radioButton19.getValue()]);
        writer.println("1,0,1,0,0,"+options[radioButton20.getValue()]);
        writer.println("1,0,1,0,1,"+options[radioButton21.getValue()]);
        writer.println("1,0,1,1,0,"+options[radioButton22.getValue()]);
        writer.println("1,0,1,1,1,"+options[radioButton23.getValue()]);
        writer.println("1,1,0,0,0,"+options[radioButton24.getValue()]);
        writer.println("1,1,0,0,1,"+options[radioButton25.getValue()]);
        writer.println("1,1,0,1,0,"+options[radioButton26.getValue()]);
        writer.println("1,1,0,1,1,"+options[radioButton27.getValue()]);
        writer.println("1,1,1,0,0,"+options[radioButton28.getValue()]);
        writer.println("1,1,1,0,1,"+options[radioButton29.getValue()]);
        writer.println("1,1,1,1,0,"+options[radioButton30.getValue()]);
        writer.println("1,1,1,1,1,"+options[radioButton31.getValue()]);
        writer.flush();
        writer.close();

        //三澤 20150122
        //描画プログラムの実行
        new DrawManager("input5.csv");
      }

       if(mouseX>=249 && mouseX<=269 && mouseY>=70 && mouseY<=80){
        stat=1;
      }
      break;
///////////////////////////////////////////////////////
    case 1:
      if(mouseX>=width-170 && mouseX<=width-170+100 && mouseY>=30 && mouseY<=67){
        writer =  createWriter("input4.csv");

        writer.println("A,B,C,D,S");
        writer.println("0,0,0,0,"+options[radioButton0.getValue()]);
        writer.println("0,0,0,1,"+options[radioButton1.getValue()]);
        writer.println("0,0,1,0,"+options[radioButton2.getValue()]);
        writer.println("0,0,1,1,"+options[radioButton3.getValue()]);
        writer.println("0,1,0,0,"+options[radioButton4.getValue()]);
        writer.println("0,1,0,1,"+options[radioButton5.getValue()]);
        writer.println("0,1,1,0,"+options[radioButton6.getValue()]);
        writer.println("0,1,1,1,"+options[radioButton7.getValue()]);
        writer.println("1,0,0,0,"+options[radioButton8.getValue()]);
        writer.println("1,0,0,1,"+options[radioButton9.getValue()]);
        writer.println("1,0,1,0,"+options[radioButton10.getValue()]);
        writer.println("1,0,1,1,"+options[radioButton11.getValue()]);
        writer.println("1,1,0,0,"+options[radioButton12.getValue()]);
        writer.println("1,1,0,1,"+options[radioButton13.getValue()]);
        writer.println("1,1,1,0,"+options[radioButton14.getValue()]);
        writer.println("1,1,1,1,"+options[radioButton15.getValue()]);
        writer.flush();
        writer.close();

        //三澤 20150122
        //描画プログラムの実行
        new DrawManager("input4.csv");
      }
       if(mouseX>=249 && mouseX<=269 && mouseY>=17 && mouseY<=27){
        stat=2;
      }else if(mouseX>=249 && mouseX<=269 && mouseY>=70 && mouseY<=80){
        stat=0;
      }

      break;
///////////////////////////////////////////////////////
    case 0:
      if(mouseX>=width-170 && mouseX<=width-170+100 && mouseY>=30 && mouseY<=67){
        writer =  createWriter("input3.csv");

        writer.println("A,B,C,S");
        writer.println("0,0,0,"+options[radioButton0.getValue()]);
        writer.println("0,0,1,"+options[radioButton1.getValue()]);
        writer.println("0,1,0,"+options[radioButton2.getValue()]);
        writer.println("0,1,1,"+options[radioButton3.getValue()]);
        writer.println("1,0,0,"+options[radioButton4.getValue()]);
        writer.println("1,0,1,"+options[radioButton5.getValue()]);
        writer.println("1,1,0,"+options[radioButton6.getValue()]);
        writer.println("1,1,1,"+options[radioButton7.getValue()]);
        writer.flush();
        writer.close();
      }

       if(mouseX>=249 && mouseX<=269 && mouseY>=17 && mouseY<=27){
         stat=1;
       }
      break;
  }
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "radio" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
