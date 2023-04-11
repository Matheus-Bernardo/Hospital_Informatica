package br.edu.colegio.hospitalinformatica.model.dao;

import br.edu.colegio.hospitalinformatica.model.GerenciadorConexao;
import br.edu.colegio.hospitalinformatica.model.pojo.Consulta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConsultaDAO {

    private ConsultaDAO() {
    }

    private static ConsultaDAO instance = null;

    public static ConsultaDAO getInstance() {
        if (instance == null) {
            instance = new ConsultaDAO();
        }
        return instance;
    }

    private String sql = null;
    private Connection connection;
    private PreparedStatement stm = null;
    private ResultSet rs = null;

    public boolean create(Consulta consulta) {
        boolean resultado = true;
        try {
            sql = "INSERT INTO consulta(id,id_paciente,id_medico,data_consulta,hora_consulta,local) "
                    + "VALUES(nextval('sequence_id_consulta'),?,?,?,?,?)";
            connection = GerenciadorConexao.getInstance().getConexao();
            stm = connection.prepareStatement(sql);
            stm.setLong(1, consulta.getPaciente().getId());
            stm.setLong(2, consulta.getMedico().getId());
            stm.setDate(3, new java.sql.Date(consulta.getDataConsulta().getTime()));
            stm.setTime(4, consulta.getHoraConsulta());
            stm.setString(5, consulta.getLocal());
            resultado = stm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }

}
