package es.ieslavereda.Server.Model.ScooterService;

import es.ieslavereda.Model.MyDataSource;
import es.ieslavereda.Server.Model.entity.*;

import java.sql.*;

public class ImpScooterService implements IScooterService {
    @Override
    public Result<Scooter> addScooter(Scooter scooter) {
        String sql = "{call GESTIONVEHICULOS.insertarPatinete(?,?,?,?,?,?,?,?,?,?,?)}";

        try(Connection con = MyDataSource.getOracleDataSource().getConnection();
            CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, scooter.getMatricula());
            cs.setFloat(2, scooter.getPrice());
            cs.setString(3, scooter.getMarca());
            cs.setString(4, scooter.getDescripcion());
            cs.setString(5, scooter.getColor().getColor());
            cs.setInt(6, scooter.getBateria());
            cs.setDate(7, scooter.getDate());
            cs.setString(8, scooter.getEstado().getstate());
            cs.setString(9, scooter.getCarnet());
            cs.setFloat(10, scooter.getNumWheels());
            cs.setFloat(11, scooter.getSize());

            cs.execute();

            return new Result.Success<>(200);
        } catch (SQLException throwables) {
            return new Result.Error(throwables.getErrorCode(), throwables.getMessage());
        }
    }

    @Override
    public Result<Scooter> updateScooter(Scooter scooter) {
        String sql = "{call GESTIONVEHICULOS.actualizarPatinete(?,?,?,?,?,?,?,?,?,?,?)}";

        try(Connection con = MyDataSource.getOracleDataSource().getConnection();
            CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, scooter.getMatricula());
            cs.setFloat(2, scooter.getPrice());
            cs.setString(3, scooter.getMarca());
            cs.setString(4, scooter.getDescripcion());
            cs.setString(5, scooter.getColor().getColor());
            cs.setInt(6, scooter.getBateria());
            cs.setDate(7, scooter.getDate());
            cs.setString(8, scooter.getEstado().getstate());
            cs.setString(9, scooter.getCarnet());
            cs.setFloat(10, scooter.getNumWheels());
            cs.setFloat(11, scooter.getSize());

            cs.execute();
            return new Result.Success<>(200);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result.Error(throwables.getErrorCode(), throwables.getMessage());
        }
    }

    @Override
    public Result<Scooter> deleteScooter(String matricula) {
        String sql = "{call GESTIONVEHICULOS.borrarPatinete(?)}";
        boolean deleted = false;

        try(Connection con = MyDataSource.getOracleDataSource().getConnection();
            CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, matricula);
            deleted = cs.execute();

        } catch (SQLException throwables) {
            return new Result.Error(throwables.getErrorCode(), throwables.getMessage());
        }
        if (deleted) {
            return new Result.Success<>(200);
        } else {
            return new Result.Error(404, "No se ha borrado ninguna moto");
        }
    }

    @Override
    public Result<Scooter> checkScooter(String matricula) {
        String sql = "{call GESTIONVEHICULOS.consultarPatinete(?,?,?,?,?,?,?,?,?,?,?)}";
        Scooter s = null;

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
            cs.registerOutParameter(10, Types.FLOAT);
            cs.registerOutParameter(11, Types.FLOAT);

            cs.execute();

            String r_matricula = matricula;
            float r_precioHora = cs.getFloat(2);
            String r_marca = cs.getString(3);
            String r_descripcion = cs.getString(4);
            String r_color = cs.getString(5);
            int r_bateria = cs.getInt(6);
            Date date = cs.getDate(7);
            String r_State = cs.getString(8);
            String r_idCarnet = cs.getString(9);
            int r_numruedas = cs.getInt(10);
            float r_tamanyo = cs.getInt(11);
            Type tipo = Type.PATINETE;

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
            switch (r_State){
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

            s = new Scooter(tipo, r_matricula, r_precioHora, r_marca, r_descripcion, col, r_bateria, e, r_idCarnet, date, r_numruedas, r_tamanyo);

        } catch (SQLException throwables) {
            return new Result.Error(throwables.getErrorCode(), throwables.getMessage());
        }
        if (s != null) {
            return new Result.Success<Scooter>(s);
        } else {
            return new Result.Error(404, "No se ha encontrado el patin");
        }
    }
}
