package runner.space.pac;

import static runner.space.pac.MainActivity.dw;
import static runner.space.pac.MainActivity.dh;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class GameView extends SurfaceView implements Runnable{

    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private Thread gameThread;
    private Canvas canvas;

    protected Bitmap back1; // картинка
    protected Bitmap back2; // картинка
    protected Bitmap back3; // картинка
    protected Bitmap back4; // картинка
    protected Bitmap false_button; // картинка
    protected Bitmap menu_box; // картинка
    protected Bitmap qu; // картинка
    protected Bitmap bm; // картинка
    protected Bitmap Star; // картинка

    protected int bitmapId; // id картинки

    boolean firstTime = true;
    boolean gameRunning=true;
    boolean b=false;

    byte cadres=60;
    byte frames=60;
    byte speed_coef=1;
    byte game=0;

    int tick=0;
    int n_j;
    int n_e;
    int n_b;
    int increment=1000000/60;
    int target;

    float rec;
    float need_leng=0;
    float tu;
    float offset_x=0;
    float offset_y=0;
    float x_ac=0;
    float y_ac=0;
    float angle=0;

   // long target_x;
   // long target_y;

    Date time;
    Date control_date;
    Date d;

    public static Space_ship player;
    private GUI gui;

    ArrayList<Space_meteorite> met=new ArrayList<>();
    ArrayList<float[]> stars=new ArrayList<>();
    ArrayList<Matrix> matrix_stars=new ArrayList<>();
    ArrayList<Float[]> boom=new ArrayList<>();

    ArrayList<Space_station> stations=new ArrayList<>();

    LocalServer ls;

    public GameView(Context context) {
        super(context);
        //инициализируем обьекты для рисования

        // инициализируем поток
        boolean isLocal=true;
        if(isLocal){
            ls=new LocalServer();
        }

        try {
            JSONObject j=new JSONObject();
            j.put("Type","Login");
            j.put("Login","player");
            j.put("Password","1111");
            ls.resieveMessage(j);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        gameThread = new Thread(this);
        gameThread.start();
    }

    public void localServerTransmit(JSONObject j){
        if(j!=null){
            try {
                switch ((String)j.get("Type")){
                    case "Login":
                        if (((String)j.get("Status")).equals("OK") ){
                            player =(Space_ship) j.get("Player");
                            target=(int)j.get("Target");
                        }
                        else{

                        }
                        break;
                    case "newObjects":
                        met.addAll((ArrayList<Space_meteorite>)j.get("Meteorites"));
                        break;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public  void init(){
        surfaceHolder = getHolder();
        //MainActivity.width=surfaceHolder.getSurfaceFrame().width();
        paint = new Paint();

        // if(firstTime){ // инициализация при первом запуске
        //MainActivity.width=surfaceHolder.getSurfaceFrame().width();

        //dw= MainActivity.dw;
        //dh=MainActivity.dh;

        bitmapId = R.drawable.back1;
        Bitmap cBitmap = BitmapFactory.decodeResource(getContext().getResources(), bitmapId);
        back1 = Bitmap.createScaledBitmap(
                cBitmap, dw, dw*10, false);
        cBitmap.recycle();

        bitmapId = R.drawable.back2;
        cBitmap = BitmapFactory.decodeResource(getContext().getResources(), bitmapId);
        back2 = Bitmap.createScaledBitmap(
                cBitmap, dw, dw*10, false);
        cBitmap.recycle();

        bitmapId = R.drawable.boom;
        cBitmap = BitmapFactory.decodeResource(getContext().getResources(), bitmapId);
        bm = Bitmap.createScaledBitmap(
                cBitmap, dw/40, dw/40, false);
        cBitmap.recycle();

               /* bitmapId = R.drawable.back3;
                cBitmap = BitmapFactory.decodeResource(getContext().getResources(), bitmapId);
                back3 = Bitmap.createScaledBitmap(
                        cBitmap, dw, dw*20, false);
                cBitmap.recycle();
*/
        bitmapId = R.drawable.back4;
        cBitmap = BitmapFactory.decodeResource(getContext().getResources(), bitmapId);
        back4 = Bitmap.createScaledBitmap(
                cBitmap, dw, dh, false);
        cBitmap.recycle();

        bitmapId = R.drawable.imb;
        cBitmap = BitmapFactory.decodeResource(getContext().getResources(), bitmapId);
        false_button = Bitmap.createScaledBitmap(
                cBitmap, dw/4, dw/4, false);

        menu_box = Bitmap.createScaledBitmap(
                cBitmap, dw/2-dw/25, dw/8, false);
        cBitmap.recycle();

        bitmapId = R.drawable.quest;
        cBitmap = BitmapFactory.decodeResource(getContext().getResources(), bitmapId);
        qu = Bitmap.createScaledBitmap(
                cBitmap, dw*7/4, dw*15/4, false);
        cBitmap.recycle();

        bitmapId = R.drawable.isk;
        cBitmap = BitmapFactory.decodeResource(getContext().getResources(), bitmapId);
        Star = Bitmap.createScaledBitmap(
                cBitmap, 5, 5, false);
        cBitmap.recycle();

/*        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        n_j = myPreferences.getInt("jump", 1);

        n_b = myPreferences.getInt("bust", 0);
        //n_b=1;
        if(n_b<=1){
            n_e=1;
        }
        else{
            n_e = myPreferences.getInt("energy", 1);
        }

        String vers = myPreferences.getString("version", "0.1.0.0");

        if (vers.equals("0.1.0.0"))
            rec = 0;
        else
            rec = myPreferences.getFloat("record", 0);


        //int headers= myPreferences.getInt("headers_count", 0);
        // if (headers>0){
        //myPreferences.getAll("header");
        // }
        if (MainActivity.update==-1) {
            tu=-1;
            time=MainActivity.first_date;
        } else {
            long rd_time = myPreferences.getLong("strt_date", MainActivity.first_date.getTime());
            time = new Date(rd_time);
            tu = myPreferences.getFloat("time_to_up", -1);
        }
*/

        //SharedPreferences  mPrefs = getPreferences(MODE_PRIVATE);
        // n_j = 10;
        // n_e = 5;
        //rec = 0;
        // n_b = 10;
        // rec =0;
          /*  int n_j = 1;
            int n_e = 1;*/
          /*  int n_b = 0;
            float rec =0;
            time=MainActivity.first_date;
            tu=-1;*/



        //}


 //       firstTime = false;

        Random rand=new Random();

        for (int hei=0;hei<dh+dw;hei++)
            for (int wit=0;wit<dh+dw;wit++)
                if (rand.nextInt(5000)==0) {
                    stars.add(new float[]{wit,hei});

                    // int dd=dh+dw;
                    //float off_x=player.x%(dd)-dd;
                    // float off_y=player.y%(dd)-dd;
                    //float[]star=stars.get(ind);
                    Matrix matrix = new Matrix();
                    matrix.setTranslate(wit, hei);
                    matrix_stars.add(matrix);
//player.x%(dw+dh)+dw+dh
                    //matrix.setTranslate((star[0]-off_x)%(dd)-dh/2, (star[1]-off_y)%(dd)-dw/2);
                    //matrix.setTranslate((star[0]-off_x)%(dd)-dh/2, (star[1]-off_y)%(dd)-dw/2);
                    //matrix.preRotate(angle, body.getWidth()/2, body.getHeight()/2);
                    // matrix.postRotate(-angle, player.x+dw/12-offset_x, player.y+dw/12-offset_y);
                }
        gui=new GUI(getContext());
        offset_x=-dw/2;
        offset_y=-dh+dw/2;
        //player=new Space_ship( "0",300+rand.nextInt(200),0,0,0,0);
        //player.init(getContext(),(byte) 0,300+rand.nextInt(200),0,0,0,0);

      /*  Space_station s= new Space_station("0",100000,0,0,0,0);
        //s.init(getContext(), (byte) 0,100000,0,0,0,0);
        stations.add(s);

        long target_y=-10000-rand.nextInt(1000);
        long target_x= (long) Math.sqrt(12000*12000-target_y*target_y);

        s= new Space_station( "0",100000,target_x,target_y,0,0);
        //s.init(getContext(), (byte) 0,100000,target_x,target_y,0,0);
        stations.add(s);*/
        //target=stations.size()-1;

       /* for(int h=0; h<dh;h+=dh/10){
            for (int m=0; m<1+rand.nextInt(3);m++){
                Meteorid m0=new Meteorid();
                m0.init(getContext(),(byte) 0,50+rand.nextInt(50),rand.nextInt(dw)+offset_x,h+rand.nextInt(dh/80)+offset_y,0,0);
                met.add(m0);
            }
        }*/

        control_date = new Date();
    }

   // ArrayList<Space_meteorite> n=new ArrayList<>();

    @Override
    public void run() {

        init();
        if (MainActivity.game_mode.equals("hard"))
            while (gameRunning) {

     /*           j=new JSONObject();
                try {
                   // j.put("stars",stars);
                    j.put("meteorid",met.clone());

                   // ArrayList<> nn=j.get("meteorid");
                    n.clear();
                    n= (ArrayList<Space_meteorite>) j.get("meteorid");
                 //   Class c =j.get("meteorid").getClass();
                 //   Class cc =j.get("meteorid").getClass();
                  /*  JSONArray jj=j.getJSONArray("meteorid");

                    Gson gson = new GsonBuilder().create();
                    n.clear();

                    for (int i=0;0<jj.length();i++) {
                        n.add(gson.fromJson(jj.get(i).toString(),Space_meteorite.class ));
                    }*/

                    //Gson gson = new GsonBuilder().create();
                 //ArrayList<Space_meteorite> n= gson.fromJson(j.getJSONObject("meteorid").toString(),Space_meteorite.class );
           /*     } catch (JSONException e) {
                    e.printStackTrace();
                }
*/


                meteorid_creator();
                rolling_inertial();
                update_hard();
                //create_matrix(tick);
                if (tick%(60/cadres)==0)
                    draw();
                control();
            }
        else if (MainActivity.game_mode.equals("arcade"))
            while (gameRunning) {
                meteorid_creator();
                update_arcade();
                if (tick%(60/cadres)==0)
                    create_matrix(tick);
                    draw();
                control();
            }
        while (!gameRunning) {
            draw();
        }
    }

    private void rolling_inertial(){

        if (FirstFragment.rotating) {
            angle+=FirstFragment.rot/30;
            if (angle<0)
                angle+=360;
            if (angle>360){
                angle-= 360;
            }
            else if (angle<-360){
                angle+= 360;
            }
        }

        if (FirstFragment.moving) {
            if (Math.abs(FirstFragment.movedX - FirstFragment.startX) > dw / 8)
                x_ac = (float) ((FirstFragment.movedX - FirstFragment.startX) * 0.5 / dw);
            else
                x_ac = 0;

            if (Math.abs(FirstFragment.movedY - FirstFragment.startY) > dw / 8)
                y_ac = (float) ((FirstFragment.movedY - FirstFragment.startY) * 0.5 / dw);
            else
                y_ac = 0;
        } else {
            x_ac = 0;
            y_ac = 0;
        }
        player.set_accel(x_ac,y_ac,angle);


      /*  if (FirstFragment.rotating) {
            if(FirstFragment.rot>30|FirstFragment.rot<-30){
                angle+=FirstFragment.rot/50;
                if (angle<0)
                    angle+=360;
                if (angle>360){
                    angle-= 360;
                }
                else if (angle<-360){
                    angle+= 360;
                }
                x_ac = 0;
                y_ac = 0;
            }
            else {
            if (Math.abs(FirstFragment.movedX - FirstFragment.startX) > dw / 8)
                x_ac = (float) ((FirstFragment.movedX - FirstFragment.startX) * 0.1 / dw);
            else
                x_ac = 0;

            if (Math.abs(FirstFragment.movedY - FirstFragment.startY) > dw / 8)
                y_ac = (float) ((FirstFragment.movedY - FirstFragment.startY) * 0.1 / dw);
            else
                y_ac = 0;
            }
        }
else        if (FirstFragment.moving) {
            if (Math.abs(FirstFragment.movedX - FirstFragment.startX) > dw / 8){
                angle+=(FirstFragment.movedX - FirstFragment.startX)/500;
                if (angle<0)
                    angle+=360;
                else if (angle>360){
                    angle-= 360;
                }

            }
               // x_ac = (float) ((FirstFragment.movedX - FirstFragment.startX) * 0.5 / dw);
            //else
               // x_ac = 0;

            if (Math.abs(FirstFragment.movedY - FirstFragment.startY) > dw / 8)
                y_ac = (float) ((FirstFragment.movedY - FirstFragment.startY) * 0.2 / dw);
            else
                y_ac = 0;
        } else {
            x_ac = 0;
            y_ac = 0;
        }
        player.set_accel(x_ac,y_ac,angle);

*/


    }

    private void update_hard() {

 //       if(!firstTime) {



        player.update(speed_coef);

        stars_update();




        offset_x=player.x-dw/2;
        offset_y=player.y-dh+dw/2;

        for (int ind=0;ind<stations.size();ind++){
            stations.get(ind).update(speed_coef);
        }





          /*  if (player.accel_x>0)
            if (player.x - offset_x < dw *3/ 4-dw/12)
                offset_x = player.x - dw / 2;


            if (player.x - offset_x < dw / 2-dw/12)*/
            /*else if (player.x - offset_x > dw * 3 / 4)
                offset_x = player.x - dw * 3 / 4;*/

        /*    if (player.y - offset_y !=dh *3/ 4-dw/12)
                offset_y = player.y - dh / 4;

            else if (player.y - offset_y > dh * 3 / 4)
                offset_y = player.y - dh * 3 / 4;*/

/*
            if (player.x - offset_x < dw / 4)
                offset_x = player.x - dw / 4;
            else if (player.x - offset_x > dw * 3 / 4)
                offset_x = player.x - dw * 3 / 4;
            if (player.y - offset_y < dh / 4)
                offset_y = player.y - dh / 4;
            else if (player.y - offset_y > dh * 3 / 4)
                offset_y = player.y - dh * 3 / 4;
*/


         /*  for (Meteorid m:met){
                m.update(speed_coef);
                if(Math.abs((m.y+dw/16)-(player.y+dw/12))<dw/12+dw/16) {
                    if(Math.abs((m.x+dw/16)-(player.x+dw/12))<dw/12+dw/16) {
                        //player.y+dw/12+((m.y+dw/16)-(player.y+dw/12))/2
                        //(player.y+dw/12+(m.y+dw/16))/2
                        Float[] f=new Float[2];
                        f[0]=(player.x+dw/12+(m.x+dw/16))/2;
                        f[1]=(player.y+dw/12+(m.y+dw/16))/2;
                        boom.add(f);
                    }
                }
            }
            for (int ind=0;ind<met.size();ind++){
                Meteorid m=met.get(ind);
                if(m.far_away(-offset_x,-offset_y)){
                    met.remove(ind);
                }
            }*/
            ArrayList<Integer> remove = new ArrayList<>();
            ArrayList<Space_meteorite> creator = new ArrayList<>();
            for (int ind = 0; ind < met.size(); ind++) {

                Space_meteorite m = met.get(ind);

                m.update(speed_coef);


                if (m.isFarAway(-offset_x, -offset_y)) {
                    remove.add(ind);
                } else if (Math.abs(m.y  - player.y ) < dw / 12 + dw / 16) {
                    if (Math.abs(m.x  - player.x ) < dw / 12 + dw / 16) {

                        Float[] f = new Float[2];
                        f[0] = (player.x  + (m.x )) / 2;
                        f[1] = (player.y + (m.y )) / 2;
                        boom.add(f);
                        m.heal-=2;
                        m.speed_x+= (m.x-player.x)/500;
                        player.heal-=1;
                        ArrayList<Space_meteorite> cr=m.crusher();
                        if (cr.size()!=0) {
                            // for (Meteorid i : cr) {
                            //  creator.add(i);
                            creator.addAll(cr);
                            // }
                            remove.add(ind);
                        }
                        else{
                            if (m.heal<=0)
                                remove.add(ind);
                        }
                        if (player.heal<=0){
                            gameRunning=false;
                            game=1;}
                    }
                }

            }
            if(creator.size()>0)
                for (Space_meteorite i:creator)
                {
                    met.add(i);
                }
            if (remove.size() > 0) {
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        for (int ind2 = remove.size() - 1; ind2 >= 0; ind2--) {
                            //int e=remove.get(ind2);
                            met.remove((int)remove.get(ind2));
                            remove.remove(remove.get(ind2));
                        }
                    }
                });
                t.start();
            }
      /*  Thread tt = new Thread(new Runnable() {
            public void run() {
                while (remove.size()>0){

                }
                for (int ind = 0; ind < met.size(); ind++) {
                    met.get(ind).get_matrix(offset_x,offset_y,-angle,player.x,player.y);
                }

            }
        });
        tt.start();*/

            need_leng= (float) Math.sqrt(Math.pow(player.x-stations.get(target).x,2)+Math.pow(player.y-stations.get(target).y,2));
            if (need_leng<=(float)Resources.getObject(Space_station.class,"0").get("size")){
                gameRunning=false;
                game=2;}
    //    }
    }

    private void update_arcade() {

        if (FirstFragment.rotating) {
            angle+=FirstFragment.rot/10;
            if (angle<0)
                angle+=360;
            if (angle>360){
                angle-= 360;
            }
            else if (angle<-360){
                angle+= 360;
            }
        }

        if (FirstFragment.moving) {
            if (Math.abs(FirstFragment.movedX - FirstFragment.startX) > dw / 8)
                x_ac = (float) ((FirstFragment.movedX - FirstFragment.startX) * 0.5 / dw);
            else
                x_ac = 0;

            if (Math.abs(FirstFragment.movedY - FirstFragment.startY) > dw / 8)
                y_ac = (float) ((FirstFragment.movedY - FirstFragment.startY) * 0.5 / dw);
            else
                y_ac = 0;
        } else {
            x_ac = 0;
            y_ac = 0;
            player.speed_x=0;
            player.speed_y=0;
        }
        player.set_accel(x_ac,y_ac,angle);


        //player.speed_x=0;
        //player.speed_y=0;

        player.update(speed_coef);

        offset_x=player.x-dw/2+dw/12;
        offset_y=player.y-dh+dw/2;

        ArrayList<Integer> remove = new ArrayList<>();
        ArrayList<Space_meteorite> creator = new ArrayList<>();
        for (int ind = 0; ind < met.size(); ind++) {
            Space_meteorite m = met.get(ind);
            m.update(speed_coef);

            if (m.isFarAway(-offset_x, -offset_y)) {
                remove.add(ind);
            } else if (Math.abs((m.y + dw / 16) - (player.y + dw / 12)) < dw / 12 + dw / 16) {
                if (Math.abs((m.x + dw / 16) - (player.x + dw / 12)) < dw / 12 + dw / 16) {

                    Float[] f = new Float[2];
                    f[0] = (player.x + dw / 12 + (m.x + dw / 16)) / 2;
                    f[1] = (player.y + dw / 12 + (m.y + dw / 16)) / 2;
                    boom.add(f);
                    m.heal-=2;
                    m.speed_x+= (m.x-player.x)/500;
                    player.heal-=1;
                    ArrayList<Space_meteorite> cr=m.crusher();
                    if (cr.size()!=0) {
                        // for (Meteorid i : cr) {
                        //  creator.add(i);
                        creator.addAll(cr);
                        // }
                        remove.add(ind);
                    }
                    else{
                        if (m.heal<=0)
                            remove.add(ind);
                    }
                    if (player.heal<=0){
                        gameRunning=false;
                        game=1;}
                }
            }
        }
        if(creator.size()>0)
            for (Space_meteorite i:creator)
            {
                met.add(i);
            }
        if (remove.size() > 0) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    for (int ind2 = remove.size() - 1; ind2 >= 0; ind2--) {
                        //int e=remove.get(ind2);
                        met.remove((int)remove.get(ind2));
                        remove.remove(remove.get(ind2));
                    }
                }
            });
            t.start();
        }
        need_leng= (float) Math.sqrt(Math.pow(player.x-stations.get(target).x,2)+Math.pow(player.y-stations.get(target).y,2));
        if (need_leng<=(float)Resources.getObject(Space_station.class,"0").get("size")){
            gameRunning=false;
            game=2;}
    }

    private void create_matrix(int ticker){
          Thread tt = new Thread(new Runnable() {
         public void run() {
             if (ticker%2==0)
                 for (int ind = 0; ind < met.size(); ind+=2) {
                     met.get(ind).get_matrix(offset_x,offset_y,-angle,player.x,player.y);
                 }
             else
                 for (int ind = 1; ind < met.size(); ind+=2) {
                     met.get(ind).get_matrix(offset_x,offset_y,-angle,player.x,player.y);
                 }
            }
           });
          tt.start();
    }

    private void draw() {
        try {
            if (surfaceHolder.getSurface().isValid()) {  //проверяем валидный ли surface

                canvas = surfaceHolder.lockCanvas(); // закрываем canvas

                canvas.drawColor(Color.BLACK); // заполняем фон чёрным

                //canvas.drawBitmap(bm, dw-player.x%dw, -player.y%dh, paint);

                paint.setColor(Color.WHITE);

                for(int ind=0;ind<stars.size();ind++){
                    // canvas.drawCircle((star[0]-player.x%dw+dw)%dw, (star[1]-player.y%dh+dh)%dh,1,paint);
                    canvas.drawBitmap(Star, matrix_stars.get(ind), paint);
                }

//canvas.drawCircle(dw-player.x%dw, -player.y%dh,1,paint);
                for (int ind=0;ind<stations.size();ind++){
                    stations.get(ind).draw(paint, canvas,offset_x,offset_y,-angle,player.x/*+dw/12*/,player.y/*+dw/12*/);
                }

                player.draw(paint, canvas,offset_x,offset_y,0,player.x/*+dw/12*/,player.y/*+dw/12*/);





                // player.draw(paint, canvas,offset_x,offset_y,angle,player.x+dw/12,player.y+dw/12);

               /* for (Meteorid m:met){
                    m.draw(paint, canvas,offset_x,offset_y,0,player.x+dw/12,player.y+dw/12);
                }*/

                for (Space_meteorite m:met){
                    m.draw(paint, canvas,offset_x,offset_y,-angle,player.x/*+dw/12*/,player.y/*+dw/12*/);
                   // m.alternate_draw(paint, canvas);
                }
                Random rand=new Random();
                for (Float[] f:boom){
                    for (int i=0;i<10+rand.nextInt(10);i++) {
                        canvas.drawBitmap(bm, f[0]+(-2+ rand.nextInt(5))*8-offset_x, f[1]+(-2+ rand.nextInt(5))*8-offset_y, paint);
                    }
                }
                boom.clear();


                gui.draw(paint,canvas,player,stations.get(target),need_leng);

       /*         float al= (float) Math.toDegrees(Math.atan((player.y-target_y)/(player.x-target_x)))+angle;
                //if (al>60)
                {
float h=dh-(player.y-offset_y);
float w= (float) (h*Math.tan(Math.toRadians(al)));
paint.setColor(Color.RED);
                    //canvas.drawLine(0,0,dw,dh,paint);

canvas.drawLine(player.x-offset_x,player.y-offset_y,player.x-offset_x-w,player.y-offset_y-h,paint);
                }*/





                //if (17-pen.y<15) canvas.drawBitmap(seen, -surfaceHolder.getSurfaceFrame().width(), (float) (surfaceHolder.getSurfaceFrame().height()-surfaceHolder.getSurfaceFrame().width()*10), paint);

                //canvas.drawBitmap(back4, 0, 0, paint);

/*            if (pen.y>dh/8) canvas.drawBitmap(back3, 0, (float) (dh-dw*20.2), paint);
            else canvas.drawBitmap(back3, 0, (float) ((dh/8-pen.y)/32+dh-dw*20.2), paint);
*/
                //if (pen.y > dh - (dw * 0.2 + dw * 10.7) * 8 & pen.y <= dh - dw * 0.2 - dw * 5.2)
                //canvas.drawBitmap(back2, 0, (float) ((dh / 8 - pen.y) / 8 + dh - dw * 10.7), paint);
                //else  if (pe9n.y<=-dh/8) canvas.drawBitmap(back2, 0, (float) ((-dh/8-pen.y)/8+dh-dw*10.2), paint);
                //canvas.drawBitmap(back2, 0, (float) (dh-dw*10.2), paint);

                //float st=MainActivity.Setup.getY()+dw/4;
                //MainActivity.end=st;
                //float st = MainActivity.end - dw / 100;

               /* if (pen.y <= dh / 8 & pen.y >= dh - dw * 10.2)
                    canvas.drawBitmap(back1, 0, (float) ((dh / 8 - pen.y) +st - dw * 10.2), paint);
                else if (pen.y > dh / 8)
                    canvas.drawBitmap(back1, 0, (float) (st - dw * 10.2), paint);
*/

                //else  if (pen.y<=-dh/8& pen.y<=-dw*10.2)  canvas.drawBitmap(back1, 0, (float) ((dh/8-pen.y)+dh-dw*10.2), paint);

                //pen.drow(paint, canvas); // рисуем пингвина и меню

               /* canvas.drawBitmap(false_button, dw * 3 / 4 - dw / 100, st - dw / 4, paint);
                canvas.drawBitmap(menu_box, dw / 4 + dw / 50, st - dw / 4, paint);
                canvas.drawBitmap(menu_box, dw / 4 + dw / 50, st - dw / 8, paint);*/
            /*if (quest!=0){
                canvas.drawBitmap(qu, -dw*3/4, -dw/7/4, paint);

            }*/
                surfaceHolder.unlockCanvasAndPost(canvas); // открываем canvas
            }
        }
        catch (Exception e){
            try {
                surfaceHolder.unlockCanvasAndPost(canvas); // открываем canvas
            }
            catch (Exception ignored){

            }
        }
    }

    int dop_inc=1;
    private void control_old() { // пауза и контроль количества кадров
       /*try {
            gameThread.sleep(12);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        d = new Date();

        if ((float) (d.getTime() - control_date.getTime()) / 250>=1 &tick>0) {
            frames= (byte) (tick*1000/(float) (d.getTime() - control_date.getTime()));
            control_date = new Date();
            tick=0;
            dop_inc=increment * frames>60?60/((increment * frames) % 60):0;
            increment=Math.max(increment * frames / 60, 1);


            /*if (cadres-frames>cadres/6)
                if(increment>1)
                    increment--;
                else if(cadres==60){
                    cadres=30;
                    speed_coef=2;
                }
            if (cadres-frames<-cadres/6 )
                increment++;*/
        }
        else{
            tick++;
        }

        //  else{
        //         byte fr= (byte) (tick*1000/(float) (d.getTime() - control_date.getTime()));
        //        increment=increment*fr/60>1 ? increment*fr/60 : 1;
        //   }

        try {
            if(dop_inc!=0){
                if(tick%dop_inc==0)
                    gameThread.sleep(increment*2);
                else
                    gameThread.sleep(increment);}
            else
                gameThread.sleep(increment);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void control() { // пауза и контроль количества кадров
       /*try {
            gameThread.sleep(12);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        d = new Date();

        if ((float) (d.getTime() - control_date.getTime()) / 50>=1 &tick>0) {
            frames= (byte) (tick*1000/(float) (d.getTime() - control_date.getTime()));
            control_date = new Date();
            tick=0;
            //dop_inc=increment * frames>60?60/((increment * frames) % 60):0;
            increment=Math.max(increment * frames / 60, 100);


            /*if (cadres-frames>cadres/6)
                if(increment>1)
                    increment--;
                else if(cadres==60){
                    cadres=30;
                    speed_coef=2;
                }
            if (cadres-frames<-cadres/6 )
                increment++;*/
        }
        else{
            tick++;
        }

        //  else{
        //         byte fr= (byte) (tick*1000/(float) (d.getTime() - control_date.getTime()));
        //        increment=increment*fr/60>1 ? increment*fr/60 : 1;
        //   }

        try {
            //if(dop_inc!=0){
           //     if(tick%dop_inc==0)
             //       gameThread.sleep(increment*2);
            //    else
               //     gameThread.sleep(increment);}
           // else
                gameThread.sleep(increment/1000,increment%1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void meteorid_creator(){
        if (met.size()==0& !b) {
            b=true;
            Thread t = new Thread(new Runnable() {
                public void run() {
                    //if (tick%10==0)  {
                    float aan = (float) Math.toRadians(angle);
                    float cos = (float) Math.cos(aan);
                    float sin = (float) Math.sin(aan);

                    float targ_y = -1;
                    float normal = (float) (1 / (Math.sqrt((player.speed_x- targ_y * sin) * (player.speed_x- targ_y * sin) + (player.speed_y+ targ_y * cos) * (player.speed_y+ targ_y * cos))));
                    float pos_x = (float) (player.x + (player.speed_x * normal- targ_y * sin ) * 1.5*dw );
                    float pos_y = (float) (player.y + (player.speed_y * normal + targ_y * cos) * 1.5*dh );
                    float spd_x = (float) ((player.x - pos_x) / 300+player.speed_x*1.0);
                    float spd_y = (float) ((player.y - pos_y) / 300+player.speed_y*1.0);
                    Random rand=new Random();
                    Space_meteorite m0 = new Space_meteorite( "0", 500 + rand.nextInt(50), pos_x, pos_y, spd_x, spd_y);
                    //m0.init(getContext(), (byte) 0, 500 + rand.nextInt(50), pos_x, pos_y, spd_x, spd_y);
                    met.add(m0);

                    for (int m=0; m<5+rand.nextInt(30);m++){
                        m0=new Space_meteorite( "0",50+rand.nextInt(50),pos_x-dw/4+rand.nextInt(dw/2),pos_y-dw/4+rand.nextInt(dw/2),spd_x,spd_y);
                        //m0.init(getContext(),(byte) 0,50+rand.nextInt(50),pos_x-dw/4+rand.nextInt(dw/2),pos_y-dw/4+rand.nextInt(dw/2),spd_x,spd_y);
                        met.add(m0);
                    }
                    create_matrix(0);
                    create_matrix(1);
                    b=false;
                    //}
                }
            });
            t.start();
        }








                /*for (int m=0; m<1+rand.nextInt(50);m++){
                    Meteorid m0=new Meteorid();
                    m0.init(getContext(),(byte) 0,50+rand.nextInt(500),player.x +rand.nextInt(dw),player.y+rand.nextInt(dh/80)+offset_y,0,0);
                    met.add(m0);
                }}*/


     /*   if(met.size()<30){
            Meteorid m0=new Meteorid();
            m0.init(getContext(),(byte) 0,50+rand.nextInt(50),rand.nextInt(3*dw)-dw+offset_x,-2*dw-rand.nextInt(dw)+offset_y,0,5+rand.nextInt(3));
            met.add(m0);
        }*/

       /* if(met.size()<30){
            Meteorid m0=new Meteorid();
            m0.init(getContext(),(byte) 0,50+rand.nextInt(50),rand.nextInt(2*dw)+offset_x,-2*dw-rand.nextInt(dw)+offset_y,-5-rand.nextInt(3),5+rand.nextInt(3));
            met.add(m0);
        }*/
    }

    private void stars_update(){
        Thread tt = new Thread(new Runnable() {
            public void run() {

                int dd = dh + dw;
                float off_x = player.x % (dd) - dd;
                float off_y = player.y % (dd) - dd;
                for (int ind = 0; ind < stars.size(); ind++){
                    float[] star = stars.get(ind);
                    Matrix matrix = matrix_stars.get(ind);
                    matrix.reset();
//player.x%(dw+dh)+dw+dh
                    matrix.setTranslate((star[0] - off_x) % (dd) - dh / 2, (star[1] - off_y) % (dd) /*+ dw / 4*/);

                    //matrix.preRotate(angle, body.getWidth()/2, body.getHeight()/2);
                    matrix.postRotate(-angle, player.x  - offset_x, player.y  - offset_y);

                }
            }
        });
        tt.start();
    }

}