
package br.edu.colegio.hospitalinformatica.controller;

import br.edu.colegio.hospitalinformatica.model.dao.ConsultaDAO;
import br.edu.colegio.hospitalinformatica.model.pojo.Consulta;
import br.edu.colegio.hospitalinformatica.model.pojo.Medico;
import br.edu.colegio.hospitalinformatica.model.pojo.Paciente;
import java.sql.Time;
import java.util.Date;

public class ConsultaController {
    
    private ConsultaController(){}
    
    private static ConsultaController instance = null;
    
    public static ConsultaController getInstance(){
        if(instance==null){
            instance = new ConsultaController();
        }
        return instance;
    }
    
    public boolean createConsulta(Paciente paciente, Medico medico, 
            Date dataConsulta, Time horaConsulta, String local){
        boolean resultado = true;
        Consulta consulta = new Consulta();
        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setHoraConsulta(horaConsulta);
        consulta.setDataConsulta(dataConsulta);
        consulta.setLocal(local);
        resultado = ConsultaDAO.getInstance().create(consulta);
        return resultado;
    }
    
}
