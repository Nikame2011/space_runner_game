package runner.space.pac;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import static runner.space.pac.MainActivity.dw;
import static runner.space.pac.MainActivity.dh;
import static runner.space.pac.MainActivity.gw;


public class GUI {
    Bitmap b;
    Bitmap b1;
    Context con;


    public GUI(Context context){
        con=context;
        int bitmapId = R.drawable.cursor2;//f0;// определяем начальные параметры
        Bitmap cBitmap = BitmapFactory.decodeResource(con.getResources(), bitmapId);
        b = Bitmap.createScaledBitmap(
                cBitmap, (int)(MainActivity.dw/6), (int)(MainActivity.dw/6), false);
        cBitmap.recycle();

        bitmapId = R.drawable.cursor3;//f0;// определяем начальные параметры
        cBitmap = BitmapFactory.decodeResource(con.getResources(), bitmapId);
        b1 = Bitmap.createScaledBitmap(
                cBitmap, (int)(MainActivity.dw/6), (int)(MainActivity.dw/6), false);
        cBitmap.recycle();
    }

    void draw(Paint paint, Canvas canvas, Space_ship player,Space_station targeted/*float target_x,float target_y*/,float target){

        paint.setColor(Color.RED);
        paint.setTextSize((float) (dw / 20.0));


        canvas.drawRect(dw / 4 , dh- dw / 50 , dw / 4 + (dw*2 / 4) * player.heal / player.max_heal, dh, paint);


        //canvas.drawRect(dw / 8 + dw / 50, dh- dw / 16 + dw / 200, dw / 8 + dw / 50 + (dw*3 / 4 - dw / 25) * player.heal / player.max_heal, dh-dw/200, paint);
        paint.setColor(Color.WHITE);
        //canvas.drawText(/*con.getString(R.string.energy)+*/"HEAL: "+String.valueOf(player.heal) + " / " + String.valueOf(player.max_heal), dw / 2 - dw / 8, dh-dw/16 + dw / 200 +dw/30, paint);

        //float al= (float) Math.toDegrees(Math.atan((player.y-target_y)/(player.x-target_x)))+gw.angle;

        float all= (float) Math.toDegrees(Math.atan((player.x-targeted.x)/(-player.y+targeted.y)))-gw.angle;
        if (-player.y+targeted.y>0)
            all+=180;

        Matrix matrix = new Matrix();
        matrix.setTranslate(0, dh-dw/6);

        matrix.preRotate(all,   dw/12, dw/12);
        //matrix.postRotate(attach_angle, attach_x-offset_x, attach_y-offset_y);
        canvas.drawBitmap(b, matrix, paint);

        if (gw.met.size()>0){
        float al= (float) Math.toDegrees(Math.atan((player.x-gw.met.get(0).x)/(-player.y+gw.met.get(0).y)))-gw.angle;
        if (-player.y+gw.met.get(0).y>0)
            al+=180;
        canvas.drawText(/*con.getString(R.string.energy)+*/"Target: "+String.valueOf(target)/*String.valueOf(-target_y+player.y)+" / "+String.valueOf(target_x-player.x) +" / "+String.valueOf(al)*/,  dw / 8, dw/16 + dw / 200 +dw/30, paint);

        canvas.drawText(/*con.getString(R.string.energy)+*/"fps:"+String.valueOf(gw.frames)+" / "+String.valueOf(gw.increment)+" / "+String.valueOf(gw.dop_inc) , dw*3 / 5 , dw/16 + dw / 200 +dw/30, paint);

        //yy= (float) (y_ac*Math.cos(angle)+x_ac*Math.sin(angle));

        //xx= (float) (x_ac*Math.cos(angle)-y_ac*Math.sin(angle));
       // canvas.drawText(/*con.getString(R.string.energy)+*/String.valueOf(gw.x_ac)+" / "+String.valueOf(player.accel_x)+" / "+String.valueOf(player.speed_x) , 0 , dh/2+dw/20, paint);
        //canvas.drawText(/*con.getString(R.string.energy)+*/String.valueOf(gw.y_ac)+" / "+String.valueOf(player.accel_y)+" / "+String.valueOf(player.speed_y) , 0 , dh/2+dw*2/20, paint);
        //canvas.drawText(/*con.getString(R.string.energy)+*/String.valueOf(Math.cos(Math.toRadians(gw.angle)))+" / "+String.valueOf(Math.sin(Math.toRadians(gw.angle))) , 0 , dh/2+dw*3/20, paint);
        matrix = new Matrix();
        matrix.setTranslate(0, dh-dw/6);

        matrix.preRotate(al,   dw/12, dw/12);
        //matrix.postRotate(attach_angle, attach_x-offset_x, attach_y-offset_y);
        canvas.drawBitmap(b1, matrix, paint);


        canvas.drawText(/*con.getString(R.string.energy)+*/String.valueOf(FirstFragment.rot) , dw/2 , dh/2, paint);


        }




        paint.setTextSize((float) (dw / 10.0));
        if(gw.game==1){
            paint.setColor(Color.RED);
        canvas.drawText(/*con.getString(R.string.energy)+*/"FAIL" , dw/2-dw/10  , dh/2, paint);
        }
        if(gw.game==2){
            paint.setColor(Color.GREEN);
            canvas.drawText(/*con.getString(R.string.energy)+*/"WIN" , dw/2-dw/10 , dh/2, paint);
        }

    }







}
