package CarFile;

public class VehicleInfo {
    private String VIN;
    private double Mileage;
    private double GasConsumption;
    private double MilesUntilOilChange;
    private double EngineSize;


    public String getVIN() {
        return VIN;
    }

    public double getMileage() {
        return Mileage;
    }

    public double getGasConsumption() {
        return GasConsumption;
    }

    public double getMilesUntilOilChange() {
        return MilesUntilOilChange;
    }

    public double getEngineSize() {
        return EngineSize;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public void setMileage(double mileage) {
        Mileage = mileage;
    }

    public void setGasConsumption(double gasConsumption) {
        GasConsumption = gasConsumption;
    }

    public void setMilesUntilOilChange(double milesUntilOilChange) {
        MilesUntilOilChange = milesUntilOilChange;
    }

    public void setEngineSize(double engineSize) {
        EngineSize = engineSize;
    }
}
