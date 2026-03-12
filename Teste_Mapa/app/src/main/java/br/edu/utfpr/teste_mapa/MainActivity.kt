package br.edu.utfpr.teste_mapa

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var locationClient: FusedLocationProviderClient
    private lateinit var checkInButton: Button

    private val cristoRedentor = LatLng(-22.9519, -43.2105)
    private val checkInRadius = 100.0 // metros

    private var isInsideArea = false
    private var isCheckedIn = false
    private var checkInStartTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkInButton = findViewById(R.id.btnCheckIn)
        checkInButton.setOnClickListener {
            if (isInsideArea) {
                isCheckedIn = true
                checkInStartTime = SystemClock.elapsedRealtime()
                Toast.makeText(this, "Check-in iniciado", Toast.LENGTH_SHORT).show()
            }
        }

        locationClient = LocationServices.getFusedLocationProviderClient(this)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cristoRedentor, 17f))

        mMap.addCircle(
            CircleOptions()
                .center(cristoRedentor)
                .radius(checkInRadius)
                .strokeColor(0x5500ff00)
                .fillColor(0x2200ff00)
        )

        mMap.addMarker(MarkerOptions().position(cristoRedentor).title("Cristo Redentor"))

        startLocationUpdates()
    }

    private fun startLocationUpdates() {
        val locationRequest = LocationRequest.create().apply {
            interval = 3000
            fastestInterval = 2000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1001)
            return
        }

        locationClient.requestLocationUpdates(locationRequest, object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                val location = result.lastLocation ?: return
                handleLocation(location)
            }
        }, mainLooper)
    }

    private fun handleLocation(location: Location) {
        val userLatLng = LatLng(location.latitude, location.longitude)

        val distance = FloatArray(1)
        Location.distanceBetween(
            userLatLng.latitude, userLatLng.longitude,
            cristoRedentor.latitude, cristoRedentor.longitude,
            distance
        )

        val withinArea = distance[0] <= checkInRadius

        if (withinArea && !isInsideArea) {
            Toast.makeText(this, "Você entrou na área", Toast.LENGTH_SHORT).show()
        } else if (!withinArea && isInsideArea) {
            Toast.makeText(this, "Você saiu da área", Toast.LENGTH_SHORT).show()

            if (isCheckedIn) {
                val duration = SystemClock.elapsedRealtime() - checkInStartTime
                val seconds = duration / 1000
                Toast.makeText(this, "Você ficou por $seconds segundos na área", Toast.LENGTH_LONG).show()
                isCheckedIn = false
            }
        }

        isInsideArea = withinArea
        checkInButton.isEnabled = withinArea
    }

    // Permissão
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1001 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startLocationUpdates()
        } else {
            Toast.makeText(this, "Permissão de localização necessária", Toast.LENGTH_LONG).show()
        }
    }
}
