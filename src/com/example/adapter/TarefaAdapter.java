package com.example.adapter;

import java.util.List;

import com.example.model.Tarefa;

import com.example.todo.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
		
		TextView idCategoria = (TextView) convertView.findViewById(R.id.id_tarefa);
		TextView nomeCategoria = (TextView) convertView.findViewById(R.id.nm_tarefa);

		Tarefa tarefa = tarefas.get(position);
		
		idCategoria.setText(String.valueOf(tarefa.getId()));
		nomeCategoria.setText(tarefa.getNome());		
		
		return convertView;
	}

}
