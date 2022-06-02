package es.ieslavereda.Server.Model.entity;

public class Bike extends Vehicle {
    private String type;

    public Bike(Type type, String matricula, float price, String marca, String descripcion, Color color, int bateria, State estado, String carnet, String type1) {
        super(type, matricula, price, marca, descripcion, color, bateria, estado, carnet);
        this.type = type1;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString() + "Bike{" +
                "type='" + type + '\'' +
                '}';
    }
}
