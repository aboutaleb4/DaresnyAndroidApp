package edu.aucegypt.learningcentershub.service.data;

public class Address {
    String Street, BuildingNo, FloorNo, AptNo, City, Long, Lat;
    String Area;

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public Address() {
    }

    public Address(String street, String buildingNo, String floorNo, String aptNo, String city, String aLong, String lat) {
        Street = street;
        BuildingNo = buildingNo;
        FloorNo = floorNo;
        AptNo = aptNo;
        City = city;
        Long = aLong;
        Lat = lat;
    }

    public String getStreet() {
        return Street;
    }

    public String getBuildingNo() {
        return BuildingNo;
    }

    public String getFloorNo() {
        return FloorNo;
    }

    public String getAptNo() {
        return AptNo;
    }

    public String getCity() {
        return City;
    }

    public String getLong() {
        return Long;
    }

    public String getLat() {
        return Lat;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public void setBuildingNo(String buildingNo) {
        BuildingNo = buildingNo;
    }

    public void setFloorNo(String floorNo) {
        FloorNo = floorNo;
    }

    public void setAptNo(String aptNo) {
        AptNo = aptNo;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setLong(String aLong) {
        Long = aLong;
    }

    public void setLat(String lat) {
        Lat = lat;
    }
}
