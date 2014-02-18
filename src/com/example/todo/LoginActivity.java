package com.example.todo;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.persistencia.DAO;

import com.exemple.persistenciaWeb.ConexaoHttpClient;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private Button bt_logar, bt_cancelar;
	private EditText ed_telefone, ed_senha;
    private TextView txt_ncadastrado;
    DAO dao;
    int respostaLogin;
    
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	    
		bt_logar = (Button) findViewById(R.id.btentrar);
	    txt_ncadastrado = (TextView) findViewById(R.id.txtNCadastrado);	
	    ed_telefone = (EditText) findViewById(R.id.edTelefone);
	    ed_senha = (EditText) findViewById(R.id.edSenha);
	    bt_logar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dao = new DAO(getApplicationContext());
				String telefone = ed_telefone.getText().toString();
				String senha = ed_senha.getText().toString();
				
				//Log.i("Logar","Entrou no evento Onclick");
				
		        //--------------------- Inicio Web Service ------------------------
				String urlGet ="http://10.0.2.2/projects/usuario.php?telefone="+telefone+"&senha="+senha+"";
				String respostaRetornada = null;
				
				try { 
					respostaRetornada = ConexaoHttpClient.executaHttpGet(urlGet);
					
					String resposta = respostaRetornada.toString();
					resposta = resposta.replaceAll("\\s+", "");
					Log.i("Logar","Resposta : "+resposta.charAt(0));
					
					if (resposta.equals("1"))
						respostaLogin = 1;
					else
					//	Log.i("Logar","Entrou 0");
						respostaLogin = 0;
					
					
				} catch (Exception e) {
					Log.i("Erro ","Erro : " +e);
					Toast.makeText(LoginActivity.this,"Erro : " +e,Toast.LENGTH_LONG);
				}
			  //-------------------------------------------------------------------------------------------------------- Fim WebService
				if (!telefone.equals("") && !senha.equals("")){
					boolean isAutenticado = dao.autenticarLogin(telefone, senha);
					if (isAutenticado == true && respostaLogin == 1){
					  startActivity(new Intent(LoginActivity.this,ListTarefaActivity.class));
					  finish();
					}else{
						Toast.makeText(getApplicationContext(), "Verifique seu usuário e sua senha!", Toast.LENGTH_LONG).show();
					}
				}else{
					Toast.makeText(getApplicationContext(), "Campo usuário e/ou senha vazio!", Toast.LENGTH_LONG).show();
				}
		
				
				
			}
		});
	    
	    txt_ncadastrado.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this,CadastroLoginActivity.class));
				
			}
		});
	
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
