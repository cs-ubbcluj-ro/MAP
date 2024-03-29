package Domain;

public class Car extends Entity{
    private String model;

    public Car(int id, String model) {
        super(id);
        this.model = model;
    }

    public Car(String line) {
        super(Integer.parseInt(line.split(",")[0]));
        model = line.split(",")[1];
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString()
    {
        return "id: " + id + ", model: " + model;
    }

    public String toStringLine()
    {
        return id + "," + model + "\n";
    }
}
