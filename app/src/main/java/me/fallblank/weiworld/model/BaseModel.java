package me.fallblank.weiworld.model;

/**
 * Created by fallb on 2017/3/6.
 * Base model for MVP model
 */

public abstract class BaseModel {

    //store model state
    protected abstract void store();

    //restore model state
    protected abstract void restore();
}
