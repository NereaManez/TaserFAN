package es.ieslavereda.Model;

import oracle.jdbc.datasource.impl.OracleDataSource;
import es.ieslavereda.Properties.MyConfig;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class MyDataSource {
    public static DataSource getOracleDataSource() {

        Properties props = new Properties();
        OracleDataSource oracleDS = null;

        try(FileInputStream fis = new FileInputStream("custom.properties");) {
            props.load(fis);

            String host = MyConfig.getInstance().getOracleDBHost();
            String port = MyConfig.getInstance().getOracleDBPort();
            String user = MyConfig.getInstance().getOracleUsername();
            String password = MyConfig.getInstance().getOraclePassword();

            oracleDS = new OracleDataSource();
            oracleDS.setURL("jdbc:oracle:thin:@" + host + ":" + port + ":xe");
            oracleDS.setUser(user);
            oracleDS.setPassword(password);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return oracleDS;

    }
}
