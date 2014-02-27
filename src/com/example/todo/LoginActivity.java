package com.example.todo;

import com.example.persistencia.DAO;
import com.example.persistenciaWeb.ConexaoHttpClient;
import com.example.todo.R;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
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
    SharedPreferences sharedPreferences;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		setContentView(R.layout.activity_login);
		 
		bt_logar = (Button) findViewById(R.id.btentrar);
	    txt_ncadastrado = (TextView) findViewById(R.id.txtNCadastrado);	
	    ed_telefone = (EditText) findViewById(R.id.edTelefone);
	    ed_senha = (EditText) findViewById(R.id.edSenha);
	    
	   loadPreferences();
	 
	    
	    bt_logar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dao = new DAO(getApplicationContext());
				
				
				String telefone = ed_telefone.getText().toString();
				String senha = ed_senha.getText().toString(); 
				
				saveLoadPreferences(telefone);
				
				
		        //--------------------- Inicio Web Service ------------------------
						String urlGet ="http://10.0.2.2/projects/usuario.php?telefone="+telefone+"&senha="+senha+"";
						String respostaRetornada = null;
						
						try { 
							respostaRetornada = ConexaoHttpClient.executaHttpGet(urlGet);
							
							String resposta = respostaRetornada.toString();
							resposta = resposta.replaceAll("\\s+", "");
							Log.i("Logar","Resposta : "+resposta.charAt(0));
							
							if (resposta.equals("1")){
								startActivity(new Intent(LoginActivity.this,ListTarefaActivity.class));
								finish();
								respostaLogin = 1;
							}
							else{
							//	Log.i("Logar","Entrou 0");
								Toast.makeText(getApplicationContext(), "Verifique seu usuário e sua senha!", Toast.LENGTH_LONG).show();
								respostaLogin = 0;
							}
							
							
						} catch (Exception e) {
							Log.i("Erro ","Erro : " +e);
							Toast.makeText(LoginActivity.this,"Erro : " +e,Toast.LENGTH_LONG);
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
	
	private void loadPreferences() {
		sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
		String telefone = sharedPreferences.getString("telefone", "");
		EditText telefoneView = (EditText) findViewById(R.id.edTelefone);
		telefoneView.setText(telefone);
	}
	
	private void saveLoadPreferences(String telefone){
		Editor editor = sharedPreferences.edit();
		 editor.putString("telefone", telefone);
		 editor.commit();
	}

}
