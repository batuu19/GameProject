package data;

/**
 * Created by Bartek on 05.07.2017.
 */
public interface Entity {
    float getX();
    float getY();
    int getWidth();
    int getHeight();
    void setX(float x);
    void setY(float y);
    void setWidth(int width);
    void setHeight(int height);
    void update();
    void draw();

}
