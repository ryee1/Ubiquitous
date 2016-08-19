package com.example.android.sunshine.app;

import android.util.Log;

import com.example.android.sunshine.app.sync.SunshineSyncAdapter;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

public class WearableMessageListenerService extends WearableListenerService {
    public WearableMessageListenerService() {
    }

    private static final String REQUEST_SYNC_PATH = "/request_sync_path";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.e("Log", "message received");
        if (messageEvent.getPath().equals(REQUEST_SYNC_PATH)) {
            Log.e("Log", "message sync request received");
            SunshineSyncAdapter.syncImmediately(getApplicationContext());
        }
    }
}
