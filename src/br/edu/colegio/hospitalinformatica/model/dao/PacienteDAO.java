package br.edu.colegio.hospitalinformatica.model.dao;

import br.edu.colegio.hospitalinformatica.model.GerenciadorConexao;
import br.edu.colegio.hospitalinformatica.model.pojo.Paciente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    //Singleton
    private PacienteDAO() {
    }

    private static PacienteDAO instance = null;

    public static PacienteDAO getInstance() {
        if (instance == null) {
            instance = new PacienteDAO();
        }
        return instance;
    }
    //Fim singleton

    private String sql = null;
    private Connection connection;
    private PreparedStatement stm = null;
    private ResultSet rs = null;

    public boolean create(Paciente paciente) {
        boolean resultado = true;

        try {
            sql = "INSERT INTO paciente(id,nome,cpf,telefone,endereco,data_nascimento,genero) "
                    + "VALUES(nextval('sequence_id_paciente'),?,?,?,?,?,?)";

            connection = GerenciadorConexao.getInstance().getConexao();
            stm = connection.prepareStatement(sql);
            stm.setString(1, paciente.getNome());
            stm.setString(2, paciente.getCpf());
            stm.setString(3, paciente.getTelefone());
            stm.setString(4, paciente.getEndereco());
            stm.setDate(5, new Date(paciente.getDataNascimento().getTime()));
            stm.setString(6, paciente.getGenero());
            //Executa a query no banco
            resultado = stm.execute();
        } catch (Exception e) {
            System.out.println("Falha ao salvar o paciente!");
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

    public List<Paciente> readAll() {
        List<Paciente> listaPaciente = new ArrayList<>();

        try {
            sql = "SELECT * FROM paciente";
            connection = GerenciadorConexao.getInstance().getConexao();
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                Paciente p = new Paciente();
                p.setId(rs.getLong("id"));
                p.setNome(rs.getString("nome"));
                p.setCpf(rs.getString("cpf"));
                p.setTelefone(rs.getString("telefone"));
                p.setEndereco(rs.getString("endereco"));
                p.setDataNascimento(new java.util.Date(rs.getDate("data_nascimento").getTime()));
                p.setGenero(rs.getString("genero"));
                listaPaciente.add(p);
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
        return listaPaciente;
    }

    public boolean deleteById(Long id) {
        boolean resultado = true;
        try {
            sql = "DELETE FROM paciente WHERE id=?";
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
        int pacientesDeletados = 0;
        try {
            sql = "DELETE FROM paciente";
            connection = GerenciadorConexao.getInstance().getConexao();
            stm = connection.prepareStatement(sql);

            pacientesDeletados = stm.executeUpdate();
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
        return pacientesDeletados;
    }

    public Paciente readById(Long id) {
        Paciente paciente = null;

        try {
            sql = "SELECT * FROM paciente WHERE id=?";
            connection = GerenciadorConexao.getInstance().getConexao();
            stm = connection.prepareStatement(sql);
            stm.setLong(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                paciente = new Paciente();
                paciente.setId(rs.getLong("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setGenero(rs.getString("genero"));
                paciente.setEndereco(rs.getString("endereco"));
                paciente.setDataNascimento(new java.util.Date(rs.getDate("data_nascimento").getTime()));
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
        return paciente;
    }//fim readById

    public boolean updateById(Paciente paciente) {
        boolean resultado = true;

        try {
            sql = "UPDATE paciente SET nome=?, cpf=?, telefone=?, endereco=?, data_nascimento=?, genero=? WHERE id=?";
            connection = GerenciadorConexao.getInstance().getConexao();
            stm = connection.prepareStatement(sql);
            stm.setString(1, paciente.getNome());
            stm.setString(2, paciente.getCpf());
            stm.setString(3, paciente.getTelefone());
            stm.setString(4, paciente.getEndereco());
            stm.setDate(5, new Date(paciente.getDataNascimento().getTime()));
            stm.setString(6, paciente.getGenero());
            stm.setLong(7, paciente.getId());

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

}//fim class
