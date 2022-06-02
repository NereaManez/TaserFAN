package es.ieslavereda.Server.Model;

import es.ieslavereda.Server.Controller.EmployeeController;
import es.ieslavereda.Server.Controller.VehicleController;

import static spark.Spark.*;

public class APP {
    public static void main(String[] args) {
        post(API.Routes.EMPLEADO, EmployeeController::authenticate, new JsonTransformer<>());
        get(API.Routes.VEHICLES, VehicleController::getVehicles , new JsonTransformer<>());
    }
}
