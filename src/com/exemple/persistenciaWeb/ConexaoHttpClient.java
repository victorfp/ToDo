package com.exemple.persistenciaWeb;



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

import android.annotation.SuppressLint;
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
	

/*public int autenticacaoUsuario(String telefone, String senha){
	Log.i("Logar","Entrou no evento Onclick WEB");
	
  //  String urlPost ="http://10.0.2.2/projects/usuario.php";
	String urlGet ="http://10.0.2.2/projects/usuario.php?telefone="+telefone+"&senha="+senha+"";
	ArrayList<NameValuePair> paramtrosPost = new ArrayList<NameValuePair>();
	paramtrosPost.add(new BasicNameValuePair("usuario",et_usuario.getText().toString()));
	paramtrosPost.add(new BasicNameValuePair("senha",et_senha.getText().toString()));
	Log.i("Usuario ",et_usuario.getText().toString());
	Log.i("Senha ",et_senha.getText().toString());
	String respostaRetornada = null;
	Log.i("Logar","vai entrar no try WEB");
	try { 
		respostaRetornada = ConexaoHttpClient.executaHttpGet(urlGet);
		//respostaRetornada = ConexaoHttpClient.executaHttpPost(urlPost, paramtrosPost);
		String resposta = respostaRetornada.toString();
		resposta = resposta.replaceAll("\\s+", "");
		Log.i("Logar","Resposta : "+resposta.charAt(0));
		Log.i("Logar","Resposta : "+(int) resposta.charAt(1));
		if (resposta.equals("1"))
			return 1;
		else
			return 0;
		
		
		
	} catch (Exception e) {
		Log.i("Erro ","Erro : " +e);
		return 0;
		
	}
	
	
}*/
	 
	
}
