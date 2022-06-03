package es.ieslavereda.Server.Model.BikeService;

import es.ieslavereda.Server.Model.entity.Bike;
import es.ieslavereda.Server.Model.entity.Result;

public interface IBikeService {
    Result<Bike> addBike(Bike bike);
    Result<Bike> updateBike(Bike bike);
    Result<Bike> deleteBike(String matricula);
    Result<Bike> checkBike(String matricula);
}
