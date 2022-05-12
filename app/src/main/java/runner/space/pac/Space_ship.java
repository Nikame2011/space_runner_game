package runner.space.pac;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import java.util.Random;

//import runner.space.pac.R;

public class Space_ship extends Space_object{
    Bitmap fire_b;
    Bitmap fire_r;
    Bitmap fire_l;
    Bitmap fire_f;

    public Space_ship(Context context, byte ltype, int lheal, float lx, float ly, float lspeed_x, float lspeed_y){
        super(context, ltype, lheal, lx, ly,lspeed_x, lspeed_y);
        int bitmapId = R.drawable.runner_0;//f0;// определяем начальные параметры
        Bitmap cBitmap = BitmapFactory.decodeResource(con.getResources(), bitmapId);
        body = Bitmap.createScaledBitmap(
                cBitmap, (int)(MainActivity.dw/(6-type)), (int)(MainActivity.dw/(6-type)), false);
        cBitmap.recycle();

        bitmapId = R.drawable.back_fire;//f0;// определяем начальные параметры
        cBitmap = BitmapFactory.decodeResource(con.getResources(), bitmapId);
        fire_b= Bitmap.createScaledBitmap(
                cBitmap, (int)(MainActivity.dw/(6-type)), (int)(MainActivity.dw/(6-type)), false);
        cBitmap.recycle();

        bitmapId = R.drawable.forward_fire;//f0;// определяем начальные параметры
        cBitmap = BitmapFactory.decodeResource(con.getResources(), bitmapId);
        fire_f= Bitmap.createScaledBitmap(
                cBitmap, (int)(MainActivity.dw/(6-type)), (int)(MainActivity.dw/(6-type)), false);
        cBitmap.recycle();

        bitmapId = R.drawable.left_fire;//f0;// определяем начальные параметры
        cBitmap = BitmapFactory.decodeResource(con.getResources(), bitmapId);
        fire_l= Bitmap.createScaledBitmap(
                cBitmap, (int)(MainActivity.dw/(6-type)), (int)(MainActivity.dw/(6-type)), false);
        cBitmap.recycle();

        bitmapId = R.drawable.right_fire;//f0;// определяем начальные параметры
        cBitmap = BitmapFactory.decodeResource(con.getResources(), bitmapId);
        fire_r= Bitmap.createScaledBitmap(
                cBitmap, (int)(MainActivity.dw/(6-type)), (int)(MainActivity.dw/(6-type)), false);
        cBitmap.recycle();

        max_speed=30;
    }

