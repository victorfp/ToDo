package com.example.todo;

import com.example.model.Tarefa;
import com.example.persistencia.DAO;
import com.example.persistenciaWeb.ConexaoHttpClient;
import com.example.todo.R;

import android.os.Bundle;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.util.Log;
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
				dao = new DAO(getApplicationContext());
				int notificar;
				
				Tarefa t = new Tarefa();
				if (chb_notificar.isChecked()){
					notificar = 1;
				}else {
					notificar = 0;
				}
				
				if (!tarefa.equals("") && !obs.equals("") && !data.equals("")){
				//webService
				Log.i("Logar","Entrou no evento Onclick WEB");
				String urlGet ="http://10.0.2.2/projects/inserirTarefa.php?nm_tarefa="+tarefa+"&obs="+obs+"&dt_finalizacao="+t.postarFormato(data)+"&notificar="+notificar+"";
				String respostaRetornada = null;
				Log.i("Logar","vai entrar no try WEB");
				try { 
					respostaRetornada = ConexaoHttpClient.executaHttpGet(urlGet);
					String resposta = respostaRetornada.toString();
					resposta = resposta.replaceAll("\\s+", "");
					Log.i("Logar","Resposta : "+resposta.charAt(0));
					if (resposta.equals("1")){
						dao.inserirTarefa(tarefa, obs, data, notificar);
						Toast.makeText(getApplicationContext(), "Gravando tarefa", Toast.LENGTH_SHORT).show();
						}
					else
						Toast.makeText(getApplicationContext(), "Usuário NÃO foi inserido com sucesso!", Toast.LENGTH_LONG).show();


				} catch (Exception e) {
					Log.i("Erro ","Erro : " +e);

				}
				
				}else{
					Toast.makeText(getApplicationContext(), "Verifique se TODOS os campos estão preenchidos!", Toast.LENGTH_LONG).show();
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
		getMenuInflater().inflate(R.menu.cadastro_tarefa, menu);
		return true;
	}

}
