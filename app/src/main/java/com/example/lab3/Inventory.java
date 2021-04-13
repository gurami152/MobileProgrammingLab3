package com.example.lab3;

import java.util.Date;
import java.util.UUID;

public class Inventory {
    private UUID mId; // ідентифікатор інвентарю
    private String mTitle; // назва
    private Date mDate; // дата внесення
    private boolean mSolved; // статус

    public Inventory() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean ismSolved() {
        return mSolved;
    }

    public void setmSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }
}
