package br.edu.colegio.hospitalinformatica.controller;

import br.edu.colegio.hospitalinformatica.model.dao.PacienteDAO;
import br.edu.colegio.hospitalinformatica.model.pojo.Paciente;
import java.util.Date;
import java.util.List;

public class PacienteController {

    //Singleton
    private PacienteController() {
    }

    private static PacienteController instance = null;

    public static PacienteController getInstance() {
        if (instance == null) {
            instance = new PacienteController();
        }
        return instance;
    }
    //fim singleton

    public boolean createPaciente(String nome, String cpf,
            String telefone, String endereco, Date dataNascimento,
            String genero) {
        boolean ret = true;
        Paciente p = new Paciente();
        
        p.setNome(nome);
        p.setCpf(cpf);
        p.setTelefone(telefone);
        p.setEndereco(endereco);
        p.setDataNascimento(dataNascimento);
        p.setGenero(genero);
        
        ret = PacienteDAO.getInstance().create(p);
        
        return ret;
    }
    
    public List<Paciente> readAllPacientes(){
        return PacienteDAO.getInstance().readAll();
    }

    public boolean deletePacienteById(Long id) {
        return PacienteDAO.getInstance().deleteById(id);
    }
    
    public int deleteAllPacientes() {
        return PacienteDAO.getInstance().deleteAll();
    }
    
    public Paciente readPacienteById (Long idPaciente){
        return PacienteDAO.getInstance().readById(idPaciente);
    }
    
    public boolean updatePacienteById(Long id, String nome, String cpf,
            String telefone, String endereco, Date dataNascimento, String genero){
        boolean resultado = true;
        
        Paciente p = new Paciente();
        p.setId(id);
        p.setNome(nome);
        p.setCpf(cpf);
        p.setDataNascimento(dataNascimento);
        p.setEndereco(endereco);
        p.setTelefone(telefone);
        p.setGenero(genero);
        
        resultado = PacienteDAO.getInstance().updateById(p);
        
        return resultado;
    }
}
