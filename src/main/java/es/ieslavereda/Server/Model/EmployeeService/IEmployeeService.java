package es.ieslavereda.Server.Model.EmployeeService;

import es.ieslavereda.Model.AuthenticateData;
import es.ieslavereda.Server.Model.entity.Employee;
import es.ieslavereda.Server.Model.entity.Result;

public interface IEmployeeService {
    Result<Employee> authenticate(AuthenticateData ad);
}
