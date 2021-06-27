package ui;

import geometry.ICurve;
import geometry.IPoint;
import geometry.composite.Chain;
import geometry.decorator.ADecorator;
import geometry.decorator.Fragment;
import geometry.decorator.MoveTo;
import geometry.impl.Point;
import shared.Config;
import shared.util.Generator;
import shared.util.IGenerator;
import visual.IDrawable;
import visual.IDrawer;
import visual.VisualCurve;
import visual.impl.DashedLineGraphicsDrawer;
import visual.impl.GreenColorGraphicsDrawer;
import visual.impl.SvgDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserInterface extends JFrame {

    private List<Canvas> canvases;
    private IGenerator generator;
    private IDrawer saver;

    public UserInterface() throws HeadlessException {
        super("Curves Drawer");
        setSize(Config.getInstance().getWindowWidth(), Config.getInstance().getWindowHeight());
        setLocation(Config.getInstance().getWindowLocationX(), Config.getInstance().getGetWindowLocationY());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        FlowLayout layout2 = new FlowLayout(FlowLayout.CENTER);
        panel.setLayout(layout);
        layout2.setVgap(8);
        panel2.setLayout(layout2);
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);

        canvases = new ArrayList<>();
        saver = new SvgDrawer(System.out);
        generator = new Generator();

        GreenColorGraphicsDrawer greenDrawer = new GreenColorGraphicsDrawer();
        DashedLineGraphicsDrawer dashedDrawer = new DashedLineGraphicsDrawer();

        canvases.add(new Canvas(greenDrawer));
        canvases.add(new Canvas(dashedDrawer));

//        Line line = new Line(new Point(200.0, 500.0), new Point(700.0, 700.0));
//        Line line2 = new Line(new Point(100.0, 200.0), new Point(700.0, 700.0));
//        Bezier bezier = new Bezier(new Point(300.0, 100.0),
//                new Point(400.0, 400.0), new Point(600.0, 600.0), new Point(300.0, 400.0));


        canvases.forEach(c -> panel.add(c, new GridBagConstraints()));

        addButtons(panel2);
        add(panel, BorderLayout.CENTER);
        add(panel2, BorderLayout.NORTH);

        setVisible(true);
    }

    private void addButtons(JPanel panel) {
        JButton genButton = new JButton("Generate");
        genButton.setSize(80, 190);
        genButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillCanvases();
                repaint();
            }
        });

        JButton moveButton = new JButton("Move");
        moveButton.setSize(80, 190);
        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveCurve();
                repaint();
            }
        });

        JButton fragmentButton = new JButton("Fragment");
        fragmentButton.setSize(80, 190);
        fragmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fragmentCurve();
                repaint();
            }
        });

        JButton joinButton = new JButton("Join");
        joinButton.setSize(80, 190);
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                joinCurves();
                repaint();
            }
        });

        JButton compositButton = new JButton("Composit");
        joinButton.setSize(80, 190);
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createComposit();
                repaint();
            }
        });

        JLabel space = new JLabel("   ");
        JButton saveButton = new JButton("Save");
        saveButton.setSize(120, 100);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCurves();
            }
        });

        JButton resetButton = new JButton("Reset");
        resetButton.setSize(80, 190);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetDecorations();
                repaint();
            }
        });

        panel.add(genButton);
        panel.add(moveButton);
        panel.add(fragmentButton);
        panel.add(joinButton);
        panel.add(resetButton);
        panel.add(saveButton);
        //panel.add(space);
    }

    public List<Canvas> getCanvases() {
        return canvases;
    }

    public void setCanvases(List<Canvas> canvases) {
        this.canvases = canvases;
    }

    private void fillCanvases() {
        canvases.forEach(c -> {
            c.clearCanvas();
            Random random = new Random();
            int linesNumber = random.nextInt(4) + 1;
            for (int i = 0; i < linesNumber; i++) {
                VisualCurve curve = generator.getRandomVisualCurve(0, 0,
                        Config.getInstance().getCanvasWidth(),
                        Config.getInstance().getCanvasHeight());
                c.addCurve(curve);
            }
        });
    }

    private void saveCurves() {
        if (canvases == null) {
            return;
        }
        canvases.forEach(c -> {
            for (IDrawable drawable: c.getCurves()) {
                drawable.draw(saver);
            }
        });
    }

    private void moveCurve() {
        if (canvases == null || canvases.isEmpty()) {
            return;
        }

        Canvas c = canvases.get(0);
        VisualCurve visualCurve = c.getCurves().get(0);
        ICurve curve = visualCurve.getCurve();

        ICurve decorated = new MoveTo(curve, new Point(20, 20));
        visualCurve.setCurve(decorated);
    }

    private void fragmentCurve() {
        if (canvases == null || canvases.isEmpty()) {
            return;
        }

        Canvas c = canvases.get(0);
        VisualCurve visualCurve = c.getCurves().get(0);
        ICurve curve = visualCurve.getCurve();

        ICurve decorated = new Fragment(curve, 0.5, 1.0);
        ICurve decorated2 = new Fragment(decorated, 1.0, 0);
        visualCurve.setCurve(decorated2);
    }

    private void joinCurves() {
        if (canvases == null || canvases.isEmpty()) {
            return;
        }

        Canvas c = canvases.get(0);

        if (c.getCurves().size() > 1) {
            VisualCurve first = c.getCurves().get(0);
            VisualCurve second = c.getCurves().get(1);

            ICurve firstCurve = first.getCurve();
            ICurve secondCurve = second.getCurve();

            IPoint firstEndPoint = firstCurve.getPoint(1.0);
            IPoint secondStartPoint = secondCurve.getPoint(0.0);

            double xShift = secondStartPoint.getX() > firstEndPoint.getX()
                    ? -1.0 * (secondStartPoint.getX() - firstEndPoint.getX())
                    : firstEndPoint.getX() - secondStartPoint.getX();

            double yShift = secondStartPoint.getY() > firstEndPoint.getY()
                    ? -1.0 * (secondStartPoint.getY() - firstEndPoint.getY())
                    : firstEndPoint.getY() - secondStartPoint.getY();

            MoveTo movedSecondCurve = new MoveTo(secondCurve, new Point(xShift, yShift));

            second.setCurve(movedSecondCurve);
        }
    }

    private void createComposit() {
        if (canvases == null || canvases.isEmpty()) {
            return;
        }

        Canvas c = canvases.get(0);

        if (c.getCurves().size() > 1) {
            ICurve first = c.getCurves().get(0).getCopy();
            ICurve second = c.getCurves().get(1).getCopy();
            ICurve composit = new Chain(first, second);


            second.setCurve(movedSecondCurve);
        }
    }

    private void resetDecorations() {
        getCanvases().forEach(c -> {
            for (VisualCurve visualCurve: c.getCurves()) {
                while (visualCurve.getCurve() instanceof ADecorator) {
                    visualCurve.setCurve(((ADecorator) visualCurve.getCurve()).getComponent());
                }
            }
        });
    }
}
