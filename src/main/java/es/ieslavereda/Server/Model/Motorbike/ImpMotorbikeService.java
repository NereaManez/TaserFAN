package es.ieslavereda.Server.Model.Motorbike;

import es.ieslavereda.Model.MyDataSource;
import es.ieslavereda.Server.Model.entity.*;

import java.sql.*;

public class ImpMotorbikeService implements IMotorbikeService {
    @Override
    public Result<Motorbike> addMoto(Motorbike motorbike) {
        String sql = "{call GESTIONVEHICULOS.insertarMoto(?,?,?,?,?,?,?,?,?,?,?)}";
        
        try(Connection con = MyDataSource.getOracleDataSource().getConnection();
            CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, motorbike.getMatricula());
            cs.setFloat(2, motorbike.getPrice());
            cs.setString(3, motorbike.getMarca());
            cs.setString(4, motorbike.getDescripcion());
            cs.setString(5, motorbike.getColor().getColor());
            cs.setInt(6, motorbike.getBateria());
            cs.setDate(7, motorbike.getDate());
            cs.setString(8, motorbike.getEstado().getstate());
            cs.setString(9, motorbike.getCarnet());
            cs.setFloat(10, motorbike.getMaxVelocity());
            cs.setFloat(11, motorbike.getDisplacement());

            cs.execute();

            return new Result.Success<>(200);
        } catch (SQLException throwables) {
            return new Result.Error(throwables.getErrorCode(), throwables.getMessage());
        }
    }

    @Override
    public Result<Motorbike> updateMoto(Motorbike motorbike) {
        String sql = "{call GESTIONVEHICULOS.actualizarMoto(?,?,?,?,?,?,?,?,?,?,?)}";

        try(Connection con = MyDataSource.getOracleDataSource().getConnection();
            CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, motorbike.getMatricula());
            cs.setFloat(2, motorbike.getPrice());
            cs.setString(3, motorbike.getMarca());
            cs.setString(4, motorbike.getDescripcion());
            cs.setString(5, motorbike.getColor().getColor());
            cs.setInt(6, motorbike.getBateria());
            cs.setDate(7, motorbike.getDate());
            cs.setString(8, motorbike.getEstado().getstate());
            cs.setString(9, motorbike.getCarnet());
            cs.setFloat(10, motorbike.getMaxVelocity());
            cs.setFloat(11, motorbike.getDisplacement());

            cs.execute();
            return new Result.Success<>(200);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result.Error(throwables.getErrorCode(), throwables.getMessage());
        }
    }

    @Override
    public Result<Motorbike> deleteMoto(String matricula) {
        String sql = "{call GESTIONVEHICULOS.borrarMoto(?)}";
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
    public Result<Motorbike> checkMoto(String matricula) {
        String sql = "{call GESTIONVEHICULOS.consultarMoto(?,?,?,?,?,?,?,?,?,?,?)}";
        Motorbike m = null;
        
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
            String r_estado = cs.getString(8);
            String r_idCarnet = cs.getString(9);
            float r_velMax = cs.getFloat(10);
            float r_cilindrada = cs.getFloat(11);
            Type tipo = Type.MOTO;

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

            m = new Motorbike(tipo, r_matricula, r_precioHora, r_marca, r_descripcion, col, r_bateria, e, r_idCarnet, date, r_velMax, r_cilindrada);

        } catch (SQLException throwables) {
            return new Result.Error(throwables.getErrorCode(), throwables.getMessage());
        }

        if (m != null) {
            return new Result.Success<Motorbike>(m);
        } else {
            return new Result.Error(404, "No se ha encontrado la moto");
        }
    }
}
