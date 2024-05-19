package train;

public class Railcar {

    private String serialNumber; // 7 characters, alphanumeric, cannot be changed
    private RailcarType type; // Cannot be changed
    private int weight; // in tons

    private static final String serialNumberRegex = "^[A-Z0-9]{7}$";
    public static final int EMPTY_WEIGHT = 30; // in tons
    public static final int MAX_TOTAL_WEIGHT = 130; // in tons


    public Railcar(String serialNumber, RailcarType type, int weight) throws InvalidParameterException {
        setSerialNumber(serialNumber);
        setType(type);
        setContentWeight(weight);
    }

    private void setSerialNumber(String serialNumber) throws InvalidParameterException {
        if (serialNumber == null) {
            throw new InvalidParameterException("Serial number must not be null");
        }
        if (serialNumber.matches(serialNumberRegex)){
            this.serialNumber = serialNumber;
        } else {
            throw new InvalidParameterException("Serial number must be 7 alphanumeric characters");
        }
    }

    public void setType(RailcarType type) throws InvalidParameterException {

        if (type == null){
            throw new InvalidParameterException("Type cannot be null");
        }
        this.type = type;

    }

    public void setContentWeight(int weightIn) throws InvalidParameterException {
        if (weightIn < 0) {
            throw new InvalidParameterException("Weight must not be negative: " + weightIn);
        }
        if (weightIn + EMPTY_WEIGHT > MAX_TOTAL_WEIGHT){
            throw new InvalidParameterException("Weight must not exceed 130 tons: " + (weightIn + EMPTY_WEIGHT) + " > " + MAX_TOTAL_WEIGHT);
        }
        this.weight = weightIn;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public RailcarType getType() {
        return type;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Railcar{" +
                "serialNumber='" + getSerialNumber() + '\'' +
                ", type=" + getType() +
                ", content weight=" + getWeight() + " tons" +
                ", total weight=" + (getWeight() + EMPTY_WEIGHT) + " tons" +
                '}';
    }
}
