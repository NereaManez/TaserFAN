package es.ieslavereda.Server.Model.ScooterService;

import es.ieslavereda.Server.Model.entity.Result;
import es.ieslavereda.Server.Model.entity.Scooter;

public interface IScooterService {
    Result<Scooter> addScooter(Scooter scooter);
    Result<Scooter> updateScooter(Scooter scooter);
    Result<Scooter> deleteScooter(String matricula);
    Result<Scooter> checkScooter(String matricula);
}
