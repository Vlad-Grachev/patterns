package ui;

import shared.Config;
import visual.VisualCurve;
import visual.AGraphicsDrawer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Canvas extends JComponent {
    private List<VisualCurve> curves = new ArrayList<>();
    private AGraphicsDrawer graphicsContext;

    public Canvas(AGraphicsDrawer graphicsContext) {
        super();
        this.graphicsContext = graphicsContext;
    }

    public void addCurve(VisualCurve curve) {
        curves.add(curve);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        graphicsContext.setG2d(g2d);
        g2d.drawLine(0, 0,0, Config.getInstance().getCanvasHeight());
        g2d.drawLine(0, 0,getWidth(), 0);
        g2d.drawLine(0, Config.getInstance().getCanvasHeight() - 1, 
                Config.getInstance().getCanvasWidth(), Config.getInstance().getCanvasHeight() - 1);
        g2d.drawLine(Config.getInstance().getCanvasWidth() - 1, 0,
                Config.getInstance().getCanvasWidth() -1, Config.getInstance().getCanvasHeight() - 1);

        for (VisualCurve curve: curves) {
            curve.draw(graphicsContext);
        }
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(Config.getInstance().getCanvasWidth(),
                Config.getInstance().getCanvasHeight());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Config.getInstance().getCanvasWidth(),
                Config.getInstance().getCanvasHeight());
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Config.getInstance().getCanvasWidth(),
                Config.getInstance().getCanvasHeight());
    }

    public void clearCanvas() {
        curves = new ArrayList<>();
    }

    public List<VisualCurve> getCurves() {
        return curves;
    }

    public void setCurves(List<VisualCurve> curves) {
        this.curves = curves;
    }

    public AGraphicsDrawer getGraphicsContext() {
        return graphicsContext;
    }

    public void setGraphicsContext(AGraphicsDrawer graphicsContext) {
        this.graphicsContext = graphicsContext;
    }
}
