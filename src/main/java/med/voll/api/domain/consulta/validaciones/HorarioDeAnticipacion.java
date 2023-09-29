package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class HorarioDeAnticipacion implements ValidadorDeConsultas{

    public void validar(DatosAgendarConsulta datos){

        var ahora = LocalDateTime.now();
        var horaDeConsulta =datos.fecha();

        var diferencia30Min= Duration.between(ahora,horaDeConsulta).toMinutes()<30;
        if (diferencia30Min){
            throw new ValidationException("Las consultas deben programarse con al menos 30 minutos de anticipacion");
        }
    }

}
