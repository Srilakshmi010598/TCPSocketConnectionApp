package com.example.socketsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.view.View;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    EditText e1,e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editMsg);

        Thread myThread = new Thread(new MyServer());
        myThread.start();

    }
    public void send(View v){
        Log.d("output12",e1.getText().toString()+"  socket");
        MessageSender messageSender = new MessageSender();
        messageSender.execute(e1.getText().toString(),e2.getText().toString());
    }

    class MyServer implements Runnable{
        ServerSocket ss;
        Socket mysocket;
        DataInputStream dis;
        BufferedReader bufferedReader;
        String msg;
        Handler handler = new Handler();
        @Override
        public void run() {
            try{
            ss = new ServerSocket(9700);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),"Waiting to establish connection",Toast.LENGTH_SHORT).show();
                }
            });
            while(true){
                mysocket = ss.accept();
                dis = new DataInputStream(mysocket.getInputStream());
                msg = dis.readUTF();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"Message:  "+msg,Toast.LENGTH_SHORT).show();
                    }
                });

            }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }


}