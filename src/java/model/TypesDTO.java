package model;

public class TypesDTO {

    private int typeID;
    private String typeName;
    private boolean status;

    public TypesDTO() {
    }

    public TypesDTO(int typeID, String typeName, boolean status) {
        this.typeID = typeID;
        this.typeName = typeName;
        this.status = status;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TypesDTO{" + "typeID=" + typeID + ", typeName=" + typeName + ", status=" + status + '}';
    }

}
