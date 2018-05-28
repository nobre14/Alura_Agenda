package com.example.nobre.agenda.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.nobre.agenda.R;
import com.example.nobre.agenda.dao.AlunoDAO;

public class SMSReceiver extends BroadcastReceiver{
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] pdus = (Object[])intent.getSerializableExtra("pdus");
        byte[] pdu = (byte[])pdus[0];
        String formato = (String) intent.getSerializableExtra("format");
        SmsMessage sms = SmsMessage.createFromPdu(pdu, formato); //pega a mensagem e transforma em sms
        String telefone = sms.getDisplayOriginatingAddress();// recebe o telefone de quem envia o SMS
        AlunoDAO dao = new AlunoDAO(context);
        if(dao.ehAluno(telefone)) {
            Toast.makeText(context, "Chegou um SMS de um Aluno", Toast.LENGTH_SHORT).show();
            MediaPlayer mp = MediaPlayer.create(context,R.raw.msg );
            mp.start();// inicia o som desejado
        }
        dao.close();
    }
}
