package com.example.nobre.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nobre.agenda.modelo.Prova;

import java.util.Arrays;
import java.util.List;

public class ListaProvasFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_provas, container, false); // retorna a view com a tela montada

        List<String> topicosPort = Arrays.asList("Sujeito", "Objeto direto", "Objeto indireto");
        Prova provaPortugues = new Prova("Portugues", "25/05/2018", topicosPort);

        List<String> topicosMat = Arrays.asList("Equações 2 grau", "Trigonometria");
        Prova provaMatematica = new Prova("Matematica", "27/05/2018", topicosMat);

        List<Prova> provas = Arrays.asList(provaMatematica, provaPortugues);
        ArrayAdapter<Prova> adapter = new ArrayAdapter<Prova>(getContext(), android.R.layout.simple_list_item_1, provas);

        ListView lista = (ListView)view.findViewById(R.id.provas_lista); // procura a lista na view
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prova prova = (Prova)parent.getItemAtPosition(position);
                Toast.makeText(getContext(), "Clicou na prova " + prova, Toast.LENGTH_SHORT).show();

                ProvasActivity provasActivity = (ProvasActivity)getActivity();
                provasActivity.selecionaProva(prova);
            }
        });

        return view;
    }
}
