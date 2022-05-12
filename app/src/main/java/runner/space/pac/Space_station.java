package runner.space.pac;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import java.util.Random;

import runner.space.pac.R;



public class Space_station extends Space_object{
  /*  Bitmap fire_b;
    Bitmap fire_r;
    Bitmap fire_l;
    Bitmap fire_f;*/
  public Space_station(Context context, byte ltype, int lheal, float lx, float ly, float lspeed_x, float lspeed_y) {
      super(context, ltype, lheal, lx, ly, lspeed_x, lspeed_y);
      int bitmapId = R.drawable.runner_0;//f0;// определяем начальные параметры
      Bitmap cBitmap = BitmapFactory.decodeResource(con.getResources(), bitmapId);
      body = Bitmap.createScaledBitmap(
              cBitmap, (int)(MainActivity.dw/(1-type)), (int)(MainActivity.dw/(1-type)), false);
      cBitmap.recycle();
  }
  /*  @Override
    void init2() {
        super.init2();
        int bitmapId = R.drawable.runner_0;//f0;// определяем начальные параметры
        Bitmap cBitmap = BitmapFactory.decodeResource(con.getResources(), bitmapId);
        body = Bitmap.createScaledBitmap(
                cBitmap, (int)(MainActivity.dw/(1-type)), (int)(MainActivity.dw/(1-type)), false);
        cBitmap.recycle();*/

      /*  bitmapId = R.drawable.back_fire;//f0;// определяем начальные параметры
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
        cBitmap.recycle();*/
 //   }

 /*   @Override
    void draw_2(Paint paint, Canvas canvas, float draw_x, float draw_y, float attach_angle, float attach_x, float attach_y) {
        super.draw_2(paint, canvas, draw_x,draw_y, attach_angle, attach_x, attach_y);
        if(targ_x<0){
            Random rand=new Random();
            for (int i=0;i<5+rand.nextInt(5);i++) {
                Matrix matrix = new Matrix();
                matrix.setTranslate(draw_x - 1 + rand.nextInt(6)*3, draw_y+(-1+ rand.nextInt(2))*2);
                matrix.postRotate(attach_angle, attach_x, attach_y);
                canvas.drawBitmap(fire_r, matrix, paint);
            }
        }
        else if(targ_x>0){
            Random rand=new Random();
            for (int i=0;i<5+rand.nextInt(5);i++) {
                Matrix matrix = new Matrix();
                matrix.setTranslate(draw_x + 1 - rand.nextInt(6)*3, draw_y+(-1+ rand.nextInt(2))*2);
                matrix.postRotate(attach_angle, attach_x, attach_y);
                canvas.drawBitmap(fire_l, matrix, paint);
            }
        }

        if(targ_y<0){
            Random rand=new Random();
            for (int i=0;i<5+rand.nextInt(5);i++) {
                Matrix matrix = new Matrix();
                matrix.setTranslate(draw_x - 1 + rand.nextInt(2)*2, draw_y-2+ rand.nextInt(6)*3);
                matrix.postRotate(attach_angle, attach_x, attach_y);
                canvas.drawBitmap(fire_b, matrix, paint);
            }
        }
        else if(targ_y>0){
            Random rand=new Random();
            for (int i=0;i<5+rand.nextInt(5);i++) {
                Matrix matrix = new Matrix();
                matrix.setTranslate(draw_x - 1 + rand.nextInt(2)*2, draw_y+2- rand.nextInt(6)*3);
                matrix.postRotate(attach_angle, attach_x, attach_y);
                canvas.drawBitmap(fire_f, matrix, paint);
            }
        }
    }*/

    float targ_x=0;
    float targ_y=0;

    void set_accel(float x_ac, float y_ac, float angle){
        float aan= (float) Math.toRadians(angle);
        float cos= (float) Math.cos(aan);
        float sin= (float) Math.sin(aan);
        targ_x=x_ac;
        targ_y=y_ac;
        accel_x= x_ac*cos-y_ac*sin;
        accel_y= y_ac*cos+x_ac*sin;
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
