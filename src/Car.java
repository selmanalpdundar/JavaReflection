public class Car extends Vehicle implements Movable, Breakable {
    private int model;
    private int color;
    private String year;

    public Car(String vehicleType, int price, int model, int color, String year){
        super(vehicleType,price);
        setColor(color);
        setModel(model);
        setYear(year);

    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public void move() {
        System.out.println("Car is moving ....");
    }

    @Override
    public void broke() {
        System.out.println("Car was broked ....");
    }
}
