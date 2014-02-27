package com.example.model;


public class Tarefa {
	
	private int id;
	private String nome;
	private String  observacao;
	private String data;
	private Boolean notificar;
	
	public Tarefa() {
		// TODO Auto-generated constructor stub
	}
	
	public Tarefa(int id, String nome, String obs, String dt, Boolean b) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.nome = nome;
		observacao = obs;
		data = formato(dt);
		notificar = b;
	}
	
	public String formato(String dt){
		String s = "";
		if (dt.indexOf('-')>=0)
			s = dt.split("-")[2]+"/"+dt.split("-")[1]+"/"+dt.split("-")[0];
		else
			s =dt;
		return s;
	}
	
	public String getObservacao() {
		return observacao;
	}
	
	public String getData() {
		return data;
	}
	
	public Boolean getNotificar() {
		return notificar;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setNotificar(Boolean notificar) {
		this.notificar = notificar;
	}
	
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public String postarFormato(String dt){
		String s = "";
		if (dt.contains("/")){
			s = dt.split("/")[2]+"-"+dt.split("/")[1]+"-"+dt.split("/")[0];
		}else{
			s = dt;
		}
		return s;
	}
}
