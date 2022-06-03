package es.ieslavereda.Server.Controller;

import es.ieslavereda.Server.Model.Motorbike.IMotorbikeService;
import es.ieslavereda.Server.Model.Motorbike.ImpMotorbikeService;
import es.ieslavereda.Server.Model.JsonTransformer;
import es.ieslavereda.Server.Model.entity.Motorbike;
import es.ieslavereda.Server.Model.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

public class MotorbikeController {
    static Logger logger = LoggerFactory.getLogger(MotorbikeController.class);
    private static IMotorbikeService service = new ImpMotorbikeService();
    private static JsonTransformer<Motorbike> jsonTransformer = new JsonTransformer<>();

    public static Result insertMotorbike(Request req, Response res){
        logger.info("Añadiendo moto");
        String body = req.body();

        Motorbike m = jsonTransformer.getObject(body, Motorbike.class);
        Result result = service.addMoto(m);
        
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        
        return result;
    }

    public static Result updateMotorbike(Request req, Response res){
        logger.info("Modificando moto");
        String body = req.body();

        Motorbike m = jsonTransformer.getObject(body, Motorbike.class);
        Result result = service.updateMoto(m);

        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }

        return result;
    }

    public static Result deleteMotorbike(Request req, Response res){
        logger.info("Borrando moto");
        String body = req.body();

        Motorbike m = jsonTransformer.getObject(body, Motorbike.class);
        Result result = service.deleteMoto(m.getMatricula());

        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }

        return result;
    }


    public static Result<Motorbike> checkMotorbike(Request req, Response res){
        logger.info("Consultado moto");
        String matricula = req.queryParams("matricula");

        Result result = service.checkMoto(matricula);

        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }

        return result;
    }
}