  /*  @Override
    void init2() {
        //super.init2();
        int bitmapId = R.drawable.runner_0;//f0;// определяем начальные параметры
        Bitmap cBitmap = BitmapFactory.decodeResource(con.getResources(), bitmapId);
        body = Bitmap.createScaledBitmap(
                cBitmap, (int)(MainActivity.dw/(6-type)), (int)(MainActivity.dw/(6-type)), false);
        cBitmap.recycle();

        bitmapId = R.drawable.back_fire;//f0;// определяем начальные параметры
        cBitmap = BitmapFactory.decodeResource(con.getResources(), bitmapId);
        fire_b= Bitmap.createScaledBitmap(
                cBitmap, (int)(MainActivity.dw/(6-type)), (int)(MainActivity.dw/(6-type)), false);
        cBitmap.recycle();

        bitmapId = R.drawable.forward_fire;//f0;// определяем начальные параметры
        cBitmap = BitmapFactory.decodeResource(con.getResources(), bitmapId);
        fire_f= Bitmap.createScaledBitmap(
                cBitmap, (int)(MainActivity.dw/(6-type)), (int)(MainActivity.dw/(6-type)), false);
        cBitmap.recycle();

        bitmapId = R.drawable.left_fire;//f0;// определяем начальные параметры
        cBitmap = BitmapFactory.decodeResource(con.getResources(), bitmapId);
        fire_l= Bitmap.createScaledBitmap(
                cBitmap, (int)(MainActivity.dw/(6-type)), (int)(MainActivity.dw/(6-type)), false);
        cBitmap.recycle();

        bitmapId = R.drawable.right_fire;//f0;// определяем начальные параметры
        cBitmap = BitmapFactory.decodeResource(con.getResources(), bitmapId);
        fire_r= Bitmap.createScaledBitmap(
                cBitmap, (int)(MainActivity.dw/(6-type)), (int)(MainActivity.dw/(6-type)), false);
        cBitmap.recycle();

        max_speed=30;
    }
*/
    @Override
   // void draw_2(Paint paint, Canvas canvas,float draw_x,float draw_y,float attach_angle,float attach_x,float attach_y) {
     //   super.draw_2(paint, canvas, draw_x,draw_y, attach_angle, attach_x, attach_y);
    void draw   (Paint paint, Canvas canvas, float offset_x,float offset_y,float attach_angle,float attach_x,float attach_y){
        super.draw   (paint, canvas, offset_x,offset_y,attach_angle,attach_x,attach_y);
        float draw_x=x-offset_x-body.getWidth()/2;
        float draw_y=y-offset_y-body.getHeight()/2;
        if(accel_x<0){
            Random rand=new Random();
            for (int i=0;i<5+rand.nextInt(5);i++) {
                Matrix matrix = new Matrix();
                matrix.setTranslate(draw_x - 1 + rand.nextInt(6)*3, draw_y+(-1+ rand.nextInt(2))*2);
                matrix.postRotate(attach_angle, attach_x, attach_y);
                canvas.drawBitmap(fire_r, matrix, paint);
            }
        }
        else if(accel_x>0){
            Random rand=new Random();
            for (int i=0;i<5+rand.nextInt(5);i++) {
                Matrix matrix = new Matrix();
                matrix.setTranslate(draw_x + 1 - rand.nextInt(6)*3, draw_y+(-1+ rand.nextInt(2))*2);
                matrix.postRotate(attach_angle, attach_x, attach_y);
                canvas.drawBitmap(fire_l, matrix, paint);
            }
        }

        if(accel_y<0){
            Random rand=new Random();
            for (int i=0;i<5+rand.nextInt(5);i++) {
                Matrix matrix = new Matrix();
                matrix.setTranslate(draw_x - 1 + rand.nextInt(2)*2, draw_y-2+ rand.nextInt(6)*3);
                matrix.postRotate(attach_angle, attach_x, attach_y);
                canvas.drawBitmap(fire_b, matrix, paint);
            }
        }
        else if(accel_y>0){
            Random rand=new Random();
            for (int i=0;i<5+rand.nextInt(5);i++) {
                Matrix matrix = new Matrix();
                matrix.setTranslate(draw_x - 1 + rand.nextInt(2)*2, draw_y+2- rand.nextInt(6)*3);
                matrix.postRotate(attach_angle, attach_x, attach_y);
                canvas.drawBitmap(fire_f, matrix, paint);
            }
        }
    }

   // float targ_x=0;
   // float targ_y=0;
float targ_angle=0;
    void set_accel(float x_ac, float y_ac, float anglee){
        targ_angle=anglee;
        float aan= (float) Math.toRadians(targ_angle);
        float cos= (float) Math.cos(aan);
        float sin= (float) Math.sin(aan);
       // targ_x=x_ac;
       // targ_y=y_ac;
       // accel_x= x_ac*cos-y_ac*sin;
       // accel_y= y_ac*cos+x_ac*sin;
        accel_x=x_ac;
        accel_y=y_ac;
        if (accel_x==0){
                accel_x=Math.abs((speed_x*cos+speed_y*sin)/(35))>0.0005?-(speed_x*cos+speed_y*sin)/(35):0;
        }
        if (accel_y==0){
            if ((speed_y*cos-speed_x*sin)/(35)>0.0005)
                accel_y=-(speed_y*cos-speed_x*sin)/(35);
            else if((speed_y*cos-speed_x*sin)<-max_speed*0.6)
                accel_y=-(speed_y*cos-speed_x*sin)/(35);
        }
    }


    @Override
    void update(byte speed_coefficient) {
        float aan= (float) Math.toRadians(targ_angle);
        float cos= (float) Math.cos(aan);
        float sin= (float) Math.sin(aan);

        if(Math.sqrt(Math.pow(speed_x-accel_y*sin*speed_coefficient,2)+Math.pow(speed_y+accel_y*cos*speed_coefficient,2))<=max_speed){
            speed_x+=-accel_y*sin*speed_coefficient;
            speed_y+=accel_y*cos*speed_coefficient;
        }
        else
        {
            accel_y=0;
        }
        if(Math.sqrt(Math.pow(speed_x+accel_x*cos*speed_coefficient,2)+Math.pow(speed_y+accel_x*sin*speed_coefficient,2))<=max_speed){
            speed_x+=accel_x*cos*speed_coefficient;
            speed_y+=accel_x*sin*speed_coefficient;
        }
        else
        {
            accel_x=0;
        }
        x+=speed_x*speed_coefficient;
        y+=speed_y*speed_coefficient;
        angle+=speed_angle*speed_coefficient;

        if (angle>360){
            angle-= 360;
        }
        else if (angle<-360){
            angle+= 360;
        }


    }
  /*  void arcade_stopper(byte speed_coef) {

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

    }*/
}
