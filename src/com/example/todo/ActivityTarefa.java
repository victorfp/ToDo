package com.example.todo;

import com.example.model.Tarefa;
import com.example.persistencia.DAO;
import com.example.persistenciaWeb.ConexaoHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityTarefa extends Activity {

	public static int id;
	public static String titulo;
	public static String obs;
	public static String data;
	public static Boolean notificar;
	
	private DAO dao;
	private TextView tx;
	private EditText et;
	private EditText dt;
	private CheckBox cb;
	private Tarefa t;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tarefa);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		tx = (TextView) findViewById(R.id.titulo_tarefa);
		et = (EditText) findViewById(R.id.edObservacao);
		dt = (EditText) findViewById(R.id.edDtFinalizacao);
		cb = (CheckBox) findViewById(R.id.chbNotificar);
		
		t = new Tarefa(id,titulo,obs,data,notificar);
		tx.setText(titulo);
		et.setText(obs);
		dt.setText(t.formato(data));
		cb.setChecked(notificar);
		
		
		
		Button salvar = (Button) findViewById(R.id.btnOkTarefa);
		Button cancelar = (Button) findViewById(R.id.btnCancelarTarefa);
		Button excluir = (Button) findViewById(R.id.btnExcluirTarefa);
		
		salvar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String tarefa = tx.getText().toString();
				String obs = et.getText().toString();
				String data = dt.getText().toString();
				dao = new DAO(getApplicationContext());
				int notificar;
				
				
				if (cb.isChecked()){
					notificar = 1;
				}else {
					notificar = 0;
				}
				
				if (!tarefa.equals("") && !obs.equals("") && !data.equals("")){
				//webService
				String urlGet ="http://10.0.2.2/projects/updateTarefa.php?nm="+tarefa+"&obs="+obs+"&data="+t.postarFormato(data)+"&not="+notificar+"";
				String respostaRetornada = null;
				try {
					respostaRetornada = ConexaoHttpClient.executaHttpGet(urlGet);
					String resposta = respostaRetornada.toString();
					resposta = resposta.replaceAll("\\s+", "");
					Log.i("Logar","Resposta : "+resposta);
					if (resposta.equals("1")){
						Tarefa t = new Tarefa(0, tarefa, obs, data, Boolean.valueOf(notificar+""));
						dao.updateTarefa(t);
						Toast.makeText(getApplicationContext(), "Atualizando tarefa", Toast.LENGTH_SHORT).show();
						}
					else
						Toast.makeText(getApplicationContext(), "Erro ao atualizar a tarefa", Toast.LENGTH_LONG).show();


				} catch (Exception e) {
					Log.i("Erro ","Erro : " +e);

				}
				
				}else{
					Toast.makeText(getApplicationContext(), "Verifique se TODOS os campos estão preenchidos!", Toast.LENGTH_LONG).show();
				}
				finish();
			}
			
		});
		
		cancelar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
		
		excluir.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String urlGet ="http://10.0.2.2/projects/excluirTarefa.php?nm="+titulo+"";
				String respostaRetornada = null;
				dao = new DAO(getApplicationContext());
				try { 
					respostaRetornada = ConexaoHttpClient.executaHttpGet(urlGet);
					String resposta = respostaRetornada.toString();
					Log.i("RSP",resposta);
					resposta = resposta.replaceAll("\\s+", "");					
					if (resposta.equals("1")){
						dao.excluirTarefa(titulo);
						Toast.makeText(getApplicationContext(), "Excluindo Tarefa", Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					Log.i("Erro ","Erro : " +e);

				}
				finish();
			}
		});
	}
	
}
