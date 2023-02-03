public class MedicalUnit {

    int medUnitsId;
    String medUnitsName;


    public MedicalUnit(int medUnitsId, String medUnitsName){
        this.medUnitsId = medUnitsId;
        this.medUnitsName = medUnitsName;
    }


    public String getMedUnitsName() {
        return medUnitsName;
    }

    public int getMedUnitsId() {
        return medUnitsId;
    }
}