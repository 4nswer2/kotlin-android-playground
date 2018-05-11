package jp.co.example.playground

import android.location.Location
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

//--------------------------------------------------
//	move camera
//--------------------------------------------------
internal fun GoogleMap.moveCamera(latLng: LatLng, zoom: Float? = null) {
	when (zoom) {
		null -> { moveCamera(CameraUpdateFactory.newLatLng(latLng)) }
		else -> { moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom)) }
	}
}

internal fun GoogleMap.moveCamera(latitude: Double, longitude: Double, zoom: Float? = null) {
	moveCamera(LatLng(latitude, longitude), zoom)
}

internal fun GoogleMap.moveCamera(location: Location, zoom: Float? = null) {
	moveCamera(location.toLatLng(), zoom)
}

internal fun GoogleMap.moveCamera(bounds: LatLngBounds, padding: Int) {
	moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding))
}

//--------------------------------------------------
//	animate camera
//--------------------------------------------------
internal fun GoogleMap.animateCamera(latLng: LatLng, zoom: Float? = null) {
	when (zoom) {
		null -> { animateCamera(CameraUpdateFactory.newLatLng(latLng)) }
		else -> { animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom)) }
	}
}

internal fun GoogleMap.animateCamera(latitude: Double, longitude: Double, zoom: Float? = null) {
	animateCamera(LatLng(latitude, longitude), zoom)
}

internal fun GoogleMap.animateCamera(location: Location, zoom: Float? = null) {
	animateCamera(location.toLatLng(), zoom)
}

internal fun GoogleMap.animateCamera(bounds: LatLngBounds, padding: Int) {
	animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding))
}

//--------------------------------------------------
//	private
//--------------------------------------------------
private fun Location.toLatLng(): LatLng {
	return LatLng(latitude, longitude)
}