package data;

/**
 * Created by Bartek on 30.06.2017.
 */
public class TileGrid {
    public Tile[][] map;

    public TileGrid() {
        map = new Tile[20][15];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Tile(i*64,j*64,64,64,TileType.Grass);
            }
        }
    }

    public TileGrid(int[][] newMap) {
        map = new Tile[20][15];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Tile(i*64,j*64,64,64,
                        newMap[j][i]==0?
                                TileType.Grass:
                                TileType.Dirt);

            }
        }
    }

    public void Draw(){

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j].Draw();
            }
        }
    }
}
