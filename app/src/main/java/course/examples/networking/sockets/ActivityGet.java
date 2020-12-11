package course.examples.networking.sockets;

import android.app.Activity;

public class ActivityGet {
    private static Activity activity;

    public static Activity getActivity() {
        return activity;
    }

    public static void setActivity(Activity activity) {
        ActivityGet.activity = activity;
    }
}
