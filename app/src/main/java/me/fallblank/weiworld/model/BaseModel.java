package me.fallblank.weiworld.model;

import android.content.Context;

/**
 * Created by fallb on 2017/3/6.
 * Base model for MVP model
 */

public abstract class BaseModel {
    protected Context mContext;
    
    public BaseModel(Context context) {
        this.mContext = context;
    }
    
    //store model state
    protected abstract void store();
    
    //restore model state
    protected abstract void restore();
}
