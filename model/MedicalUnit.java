package model;

public class MedicalUnit {
    
    private int unitID;
    private String unitName;

    public MedicalUnit() {}

    public MedicalUnit(int unitID, String unitName) {
        this.unitID = unitID;
        this.unitName = unitName;
    }

    public int getUnitID() {
        return unitID;
    }
    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public String getUnitName() {
        return unitName;
    }
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    @Override
    public String toString() {
        return "MedicalUnit [unitID=" + unitID + ", unitName=" + unitName + "]";
    }
}
