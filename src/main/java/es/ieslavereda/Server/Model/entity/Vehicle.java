package es.ieslavereda.Server.Model.entity;

public class Vehicle {
    private String matricula;
    private float price;
    private String marca;
    private String descripcion;
    private Color color;
    private int bateria;
    private State estado;
    private String carnet;
    private Type type;

    public Vehicle(Type type, String matricula, float price, String marca, String descripcion, Color color, int bateria, State estado, String carnet) {
        this.type = type;
        this.matricula = matricula;
        this.price = price;
        this.marca = marca;
        this.descripcion = descripcion;
        this.color = color;
        this.bateria = bateria;
        this.estado = estado;
        this.carnet = carnet;
    }

    public Vehicle(Type type, String matricula, float price, String marca, Color color, State estado) {
        this.type = type;
        this.matricula = matricula;
        this.price = price;
        this.marca = marca;
        this.color = color;
        this.estado = estado;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    public State getEstado() {
        return estado;
    }

    public void setEstado(State estado) {
        this.estado = estado;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }
}
