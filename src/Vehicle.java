public class Vehicle {
    private String vehicleType;
    private int vehiclePrice;

    public Vehicle(String vehicleType, int vehiclePrice){
        setVehiclePrice(vehiclePrice);
        setVehicleType(vehicleType);
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(int vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }
}
