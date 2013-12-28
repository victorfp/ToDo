package com.example.todo;

import com.example.persistencia.DAO;
import android.os.Bundle;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroTarefaActivity extends Activity  {
    private EditText ed_tarefa, ed_observacao, ed_data;
    private CheckBox chb_notificar;
    private Button bt_ok, bt_cancelar;
    private DAO dao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_tarefa);
		ed_tarefa = (EditText) findViewById(R.id.edTarefa);
		ed_observacao = (EditText) findViewById(R.id.edObservacao);
		ed_data = (EditText) findViewById(R.id.edDtFinalizacao);
		chb_notificar = (CheckBox) findViewById(R.id.chbNotificar);
		bt_ok = (Button) findViewById(R.id.btnOkTarefa);
		bt_cancelar= (Button) findViewById(R.id.btnCancelarTarefa);
		
		
		bt_ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String tarefa = ed_tarefa.getText().toString();
				String obs = ed_observacao.getText().toString();
				String data = ed_data.getText().toString();
				int notificar = -1;
				if (chb_notificar.isChecked()){
					notificar = 1;
				}else {
					notificar = 0;
				}
				dao = new DAO(getApplicationContext());
				dao.inserirTarefa(tarefa, obs, data, notificar);
				Toast.makeText(getApplicationContext(), "Gravando tarefa", Toast.LENGTH_SHORT).show();
				
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.cadastro_tarefa, menu);
		return true;
	}

}
