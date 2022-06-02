package es.ieslavereda.Server.Model.entity;

public class Scooter extends Vehicle {
    private float numWheels;
    private float size;

    public Scooter(Type type, String matricula, float price, String marca, String descripcion, Color color, int bateria, State estado, String carnet, float numWheels, float size) {
        super(type, matricula, price, marca, descripcion, color, bateria, estado, carnet);
        this.numWheels = numWheels;
        this.size = size;
    }

    public float getNumWheels() {
        return numWheels;
    }

    public void setNumWheels(float numWheels) {
        this.numWheels = numWheels;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return super.toString() + "Scooter" +
                "numWheels=" + numWheels +
                ", size=" + size +
                '}';
    }
}
