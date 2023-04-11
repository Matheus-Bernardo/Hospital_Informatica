package br.edu.colegio.hospitalinformatica.model.dao;

import br.edu.colegio.hospitalinformatica.model.GerenciadorConexao;
import br.edu.colegio.hospitalinformatica.model.pojo.Medico;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {

    //Singleton
    private MedicoDAO() {
    }

    private static MedicoDAO instance = null;

    public static MedicoDAO getInstance() {
        if (instance == null) {
            instance = new MedicoDAO();
        }
        return instance;
    }
    //Fim singleton

    private String sql = null;
    private Connection connection;
    private PreparedStatement stm = null;
    private ResultSet rs = null;

    public boolean create(Medico medico) {
        boolean resultado = true;

        try {
            sql = "INSERT INTO medico(id,nome,cpf,telefone,crm,data_admissao,email) "
                    + "VALUES(nextval('sequence_id_medico'),?,?,?,?,?,?)";

            connection = GerenciadorConexao.getInstance().getConexao();
            stm = connection.prepareStatement(sql);
            stm.setString(1, medico.getNome());
            stm.setString(2, medico.getCpf());
            stm.setString(3, medico.getTelefone());
            stm.setString(4, medico.getCrm());
            stm.setDate(5, new Date(medico.getDataAdmissao().getTime()));
            stm.setString(6, medico.getEmail());
            //Executa a query no banco
            resultado = stm.execute();
        } catch (Exception e) {
            System.out.println("Falha ao salvar o médico!");
            e.printStackTrace();
        } finally {
            try {
                //Fecha o prepared statement
                if (stm != null) {
                    stm.close();
                }
                //Fecha a connection
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }//fim-finally

        return resultado;
    }//fim-create
    
        public List<Medico> readAll(){
        List<Medico> listaMedico = new ArrayList<>();
        
        try {
            sql = "SELECT * FROM medico";
            connection = GerenciadorConexao.getInstance().getConexao();
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            
            while(rs.next()){
                Medico m = new Medico();
                m.setId(rs.getLong("id"));
                m.setNome(rs.getString("nome"));
                m.setCpf(rs.getString("cpf"));
                m.setTelefone(rs.getString("telefone"));
                m.setCrm(rs.getString("crm"));
                m.setDataAdmissao(new java.util.Date(rs.getDate("data_admissao").getTime()));
                m.setEmail(rs.getString("email"));
                listaMedico.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(rs!=null){
                    rs.close();
                }
                if(stm!=null){
                    stm.close();
                }
                if(connection!=null){
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listaMedico; 
    }
    public boolean deleteById(Long id) {
        boolean resultado = true;
        try {
            sql = "DELETE FROM medico WHERE id=?";
            connection = GerenciadorConexao.getInstance().getConexao();
            stm = connection.prepareStatement(sql);
            stm.setLong(1, id);
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

    public int deleteAll() {
        int medicosDeletados = 0;
        try {
            sql = "DELETE FROM medico";
            connection = GerenciadorConexao.getInstance().getConexao();
            stm = connection.prepareStatement(sql);
 
            medicosDeletados = stm.executeUpdate();
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
        return medicosDeletados;
    }

    public Medico readById(Long id) {
        Medico medico = null;

        try {
            sql = "SELECT * FROM medico WHERE id=?";
            connection = GerenciadorConexao.getInstance().getConexao();
            stm = connection.prepareStatement(sql);
            stm.setLong(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                medico = new Medico();
                medico.setId(rs.getLong("id"));
                medico.setNome(rs.getString("nome"));
                medico.setCpf(rs.getString("cpf"));
                medico.setCrm(rs.getString("crm"));
                medico.setTelefone(rs.getString("telefone"));
                medico.setEmail(rs.getString("email"));
                medico.setDataAdmissao(new java.util.Date(rs.getDate("data_admissao").getTime()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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
        return medico;
    }//fim readById

    public boolean updateById(Medico medico) {
        boolean resultado = true;

        try {
            sql = "UPDATE medico SET nome=?, cpf=?, telefone=?, email=?, crm=?, data_admissao=? WHERE id=?";
            connection = GerenciadorConexao.getInstance().getConexao();
            stm = connection.prepareStatement(sql);
            stm.setString(1, medico.getNome());
            stm.setString(2, medico.getCpf());
            stm.setString(3, medico.getTelefone());
            stm.setString(4, medico.getEmail());
            stm.setString(5, medico.getCrm());
            stm.setDate(6, new Date(medico.getDataAdmissao().getTime()));
            stm.setLong(7, medico.getId());

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
