package com.flux.kituri.app.data;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;
@Table("user")
public class UserData extends Entry{
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    public int _id;

    @Column("error")
    private boolean error;


    public void setError(boolean error){
        this.error = error;
    }
    public boolean getError(){
        return this.error;
    }

}