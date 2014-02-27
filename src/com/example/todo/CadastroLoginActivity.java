package com.example.todo;

import com.example.persistencia.DAO;
import com.example.persistenciaWeb.ConexaoHttpClient;
import com.example.todo.R;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CadastroLoginActivity extends Activity {

	private Button bt_cancelar, bt_ok;
	private EditText ed_email, ed_telefone, ed_senha;
	
	SQLiteDatabase db;
	DAO dao;
	String email, telefone, senha;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		setContentView(R.layout.activity_cadastro_login);
		
		ed_email = (EditText) findViewById(R.id.edEmailCad);
		ed_telefone = (EditText) findViewById(R.id.edTelefoneCad);
		ed_senha = (EditText) findViewById(R.id.edSenhaCad);
		bt_ok = (Button) findViewById(R.id.btOkCad);
		bt_cancelar = (Button) findViewById(R.id.btCancelarCad);
	
		dao = new DAO(getApplicationContext());
		bt_ok.setOnClickListener(new View.OnClickListener() {
		 	
			@Override
			public void onClick(View v) {
				
				email = ed_email.getText().toString();
				telefone = ed_telefone.getText().toString();
				senha = ed_senha.getText().toString();
				//webService
				Log.i("Logar","Entrou no evento Onclick WEB");
				String urlGet ="http://10.0.2.2/projects/inserirUsuario.php?email="+email+"&telefone="+telefone+"&senha="+senha+"";
				String respostaRetornada = null;
				Log.i("Logar","vai entrar no try WEB");
				try { 
					Log.i("Teste",urlGet);
					respostaRetornada = ConexaoHttpClient.executaHttpGet(urlGet);
					String resposta = respostaRetornada.toString();
					resposta = resposta.replaceAll("\\s+", "");
					Log.i("Logar","Resposta : "+resposta.charAt(0));
					//Log.i("Logar","Resposta : "+(int) resposta.charAt(1));
					if (resposta.equals("1")){
						dao.inserirUsuario(email, telefone , senha);
						Toast.makeText(CadastroLoginActivity.this, "Usuário inserido com sucesso!", Toast.LENGTH_LONG);
					}
					else
						Toast.makeText(CadastroLoginActivity.this, "Usuário NÃO foi inserido com sucesso!", Toast.LENGTH_LONG);


				} catch (Exception e) {
					Log.i("Erro ","Erro : " +e);

				}


				finish();
			}
		});
		
		bt_cancelar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro_login, menu);
		return true;
	}
	
	

}
