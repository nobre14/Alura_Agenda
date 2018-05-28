package com.example.nobre.agenda;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.nobre.agenda.converter.AlunoConverter;
import com.example.nobre.agenda.dao.AlunoDAO;
import com.example.nobre.agenda.modelo.Aluno;

import java.util.List;

public class EnviaAlunosTask extends AsyncTask<Void, Void, String>{ // parametros doInBackground,    , Retorno   ---->> void signfica sem parametros
    private Context context;
    ProgressDialog dialog;

    public EnviaAlunosTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context, "Aguarde", "Enviando alunos ...", true, true);
    }

    @Override
    protected String doInBackground(Void... params) {
        AlunoDAO dao = new AlunoDAO(context);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();

        AlunoConverter conversor = new AlunoConverter();
        String json = conversor.converterParaJSON(alunos);

        WebClient client = new WebClient();
        String resposta = client.post(json);

        return resposta;
    }

    @Override
    protected void onPostExecute(String resposta) {
        dialog.dismiss();;
        Toast.makeText(context, resposta, Toast.LENGTH_LONG).show();
    }
}
