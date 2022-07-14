package runner.space.pac;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import static runner.space.pac.MainActivity.dw;
import static runner.space.pac.MainActivity.dh;


public abstract class Space_object {

    protected float x;// координаты
    protected float speed_x; // скорость
    protected float accel_x; // ускорение

    protected float y;// координаты
    protected float speed_y; // скорость
    protected float accel_y; // ускорение

    protected float angle; // текущий угол вращения
    protected float speed_angle; //скорость изменения угла вращения

    int heal;

    String type;
    //String classificator;

    //protected float max_speed; // скорость
    //int max_heal;
    //float size;
    //Bitmap body;
    //Context con;

    public Space_object(/*Context context,*/ String type, /*String classificator,*/ int heal, float x, float y,float speed_x, float speed_y){
        //con=context;
        this.type=type;
        //this.classificator=classificator;
                //this.max_heal =lheal;
        this.heal=heal;
        this.x=x;
        this.y=y;
        this.speed_x=speed_x;
        this.speed_y=speed_y;
        //size= (float) (heal/50.0);
    }

    void update(byte speed_coef) {
        speed_x+=accel_x*speed_coef;
        speed_y+=accel_y*speed_coef;
        x+=speed_x*speed_coef;
        y+=speed_y*speed_coef;
        angle+=speed_angle*speed_coef;

        if (angle>360){
            angle-= 360;
        }
        else if (angle<-360){
            angle+= 360;
        }
    }

    void draw(Paint paint, Canvas canvas, float offset_x,float offset_y,float attach_angle,float attach_x,float attach_y){
        float size= (float) Resources.getObject(this.getClass(),type).get("size");
        float draw_x=x-offset_x-size;
        float draw_y=y-offset_y-size;

        if (draw_x+size>=-dw & draw_x-size<=2* dw & draw_y+size>=-dw & draw_y<=2* dh-size)
        {
            Matrix matrix = new Matrix();
            matrix.setTranslate(draw_x, draw_y);
            matrix.preRotate(angle, size, size);
            matrix.postRotate(attach_angle, attach_x-offset_x, attach_y-offset_y);
            canvas.drawBitmap((Bitmap) Resources.getObject(this.getClass(),type).get("body"), matrix, paint);
        }
    }

    void alternate_draw(Paint paint, Canvas canvas/*, Matrix matrix*/){
        if (matrix!=null){
            canvas.drawBitmap((Bitmap) Resources.getObject(this.getClass(),type).get("body"), matrix, paint);
        }
    }

    Matrix matrix;

    void get_matrix(float offset_x,float offset_y,float attach_angle,float attach_x,float attach_y){
        float size= (float) Resources.getObject(this.getClass(),type).get("size");
        float draw_x=x-offset_x-size;
        float draw_y=y-offset_y-size;
        if (draw_x+size>=-dw & draw_x-size<=2* dw & draw_y+size>=-dw & draw_y<=2* dh-size)
        {
            Matrix mat = new Matrix();
            mat.setTranslate(draw_x, draw_y);

            mat.preRotate(angle, size, size);
            mat.postRotate(attach_angle, attach_x-offset_x, attach_y-offset_y);
            matrix=mat;
        }
        else{
            matrix=null;
        }
    }

    boolean isFarAway(float offset_x, float offset_y){
    if (y>4*MainActivity.dh-offset_y|y<-4*MainActivity.dh-offset_y |x>4*MainActivity.dh-offset_x|x<-4*MainActivity.dh-offset_x)
        return true;
    else
        return false;
    }

    boolean isvisible(float offset_x, float offset_y){
        if (y<=4*MainActivity.dh-offset_y && y>=-4*MainActivity.dh-offset_y && x<=4*MainActivity.dh-offset_x && x>=-4*MainActivity.dh-offset_x)
            return true;
        else
            return false;
    }
}
