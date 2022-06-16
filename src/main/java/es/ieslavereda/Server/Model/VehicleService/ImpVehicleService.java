package es.ieslavereda.Server.Model.VehicleService;

import es.ieslavereda.Model.MyDataSource;
import es.ieslavereda.Server.Model.entity.Color;
import es.ieslavereda.Server.Model.entity.State;
import es.ieslavereda.Server.Model.entity.Type;
import es.ieslavereda.Server.Model.entity.Vehicle;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.util.*;

public class ImpVehicleService implements IVehicleService{

    @Override
    public List<Vehicle> getVehicles(Type type) {
        List<Vehicle> vehicles = new ArrayList<>();
        ResultSet rs;

        String sql = "{call gestionvehiculos.listarVehículos(?,?)}";

        try (Connection con = MyDataSource.getOracleDataSource().getConnection();
             CallableStatement cs = con.prepareCall(sql);){

            cs.setString(1 ,type.getType());
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();

            rs = (ResultSet) cs.getObject(2);

            String matricula;
            float precio;
            String marca;
            String color;
            String estado;
            Color c;
            State s;

            while (rs.next()) {
                matricula = rs.getString("c1");
                precio = rs.getFloat("n1");
                marca = rs.getString("c2");
                color = rs.getString("c4");
                estado = rs.getString("c5");

                switch (color) {
                    case "rojo":
                        c = Color.ROJO;
                        break;
                    case "verde":
                        c = Color.VERDE;
                        break;
                    case "amarillo":
                        c = Color.AMARILLO;
                        break;
                    case "blanco":
                        c = Color.BLANCO;
                        break;
                    case "negro":
                        c = Color.NEGRO;
                        break;
                    case "azul":
                        c = Color.AZUL;
                        break;
                    default:
                        c = Color.BLANCO;
                }
                switch (estado) {
                    case "preparado":
                        s = State.PREPARADO;
                        break;
                    case "alquilado":
                        s = State.ALQUILADO;
                        break;
                    case "reservado":
                        s = State.RESERVADO;
                        break;
                    case "taller":
                        s = State.TALLER;
                        break;
                    case "baja":
                        s = State.BAJA;
                        break;
                    default:
                        s = State.PREPARADO;
                }

                vehicles.add(new Vehicle(type, matricula, precio, marca, c, s));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return vehicles;
    }

    @Override
    public List<Vehicle> getVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();

        for (Type t : Type.values()) {
            vehicles.addAll(getVehicles(t));
        }

        return vehicles;
    }
}
