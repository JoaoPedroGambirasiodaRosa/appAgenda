package br.ulbra.appagenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Registros> registros;

    TextView txtNomeList, txtFoneList, txtEndeList, txtOrdList, txtErroList;
    Button btCadastrar, btListar, btFecharCad, btCadastrarCad, btLimparCad, btVoltar, btContinuar, btFecharLis;
    EditText etNomeCad, etFoneCad, etEndeCad, etNomeList, etFoneList, etEndeList;
    int ord = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tela1();
        registros = new ArrayList<Registros>();
    }

    public ArrayList<Registros> getRegistros() {
        return registros;
    }
    public void ExibirMensagem(String msg) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle("Aviso");
        dialogo.setMessage(msg);
        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }

    public void tela1() {
        setContentView(R.layout.activity_main);

        btCadastrar = findViewById(R.id.btnCadastrar);
        btListar = findViewById(R.id.btnListar);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tela2();
            }
        });

        btListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tela3();
            }
        });
    }

    public void tela2() {
        setContentView(R.layout.cadastro);

        btCadastrarCad = findViewById(R.id.btncad);
        btLimparCad = findViewById(R.id.btnclean);
        btFecharCad = findViewById(R.id.btncan);
        etNomeCad = findViewById(R.id.edtnome);
        etFoneCad = findViewById(R.id.edtendereco);
        etEndeCad = findViewById(R.id.edttelefone);

        btLimparCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etNomeCad.setText(null);
                etFoneCad.setText(null);
                etEndeCad.setText(null);
            }
        });
        btFecharCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tela1();
            }
        });
        btCadastrarCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome, fone, endereco;

                if (etNomeCad.getText().toString().equals("") || etFoneCad.getText().toString().equals("") || etEndeCad.getText().toString().equals("")) {
                    ExibirMensagem("Preencha todos os campos");
                }else {
                    nome = etNomeCad.getText().toString();
                    fone = etFoneCad.getText().toString();
                    endereco = etEndeCad.getText().toString();
                    getRegistros().add(new Registros(nome, fone,endereco));
                    ExibirMensagem("Cadastro efetuado com sucesso");
                    etNomeCad.setText(null);
                    etFoneCad.setText(null);
                    etEndeCad.setText(null);
                }


            }
        });
    }

    public void tela3() {
        setContentView(R.layout.listagem);
        txtNomeList = findViewById(R.id.txtList1);
        txtFoneList = findViewById(R.id.txtList2);
        txtEndeList = findViewById(R.id.txtList3);
        txtOrdList = findViewById(R.id.txtListOrd);
        txtErroList = findViewById(R.id.txtErro);
        etNomeList = findViewById(R.id.etList1);
        etFoneList = findViewById(R.id.etList2);
        etEndeList = findViewById(R.id.etList3);
        btVoltar = findViewById(R.id.btAnterior);
        btFecharLis = findViewById(R.id.btFecharLis);
        btContinuar = findViewById(R.id.btProximo);

        if (!registros.isEmpty()) {
            txtEndeList.setVisibility(View.VISIBLE);
            txtFoneList.setVisibility(View.VISIBLE);
            txtNomeList.setVisibility(View.VISIBLE);
            txtOrdList.setVisibility(View.VISIBLE);
            txtErroList.setVisibility(View.INVISIBLE);
            etNomeList.setVisibility(View.VISIBLE);
            etFoneList.setVisibility(View.VISIBLE);
            etEndeList.setVisibility(View.VISIBLE);
            if(ord != registros.size()) {
                btContinuar.setEnabled(true);
            }
            if (ord > 1) {
                btVoltar.setEnabled(true);
            }
            listar(ord);
        }

        btContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ord++;
                listar(ord);
                if (ord > 1) {
                    btVoltar.setEnabled(true);
                }
                if (ord == registros.size()) {
                    btContinuar.setEnabled(false);
                }
            }
        });
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ord--;
                listar(ord);
                if (ord < 2) {
                    btVoltar.setEnabled(false);
                }
                if (ord != registros.size()) {
                    btContinuar.setEnabled(true);
                }
            }
        });
        btFecharLis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tela1();
            }
        });
    }


    public  void listar(int ord) {
        txtOrdList.setText("Regsitros: " + ord + "/" + registros.size());
        etNomeList.setText(getRegistros().get(ord-1).getNome());
        etFoneList.setText(getRegistros().get(ord-1).getFone());
        etEndeList.setText(getRegistros().get(ord-1).getEndereco());
    }
}