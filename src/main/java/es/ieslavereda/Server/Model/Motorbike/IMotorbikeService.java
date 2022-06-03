package es.ieslavereda.Server.Model.Motorbike;

import es.ieslavereda.Server.Model.entity.Motorbike;
import es.ieslavereda.Server.Model.entity.Result;

public interface IMotorbikeService {
    Result<Motorbike> addMoto(Motorbike motorbike);
    Result<Motorbike> updateMoto(Motorbike motorbike);
    Result<Motorbike> deleteMoto(String matricula);
    Result<Motorbike> checkMoto(String matricula);
}
