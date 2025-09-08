package org.example;


import java.util.LinkedList;


public class Snake {
    private LinkedList<Node> body;

    private Direction direction=Direction.LEFT;

    private boolean isLiving=true;

    public Snake()
    {
        initSnake();
    }

    private void initSnake()
    {
        body=new LinkedList<>();
        body.add(new Node(16,20));
        body.add(new Node(17,20));
    }

    public void move()
    {
        if(!isLiving)
        {
            return;
        }
        Node head=body.getFirst();
        switch(direction)
        {
            case UP:
                body.addFirst(new Node(head.getX(),head.getY()-1));
                break;
            case DOWN:
                body.addFirst(new Node(head.getX(),head.getY()+1));
                break;
            case LEFT:
                body.addFirst(new Node(head.getX()-1,head.getY()));
                break;
            case RIGHT:
                body.addFirst(new Node(head.getX()+1,head.getY()));
                break;
        }
        head=body.getFirst();
        for(int i=1;i<body.size();i++)
        {
            Node node=body.get(i);
            if(head.getX()==node.getX()&&head.getY()==node.getY())
            {
                isLiving=false;
                break;
            }
        }
        if(head.getX()<0||head.getX()>38||head.getY()<0||head.getY()>38)
        {
            isLiving=false;
        }
        if(isLiving)
        {
            body.removeLast();
        }


    }


    public LinkedList<Node> getBody() {
        return body;
    }

    public void setBody(LinkedList<Node> body) {
        this.body = body;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public void eat(Node food)
    {
        Node head=body.getFirst();
        switch(direction)
        {
            case UP:
                body.addFirst(new Node(head.getX(),head.getY()-1));
                break;
            case DOWN:
                body.addFirst(new Node(head.getX(),head.getY()+1));
                break;
            case LEFT:
                body.addFirst(new Node(head.getX()-1,head.getY()));
                break;
            case RIGHT:
                body.addFirst(new Node(head.getX()+1,head.getY()));
                break;
        }
    }
}
