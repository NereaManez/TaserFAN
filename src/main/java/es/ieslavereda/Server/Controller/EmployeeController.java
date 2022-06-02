package es.ieslavereda.Server.Controller;

import es.ieslavereda.Model.AuthenticateData;
import es.ieslavereda.Server.Model.entity.Result;
import es.ieslavereda.Server.Model.EmployeeService.IEmployeeService;
import es.ieslavereda.Server.Model.EmployeeService.ImpEmployeeService;
import es.ieslavereda.Server.Model.JsonTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

public class EmployeeController {
    static Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private static JsonTransformer<AuthenticateData> jsonTransformer = new JsonTransformer<>();
    private static IEmployeeService service = new ImpEmployeeService();

    public static Result authenticate(Request req, Response res) {
        logger.info("Autenticando empleado");

        String body = req.body();
        AuthenticateData ad = jsonTransformer.getObject(body, AuthenticateData.class);

        Result result = service.authenticate(ad);
        if (result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error) result;
            res.status(error.getCode());
        }

        return result;
    }
}
