package com.example.socketsapp;
import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import android.util.Log;
public class MessageSender extends AsyncTask <String,Void,Void>{

    Socket s;
    DataOutputStream dos;
    PrintWriter pw;

    @Override
    protected Void doInBackground(String... voids){

        String msg = voids[1];
        String ip = voids[0];
        Log.d("output12"," in messagesender"+msg);
        try{
            s = new Socket(ip,9700);
            //pw = new PrintWriter(s.getOutputStream());
            //pw.write(msg);
            //pw.flush();
            //pw.close();
            dos = new DataOutputStream(s.getOutputStream());
            dos.writeUTF(msg);
            dos.close();
            s.close();
            Log.d("output12","sent  "+msg+"  "+ip);
        }
        catch (IOException e){
            e.printStackTrace();
            Log.d("output12","exc  "+e);
        }



        return null;
    }
}
