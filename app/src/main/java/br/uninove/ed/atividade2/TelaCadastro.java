package br.uninove.ed.atividade2;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TelaCadastro  extends AppCompatActivity {

    EditText ra;
    EditText nome;
    EditText email;
    EditText telefone;
    EditText curso;
    EditText senha;
    EditText nota1;
    EditText nota2;
    EditText nota3;
    EditText nota4;
    Button btCadastrar;
    List<Aluno> listaAlunosAPI;
    ArrayList<String> listaNotasAlunos = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro);

        // ActionBar actionBar = getSupportActionBar();

        ra = findViewById(R.id.txt_ra);
        nome = findViewById(R.id.txt_nome);
        email = findViewById(R.id.txt_email);
        telefone = findViewById(R.id.txt_telefone);
        curso = findViewById(R.id.txt_curso);
        senha = findViewById(R.id.txt_senha);
        nota1 = findViewById(R.id.txt_nota1);
        nota2 = findViewById(R.id.txt_nota2);
        nota3 = findViewById(R.id.txt_nota3);
        nota4 = findViewById(R.id.txt_nota4);
        btCadastrar = (Button) findViewById(R.id.btnCadastrar);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    if (ra.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"Informe o R.A.!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (nome.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"Informe o Nome!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (email.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"Informe o E-mail!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (telefone.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"Informe o Telefona!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (curso.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"Informe o Curso!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (senha.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"Informe a Senha!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (nota1.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"Informe a Nota 1!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (nota2.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"Informe a Nota 2!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (nota3.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"Informe a Nota 3!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (nota4.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"Informe a Nota 4!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    listaAlunosAPI = new HttpService("", ra.getText().toString()).execute().get();

                    if (listaAlunosAPI.size() > 0){

                        Toast.makeText(getApplicationContext(),"Já existe um aluno com esse R.A.!", Toast.LENGTH_LONG).show();
                        return;

                    } else {

                        listaAlunosAPI.clear();

                        listaAlunosAPI = new HttpService(ra.getText().toString(), nome.getText().toString(), email.getText().toString(), telefone.getText().toString(), curso.getText().toString(), senha.getText().toString(), nota1.getText().toString(), nota2.getText().toString(), nota3.getText().toString(), nota4.getText().toString()).execute().get();

                        if (listaAlunosAPI.size() > 0){

                            Toast.makeText(getApplicationContext(),"Operação realizada!", Toast.LENGTH_LONG).show();

                            Intent telaLogin = new Intent(TelaCadastro.this, MainActivity.class);

                            startActivity(telaLogin);

                        } else {

                            Toast.makeText(getApplicationContext(),"Houve um erro ao cadastrar o aluno!", Toast.LENGTH_LONG).show();

                        }

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
