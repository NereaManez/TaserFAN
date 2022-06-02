package es.ieslavereda.Server.Model.EmployeeService;

import es.ieslavereda.Model.AuthenticateData;
import es.ieslavereda.Server.Model.entity.Employee;
import es.ieslavereda.Model.MyDataSource;
import es.ieslavereda.Server.Model.entity.Result;

import javax.sql.DataSource;
import java.sql.*;

public class ImpEmployeeService implements IEmployeeService {

    @Override
    public Result<Employee> authenticate(AuthenticateData ad) {
        DataSource ds = MyDataSource.getOracleDataSource();
        String dni, nombre, apellidos;
        String email = ad.getEmail();
        String password = ad.getPassword();
        String sql = "SELECT * FROM empleado WHERE email = ? AND password = ENCRYPT_PASWD.encrypt_val(?)";

        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            int pos = 0;
            pstmt.setString(++pos, email);
            pstmt.setString(++pos,password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                dni = rs.getString("dni");
                nombre = rs.getString("nombre");
                apellidos = rs.getString("apellidos");

                Employee e = new Employee(dni, nombre, apellidos, email);
                return new Result.Success<>(e);
            } else
                return new Result.Error(404, "Email y/o password incorrectos.");
        } catch (SQLException throwables) {
            return new Result.Error(404, throwables.getMessage());
        }
    }
}
