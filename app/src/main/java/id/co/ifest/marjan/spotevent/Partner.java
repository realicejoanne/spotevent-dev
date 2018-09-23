package id.co.ifest.marjan.spotevent;

public class Partner {
    private String name, quantity, nTypes, info;

    Partner(String name, String quantity, String nTypes, String info){
        this.name = name;
        this.quantity = quantity;
        this.nTypes = nTypes;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getnTypes() {
        return nTypes;
    }

    public void setnTypes(String nTypes) {
        this.nTypes = nTypes;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
