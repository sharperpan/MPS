import ddf.minim.*;
AudioPlayer player;
AudioInput input;
Minim minim;
int posX = 0;
float strokeW = 10;
int color_R = 125;
int color_B = 125;
int color_L = 125;
float x,y;
void setup() {
  size(1024, 500);
  background(0);
  smooth();
  minim=new Minim(this);
  player=minim.loadFile("/turkish.wav", 1024);
  player.play();
  input = minim.getLineIn();
}

/*void draw(){
  background(0);
  stroke(255);
  for (int i=0;i<10;++i){
    strokeWeight(abs(player.left.get(i)*10));
    ellipse(i*120,mouseY,player.left.get(0)*500,player.left.get(0)*500);
    rect(i*20+10, 250+player.right.get(i)*10, 1, 75+player.right.get(i)*250);
  }
}*/
void draw(){
  System.out.println(player.right.get(0));
  if (player.right.get(0) > 0.05){
  x = random(1024);
  y = random(250,500);
  ellipse(int(x),int(y),10,10);
  stroke(255);}
  //line(512,250,player.right.get(0)*1000+512,player.left.get(0)*1000+250);
}

/*
void draw()  
{    
  x = posX%1024;
  y = 50*int(posX/1024);
  System.out.println(x+"  "+y);
  line(posX%1024,100*int(posX/1024),posX%1024,100*int(posX/1024)+40);
  strokeWeight(strokeW);  
  stroke(color_R,0,0); 
  line(posX%1024,100*int(posX/1024)+50,posX%1024,100*int(posX/1024)+90);
  strokeWeight(strokeW);  
  stroke(225,225,color_L); 
  posX = posX + 3; 
  color_R = int(abs(player.right.get(0)*1000)>30) * 120 + 30;  
  color_R = abs(color_R) % 255;
  color_L = int(abs(player.left.get(0)*1000)>30) * 120 + 30;  
  color_L = 255 - abs(color_L) % 255;
  //System.out.println(color_R);
}  */