package br.edu.utfpr.usandoservicos

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.net.Socket

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread{
            while(true){

                val server = Socket("10.0.2.2",12345)

                val input = server.getInputStream().bufferedReader()
                val output = server.getOutputStream().bufferedWriter()

                while(true){
                    output.write("hora\n")
                    output.flush()

                    val resposta = input.readLine()

                   println(resposta)


                    Thread.sleep(1000) // para o procedssamemto a cada um segundo
                }

                Thread.sleep(1000)
            }
        }.start()

        return START_NOT_STICKY
    }
}