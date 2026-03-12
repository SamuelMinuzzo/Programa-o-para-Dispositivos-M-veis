package br.edu.utfpr.usandogps

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), LocationListener {

    private lateinit var locationManager: LocationManager
    var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this) // ou LocationManager.GPS_PROVIDER ou LocationManager.NETWORK_PROVIDER

       // var location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) colocar dentro de um botão para atualizar a posição

    }

    override fun onLocationChanged(location: Location) {
        val latitude = location.latitude
        val longitude = location.longitude

        val tvLatitude = findViewById<TextView>(R.id.tvLatitude)
        val tvLongitude = findViewById<TextView>(R.id.tvLongitude)
        val tvLocal = findViewById<TextView>(R.id.tvLocal)

        tvLatitude.text = latitude.toString()
        tvLongitude.text = longitude.toString()

        if(longitude >= -52.6897000 && longitude <= -52.6861000 && latitude >= -26.1981000 && latitude <= -26.1956500){
            tvLocal.text = "Dentro do ginásio e valor de i = ${i}"
            i++
        }else{
            tvLocal.text = "Usuário está fora da área. e valor de i ${i}"
            


        }
    }

}