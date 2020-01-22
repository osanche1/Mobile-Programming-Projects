package com.example.program6_battlebots;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MainActivity extends Activity implements Button.OnClickListener{
    private int myPower;
    private int myArmor;
    private int myScan;
    private int health;
    private String myMessage;
    private Thread myThread;
    private String address;
    private Socket connection;
    private boolean finished;

    private ImageButton left;
    private ImageButton right;
    private ImageButton up;
    private ImageButton down;
    private ImageButton fire;
    private ImageButton scan;
    private TextView outputText;
    private boolean readyWrite;
    MediaPlayer mp;
    Context context = this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the Intent that started this activity and extract the attributes
        Intent intent = getIntent();
        myPower = getIntent().getIntExtra("muh power", 0);
        myArmor = getIntent().getIntExtra("muh armor", 0);
        myScan = getIntent().getIntExtra("muh scan", 0);
        address = getIntent().getStringExtra("muh address");

        //initialize buttons and listeners
        left = (ImageButton) findViewById(R.id.left);
        left.setOnClickListener(this);
        right = (ImageButton) findViewById(R.id.right);
        right.setOnClickListener(this);
        up = (ImageButton) findViewById(R.id.up);
        up.setOnClickListener(this);
        down = (ImageButton) findViewById(R.id.down);
        down.setOnClickListener(this);
        fire = (ImageButton) findViewById(R.id.fire);
        fire.setOnClickListener(this);
        scan = (ImageButton) findViewById(R.id.scan);
        scan.setOnClickListener(this);

        //initialize output view
        outputText = (TextView) findViewById(R.id.output);

        //address = "10.216.217.71";
        finished = false;

        health = myArmor+1;
        readyWrite = true;
        myMessage = "Jeffrey" + " " + myArmor + " " + myPower + " " + myScan;
        mp = MediaPlayer.create(context,  R.raw.horn);


        myThread = new Thread(new doNetwork());
        myThread.start();

    }

    @Override
    //If the back button is pushed, close the connection, end the game, and stop the thread
    protected void onStop() {
        super.onStop();
        finished = true;

        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        myThread = null;
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message myMessage){
            outputText.setText(myMessage.getData().getString("myMessage"));
        }
    };


    class doNetwork implements Runnable{
        public void run(){
            int port = 3012;
            mkMsg("host is " + address + "\n");
            try{
                InetAddress serverAddr = InetAddress.getByName(address);
                mkMsg("Attempt Connecting..." + address + "\n");
                connection = new Socket(serverAddr, port);
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(connection.getOutputStream())),true);
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                while(!finished){
                    try{
                        String str = in.readLine();
                        String[] data = str.split(" ");

                        while(data[0].equals("Info")){
                            mkMsg(str);
                            if(data[1].equals("Dead") || data[1].equals("GameOver")){
                                finished = true;
                                break;
                            }
                            str = in.readLine();
                            data = str.split(" ");
                        }
                        if(finished){
                            mkMsg("Game over");
                            break;
                        }

                        if(data[0].equals("Status")){
                            readyWrite = true;
                            if(Integer.parseInt(data[5]) < health){
                                health = Integer.parseInt(data[5]);
                            }
                        }
                        if(readyWrite && !myMessage.equals("noop")){
                            String[] messageData = myMessage.split(" ");

                            if(data[0].equals("setup")){
                                out.println(myMessage);
                            }
                            else if(messageData[0].equals("move")){
                                if(Integer.parseInt(data[3]) < 0){
                                    mkMsg("You must wait to move");
                                }
                                else{
                                    out.println(myMessage);
                                }
                            }
                            else if(messageData[0].equals("shot")){
                                if(Integer.parseInt(data[4]) < 0){
                                    mkMsg("You must wait to shoot");
                                }
                                else{
                                    out.println(myMessage);
                                }
                            }
                            else if(messageData[0].equals("scan")){
                                out.println(myMessage);
                                str = in.readLine();
                                data = str.split(" ");

                                while(data[0].equals("scan") && !data[1].equals("done")){
                                    mkMsg(str);
                                    str = in.readLine();
                                    data = str.split(" ");
                                }
                                mkMsg(str);
                            }
                            myMessage = "noop";
                            readyWrite = false;
                        }
                        out.println("noop");
                        Thread.sleep(400);
                    }
                    catch(Exception e){
                        mkMsg("Communication error");
                        finished = true;
                    }
                }
            }
            catch(Exception e){
                if(!finished){
                    mkMsg("Cannot connect");
                    finished = true;
                }
            }
        }
    }



    @Override
    public void onClick(View view) {
        if(view == left){
            myMessage = "move -1 0";
        }
        else if (view == right) {
            myMessage = "move 1 0";
        }
        else if (view == down) {
            myMessage = "move 0 1";
        }
        else if (view == up) {
            myMessage = "move 0 -1";
        }
        else if(view == fire){
            mp.start();
            myMessage = "shot 0";
        }
        else if(view == scan){
            myMessage = "scan";
        }


    }

    public void mkMsg(String str) {
        Message msg = new Message();
        Bundle b = new Bundle();
        b.putString("msg", str);
        msg.setData(b);
        handler.sendMessage(msg);
    }

}



