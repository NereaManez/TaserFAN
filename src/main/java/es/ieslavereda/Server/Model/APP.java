package es.ieslavereda.Server.Model;

import es.ieslavereda.Server.Controller.*;

import static spark.Spark.*;

public class APP {
    public static void main(String[] args) {
        post(API.Routes.EMPLEADO, EmployeeController::authenticate, new JsonTransformer<>());
        get(API.Routes.VEHICLES, VehicleController::getVehicles , new JsonTransformer<>());

        //Car
        post(API.Routes.CAR, CarController::insertCar, new JsonTransformer<>());
        get(API.Routes.CAR, CarController::checkCar, new JsonTransformer<>());
        put(API.Routes.CAR, CarController::updateCar, new JsonTransformer<>());
        delete(API.Routes.CAR, CarController::deleteCar, new JsonTransformer<>());

        //Bike
        post(API.Routes.BIKE, BikeController::insertBike, new JsonTransformer<>());
        get(API.Routes.BIKE, BikeController::checkBike, new JsonTransformer<>());
        put(API.Routes.BIKE, BikeController::updateBike, new JsonTransformer<>());
        delete(API.Routes.BIKE, BikeController::deleteBike, new JsonTransformer<>());

        //Motorbike
        post(API.Routes.MOTO, MotorbikeController::insertMotorbike, new JsonTransformer<>());
        get(API.Routes.MOTO, MotorbikeController::checkMotorbike, new JsonTransformer<>());
        put(API.Routes.MOTO, MotorbikeController::updateMotorbike, new JsonTransformer<>());
        delete(API.Routes.MOTO, MotorbikeController::deleteMotorbike, new JsonTransformer<>());

        //Scooter
        post(API.Routes.SCOOTER, ScooterController::insertScooter, new JsonTransformer<>());
        get(API.Routes.SCOOTER, ScooterController::checkScooter, new JsonTransformer<>());
        put(API.Routes.SCOOTER, ScooterController::updateScooter, new JsonTransformer<>());
        delete(API.Routes.SCOOTER, ScooterController::deleteScooter, new JsonTransformer<>());
    }
}
