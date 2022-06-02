package es.ieslavereda.Server.Model.entity;

public enum Type {
    COCHE("COCHE"), MOTO("MOTO"), BICICLETA("BICICLETA"), PATINETE("PATINETE");

    private String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
