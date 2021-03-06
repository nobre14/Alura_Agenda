package com.example.nobre.agenda.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nobre.agenda.ListaAlunosActivity;
import com.example.nobre.agenda.R;
import com.example.nobre.agenda.modelo.Aluno;

import java.util.List;

public class AlunosAdapter extends BaseAdapter {
    private final List<Aluno> alunos;
    private final Context context;

    public AlunosAdapter(Context context, List<Aluno> alunos) {
        this.alunos = alunos;
        this.context = context;
    }

    @Override
    public int getCount() { //quantos itens a lista pede
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Aluno aluno = alunos.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView; // só infla a view se a convertView(preenche de forma automática
                                // a lista de forma que não necessita ser inflada,
                                // adicionando itens a medida que o android é rolado) estiver nula
        if(view == null){
            view = inflater.inflate(R.layout.list_item, parent, false); // root(parent) é o "pai" do layout, o false manda ainda não colocar o parent na lista
        }

        TextView campoNome = view.findViewById(R.id.item_nome);
        campoNome.setText(aluno.getNome());

        TextView campoTelefone = view.findViewById(R.id.item_telefone);
        campoTelefone.setText(aluno.getTelefone());

        TextView campoEndereco = view.findViewById(R.id.item_endereco);
        if(campoEndereco != null) campoEndereco.setText(aluno.getEndereco());

        TextView campoSite = view.findViewById(R.id.item_site);
        if(campoSite != null) campoSite.setText(aluno.getSite());

        ImageView campoFoto = view.findViewById(R.id.item_foto);
        String caminhoFoto = aluno.getCaminhoFoto();
        if(caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 100, 100, true); /* reduz a resolução da imagem,
                                                                                                               não colocar mais que 512 pois
                                                                                                               nem todos celulares suportam*/
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);/*faz a imagem cobrir
                                                               o espaço definido*/
        }
        return view;
    }
}
