package jto.processing.sketch;


import processing.core.PApplet;

public class TestSketch extends AbstractSketch {

    public TestSketch(final PApplet parent, final int width, final int height) {
        super(parent, width, height);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void draw() {
        graphics.beginDraw();
        graphics.background(255);
        graphics.fill(0);
        graphics.ellipse(graphics.width / 2, graphics.height / 2, parent.random(10, 100) , parent.random(10, 100));
        graphics.endDraw();
    }

    @Override
    public String getName() {
        return "TestSketch";
    }

    @Override
    public void setup() {

    }

    @Override
    public void update() {

    }
}