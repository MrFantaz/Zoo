package food;

public enum Food {
    CARROT(50), MEAT(120);
    private double energyValue;

    Food(double value) {
        energyValue = value;
    }

    public double getEnergyValue() {
        return energyValue;
    }

}
