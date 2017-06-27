package processing.ffmpeg.videokit;

/**
 * Created by Ilja Kosynkin on 06.07.2016.
 * Copyright by inFullMobile
 */
public class VideoKit {
    private static boolean init = false;

    private static void loadLibs() {
        System.loadLibrary("x264");
        System.loadLibrary("avutil");
        System.loadLibrary("swresample");
        System.loadLibrary("avcodec");
        System.loadLibrary("avformat");
        System.loadLibrary("swscale");
        System.loadLibrary("avfilter");
        System.loadLibrary("avdevice");
        System.loadLibrary("videokit");
        init = true;
    }

    public VideoKit() {
        if (!init) {
            loadLibs();
        }
    }

    public VideoKit(boolean lazy) {
        if (!lazy && !init) {
            loadLibs();
        }
    }

    private LogLevel logLevel = LogLevel.NO_LOG;

    public void setLogLevel(LogLevel level) {
        logLevel = level;
    }

    int process(String[] args) {
        if (!init) {
            loadLibs();
        }

        return run(logLevel.getValue(), args);
    }

    private native int run(int loglevel, String[] args);

    public CommandBuilder createCommand() {
        return new VideoCommandBuilder(this);
    }
}
