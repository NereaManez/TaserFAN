package es.ieslavereda.Server.Model.VehicleService;

import es.ieslavereda.Server.Model.entity.Result;
import es.ieslavereda.Server.Model.entity.Type;
import es.ieslavereda.Server.Model.entity.Vehicle;

import java.util.List;

public interface IVehicleService {
    List<Vehicle> getVehicles(Type type);
    List<Vehicle> getVehicles();
}
