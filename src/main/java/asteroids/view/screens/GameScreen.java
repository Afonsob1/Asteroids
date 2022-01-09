package asteroids.view.screens;

import asteroids.model.Entities.*;
import asteroids.view.Game.AsteroidView;
import asteroids.view.Game.EnemyShipView;
import asteroids.view.Game.LaserView;
import asteroids.view.Game.PlayerView;
import asteroids.model.Constraints;
import asteroids.view.View;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import asteroids.model.GameModel;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.io.IOException;

public class GameScreen extends ScreenView {

    private final GameModel model;

    public GameScreen(GameModel model) {
        this.model = model;
        setFont(new Font(Font.MONOSPACED,Font.PLAIN, 1));
    }

    @Override
    public void draw() throws IOException {
        clear();

        for(MovingObject movingObject : model.getEntities()){
            View view = null;
            if(movingObject instanceof Player)
                view = getView((Player) movingObject);
            else if(movingObject instanceof  Asteroid)
                view = getView((Asteroid) movingObject);
            else if (movingObject instanceof LaserBeam)
                view = getView((LaserBeam) movingObject);
            else if (movingObject instanceof EnemyShip)
                view = getView((EnemyShip) movingObject);

            if(view!=null){
                view.setGraphics(getGraphics());
                view.draw();
            }
        }

        refresh();
    }

    public GameModel getModel() {
        return model;
    }

    public View getView(Player player){
        return new PlayerView(player);
    }

    public View getView(Asteroid asteroid){
        return new AsteroidView(asteroid);
    }

    public View getView(LaserBeam laserBeam){
        return new LaserView(laserBeam);
    }

    public View getView(EnemyShip enemyShip){
        return new EnemyShipView(enemyShip);
    }

    @Override
    public void setGraphics(TextGraphics graphics) {
        super.setGraphics(graphics);
    }

    public TerminalSize getSize(){
        return new TerminalSize(Constraints.WIDTH, Constraints.HEIGHT);
    }

}
