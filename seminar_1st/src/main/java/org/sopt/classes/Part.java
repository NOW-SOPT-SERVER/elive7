package org.sopt.classes;

public enum Part {
    SERVER("SERVER"),
    WEB("WEB"),
    ANDROIP("ANDROID"),
    DESIGN("DESIGN"),
    PLAN("PLAN"),
    ;
    public String part;
    Part(String part){
        this.part = part;
    }
    public String getPart(){
        return this.part;
    }
}

