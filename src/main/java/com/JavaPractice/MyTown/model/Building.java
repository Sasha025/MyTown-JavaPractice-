package com.JavaPractice.MyTown.model;

public class Building {
    private Long id;
    private String built;
    private int creationDate;
    private String title;
    private String owner;
    private String typeBuilding;
    private String address;
    private int floors;

    public Building(long id, String built, int creationDate, String title, String owner, String typeBuilding, String address, int floors) {
        this.id= id;
        this.built=built;
        this.creationDate= creationDate;
        this.title=title;
        this.owner=owner;
        this.typeBuilding=typeBuilding;
        this.address=address;
        this.floors=floors;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getBuilt() {return built;}
    public void setBuilt(String built) {this.built = built;}

    public int getCreationDate() { return creationDate; }
    public void setCreationDate(int creationDate) {
        this.creationDate = creationDate;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTypeBuilding() {
        return typeBuilding;
    }
    public void setTypeBuilding(String typeBuilding) {
        this.typeBuilding = typeBuilding;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public int getFloors() {
        return floors;
    }
    public void setFloors(int floors) {
        this.floors = floors;
    }
}
/*
    public Building() {
        this.id=Long.parseLong("-1");
        this.built=false;
        this.creationDate= 0;
        this.title="none";
        this.owner="none";
        this.typeBuilding="none";
        this.address="none";
        this.floors=0;
    }
*/