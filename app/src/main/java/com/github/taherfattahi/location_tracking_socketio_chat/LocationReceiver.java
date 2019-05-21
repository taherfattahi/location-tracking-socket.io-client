package com.github.taherfattahi.location_tracking_socketio_chat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

import br.com.safety.locationlistenerhelper.core.SettingsLocationTracker;

/**
 * @author taher fattahi
 */
public class LocationReceiver extends BroadcastReceiver {


    private Socket socket;

    @Override
    public void onReceive(Context context, Intent intent) {

        try {
            socket = IO.socket("http://192.168.1.3:3000");
            socket.connect();
//            socket.emit("join", "connect to socket");
        } catch (URISyntaxException e) {
            e.printStackTrace();

        }

        if (null != intent && intent.getAction().equals("my.action")) {
            Location locationData = (Location) intent.getParcelableExtra(SettingsLocationTracker.LOCATION_MESSAGE);
            Log.d("Location: ", "Latitude: " + locationData.getLatitude() + "Longitude:" + locationData.getLongitude());
            //send your call to api or do any things with the of location data


            socket.emit("messagedetection",locationData.getLatitude() + "-" + locationData.getLongitude());
        }
    }

}