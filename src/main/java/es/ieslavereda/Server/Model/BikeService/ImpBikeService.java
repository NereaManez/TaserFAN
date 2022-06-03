package es.ieslavereda.Server.Model.BikeService;

import es.ieslavereda.Model.MyDataSource;
import es.ieslavereda.Server.Model.entity.*;

import java.sql.*;

public class ImpBikeService implements IBikeService {
    @Override
    public Result<Bike> addBike(Bike bike) {
        String sql = "{call GESTIONVEHICULOS.insertarBici(?,?,?,?,?,?,?,?,?,?)}";

        try(Connection con = MyDataSource.getOracleDataSource().getConnection();
            CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, bike.getMatricula());
            cs.setFloat(2, bike.getPrice());
            cs.setString(3, bike.getMarca());
            cs.setString(4, bike.getDescripcion());
            cs.setString(5, bike.getColor().getColor());
            cs.setInt(6, bike.getBateria());
            cs.setDate(7, bike.getDate());
            cs.setString(8, bike.getEstado().getstate());
            cs.setString(9, bike.getCarnet());
            cs.setString(10, bike.getTipo());

            cs.execute();
            return new Result.Success<>(200);

        } catch (SQLException throwables) {
            return new Result.Error(throwables.getErrorCode(), throwables.getMessage());
        }
    }

    @Override
    public Result<Bike> updateBike(Bike bike) {
        String sql = "{call GESTIONVEHICULOS.actualizarBici(?,?,?,?,?,?,?,?,?,?)}";

        try(Connection con = MyDataSource.getOracleDataSource().getConnection();
            CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1,bike.getMatricula());
            cs.setFloat(2, bike.getPrice());
            cs.setString(3,bike.getMarca());
            cs.setString(4, bike.getDescripcion());
            cs.setString(5, bike.getColor().getColor());
            cs.setInt(6, bike.getBateria());
            cs.setDate(7, bike.getDate());
            cs.setString(8, bike.getEstado().getstate());
            cs.setString(9, bike.getCarnet());
            cs.setString(10, bike.getTipo());

            cs.execute();
            return new Result.Success<>(200);

        } catch (SQLException throwables) {
            return new Result.Error(throwables.getErrorCode(), throwables.getMessage());
        }
    }

    @Override
    public Result<Bike> deleteBike(String matricula) {
        String sql = "{call GESTIONVEHICULOS.borrarBici(?)}";
        boolean deleted = false;

        try(Connection con = MyDataSource.getOracleDataSource().getConnection();
            CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1,matricula);
            deleted = cs.execute();

        } catch (SQLException throwables) {
            return new Result.Error(throwables.getErrorCode(), throwables.getMessage());
        }
        if (deleted) {
            return new Result.Success<>(200);
        } else {
            return new Result.Error(404, "No se ha borrado ninguna bicicleta");
        }
    }

    @Override
    public Result<Bike> checkBike(String matricula) {
        String sql = "{call GESTIONVEHICULOS.consultarBicicleta(?,?,?,?,?,?,?,?,?,?)}";
        Bike b = null;

        try(Connection con = MyDataSource.getOracleDataSource().getConnection();
            CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1,matricula);
            cs.registerOutParameter(2, Types.FLOAT);
            cs.registerOutParameter(3,Types.VARCHAR);
            cs.registerOutParameter(4, Types.VARCHAR);
            cs.registerOutParameter(5, Types.VARCHAR);
            cs.registerOutParameter(6, Types.INTEGER);
            cs.registerOutParameter(7, Types.DATE);
            cs.registerOutParameter(8, Types.VARCHAR);
            cs.registerOutParameter(9, Types.VARCHAR);
            cs.registerOutParameter(10, Types.VARCHAR);

            cs.execute();

            String r_matricula = matricula;
            float r_precioHora = cs.getFloat(2);
            String r_marca = cs.getString(3);
            String r_descripcion = cs.getString(4);
            String r_color = cs.getString(5);
            int r_bateria = cs.getInt(6);
            Date date = cs.getDate(7);
            String r_estado = cs.getString(8);
            String r_idCarnet = cs.getString(9);
            String tipobici = cs.getString(10);
            Type tipo = Type.BICICLETA;

            Color col = Color.NEGRO;
            State e = State.PREPARADO;
            switch (r_color){
                case "verde":
                    col = Color.VERDE;
                    break;
                case "amarillo":
                    col = Color.AMARILLO;
                    break;
                case "rojo":
                    col = Color.ROJO;
                    break;
                case "blanco":
                    col = Color.BLANCO;
                    break;
                case "negro":
                    col = Color.NEGRO;
                    break;
                case "azul":
                    col = Color.AZUL;
                    break;
            }
            switch (r_estado){
                case "preparado":
                    e = State.PREPARADO;
                    break;
                case "baja":
                    e = State.BAJA;
                    break;
                case "taller":
                    e = State.TALLER;
                    break;
                case "reservado":
                    e = State.RESERVADO;
                    break;
                case "alquilado":
                    e = State.ALQUILADO;
                    break;
            }

            b = new Bike(tipo, r_matricula, r_precioHora, r_marca, r_descripcion, col, r_bateria, e, date, r_idCarnet, tipobici);

        } catch (SQLException throwables) {
            return new Result.Error(throwables.getErrorCode(), throwables.getMessage());
        }

        if(b != null) {
            return new Result.Success<Bike>(b);
        } else {
            return new Result.Error(404, "No se ha encontrado la bicicleta");
        }
    }
}
