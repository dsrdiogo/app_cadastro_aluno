package br.uninove.ed.atividade2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {

    EditText ra;
    EditText senha;
    Button btnEntrar;
    Button btnCadastrar;
    List<Aluno> listaUsuarioAPI;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ra = findViewById(R.id.input_login);
        senha = findViewById(R.id.input_senha);
        btnEntrar = (Button) findViewById(R.id.btn_entrar);
        btnCadastrar = (Button) findViewById(R.id.btn_cadastrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    if (ra.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"Informe o R.A.!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (senha.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"Informe a Senha!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    listaUsuarioAPI = new HttpService("",
                                ra.getText().toString(), senha.getText().toString()).execute().get();


                    if(listaUsuarioAPI.size() == 0) {
                        Toast.makeText(getApplicationContext(),
                                "Usuario nâo encontrado",
                                Toast.LENGTH_LONG).show();
                    } else {

                        Intent telaInicial = new Intent(MainActivity.this, TelaInicial.class);

                        Bundle dados = new Bundle();

                        // listaUsuarioAPI = new HttpService("",ra.getText().toString()).execute().get();

                        dados.putString("pRa",ra.getText().toString());

                        telaInicial.putExtras(dados);

                        startActivity(telaInicial);
                    }

                    ra.setText("");
                    senha.setText("");

                } catch (InterruptedException e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                } catch (ExecutionException e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent telaCadastral = new Intent(MainActivity.this, TelaCadastro.class);

                    startActivity(telaCadastral);

            }
        });





    }
}
