package es.ieslavereda.Server.Controller;

import es.ieslavereda.Server.Model.JsonTransformer;
import es.ieslavereda.Server.Model.VehicleService.IVehicleService;
import es.ieslavereda.Server.Model.VehicleService.ImpVehicleService;
import es.ieslavereda.Server.Model.entity.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.util.List;

public class VehicleController {
    static Logger logger = LoggerFactory.getLogger(Vehicle.class);
    private static JsonTransformer<Vehicle> jsonTransformer = new JsonTransformer<>();
    private static IVehicleService service = new ImpVehicleService();

    public static List<Vehicle> getVehicles(Request req, Response res) {
        logger.info("Recogiendo vehiculos");

        return service.getVehicles();
    }
}
