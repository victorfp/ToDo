package com.example.persistencia;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Tarefa;
import com.example.todo.CadastroLoginActivity;
import com.example.todo.R;

import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
public class DAO extends SQLiteOpenHelper{
	private final static String DATABASE_NOME = "BDTODO";
	private final static String TABELA_USUARIO = "TB_USUARIO";
	private final static String TABELA_TAREFA= "TB_TAREFA";
	private static final int DATABASE_VERSION = 1;
	
	private static final String tb_usuario =
		    "create table if not exists " + TABELA_USUARIO +" (id integer primary key autoincrement, "
		    + "email VARCHAR not null, telefone VARCHAR not null, " 
		    + "senha VARCHAR not null);";
	private static final String tb_tarefa =
		    "create table if not exists " + TABELA_TAREFA +" (id integer primary key autoincrement, "
		    + "tarefa VARCHAR not null, observacao VARCHAR not null, " 
		    + "dtfinalizacao VARCHAR not null, notificar INTEGER not null);";


	public DAO(Context context) {
		
		super(context, DATABASE_NOME, null, 4);
		Log.i("SUCESSO!", "DataBase Criado!");
	}

	public static DAO getInstance(Context context){
		return new DAO(context);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
	try{	
		db.execSQL(tb_usuario);
		db.execSQL(tb_tarefa);
	}catch(Exception e){
		Log.i("ERRO!", "Erro ao criar as tabelas");
	}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
		
	}
	public void inserirUsuario(String email, String telefone, String senha){
		SQLiteDatabase db = this.getWritableDatabase();
	    ContentValues values = new ContentValues();
	    values.put("email", email);
	    values.put("telefone", telefone);
	    values.put("senha", senha);
	    db.insert(TABELA_USUARIO, null, values);

	}
	
	public void inserirTarefa(String tarefa, String observacao, String data, int notificar){
		SQLiteDatabase db = this.getWritableDatabase();
	    ContentValues values = new ContentValues();
	    values.put("tarefa", tarefa);
	    values.put("observacao", observacao);
	    values.put("dtfinalizacao", data);
	    values.put("notificar", notificar);
	    db.insert(TABELA_TAREFA, null, values);
	    Log.i("Sucesso", "Inseriu uma tarefa no banco");

	}
		
   public boolean autenticarLogin(String telefone, String senha){
	   SQLiteDatabase db = this.getReadableDatabase();
	   String sql = "SELECT * FROM tb_usuario WHERE telefone="+telefone+" and senha="+senha+"";
	   Cursor c = db.rawQuery(sql, null);
	    if (c.getCount() > 0){
	    	return true;
	    }else{
	    	return false;
	    }
   }
   
   public List<Tarefa> listarTodasTarefas(){
	   SQLiteDatabase db = this.getReadableDatabase();
	   List<Tarefa> list = new ArrayList<Tarefa>();
	   int id = 0;
	   String sql = "SELECT * FROM tb_tarefa";
	   Cursor c = db.rawQuery(sql, null);
	   while (c.moveToNext()){
		   list.add(new Tarefa(++id,c.getString(2)));
	   }
	   return list;
   }
	
	 public SQLiteDatabase getDatabase() {
		    return this.getWritableDatabase();
		  }
	
	
}
