package com.example.todo;

import com.example.persistencia.DAO;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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
				if (!telefone.equals("") && !senha.equals("")){
					boolean isAutenticado = dao.autenticarLogin(telefone, senha);
					if (isAutenticado == true){
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
