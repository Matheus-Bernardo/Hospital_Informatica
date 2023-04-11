package br.edu.colegio.hospitalinformatica.controller;

import br.edu.colegio.hospitalinformatica.model.dao.MedicoDAO;
import br.edu.colegio.hospitalinformatica.model.pojo.Medico;
import java.util.Date;
import java.util.List;

public class MedicoController {

    //Singleton
    private MedicoController() {
    }

    private static MedicoController instance = null;

    public static MedicoController getInstance() {
        if (instance == null) {
            instance = new MedicoController();
        }
        return instance;
    }
    //fim singleton

    public boolean createMedico(String nome, String cpf,
            String telefone, String crm, Date dataAdmissao,
            String email) {
        boolean ret = true;
        Medico m = new Medico();
        
        m.setNome(nome);
        m.setCpf(cpf);
        m.setTelefone(telefone);
        m.setCrm(crm);
        m.setDataAdmissao(dataAdmissao);
        m.setEmail(email);
        
        ret = MedicoDAO.getInstance().create(m);
        
        return ret;
    }
    public List<Medico> readAllMedicos(){
        return MedicoDAO.getInstance().readAll();
    }

    public boolean deleteMedicoById(Long id) {
        return MedicoDAO.getInstance().deleteById(id);
    }
    
    public int deleteAllMedicos() {
        return MedicoDAO.getInstance().deleteAll();
    }
      public Medico readMedicoById (Long idMedico){
        return MedicoDAO.getInstance().readById(idMedico);
    }
    
    public boolean updateMedicoById(Long id, String nome, String cpf,
            String telefone, String crm, Date dataAdmissao,
            String email) {
        boolean resultado = true;
        Medico m = new Medico();
        
        m.setId(id);
        m.setNome(nome);
        m.setCpf(cpf);
        m.setTelefone(telefone);
        m.setCrm(crm);
        m.setDataAdmissao(dataAdmissao);
        m.setEmail(email);
        
        resultado = MedicoDAO.getInstance().updateById(m);
        
        return resultado;
    }    
}
