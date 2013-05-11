<%@ page contentType="image/jpeg;charset=UTF-8" import="java.awt.*, 
java.awt.image.*,java.util.*,javax.imageio.*" %><% 
//在内存中创建图象
int width=60, height=18;
BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
//获取图形上下文
Graphics g = image.getGraphics();
//设定背景色
g.setColor(new Color(0xDCDCDC));
g.fillRect(0, 0, width, height);
//画边框
g.setColor(Color.black);
g.drawRect(0,0,width-1,height-1);
// 取随机产生的认证码(4位数字)
String rand = request.getParameter("Rand");
rand = rand.substring(0,rand.indexOf("."));
switch(rand.length())
{
case 1: rand = "000"+rand;break;
case 2: rand = "00"+rand;break;
case 3: rand = "0"+rand;break;
default: rand = rand.substring(0,4);break;
}
 
//将认证码显示到图象中
 g.setColor(Color.black);
 g.setFont(new Font("Atlantic Inline",Font.PLAIN,18));
 
 g.drawString(rand.substring(0,1),8,17);
 g.drawString(rand.substring(1,2),20,15); 
 g.drawString(rand.substring(2,3),35,18); 
 g.drawString(rand.substring(3,4),45,15); 

// 随机产生88个干扰点,使图象中的认证码不易被其它程序探测到
 Random random=new Random();
 for(int iIndex=0;iIndex<88;iIndex++)
 {
  int x=random.nextInt(width);
  int y=random.nextInt(height);
  g.drawLine(x,y,x,y);
 }
//图象生效
g.dispose();
ImageIO.setUseCache(true);
response.reset();
// 输出图象到页面
ImageIO.write(image, "JPEG", response.getOutputStream());
%>