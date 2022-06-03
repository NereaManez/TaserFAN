package es.ieslavereda.Server.Controller;

import es.ieslavereda.Server.Model.ScooterService.IScooterService;
import es.ieslavereda.Server.Model.ScooterService.ImpScooterService;
import es.ieslavereda.Server.Model.JsonTransformer;
import es.ieslavereda.Server.Model.entity.Scooter;
import es.ieslavereda.Server.Model.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

public class ScooterController {
    static Logger logger = LoggerFactory.getLogger(ScooterController.class);
    private static IScooterService service = new ImpScooterService();
    private static JsonTransformer<Scooter> jsonTransformer = new JsonTransformer<>();

    public static Result insertScooter(Request req, Response res){
        logger.info("Añadiendo patinete");
        String body = req.body();

        Scooter s = jsonTransformer.getObject(body, Scooter.class);
        Result result = service.addScooter(s);
        
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }

        return result;
    }

    public static Result updateScooter(Request req, Response res){
        logger.info("Modificando patinete");
        String body = req.body();

        Scooter s = jsonTransformer.getObject(body, Scooter.class);
        Result result = service.updateScooter(s);

        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }

        return result;
    }

    public static Result deleteScooter(Request req, Response res){
        logger.info("Borrando patinete");
        String body = req.body();

        Scooter s = jsonTransformer.getObject(body, Scooter.class);
        Result result = service.deleteScooter(s.getMatricula());

        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }

        return result;
    }


    public static Result<Scooter> checkScooter(Request req, Response res){
        logger.info("Consultado patinete");
        String matricula = req.queryParams("matricula");

        Result result = service.checkScooter(matricula);

        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }

        return result;
    }
}
