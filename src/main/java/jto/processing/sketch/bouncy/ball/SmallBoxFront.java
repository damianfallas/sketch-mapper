package jto.processing.sketch.bouncy.ball;

import jto.processing.event.EdgeEnum;
import jto.processing.event.EdgeEvent;
import jto.processing.model.Ball;
import jto.processing.sketch.BounyBallSketch;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.Iterator;

public class SmallBoxFront extends BounyBallSketch {
    public SmallBoxFront(PApplet parent, int width, int height) {
        super(parent, width, height);
    }

    @Override
    public void edgeEvent(EdgeEvent edgeEvent) {
        Ball ball = new Ball();
        ball.setVelocity(edgeEvent.getVelocity());
        switch (edgeEvent.getReceivingEdge()) {
            case TOP: {
                ball.setLocation(new PVector((float) (graphics.width * edgeEvent.getLocation()), 0.0f));
                break;
            }
            case LEFT: {
                ball.setLocation(new PVector(0.0f, (float) (graphics.height * edgeEvent.getLocation())));
                break;
            }
            default: {
                return;
            }
        }
        balls.add(ball);
    }

    @Override
    public String getName() {
        return "SmallBoxFront";
    }

    @Override
    public void update() {
        Iterator<Ball> it = balls.iterator();
        while (it.hasNext()) {
            Ball ball = it.next();
            ball.getLocation().set(ball.getLocation().x + ball.getVelocity().x, ball.getLocation().y + ball.getVelocity().y);
            if (ball.getLocation().x < 0) {
                double ratio = (double) ball.getLocation().y / (double) graphics.height;
                EdgeEvent edgeEvent = new EdgeEvent(getName(), EdgeEnum.LEFT, EdgeEnum.RIGHT, ratio, ball.getVelocity());
                publishEvent(edgeEvent);
                it.remove();
                continue;
            }
            if (ball.getLocation().y < 0) {
                double ratio = (double) ball.getLocation().x / (double) graphics.width;
                EdgeEvent edgeEvent = new EdgeEvent(getName(), EdgeEnum.TOP, EdgeEnum.BOTTOM, ratio, ball.getVelocity());
                publishEvent(edgeEvent);
                it.remove();
                continue;
            }
            if (ball.getLocation().x > graphics.width) {
                ball.getVelocity().set(ball.getVelocity().x * -1, ball.getVelocity().y);
            }
            if (ball.getLocation().y > graphics.height) {
                ball.getVelocity().set(ball.getVelocity().x, ball.getVelocity().y * -1);
            }
        }
    }
}