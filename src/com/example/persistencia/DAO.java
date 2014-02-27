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
import android.text.format.DateFormat;
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
		    + "dtfinalizacao VARCHAR(30) not null, notificar INTEGER not null);";


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
	    db.close();
	}
	
	public void inserirTarefa(String tarefa, String observacao, String data, int notificar){
		SQLiteDatabase db = this.getWritableDatabase();
		boolean flag = false;
		if (notificar == 1)
			flag = true;
		Tarefa t = new Tarefa();
	    ContentValues values = new ContentValues();
	    values.put("tarefa", tarefa);
	    values.put("observacao", observacao);
	    values.put("dtfinalizacao", t.postarFormato(data));
	    values.put("notificar", notificar);
	    db.insert(TABELA_TAREFA, null, values);
	    Log.i("Sucesso", "Inseriu uma tarefa no banco");
	    db.close();
	}
		
   public boolean autenticarLogin(String telefone, String senha){
	   SQLiteDatabase db = this.getReadableDatabase();
	   String sql = "SELECT * FROM tb_usuario WHERE telefone="+telefone+" and senha="+senha+"";
	   Cursor c = db.rawQuery(sql, null);
	    if (c.getCount() > 0){
	    	db.close();
	    	return true;
	    }else{
	    	db.close();
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
		   Boolean notificar;
		   if (c.getString(5).equals("1"))
			   notificar = true;
		   else
			   notificar = false;
			   
		   list.add(new Tarefa(++id,c.getString(2),c.getString(3),c.getString(4),notificar));
	   }
	   db.close();
	   return list;
   }
	
   public void excluirTarefa(String t){
	   SQLiteDatabase db = this.getWritableDatabase();
	   String sql = "DELETE FROM tb_tarefa WHERE tarefa = '"+t+"'";
	   db.execSQL(sql);
	   db.close();
   }
   
   public void updateTarefa(Tarefa t){
	   SQLiteDatabase db = this.getReadableDatabase();
	   int i = 0;
	   if (t.getNotificar()){
		   i = 1;
	   }

	   ContentValues values = new ContentValues();
	   values.put("observacao", t.getNome());
	   values.put("dtfinalizacao", t.postarFormato(t.getData()));
	   values.put("notificar", i);
	   db.update(TABELA_TAREFA, values, "tarefa = '"+t.getNome()+"'", null);
	   Log.i("Sucesso", "Atualizou uma tarefa");
	   System.out.println("Data: "+t.getData()+" - "+t.postarFormato(t.getData()));
	   db.close();
   }
   
	 public SQLiteDatabase getDatabase() {
		    return this.getWritableDatabase();
		  }
	
	
}
