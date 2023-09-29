package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class HorarioDeFuncionamientoClinica implements ValidadorDeConsultas{

    public void validar(DatosAgendarConsulta datos){
        var domingo = DayOfWeek.SUNDAY.equals(datos.fecha().getDayOfWeek());
        var antesDeApertura=datos.fecha().getHour()<7;
        var despesDeCierre=datos.fecha().getHour()>19;

        if (domingo|| antesDeApertura || despesDeCierre){
           throw new ValidationException("el horario de atencion de la clinica es de lunes a sabado de 7 a 19 horas");
        }
    }
}
