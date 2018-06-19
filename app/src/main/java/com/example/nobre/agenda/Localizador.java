package com.example.nobre.agenda;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public class Localizador implements GoogleApiClient.ConnectionCallbacks, LocationListener {

    private final GoogleApiClient client;
    private final GoogleMap mapa;

    public Localizador(Context context, GoogleMap mapa) {
        client = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .build();

        client.connect();

        this.mapa = mapa;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        @SuppressLint("RestrictedApi") LocationRequest request = new LocationRequest();
        request.setSmallestDisplacement(50); // atualiza a cada 50m
        request.setInterval(1000); // intervalo de 1000 milisegundos
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); // precisão da localização, PRIORITY_HIGH_ACCURACY é a maior possível, gasta bateria

        LocationServices.FusedLocationApi.requestLocationUpdates(client, request, this); // necessária permissão para gps
        // terminei de assistir no 20:51
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng coordenada = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(coordenada);
        mapa.moveCamera(cameraUpdate);
    }
}
