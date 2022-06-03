package es.ieslavereda.Server.Model.CarService;

import es.ieslavereda.Server.Model.entity.Car;
import es.ieslavereda.Server.Model.entity.Result;

public interface ICarService {
    Result<Car> addCar(Car car);
    Result<Car> updateCar(Car car);
    Result<Car> deleteCar(String matricula);
    Result<Car> checkCar(String matricula);
}
