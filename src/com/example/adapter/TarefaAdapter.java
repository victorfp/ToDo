package com.example.adapter;

import java.util.List;

import com.example.model.Tarefa;
import com.example.todo.ActivityTarefa;
import com.example.todo.CadastroLoginActivity;
import com.example.todo.CadastroTarefaActivity;
import com.example.todo.ListTarefaActivity;
import com.example.todo.LoginActivity;
import com.example.todo.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class TarefaAdapter extends BaseAdapter{

	private LayoutInflater inflater;
	private List<Tarefa> tarefas;
	
	public TarefaAdapter(Context context, List<Tarefa> tarefas) {
		// TODO Auto-generated constructor stub
		this.tarefas = tarefas;
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return tarefas.size();
	}

	@Override
	public Object getItem(int id) {
		// TODO Auto-generated method stub
		return tarefas.get(id);
	}

	@Override
	public long getItemId(int id) {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_tarefa, null);
		}
		
		//TextView idCategoria = (B) convertView.findViewById(R.id.id_tarefa);
		Button nomeCategoria = (Button) convertView.findViewById(R.id.nm_tarefa);

		Tarefa tarefa = tarefas.get(position);
		
		final int id = position;
		final String titulo = tarefa.getNome();
		final String obs = tarefa.getObservacao();
		final String data = tarefa.getData();
		final Boolean not = tarefa.getNotificar();
		//idCategoria.setText(String.valueOf(tarefa.getId()));
		nomeCategoria.setText(titulo);
		nomeCategoria.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ActivityTarefa.id = id;
				ActivityTarefa.titulo = titulo;
				ActivityTarefa.obs = obs;
				ActivityTarefa.data = data;
				Log.i("BOOL", not.toString());
				ActivityTarefa.notificar = not;
				Intent it = new Intent(v.getContext(),ActivityTarefa.class);
				v.getContext().startActivity(it);
			}
		});
		return convertView;
	}

	
}
