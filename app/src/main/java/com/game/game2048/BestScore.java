package com.game.game2048;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jingluyuan on 6/24/18.
 */

public class BestScore{
    private SharedPreferences sp;

    public BestScore(Context context)
    {
        sp = context.getSharedPreferences("bestscore",context.MODE_PRIVATE);
    }

    public int getBestScore()
    {
        int bestscore = sp.getInt("bestscore",0);
        return bestscore;
    }

    public void setBestScore(int bestScore)
    {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("bestscore",bestScore);
        editor.commit();
    }
}