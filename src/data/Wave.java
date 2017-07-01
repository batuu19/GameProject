package data;

import java.util.ArrayList;

import static helpers.Clock.Delta;

/**
 * Created by Bartek on 01.07.2017.
 */
public class Wave {

    private float timeSinceLastSpawn,spawnTime;
    private Enemy enemyType;
    private ArrayList<Enemy> enemyList;

    public Wave(float spawnTime, Enemy enemyType) {
        this.spawnTime = spawnTime;
        this.enemyType = enemyType;
        timeSinceLastSpawn = 0;
        enemyList = new ArrayList<>();
    }


    public void Update(){
        timeSinceLastSpawn += Delta();
        if(timeSinceLastSpawn > spawnTime){
            Spawn();
            timeSinceLastSpawn = 0;
        }

        for (Enemy e :
                enemyList) {
            e.Update();
            e.Draw();
        }
    }

    private void Spawn(){
        enemyList.add(new Enemy(enemyType.getTexture(),enemyType.getStartTile(),64,64,enemyType.getSpeed()));

    }
}
