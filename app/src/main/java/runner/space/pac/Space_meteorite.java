package runner.space.pac;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.Random;

public class Space_meteorite extends Space_object{

    public Space_meteorite( String type, int heal, float x, float y, float speed_x, float speed_y) {
        super(type, heal, x, y, speed_x, speed_y);
    //@Override
   // void init2() {
    //    super.init2();

        Random rand=new Random();
        angle= (float) (rand.nextFloat()*360);
        speed_angle=(float) (rand.nextFloat()*360*2/40-360/40);
    }

    ArrayList<Space_meteorite> crusher() {
        ArrayList<Space_meteorite> s=new ArrayList<>();
        if(heal>10)
        if ((int)Resources.getObject(this.getClass(),type).get("max_heal")/heal>1)
        {
            Random r = new Random();
            int ran=r.nextInt(20);
            if (ran<=5 & ran>1){
                for(int n=0;n<ran;n++){
                    Space_meteorite so=new Space_meteorite( type,heal/(ran+1)+r.nextInt(heal/(2*ran)), (float) (x*(19.5+r.nextFloat())/20), (float) (y*(19.5+r.nextFloat())/20),/*speed_x,speed_y*/ (float) (speed_x *(19.5+r.nextFloat())/20), (float) (speed_y*(19.5+r.nextFloat())/20));
                    //so.init(con, (byte) (type+1),heal/(ran+1)+r.nextInt(heal/(2*ran)), (float) (x*(19.5+r.nextFloat())/20), (float) (y*(19.5+r.nextFloat())/20),/*speed_x,speed_y*/ (float) (speed_x *(19.5+r.nextFloat())/20), (float) (speed_y*(19.5+r.nextFloat())/20));
                    s.add(so);
                }
            }
        }
        return s;
    }
}