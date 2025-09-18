package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

//我要实现一个俄罗斯方块游戏
public class Tetris extends JFrame implements KeyListener {

    private static final int x=26;
    private static final int y=12;

    JTextArea[][] text;
    int [][] data;
    JLabel label1;
    JLabel label;
    boolean isRunning=true;
    int[] allRect;
    int rect;
    int nowX,nowY;
    int sleepTime=1000;
    int score=0;
    boolean gamePause=false;
    int pauseTimes=0;

    public Tetris() {
        text =new JTextArea[x][y];
        data =new int[x][y];
        label1 =new JLabel("游戏状态：正在游戏中");
        label =new JLabel("分数：0");
        initFrame();
        initGamePanel();
        initExplainPanel();
        setVisible(true);
        allRect=new int[]{0x00cc,0x8888,0x000f,0x888f,0xf888,0xf111,0x111f,0x0eee,0xffff,0x0008,0x0888,0x000e,0x0088, 0x000c,0x08c8,0x00e4,0x04c4,0x004e,0x08c4,0x006c,0x04c8,0x00c6};
    }
    //初始化窗体
    public void initFrame() {
        setTitle("俄罗斯方块");
        setDefaultCloseOperation(3);
        setSize(600, 850);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    //初始化界面
    public void initGamePanel()
    {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(x,y,1,1));

        for(int i=0;i<text.length;i++){
            for(int j=0;j<text[i].length;j++){
                text[i][j]=new JTextArea(x,y);
                text[i][j].setBackground(Color.white);

                text[i][j].addKeyListener(this);
                if(j==0||j==text[i].length-1||i==text.length-1)
                {
                    text[i][j].setBackground(Color.yellow);
                    data[i][j]=1;
                }
                text[i][j].setEditable(false);
                text[i][j].setForeground(Color.black);
                jPanel.add(text[i][j]);
            }
        }

        this.setLayout(new BorderLayout());
        this.add(jPanel,BorderLayout.CENTER);

    }

