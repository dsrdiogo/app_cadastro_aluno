package br.uninove.ed.atividade2;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TelaInicial extends AppCompatActivity {

    // LinearLayout grupoEntrada;
    // EditText entrada;
    EditText ra;
    EditText nome;
    EditText email;
    EditText telefone;
    EditText curso;
    Button btAlterar;
    ListView listViewAlunos;
    List<Aluno> listaAlunosAPI;
    ArrayList<String> listaNotasAlunos = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    String tipoRequisicao = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial);

        ActionBar actionBar = getSupportActionBar();

        // grupoEntrada = findViewById(R.id.grupoEntrada);

        ra = findViewById(R.id.txt_ra);
        nome = findViewById(R.id.txt_nome);
        email = findViewById(R.id.txt_email);
        telefone = findViewById(R.id.txt_telefone);
        curso = findViewById(R.id.txt_curso);

        btAlterar = (Button) findViewById(R.id.btnCadastrar);
        listViewAlunos = findViewById(R.id.listViewNotas);

        try {

            // preencher pela tela de login
            Intent tela1 = getIntent();

            Bundle dados = tela1.getExtras();

            String teste = dados.getString("pRa");

            ra.setText(dados.getString("pRa"));

            listaAlunosAPI = new HttpService("ra", ra.getText().toString()).execute().get();

            ra.setText("");

            listaNotasAlunos.clear();

            for (Aluno aluno : listaAlunosAPI) {

                ra.setText(aluno.getRa());
                nome.setText(aluno.getNome());
                email.setText(aluno.getEmail());
                telefone.setText(aluno.getTelefone());
                curso.setText(aluno.getCurso());

                listaNotasAlunos.add("AV1: " + aluno.getNota1());
                listaNotasAlunos.add("AV2: " + aluno.getNota2());
                listaNotasAlunos.add("AV3: " + aluno.getNota3());
                listaNotasAlunos.add("AV4: " + aluno.getNota4());
                listaNotasAlunos.add("Média: " + aluno.getMedia());

            }

            adapter = new ArrayAdapter<String>(
                    getApplicationContext(),
                    android.R.layout.simple_list_item_1, listaNotasAlunos);

            listViewAlunos.setAdapter(adapter);

        } catch (InterruptedException e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        } catch (ExecutionException e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }


        btAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    listaAlunosAPI = new HttpService(ra.getText().toString(), nome.getText().toString(), email.getText().toString(), telefone.getText().toString(), curso.getText().toString()).execute().get();

                    if (listaAlunosAPI.size() > 0){

                        Toast.makeText(getApplicationContext(),"Operação realizada!", Toast.LENGTH_LONG).show();

                        ra.setText("");
                        nome.setText("");
                        email.setText("");
                        telefone.setText("");
                        curso.setText("");
                        listaNotasAlunos.clear();

                        for (Aluno aluno : listaAlunosAPI) {

                            ra.setText(aluno.getRa());
                            nome.setText(aluno.getNome());
                            email.setText(aluno.getEmail());
                            telefone.setText(aluno.getTelefone());
                            curso.setText(aluno.getCurso());

                            listaNotasAlunos.add("AV1: " + aluno.getNota1());
                            listaNotasAlunos.add("AV2: " + aluno.getNota2());
                            listaNotasAlunos.add("AV3: " + aluno.getNota3());
                            listaNotasAlunos.add("AV4: " + aluno.getNota4());
                            listaNotasAlunos.add("Média: " + aluno.getMedia());

                        }

                        adapter = new ArrayAdapter<String>(
                                getApplicationContext(),
                                android.R.layout.simple_list_item_1, listaNotasAlunos);

                        listViewAlunos.setAdapter(adapter);
                        

                    }

                } catch (InterruptedException e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                } catch (ExecutionException e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }


}
