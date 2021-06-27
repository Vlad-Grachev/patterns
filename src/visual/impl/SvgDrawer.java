package visual.impl;

import geometry.IPoint;
import visual.IDrawer;

import java.io.FilterOutputStream;
import java.io.PrintStream;

public class SvgDrawer implements IDrawer {
    private static final String MOVE_COMMAND = "M";
    private static final String LINE_COMMAND = " L ";
    private static final String PATH_PATTERN = "<path d=\"%s\" />";

    private PrintStream stream;
    private boolean silentMode = true;
    private StringBuilder buffer;

    public SvgDrawer(PrintStream stream) {
        this.stream = stream;
        this.buffer = new StringBuilder();

    }

    public SvgDrawer(PrintStream stream, boolean silentMode) {
        this.stream = stream;
        this.silentMode = silentMode;
    }

    @Override
    public void drawFirstPoint(IPoint point) {
        buffer.setLength(0);
        buffer.append(MOVE_COMMAND + " ");
        if (!isSilentMode()) {
            write((MOVE_COMMAND + " "));
        }
        drawNextPoint(point);
    }

    @Override
    public void drawNextPoint(IPoint point) {
        buffer.append(point).append(LINE_COMMAND);
        if (!isSilentMode()) {
            write(point + LINE_COMMAND);
        }

    }

    @Override
    public void drawLastPoint(IPoint point) {
        buffer.append(point);
        write(String.format(PATH_PATTERN, buffer));
    }

    private void write(String data) {
        stream.println((data));
    }

    public FilterOutputStream getStream() {
        return stream;
    }

    public void setStream(PrintStream stream) {
        this.stream = stream;
    }

    public boolean isSilentMode() {
        return silentMode;
    }

    public void setSilentMode(boolean silentMode) {
        this.silentMode = silentMode;
    }
}
