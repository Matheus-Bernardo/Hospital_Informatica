package br.edu.colegio.hospitalinformatica.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class GerenciadorConexao {
    //Início Singleton
    //Construtor privado
    private GerenciadorConexao(){}
    //Atributo privado e estatico
    private static GerenciadorConexao instance = null;
    //Métod que retorna instancia da classe
    public static GerenciadorConexao getInstance(){
        if(instance == null){
            instance = new GerenciadorConexao();
        }
        return instance;
    }//fim getConexao 
    //Fim singleton
    //Atributos com informações do banco
    private String usuario = "postgres";
    private String senha = "postgres";
    private String driver = "org.postgresql.Driver";
    private String url = "jdbc:postgresql://localhost:5432/hospital_informatica";
    //Abre a conexão com o BD
    public Connection getConexao(){
        Connection conn = null;
        try{
            //Carrega driver na memória
            Class.forName(driver);
            //Tenta abrir a conexão com o BD
            conn = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão aceita...");
        }catch(Exception e){
            System.out.println("Falha ao conectar no BD");
            e.printStackTrace();
        }
        return conn;
    }
    
}//fim GerenciadorConexao
