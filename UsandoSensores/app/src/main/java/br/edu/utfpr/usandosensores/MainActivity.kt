package br.edu.utfpr.usandosensores

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var tvX: TextView
    private lateinit var tvY: TextView
    private lateinit var tvZ: TextView

    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvX = findViewById(R.id.tvX)
        tvY = findViewById(R.id.tvY)
        tvZ = findViewById(R.id.tvZ)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        val mAcelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) //recupera o sensor de acelerometro

        sensorManager.registerListener(this, mAcelerometro, SensorManager.SENSOR_DELAY_NORMAL) //registra o sensor para receber dados

    }

    fun btVerSensoresOnClick(view: View) {
        val lista = sensorManager.getSensorList(Sensor.TYPE_ALL) //varios sensores aqui podemos filtrar

        var sensores = StringBuilder()

        for (sensor in lista) {
            sensores.append(sensor.name + "\n")
        }
        Toast.makeText(this, sensores.toString(), Toast.LENGTH_LONG).show()
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val x = event?.values?.get(0)
        val y = event?.values?.get(1)
        val z = event?.values?.get(2)

        tvX.text = x.toString()
        tvY.text = y.toString()
        tvZ.text = z.toString()
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}