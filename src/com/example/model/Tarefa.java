package com.example.model;

public class Tarefa {
	
	private int id;
	private String nome;
	
	public Tarefa() {
		// TODO Auto-generated constructor stub
	}
	
	public Tarefa(int id, String nome) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.nome = nome;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

}
