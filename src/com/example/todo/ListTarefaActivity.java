package com.example.todo;

import java.util.List;

import com.example.adapter.TarefaAdapter;
import com.example.model.Tarefa;
import com.example.persistencia.DAO;
import com.example.persistenciaWeb.ConexaoHttpClient;
import com.example.todo.R;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListTarefaActivity extends Activity {

	private ListView listView;
	public static Context contexto;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_tarefa);
		listView = (ListView) findViewById(R.id.listView);
		contexto = ListTarefaActivity.this;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		List<Tarefa> tarefas = ConexaoHttpClient.listarTodasTarefa();
		if (!tarefas.isEmpty()){
			TarefaAdapter adapter = new TarefaAdapter(this, tarefas);
			listView.setAdapter(adapter);
		
		//List<Tarefa> tarefas = DAO.getInstance(getApplicationContext()).listarTodasTarefas();
		//if (!tarefas.isEmpty()){
		//	TarefaAdapter adapter = new TarefaAdapter(this, tarefas);
		//	listView.setAdapter(adapter);
		}else
		{
			Toast t = Toast.makeText(getApplicationContext(), "Nenhuma Tarefa Cadastrada", Toast.LENGTH_SHORT);
			t.show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_tarefa, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
        case R.id.add_item_tarefa:
        	startActivity(new Intent(ListTarefaActivity.this, CadastroTarefaActivity.class));
        	
            /*Toast.makeText(this, "Menu Item 1 selected", Toast.LENGTH_SHORT)
                    .show();*/
            break;
        /*case R.id.action_anterior:
            Toast.makeText(this, "Menu item 2 selected", Toast.LENGTH_SHORT)
                    .show();
            break;*/
    }
 
    return true;
	}
	
	
}
