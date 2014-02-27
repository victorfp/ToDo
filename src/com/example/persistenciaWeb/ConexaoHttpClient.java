package com.example.persistenciaWeb;

import java.util.*;
import java.io.*;
import java.net.*;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
//bibliotecas http
import org.apache.http.client.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.example.model.Tarefa;
import com.example.persistencia.DAO;
import com.example.todo.ListTarefaActivity;
import com.example.todo.LoginActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;


public class ConexaoHttpClient {

	public static final int TIME_OUT = 30 * 1000;
	private static HttpClient httpClient;

	public static HttpClient getHttpClient(){
		if (httpClient == null)
		{
			httpClient = new DefaultHttpClient();
			final HttpParams httpParams = httpClient.getParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, TIME_OUT);
			HttpConnectionParams.setSoTimeout(httpParams, TIME_OUT);
			ConnManagerParams.setTimeout(httpParams, TIME_OUT);
		}
		return httpClient;
	}

	public static String executaHttpPost(String url,ArrayList<NameValuePair> paramtrosPost) throws Exception {
		BufferedReader bufferReader = null;
		try{
			HttpClient client = getHttpClient();
			HttpPost httpPost = new HttpPost(url);
			UrlEncodedFormEntity  formEntity = new UrlEncodedFormEntity(paramtrosPost);
			httpPost.setEntity(formEntity);
			HttpResponse httpResponse = client.execute(httpPost);
			bufferReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer stringBuffer = new StringBuffer("");
			String line = "";
			String LS = System.getProperty("line.separator");
			while((line = bufferReader.readLine()) != null){
				stringBuffer.append(line + LS);
			}
			bufferReader.close();

			String resultado = stringBuffer.toString();
			return resultado;
		}finally{
			if (bufferReader != null){
				try{
					bufferReader.close();
				}catch (IOException e){
					e.printStackTrace();
				}
			}

		}

	}

	public static String executaHttpGet(String url) throws Exception {
		BufferedReader bufferReader = null;
		try{
			HttpClient client = getHttpClient();
			HttpGet httpGet = new HttpGet(url);
			httpGet.setURI(new URI(url));
			HttpResponse httpResponse = client.execute(httpGet);
			bufferReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer stringBuffer = new StringBuffer("");
			String line = "";
			String LS = System.getProperty("line.separator");
			while((line = bufferReader.readLine()) != null){
				stringBuffer.append(line + LS);
			}
			bufferReader.close();

			String resultado = stringBuffer.toString();
			return resultado;
		}finally{
			if (bufferReader != null){
				try{
					bufferReader.close();
				}catch (IOException e){
					e.printStackTrace();
				}
			}

		}
	}

	public static List<Tarefa> listarTodasTarefa(){

		List<Tarefa> list = new ArrayList<Tarefa>();

		String urlGet ="http://10.0.2.2/projects/tarefa.php";
		String respostaRetornada = null;
		int id = 0;
		try { 
			respostaRetornada = ConexaoHttpClient.executaHttpGet(urlGet);

			String registro[] = respostaRetornada.toString().split("\n");
			String campos [];
			for (int i = 0; i < registro.length; i++) {
				campos = registro[i].split(";");
				Boolean b;
				if (campos[3].equals("1"))
					b = true;
				else
					b = false;
				Log.i("INFO2",b.toString());
				list.add(new Tarefa(++id,campos[0],campos[1],campos[2], b));
			}
		} catch (Exception e) {
			Log.i("Erro ","Erro : " +e);
			//Toast.makeText(ListTarefaActivity.this,"Erro : " +e,Toast.LENGTH_LONG).show();
		}
		return list;
	}

}