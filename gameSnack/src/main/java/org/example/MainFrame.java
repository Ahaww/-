package org.example;
//制作一个贪吃蛇游戏
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class MainFrame extends JFrame
{
    private Snake snake;

    private JPanel jPanel;

    private Timer timer;

    private Node food;

    public MainFrame()  throws HeadlessException{
        initFrame();
        initGamePanel();
        initSnake();
        initFood();
        initTimer();
        setKeyListen();
    }
    private void initFood()
    {
        food=new Node(0,0);
        food.randomFood();
    }

    private void setKeyListen()
    {
        addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                switch(e.getKeyCode())
                {
                    // 这两个键（上箭头和W键）控制蛇向上移动
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:
                        if(snake.getDirection()!=Direction.DOWN)
                            snake.setDirection(Direction.UP);
                        break;

                    // 这两个键（下箭头和S键）控制蛇向下移动
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        if(snake.getDirection()!=Direction.UP)
                            snake.setDirection(Direction.DOWN);
                        break;

                    // 这两个键（左箭头和A键）控制蛇向左移动
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
                        if(snake.getDirection()!=Direction.RIGHT)
                            snake.setDirection(Direction.LEFT);
                        break;

                    // 这两个键（右箭头和D键）控制蛇向右移动
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        if(snake.getDirection()!=Direction.LEFT)
                            snake.setDirection(Direction.RIGHT);
                        break;

                    // 其他键（例如空格键）执行其他操作，比如暂停游戏
                    default:
                        // 这里可以添加其他按键的处理逻辑
                        break;
                }
            }
        });
    }

    private void initTimer()
    {
        timer=new Timer();
        TimerTask timeTask=new TimerTask()
        {
            @Override
            public void run()
            {
                snake.move();

                Node head=snake.getBody().getFirst();
                if(head.getX()==food.getX()&&head.getY()==food.getY())
                {
                    snake.eat(food);
                    food.randomFood();

                }

                //重绘棋盘
                jPanel.repaint();
            }
        };
        timer.schedule(timeTask,1000,100);
    }

    private void initSnake()
    {

        snake=new Snake();
    }


    private void initFrame()
    {
        this.setTitle("贪吃蛇");
        this.setSize(600,623);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
    }

    private void initGamePanel()
    {
        jPanel=new JPanel()
        {
            @Override
            public void paint(Graphics g)
            {
                g.clearRect(0,0,600,600);
                //画线
                for(int i=0;i<40;i++)
                {
                    g.drawLine(0,i*15,600,i*15);
                }

                for(int i=0;i<40;i++)
                {
                    g.drawLine(i*15,0,i*15,600);
                }

                LinkedList<Node>body=snake.getBody();
                for(Node node:body)
                {
                    g.fillRect(node.getX()*15,node.getY()*15,15,15);
                }

                g.setColor(Color.RED);
                g.fillRect(food.getX()*15,food.getY()*15,15,15);



            }
        };
        add(jPanel);
    }

    public static void main(String[] args)
    {
        new MainFrame().setVisible(true);
    }
}
