package es.ieslavereda.Server.Controller;

import es.ieslavereda.Server.Model.BikeService.IBikeService;
import es.ieslavereda.Server.Model.BikeService.ImpBikeService;
import es.ieslavereda.Server.Model.JsonTransformer;
import es.ieslavereda.Server.Model.entity.Bike;
import es.ieslavereda.Server.Model.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

public class BikeController {
    static Logger logger = LoggerFactory.getLogger(BikeController.class);
    private static IBikeService service = new ImpBikeService();
    private static JsonTransformer<Bike> jsonTransformer = new JsonTransformer<>();

    public static Result insertBike(Request req, Response res){
        logger.info("Añadiendo bicicleta");
        String body = req.body();

        Bike b = jsonTransformer.getObject(body, Bike.class);
        Result result = service.addBike(b);

        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }

        return result;
    }

    public static Result updateBike(Request req, Response res){
        logger.info("Modificando bicicleta");
        String body = req.body();

        Bike b = jsonTransformer.getObject(body, Bike.class);
        Result result = service.updateBike(b);

        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }

        return result;
    }

    public static Result deleteBike(Request req, Response res){
        logger.info("Borrando bicicleta");
        String body = req.body();

        Bike b = jsonTransformer.getObject(body, Bike.class);
        Result result = service.deleteBike(b.getMatricula());

        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }

        return result;
    }


    public static Result<Bike> checkBike(Request req, Response res){
        logger.info("Consultado bicicleta");
        String matricula = req.queryParams("matricula");

        Result result = service.checkBike(matricula);

        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }

        return result;
    }
}
