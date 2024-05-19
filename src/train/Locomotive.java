package train;

import java.util.List;

public class Locomotive {

    private String serialNumber; // 9 characters, alphanumeric
    private final List<Railcar> railcars = new java.util.ArrayList<>();

    private static final String serialNumberRegex = "^[A-Z0-9]{9}$";
    private static final int maxRailcars = 50;
    private static final int maxRailcarWeight = 3900;

    public Locomotive(String serialNumber) throws InvalidParameterException {
        setSerialNumber(serialNumber);
    }

    private void setSerialNumber(String serialNumber) throws InvalidParameterException {
        if (serialNumber.matches(serialNumberRegex)) {
            this.serialNumber = serialNumber;
        } else {
            throw new InvalidParameterException("Serial number must be 9 alphanumeric characters");
        }
    }

    public void addRailcar(Railcar railcar) throws InvalidParameterException {
        if (railcar.getWeight() + getTotalRailcarWeight() > maxRailcarWeight) {
            throw new InvalidParameterException("Cannot add railcar due to weight limit exceeded");
        }
        if (railcars.size() + 1 > maxRailcars) {
            throw new InvalidParameterException("Cannot add railcar due to max railcar limit exceeded");
        }
        railcars.add(railcar);
    }

    public int getRailcarCount() {
        return railcars.size();
    }

    public int getTotalRailcarWeight() {
        int totalWeight = 0;
        for (Railcar railcar : railcars) {
            totalWeight += railcar.getWeight() + Railcar.EMPTY_WEIGHT;
        }
        return totalWeight;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Locomotive{" +
                "serialNumber='" + serialNumber + '\'' +
                ", maxRailcars=" + maxRailcars +
                ", num Railcars=" + getRailcarCount() +
                ", maxRailcarWeight=" + maxRailcarWeight + " tons" +
                ", Total Railcar weight=" + getTotalRailcarWeight() + " tons" +
                '}');
        for (Railcar railcar : railcars) {
            sb.append("\n").append("\t").append(railcar);
        }
        return sb.toString();
    }
}
