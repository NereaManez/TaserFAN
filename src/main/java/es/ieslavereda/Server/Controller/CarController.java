package es.ieslavereda.Server.Controller;

import es.ieslavereda.Server.Model.CarService.ICarService;
import es.ieslavereda.Server.Model.CarService.ImpCarService;
import es.ieslavereda.Server.Model.JsonTransformer;
import es.ieslavereda.Server.Model.entity.Car;
import es.ieslavereda.Server.Model.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

public class CarController {
    static Logger logger = LoggerFactory.getLogger(CarController.class);
    private static ICarService service = new ImpCarService();
    private static JsonTransformer<Car> jsonTransformer = new JsonTransformer<>();

    public static Result insertCar(Request req, Response res){
        logger.info("Añadiendo coche");
        String body = req.body();

        Car c = jsonTransformer.getObject(body, Car.class);
        Result result = service.addCar(c);

        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }

        return result;
    }

    public static Result updateCar(Request req, Response res){
        logger.info("Modificando coche");
        String body = req.body();

        Car c = jsonTransformer.getObject(body, Car.class);
        Result result = service.updateCar(c);
        
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        
        return result;
    }

    public static Result deleteCar(Request req, Response res){
        logger.info("Borrando coche");
        String params = req.queryParams("matricula");

        Result result = service.deleteCar(params);
        
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        
        return result;
    }


    public static Result<Car> checkCar(Request req, Response res){
        logger.info("Consultado coche");
        String matricula = req.queryParams("matricula");

        Result result = service.checkCar(matricula);
        
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        
        return result;
    }
}
