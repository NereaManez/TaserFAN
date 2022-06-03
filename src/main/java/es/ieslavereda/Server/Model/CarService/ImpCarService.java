package es.ieslavereda.Server.Model.CarService;

import es.ieslavereda.Model.MyDataSource;
import es.ieslavereda.Server.Model.BikeService.IBikeService;
import es.ieslavereda.Server.Model.entity.*;

import java.sql.*;

public class ImpCarService implements ICarService {
    @Override
    public Result<Car> addCar(Car car) {
        String sql = "{call GESTIONVEHICULOS.insertarCoche(?,?,?,?,?,?,?,?,?,?,?)}";

        try(Connection con = MyDataSource.getOracleDataSource().getConnection();
            CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, car.getMatricula());
            cs.setDouble(2, car.getPrice());
            cs.setString(3, car.getMarca());
            cs.setString(4, car.getDescripcion());
            cs.setString(5, car.getColor().getColor());
            cs.setInt(6, car.getBateria());
            cs.setDate(7, car.getDate());
            cs.setString(8, car.getEstado().getstate());
            cs.setString(9, car.getCarnet());
            cs.setFloat(10, car.getNumSeats());
            cs.setFloat(11, car.getNumDoors());

            cs.execute();

            return new Result.Success<>(200);
        } catch (SQLException throwables) {
            return new Result.Error(throwables.getErrorCode(), throwables.getMessage());
        }
    }

    @Override
    public Result<Car> updateCar(Car car) {
        String sql = "{call GESTIONVEHICULOS.actualizarCoche(?,?,?,?,?,?,?,?,?,?,?)}";

        try(Connection con = MyDataSource.getOracleDataSource().getConnection();
            CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, car.getMatricula());
            cs.setFloat(2, car.getPrice());
            cs.setString(3, car.getMarca());
            cs.setString(4, car.getDescripcion());
            cs.setString(5, car.getColor().getColor());
            cs.setInt(6, car.getBateria());
            cs.setDate(7, car.getDate());
            cs.setString(8, car.getEstado().getstate());
            cs.setString(9, car.getCarnet());
            cs.setFloat(10, car.getNumSeats());
            cs.setFloat(11, car.getNumDoors());

            cs.execute();

            return new Result.Success<>(200);
        } catch (SQLException throwables) {
            return new Result.Error(throwables.getErrorCode(), throwables.getMessage());
        }
    }

    @Override
    public Result<Car> deleteCar(String matricula) {
        String sql = "{call GESTIONVEHICULOS.borrarCoche(?)}";
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
            return new Result.Error(404, "No se ha borrado ningún coche");
        }
    }

    @Override
    public Result<Car> checkCar(String matricula) {
        String sql = "{call GESTIONVEHICULOS.consultarCoche(?,?,?,?,?,?,?,?,?,?,?)}";
        Car c = null;
        
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
            int r_numPlazas = cs.getInt(10);
            int r_numPuertas = cs.getInt(11);
            Type tipo = Type.COCHE;

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

            c = new Car(tipo, r_matricula, r_precioHora, r_marca, r_descripcion, col, r_bateria, e, r_idCarnet, date, r_numPlazas, r_numPuertas);

        } catch (SQLException throwables) {
            return new Result.Error(throwables.getErrorCode(), throwables.getMessage());
        }
        if(c != null){
            return new Result.Success<Car>(c);
        }else{
            return new Result.Error(404, "No se ha encontrado el coche");
        }
    }
}