    //左右标签说明
    public void initExplainPanel()
    {
        JPanel explain_left =new JPanel();
        JPanel explain_right =new JPanel();

        explain_left.setLayout(new GridLayout(5,1));
        explain_right.setLayout(new GridLayout(2,1));
        explain_left.add(new JLabel("按空格键，方块变形"));
        explain_left.add(new JLabel("按左箭头或A，方块左移"));
        explain_left.add(new JLabel("按右箭头或D，方块右移"));
        explain_left.add(new JLabel("按下箭头或S，方块下落"));
        explain_left.add(new JLabel("按p,暂停游戏"));


        label1.setForeground(Color.blue);
        explain_right.add(label1);
        explain_right.add(label);
        this.add(explain_left,BorderLayout.WEST);
        this.add(explain_right,BorderLayout.EAST);
    }


    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar()=='p')
        {
            if(!isRunning)
                return;
            pauseTimes++;
            gamePause=!gamePause;
            if(gamePause)
            {
                label1.setText("游戏状态：暂停中");
                pauseTimes++;
            }
            else {
                label1.setText("游戏状态：正在游戏中");
            }
        }
        if(e.getKeyChar()==KeyEvent.VK_SPACE)
        {
            if(!isRunning)
                return;
            if(gamePause)
                return;
            int old;
            for(old=0;old<allRect.length;old++)
            {
                if(allRect[old]==rect)
                {
                    break;
                }
            }
            int next;
            if(old==0||old==7||old==8||old==9)
            {
                return;
            }
            clear(nowX,nowY);
           if(old==1||old==2)
           {
               next=allRect[old==1?2:1];
               if(canTurn(next,nowX,nowY))
                   rect=next;
           }
           if(old>=3&&old<=6)
           {
               next=allRect[old+1>6?3:old+1];
               if(canTurn(next,nowX,nowY))
                   rect=next;
           }

           if(old==10||old==11)
           {
               next=allRect[old==10?11:10];
               if(canTurn(next,nowX,nowY))
                        rect=next;
           }
           if(old==12||old==13)
           {
               next=allRect[old==12?13:12];
               if(canTurn(next,nowX,nowY))
                        rect=next;
           }
           if(old>=14&&old<=17)
           {
               next=allRect[old+1>17?14:old+1];
               if(canTurn(next,nowX,nowY))
                        rect=next;
           }
           if(old==18||old==19)
           {
               next=allRect[old==18?19:18];
               if(canTurn(next,nowX,nowY))
                        rect=next;
           }
           if(old==20||old==21)
           {
               next=allRect[old==20?21:20];
               if(canTurn(next,nowX,nowY))
                        rect=next;
           }
           draw(nowX,nowY);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_A)
        {
            if(!isRunning)
                return;
            if (gamePause)
                return;
            if(nowY<=1)return;
            int temp=0x8000;
            for(int i=nowX;i<nowX+4;i++)
            {
                for(int j=nowY;j<nowY+4;j++)
                {
                    if((rect&temp)!=0)
                    {
                        if(data[i][j-1]==1)
                        {
                            return;
                        }
                    }
                }
                temp>>=1;
            }
            clear(nowX,nowY);
            nowY--;
            draw(nowX,nowY);
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_D)
        {
            if(!isRunning)
                return;
            if (gamePause)
                return;
            int temp=0x8000;
            int m=nowX;
            int n=nowY;
            int num=1;
            for(int i=0;i<4;i++)
            {
                for(int j=0;j<4;j++)
                {
                    if((rect&temp)!=0)
                    {
                       if(n>num)
                       {
                           num=n;
                       }
                    }
                    n++;
                    temp>>=1;
                }
                m++;
                n=n-4;

            }
            if(num>=y-2)
                return;
            temp=0x8000;
            for(int i=nowX;i<nowX+4;i++) {
                for (int j = nowY; j < nowY+4; j++) {
                    if ((rect & temp) != 0) {
                        if (data[i][j + 1] == 1) {
                            return;
                        }
                    }
                    temp >>= 1;
                }
            }
            clear(nowX,nowY);
            nowY++;
            draw(nowX,nowY);
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyCode()==KeyEvent.VK_S)
        {
            if(!isRunning)
                return;
            if (gamePause)
                return;
            if(!canFall(nowX,nowY))
            {
                return;
            }
            clear(nowX,nowY);
            nowX++;
            draw(nowX,nowY);
        }
        // 处理按键按下事件
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 处理按键释放事件
    }
    public boolean canTurn(int a,int x,int y)
    {
        int temp=0x8000;
        for (int i=0;i<4;i++)
        {
            for (int j=0;j<4;j++)
            {
                if((a&temp)!=0)
                {
                    if(data[x][y]==1)
                    {
                        return false;
                    }
                }
                temp>>=1;
                y++;
            }
            x++;
            y=y-4;
        }
        return true;
    }


    //游戏运行
    public void game() {
        while(true) {
            if(!isRunning) {
                break;
            }
            gameRun();
        }
        label1.setText("游戏状态：游戏结束");
    }
    public void gameRun(){
        randomRect();
        nowX=0;
        nowY=5;
        for(int i=0;i<x;i++) {
            try {
                Thread.sleep(sleepTime);
                if(gamePause)
                {
                    i--;
                }
                else {
                    if (!canFall(nowX, nowY)) {
                        changeData(nowX, nowY);
                        for (int j = nowX; j < nowX + 4; j++) {
                            int sum = 0;
                            for (int k = 1; k <= (y - 2); k++) {
                                if (data[j][k] == 1) {
                                    sum++;
                                }
                            }
                            if (sum == (y - 2)) {
                                removeRow(j);
                            }
                        }
                        for (int j = 1; j <= (y - 2); j++) {
                            if (data[3][j] == 1) {
                                isRunning = false;
                                break;
                            }
                        }
                        break;

                    } else {
                        nowX++;
                        fall(nowX, nowY);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void randomRect()
    {
        Random random=new Random();
        rect=allRect[random.nextInt(22)];
    }

    public void fall(int x,int y)
    {
        if(x>0)
        {
            clear(x-1,y);
        }
        draw(x,y);

    }
    public void clear(int x,int y)
    {
        int temp=0x8000;
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                if((rect&temp)!=0)
                {
                   text[x][y].setBackground(Color.white);
                }
                y++;
                temp>>=1;
            }
            x++;
            y=y-4;
        }
    }

    public void draw(int x,int y)
    {
        int temp=0x8000;
        for(int i=0;i<4;i++)
            {
                for(int j=0;j<4;j++)
                {
                    if((rect&temp)!=0)
                    {
                        text[x][y].setBackground(Color.CYAN);
                    }
                    y++;
                    temp>>=1;
                }
                x++;
                y=y-4;
            }
    }

    public boolean canFall(int x,int y)
    {
        int temp=0x8000;
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                if((rect&temp)!=0)
                {
                    if(data[x+1][y]==1)
                    {
                        return false;
                    }
                }
                y++;
                temp>>=1;
            }
            x++;
            y=y-4;
        }
        return true;
    }

    public void changeData(int x,int y)
    {
        int temp=0x8000;
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                if((rect&temp)!=0)
                {
                    data[x][y]=1;
                }
                y++;
                temp>>=1;
            }
            x++;
            y=y-4;
        }
    }

    public void removeRow(int x)
    {
        int temp=100;
        for(int i=x;i>0;i--)
        {
            for(int j=1;j<=(y-2);j++)
            {
                data[i][j]=data[i-1][j];
            }
        }
        reflesh(x);
        if(sleepTime>temp)
        {
            sleepTime-=temp;
        }
        score+=temp;
        label.setText("分数："+score);
    }
    public void reflesh(int x)
    {
        for(int i=x;i>0;i--)
        {
            for(int j=1;j<=(y-2);j++)
            {
                if(data[i][j]==1)
                {
                    text[i][j].setBackground(Color.CYAN);
                }
                else
                {
                    text[i][j].setBackground(Color.white);
                }
            }
        }
    }



    public static void main(String[] args) {
        Tetris tetris=new Tetris();
        tetris.game();
    }
}
