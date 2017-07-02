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
    private int enemiesPerWave;
    private boolean waveCompleted;

    public Wave(Enemy enemyType,float spawnTime,int enemiesPerWave) {
        this.spawnTime = spawnTime;
        this.enemyType = enemyType;
        this.enemiesPerWave = enemiesPerWave;
        this.timeSinceLastSpawn = 0;
        this.enemyList = new ArrayList<>();
        this.waveCompleted = false;

        Spawn();
    }


    public void Update(){
        boolean allEnemiesDead = true;
        if(enemyList.size() < enemiesPerWave){

            timeSinceLastSpawn += Delta();
            if(timeSinceLastSpawn > spawnTime){
                Spawn();
                timeSinceLastSpawn = 0;
            }
        }

        for (Enemy e :
                enemyList) {
            if(e.isAlive()){
                allEnemiesDead = false;
                e.Update();
                e.Draw();
            }
        }
        if(allEnemiesDead)
            waveCompleted = true;
    }

    private void Spawn(){
        enemyList.add(new Enemy(enemyType.getTexture(),enemyType.getStartTile(),enemyType.getTileGrid(),64,64,enemyType.getSpeed()));

    }

    public boolean isCompleted() {
        return waveCompleted;
    }
}
