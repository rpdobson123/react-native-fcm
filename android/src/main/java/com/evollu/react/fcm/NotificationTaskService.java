package com.evollu.react.fcm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;

import com.facebook.react.HeadlessJsTaskService;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.jstasks.HeadlessJsTaskConfig;

public class NotificationTaskService extends HeadlessJsTaskService {
    @Nullable
    @Override
    protected HeadlessJsTaskConfig getTaskConfig(Intent intent) {
        Bundle extras = intent.getExtras();

        if (extras != null && extras.containsKey("preserve") && !extras.getBoolean("preserve")) {
            Integer notificationID = extras.getInt("notificationID");
            NotificationManagerCompat.from(this).cancel(notificationID);
        }

        return new HeadlessJsTaskConfig(
                "NotificationTaskService",
                extras != null ? Arguments.fromBundle(extras) : null,
                5000,
                true
        );
    }
}
