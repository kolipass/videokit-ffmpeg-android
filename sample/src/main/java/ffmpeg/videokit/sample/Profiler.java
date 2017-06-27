package ffmpeg.videokit.sample;

import android.util.Log;

import java.util.concurrent.TimeUnit;

/**
 * Simple Profiler
 * Created by PE
 */
public class Profiler {
    private long start;

    public Profiler() {
        start = System.nanoTime();
    }

    public long getInterval() {
        return System.nanoTime() - start;
    }

    public String record(String tag) {

        long time = TimeUnit.MICROSECONDS.convert(getInterval(), TimeUnit.NANOSECONDS);
        String timeL = time + " μs";

        if (time > 100000) {
            time = TimeUnit.MILLISECONDS.convert(getInterval(), TimeUnit.NANOSECONDS);
            timeL = time + " ms";

            if (time > 100000) {
                time = TimeUnit.SECONDS.convert(getInterval(), TimeUnit.NANOSECONDS);
                timeL = time + " s";
            }
        }

        String record = tag + " : " + timeL;
        Log.d("profile",record);
        return record;
    }
}
