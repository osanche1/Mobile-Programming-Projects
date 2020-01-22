package com.example.program4;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
//referenced http://thetechnocafe.com/how-to-use-room-in-android-all-you-need
// -to-know-to-get-started/ for this file.

@Entity(tableName = "expense_table")
public class Expense{
    private String name;
    private String category;
    private String date;
    private Float amount;
    private String note;

    @PrimaryKey(autoGenerate = true)
    private Long ID;



    public String getName(){
        return name;
    }

    public String getCategory(){
        return category;
    }

    public String getDate(){
        return date;
    }

    public Float getAmount(){
        return amount;
    }

    public String getNote(){
        return note;
    }

    public Long getID(){
        return ID;
    }

    public Expense(String name, String category, String date, Float amount, String note){
        this.name = name;
        this.category = category;
        this.date = date;
        this.amount = amount;
        this.note = note;
    }

    public void setID(Long IDValue){
        ID = IDValue;
    }
}
