package com.example.android.sunshine.app.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class SunshineSyncService extends Service {
    private static final String LOG_TAG = SunshineSyncService.class.getSimpleName();

    private static final Object sSyncAdapterLock = new Object();
    private static SunshineSyncAdapter sSunshineSyncAdapter = null;

    @Override
    public void onCreate() {
        Log.d("SunshineSyncService", "onCreate - SunshineSyncService");
        synchronized (sSyncAdapterLock) {
//            if(mGoogleApiClient == null) {
//                mGoogleApiClient = new GoogleApiClient.Builder(this)
//                        .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
//                            @Override
//                            public void onConnected(Bundle connectionHint) {
//                                Log.e(LOG_TAG, "onConnected: " + connectionHint);
//                            }
//
//                            @Override
//                            public void onConnectionSuspended(int cause) {
//                                Log.d(LOG_TAG, "onConnectionSuspended: " + cause);
//                            }
//                        })
//                        .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
//                            @Override
//                            public void onConnectionFailed(ConnectionResult result) {
//                                Log.d(LOG_TAG, "onConnectionFailed: " + result);
//                            }
//                        })
//                        // Request access only to the Wearable API
//                        .addApi(Wearable.API)
//                        .build();
//                Log.e(LOG_TAG, "google api created");
//            }
            if (sSunshineSyncAdapter == null) {
                sSunshineSyncAdapter = new SunshineSyncAdapter(getApplicationContext(), true);
            }
            sSunshineSyncAdapter.mGoogleApiClient.connect();
//            mGoogleApiClient.connect();
        }
    }

    @Override public void onDestroy() {
        sSunshineSyncAdapter.mGoogleApiClient.disconnect();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return sSunshineSyncAdapter.getSyncAdapterBinder();
    }
}