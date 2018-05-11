package jp.co.example.playground

import android.annotation.SuppressLint
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

	private val locationClient by lazy { LocationServices.getFusedLocationProviderClient(this) }
	private val locationRequest by lazy { LocationRequest.create().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY).setInterval(1000) }
	private val locationCallback = object : LocationCallback() {
		override fun onLocationResult(result: LocationResult?) {
			super.onLocationResult(result)
			result ?: return
			onLocationChanged(result.lastLocation)
		}
	}

	private var googleMap: GoogleMap? = null
	private var lastLocation: Location? = null

	@SuppressLint("MissingPermission")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		(supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).getMapAsync(this)
		locationClient.requestLocationUpdates(locationRequest, locationCallback, mainLooper)
	}

	override fun onMapReady(googleMap: GoogleMap?) {
		this.googleMap = googleMap
	}

	fun onLocationChanged(location: Location?) {
		d("onLocationChanged $location")
		location ?: return

		lastLocation = if (lastLocation == null) {
			googleMap?.moveCamera(location.latLng, 20f)
			location
		} else {
			runOnUiThread {
				googleMap?.addPolyline(
						PolylineOptions()
								.add(lastLocation?.latLng)
								.add(location.latLng)
				)
			}
			location
		}
	}

	private val Location.latLng: LatLng
		get() = LatLng(latitude, longitude)
}
