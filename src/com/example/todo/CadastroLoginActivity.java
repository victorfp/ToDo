package com.example.todo;

import com.example.persistencia.DAO;

import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroLoginActivity extends Activity {

	private Button bt_cancelar, bt_ok;
	private EditText ed_email, ed_telefone, ed_senha;
	SQLiteDatabase db;
	DAO dao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
				
				dao.inserirUsuario(ed_email.getText().toString(),ed_telefone.getText().toString(), ed_senha.getText().toString());
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
