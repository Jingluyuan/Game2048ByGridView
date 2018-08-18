package com.game.game2048;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jingluyuan on 6/23/18.
 */

public class GameView extends GridLayout {

    private Card[][] cards = new Card[4][4];
    private List<Point> points = new ArrayList<>();


    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialGame();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialGame();
    }

    public GameView(Context context) {
        super(context);
        initialGame();
    }



    private void initialGame()
    {
        setColumnCount(4);
        setBackgroundColor(0xffbbada0);
        setOnTouchListener(new OnTouchListener() {

            private float start_x,start_y,change_x,change_y;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        start_x = motionEvent.getX();
                        start_y = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        change_x = motionEvent.getX() - start_x;
                        change_y = motionEvent.getY() - start_y;

                        if (Math.abs(change_x)>Math.abs(change_y))    //if abs(change_x) > abs(change_y) ,then it means that item is sliding left or right
                        {
                            if (change_x < -5)
                            {
                                Left();
                            }else if (change_x > 5)
                            {
                                Right();
                            }
                        }else                                       // means item is sliding up or down
                        {
                            if (change_y < -5)
                            {
                                Up();
                            }else if (change_y > 5)
                            {
                                Down();
                            }
                        }
                        break;
                    default:
                        break;

                }
                return true;
            }
        });
    }
    @Override
    protected void onSizeChanged(int w, int h, int ow, int oh)
    {
        super.onSizeChanged(w,h,ow,oh);
        int cardwidth = (Math.min(w,h)-10)/4;
        addCard(cardwidth, cardwidth);
       start();
    }

    private void addCard(int cardwidth, int cardheight)
    {
        Card card;
        for (int i =0; i<4;i++)
        {
            for (int j=0;j<4;j++)
            {
                card = new Card(getContext());
                card.setNum(0);
                //card.setBGColor(card.getNum());
                addView(card, cardwidth,cardheight);
                cards[j][i] = card;
            }
        }
    }

    private void start()
    {
        MainActivity.getMainActivity().clear();
        for (int y=0;y<4;y++)
        {
            for (int x=0;x<4;x++)
            {
                cards[x][y].setNum(0);
            }
        }

        addRandomNum();
        addRandomNum();
    }


    public void addRandomNum()
    {
        points.clear();

        for (int y=0;y<4;y++)
        {
            for (int x=0;x<4;x++)
            {
               if (cards[x][y].getNum() <= 0)
               {
                   points.add(new Point(x,y));
               }
            }
        }

        Point p = points.remove((int)(Math.random()*points.size()));
        cards[p.x][p.y].setNum(Math.random()>0.1? 2:4);
        cards[p.x][p.y].setBGColor(cards[p.x][p.y].getNum());
    }

    private void Left()
    {
        boolean flag = false;

        for (int y=0;y<4;y++)
        {
            for(int x=0;x<4;x++)
            {
                for (int x1 =x+1;x1<4;x1++)
                {
                    if (cards[x1][y].getNum() > 0)
                    {
                        if (cards[x][y].getNum()<=0)
                        {
                            cards[x][y].setNum(cards[x1][y].getNum());
                            cards[x][y].setBGColor(cards[x][y].getNum());
                            cards[x1][y].setNum(0);
                            cards[x1][y].setBGColor(cards[x1][y].getNum());
                            x--;
                            flag = true;
                        }else if (cards[x][y].equals(cards[x1][y]))
                        {
                            cards[x][y].setNum(cards[x1][y].getNum()*2);
                            cards[x][y].setBGColor(cards[x][y].getNum());
                            cards[x1][y].setNum(0);
                            cards[x1][y].setBGColor(cards[x1][y].getNum());
                            MainActivity.getMainActivity().add(cards[x][y].getNum());
                            flag = true;
                        }
                        break;
                    }

                }
            }
        }
        if (flag)
        {
            addRandomNum();
            IsFinish();
        }
    }

    private void Right()
    {
        boolean flag = false;

        for (int y=0;y<4;y++)
        {
            for(int x=3;x>=0;x--)
            {
                for (int x1 =x-1;x1>0;x1--)
                {
                    if (cards[x1][y].getNum() > 0)
                    {
                        if (cards[x][y].getNum()<=0)
                        {
                            cards[x][y].setNum(cards[x1][y].getNum());
                            cards[x][y].setBGColor(cards[x][y].getNum());
                            cards[x1][y].setNum(0);
                            cards[x1][y].setBGColor(cards[x1][y].getNum());
                            x++;
                            flag = true;
                        }else if (cards[x][y].equals(cards[x1][y]))
                        {
                            cards[x][y].setNum(cards[x][y].getNum()*2);
                            cards[x][y].setBGColor(cards[x][y].getNum());
                            cards[x1][y].setNum(0);
                            cards[x1][y].setBGColor(cards[x1][y].getNum());
                            MainActivity.getMainActivity().add(cards[x][y].getNum());
                            flag = true;
                        }
                        break;
                    }

                }
            }
        }
        if (flag)
        {
            addRandomNum();
            IsFinish();
        }
    }

    private void Up()
    {
        boolean flag = false;

        for (int x=0;x<4;x++)
        {
            for(int y=0;y<4;y++)
            {
                for (int y1 =y+1;y1<4;y1++)
                {
                    if (cards[x][y1].getNum() > 0)
                    {
                        if (cards[x][y].getNum()<=0)
                        {
                            cards[x][y].setNum(cards[x][y1].getNum());
                            cards[x][y].setBGColor(cards[x][y].getNum());
                            cards[x][y1].setNum(0);
                            cards[x][y1].setBGColor(cards[x][y1].getNum());
                            y--;
                            flag = true;
                        }else if (cards[x][y].equals(cards[x][y1]))
                        {
                            cards[x][y].setNum(cards[x][y].getNum()*2);
                            cards[x][y].setBGColor(cards[x][y].getNum());
                            cards[x][y1].setNum(0);
                            cards[x][y1].setBGColor(cards[x][y1].getNum());
                            MainActivity.getMainActivity().add(cards[x][y].getNum());
                            flag = true;
                        }
                        break;
                    }

                }
            }
        }
        if (flag)
        {
            addRandomNum();
            IsFinish();
        }
    }

    private void Down()
    {
        boolean flag = false;

        for (int x=0;x<4;x++)
        {
            for(int y=3;y>=0;y--)
            {
                for (int y1 =y-1;y1>0;y1--)
                {
                    if (cards[x][y1].getNum() > 0)
                    {
                        if (cards[x][y].getNum()<=0)
                        {
                            cards[x][y].setNum(cards[x][y1].getNum());
                            cards[x][y].setBGColor(cards[x][y].getNum());
                            cards[x][y1].setNum(0);
                            cards[x][y1].setBGColor(cards[x][y1].getNum());
                            y++;
                            flag = true;
                        }else if (cards[x][y].equals(cards[x][y1]))
                        {
                            cards[x][y].setNum(cards[x][y].getNum()*2);
                            cards[x][y].setBGColor(cards[x][y].getNum());
                            cards[x][y1].setNum(0);
                            cards[x][y1].setBGColor(cards[x][y1].getNum());
                            MainActivity.getMainActivity().add(cards[x][y].getNum());
                            flag = true;
                        }
                        break;
                    }

                }
            }
        }
        if (flag)
        {
            addRandomNum();
            IsFinish();
        }
    }

    private void IsFinish()
    {
        boolean finish = true;

        ALL:
        for (int y=0; y<4;y++)
        {
            for (int x=0;x<4;x++)
            {
                if (cards[x][y].getNum()==0
                                        ||(x>0&&cards[x][y].equals(cards[x-1][y]))
                                        ||(x<3&&cards[x][y].equals(cards[x+1][y]))
                                        ||(y>0&&cards[x][y].equals(cards[x][y-1]))
                                        ||(y<3&&cards[x][y].equals(cards[x][y+1])))
                {
                    finish =false;
                    break ALL;
                }
            }
        }

        if (finish)
        {
            new AlertDialog.Builder(getContext())
                        .setTitle("Game Over")
                        .setMessage("Score: "+MainActivity.getMainActivity().getScore())
                        .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                start();
                            }
                        })
                        .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                MainActivity.getMainActivity().finish();
                            }
                        }).show();
        }
    }


}
