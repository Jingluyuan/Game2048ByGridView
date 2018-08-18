package com.game.game2048;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by jingluyuan on 6/23/18.
 */

public class Card extends FrameLayout {
    private TextView label;
    private int num = 0;

    public Card(Context context) {
        super(context);
        label = new TextView(getContext());
        label.setTextSize(32);
        label.setGravity(Gravity.CENTER);


        LayoutParams lp = new LayoutParams(-1,-1);
        lp.setMargins(10,10,0,0);
        addView(label,lp);

        setNum(0);
    }

    public int getNum()
    {
        return num;
    }

    public void setNum(int num)
    {
        this.num = num;
        if(num<=0){
            label.setText("");
        }else{
            label.setText(num+"");
        }
    }


    public void setBGColor(int num)
    {
        switch (num){
            case 2:
                label.setBackgroundColor(0xffFFF68F);
                break;
            case 4:
                label.setBackgroundColor(0xffFFEC8B);
                break;
            case 8:
                label.setBackgroundColor(0xffFFD700);
                break;
            case 16:
                label.setBackgroundColor(0xffFFC125);
                break;
            case 32:
                label.setBackgroundColor(0xffFF890F);
                break;
            case 64:
                label.setBackgroundColor(0xffFFA500);
                break;
            case 128:
                label.setBackgroundColor(0xffFF8C00);
                break;
            case 256:
                label.setBackgroundColor(0xffFF7F24);
                break;
            case 512:
                label.setBackgroundColor(0xffFF4500);
                break;
            case 1024:
                label.setBackgroundColor(0xffFF0000);
                break;
            case 2048:
                label.setBackgroundColor(0xff7FF00);
                break;
            case 4096:
                label.setBackgroundColor(0xff68228B);
                break;
            default:
                label.setBackgroundColor(0x33ffffff);
                break;
        }

    }



    public boolean equals(Card card)
    {
        return this.getNum() == card.getNum();
    }
}
