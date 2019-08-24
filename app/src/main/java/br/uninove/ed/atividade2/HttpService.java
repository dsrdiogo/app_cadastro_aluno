package br.uninove.ed.atividade2;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HttpService extends AsyncTask<Void, Void, List<Aluno>> {
    private String stringUrl;

    // procurar pelo ra do aluno
    public HttpService(String campo, String parametro) {
                    this.stringUrl = "https://nogueira13.000webhostapp.com/query_aluno.php?ra=" + parametro;
           }

    // login
    public HttpService(String campo, String parametro, String parametro2) {
            this.stringUrl = "https://nogueira13.000webhostapp.com/query_login.php?ra=" + parametro + "&senha=" + parametro2;
    }

    public HttpService(String ra, String nome, String email, String telefone, String curso) {
        this.stringUrl = "https://nogueira13.000webhostapp.com/query_alterar.php?ra=" + ra + "&nome=" + nome + "&email=" + email + "&telefone=" + telefone + "&curso=" + curso;
    }

    public HttpService(String ra, String nome, String email, String telefone, String curso, String senha, String nota1, String nota2, String nota3, String nota4) {
        this.stringUrl = "https://nogueira13.000webhostapp.com/query_cadastrar.php?ra=" + ra + "&nome=" + nome + "&email=" + email + "&telefone=" + telefone + "&curso=" + curso + "&senha=" + senha + "&nota1=" + nota1 + "&nota2=" + nota2 + "&nota3=" + nota3 + "&nota4=" + nota4;
    }

    @Override
    protected List<Aluno> doInBackground(Void... voids) {

        StringBuilder resposta = new StringBuilder();
        try {
            URL url = new URL(stringUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("charset", "UFT-8");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.connect();

            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                resposta.append(scanner.nextLine());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Type alunoType = new TypeToken<ArrayList<Aluno>>() {}.getType();
        List<Aluno> listaAlunos;
        listaAlunos = new Gson().fromJson(resposta.toString(), alunoType);

        return listaAlunos;
    }
}