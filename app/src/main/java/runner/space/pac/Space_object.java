package runner.space.pac;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import static runner.space.pac.MainActivity.dw;
import static runner.space.pac.MainActivity.dh;

public class Space_object {

    protected float x;// координаты
    protected float y;// координаты

    protected float z_x;// координаты нуля
    protected float z_y;// координаты нуля

    protected float speed_x; // скорость
    protected float speed_y; // скорость


    protected float max_speed=0; // скорость
//    protected float max_speed_y=0; // скорость


    protected float accel_x; // ускорение
    protected float accel_y; // ускорение

    protected float angle; // текущий угол вращения
    protected float speed_angle; //скорость изменения угла вращения

    int max_heal;
    int heal;
    byte type;
    float size;

    //protected float s_x=0;//игровые координаты
    //protected float s_y=0;//игровые координаты

    //protected float saveY;//местный рекорд во время полёта

    //protected byte energy; //текущая энергия
    //protected byte max_strong=90; //ограничение концентрации
    //public int dw,dh; //восота и ширина экрана
    //protected byte anim_step=0; //счётчик анимационных движений
    //int exp=0; //счётчик для анимации 2
    //protected float downspeed= (float) -0.5; //ограничитель скорости падения
    //protected float grav=(float) 0.015;
    //private String header_type="ny_2022";

    //private int bitmapId; // id картинки
    //private Bitmap fone_red; // картинка
    //private Bitmap fone_green; // картинка
    //private Bitmap fone_white; // картинка
    //private Bitmap bod_2;
    Bitmap body;
    //private Bitmap head;
    //private Bitmap header;
    //private Bitmap hand;
    //private Bitmap legs;

    //protected Date savedate;
    //protected Date break_date;
    //protected Date d; //текущее время

    //protected float to_update=-1;
    //protected float update_time=0;//для сохранения длительности текущего улучшения

    Context con;


  /* protected float[] jump_up=new float[]{0,(float)0.07,(float) 0.08,(float) 0.09,(float) 0.11,(float) 0.13,(float)0.16,(float)0.19,(float)0.23};
    protected float[] jump_record = new float[]{(float)0,(float) 0.145, (float) 0.185, (float) 0.23, (float) 0.335, (float) 0.46, (float) 0.75, (float) 1.0};

    protected float[]  bust_up=new float[]{0,(float) 0.01,(float) 0.02,(float) 0.03,(float) 0.04,(float)0.05,(float)0.06,(float)0.07,(float) 0.09,(float) 0.11,(float) 0.13,(float) 0.17, (float) 0.21};
    protected float[] bust_record = new float[]{(float) 0.68, (float) 1.51, (float) 1.69, (float) 1.91, (float) 2.19, (float) 2.59, (float) 3.16,(float)3.88,(float)6.93,(float)12,19,57,133};

    protected byte[]energy_up=new byte[]{0,5, 6,7,8,10,12,15,19};
    protected float[] energy_record = new float[]{0, (float) 5.68, (float) 6.3, (float) 10.7, (float) 18.16, (float) 23.87, (float) 30.19, (float) 40.7};
*/

    //protected float[] time_to_up=new float[]{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5};

    //protected float[] time_to_up=new float[]{5,5,10,15,15,30,30,30,60,60,60,90,90,120,150,180,180,210,210,210,240,240,300,360,360,420,480,540,600,660,720,780};
    //перечень времён для улучшения параметров

    //protected float[] jump_up=new float[]{0,(float)0.05,(float) 0.06,(float) 0.07,(float) 0.08,(float) 0.09,(float)0.10,(float)0.11,(float)0.12,(float)0.13,(float)0.14,(float)0.15,(float)0.16,(float)0.17,(float)0.18,(float)0.19,(float)0.20,(float)0.21,(float)0.22,(float)0.23,(float)0.24};
    //protected float[] jump_record = new float[]{(float)0,(float) 0.125, (float) 0.164, (float) 0.207, (float) 0.257, (float) 0.31, (float) 0.37, (float) 0.467, (float) 0.87, (float) 0.98, (float) 4.45,(float) 23.685,(float) 32.7,(float) 55.4,(float) 56.3,(float) 65.0,(float) 77.6,(float) 103.1,(float) 153.3,(float) 195.2};

    //protected float[] bust_up=new float[]{0,(float) 0.01,(float) 0.02,(float) 0.03,(float) 0.04,(float)0.05,(float)0.06,(float)0.07,(float) 0.09,(float) 0.11,(float) 0.12,(float) 0.13, (float) 0.14,(float) 0.15,(float)0.16,(float)0.17,(float)0.18,(float)0.19,(float)0.20,(float)0.21,(float)0.22};
    //protected float[] bust_record = new float[]{(float) 0.43, (float) 0.54, (float) 0.58, (float) 0.63, (float) 0.68, (float) 0.74, (float) 0.807,(float)1.47,(float)1.88,(float)3.396,(float)3.9,(float)4.729,(float)13.08,(float) 24.3,(float) 28.3,(float) 33.4,(float) 57.3,(float) 78.8,(float) 119.9,(float) 175.0};

    //protected byte[] energy_up=new byte[]{0,1, 2,3,4,5,6,7,8,9,10,11,12,13,14,15};
    //protected float[] energy_record = new float[]{0, (float) 1.09, (float) 2.366, (float) 5.339, (float) 7.57, (float) 10.155, (float) 15.27, (float) 19.25,(float) 38.1,(float) 46.4,(float) 66.0,(float) 88.7,(float) 104.4,(float) 134.4,(float) 154.8,(float) 200.0,(float) 200.0,(float) 200.0,(float) 200.0,(float) 200.0};

    //protected byte ml_bust= (byte) (bust_up.length-1);
    //protected byte ml_jump=(byte) (jump_up.length-1);
    //protected byte ml_energy=(byte) (energy_up.length-1);

    //protected float money; //деньги
    //protected byte maxenergy; //энергия
    //protected double bust;
    //protected double jump;
    //protected float maxY;//рекорд

    //protected byte next_jump;
    //protected byte next_bust;
    //protected byte next_energy;



    //public String status="RTF";
    //public String savedstatus="NON";
    /*
    NON - отсутствует (есть только у сохраняемого статуса)
    STF - полёт заблокирован открытым меню
    UPD - происходит улучшение
    RCV - восстанавливается энергия
    RTF - ready to fly когда стоит и готов лететь
    GTF - подготовка к полёту (обратный отсчёт)
    FLU - полёт вверх
    FLD - падение
    */

    //protected String anima_type="standing";
    /*
    standing
    jump
    bust
    fly_up
    fly_down
    recovery
     */

    //Map<String, Double[]> bod2_grad= new HashMap<String, Double[]>();
    //Map<String, Double[]> head_grad= new HashMap<String, Double[]>();
    //Map<String, Double[]> hand_grad= new HashMap<String, Double[]>();
    //Map<String, Double[]> legs_grad= new HashMap<String, Double[]>();
    //Map<String, Double[]> hand2_grad= new HashMap<String, Double[]>();
    //Map<String, Double[]> legs2_grad= new HashMap<String, Double[]>();


    public Space_object(Context context, byte ltype, int lheal, float lx, float ly,float lspeed_x, float lspeed_y){
        con=context;
        type=ltype;
        max_heal =lheal;
        heal=lheal;
        x=lx;
        y=ly;
        speed_x=lspeed_x;
        speed_y=lspeed_y;
        size= (float) (heal/50.0);
        //dw=MainActivity.dw;
        //dh=MainActivity.dh;
        //con=context;

        //x=dw/2-dw/4;
        //sx=x;

        //y=MainActivity.end-dw/4-dw/25-dw/2;
        //sy=y;

        //maxY=0;
        //saveY=0;

        //speed=0;
        //strong=0;

        /*if (MainActivity.testing) {
            jump_up=new float[]{0,(float)0.07,(float) 0.08,(float) 0.09,(float) 0.11,(float) 0.13,(float)0.16,(float)0.19,(float)0.23};
            bust_up=new float[]{0,(float) 0.01,(float) 0.02,(float) 0.03,(float) 0.04,(float)0.05,(float)0.06,(float)0.07,(float) 0.09,(float) 0.11,(float) 0.13,(float) 0.17, (float) 0.21};
            energy_up=new byte[]{0,5, 6,7,8,10,12,15,19};

            jump_record = new float[]{0,(float)0,(float) 0, (float) 0, (float) 0, (float) 0, (float) 0, (float) 0, (float) 0};
            bust_record = new float[]{(float) 0, (float) 0, (float) 0, (float) 0, (float) 0, (float) 0, (float) 0,0,0,0,0,0};
            energy_record = new float[]{0,(float) 0, (float) 0, (float) 0, (float) 0, (float) 0, (float) 0, (float) 0,0};
            time_to_up=new float[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
            //time_to_up=new float[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0};

            next_jump=8;
            next_bust=12;
            next_energy=8;
        }
        else
        {*/
        //next_jump=n_j;
        //next_bust=n_b;
        //next_energy=n_e;
        //maxY=rec;


        //savedate=time;
        //to_update=tu;
        /*if (MainActivity.update!=-1){
            status="STF";
            savedstatus="UPD";
            switch (MainActivity.update){
                case 0:
                    update_time= time_to_up[next_jump];
                    break;
                case 1:
                    update_time= time_to_up[next_bust + 5];
                    break;
                case 2:
                    update_time= time_to_up[next_energy + 10];
                    break;
            }
        }*/


        //bust=bust_up[next_bust];
        //jump=jump_up[next_jump];
        //maxenergy=energy_up[next_energy];
        //energy=maxenergy;

        //init(context); // инициализация ресурсов

    }

 /*   void init(Context context, byte ltype, int lheal, float lx, float ly,float lspeed_x, float lspeed_y/*Context context, byte n_j, byte n_b, byte n_e, float rec,Date time,float tu *//*) {*/
   /*     con=context;
        type=ltype;
        max_heal =lheal;
        heal=lheal;
        x=lx;
        y=ly;
        speed_x=lspeed_x;
        speed_y=lspeed_y;
        size= (float) (heal/50.0);
//init2();
    }*/

 /*   void init2(){

    }*/


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

        float draw_x=x-offset_x-body.getWidth()/2;
        float draw_y=y-offset_y-body.getHeight()/2;
        if (draw_x+body.getWidth()/2>=-MainActivity.dw & draw_x-body.getWidth()/2<=2*MainActivity.dw & draw_y+body.getHeight()/2>=-MainActivity.dw & draw_y<=2*MainActivity.dh-body.getHeight()/2)
        {
            Matrix matrix = new Matrix();
            matrix.setTranslate(draw_x, draw_y);

            matrix.preRotate(angle, body.getWidth()/2, body.getHeight()/2);
            matrix.postRotate(attach_angle, attach_x-offset_x, attach_y-offset_y);
            canvas.drawBitmap(body, matrix, paint);

        }
       // draw_2(paint, canvas,draw_x,draw_y, attach_angle, attach_x-offset_x, attach_y-offset_y);
    }
  /*  void draw_2(Paint paint, Canvas canvas,float draw_x,float draw_y,float attach_angle,float attach_x,float attach_y) {

    }*/

    void alternate_draw(Paint paint, Canvas canvas/*, Matrix matrix*/){
        if (matrix!=null){
            canvas.drawBitmap(body, matrix, paint);
        }
    }
    Matrix matrix;
    //Matrix get_matrix(float offset_x,float offset_y,float attach_angle,float attach_x,float attach_y){
    void get_matrix(float offset_x,float offset_y,float attach_angle,float attach_x,float attach_y){
        float draw_x=x-offset_x-body.getWidth()/2;
        float draw_y=y-offset_y-body.getHeight()/2;
        if (draw_x+body.getWidth()/2>=-MainActivity.dw & draw_x-body.getWidth()/2<=2*MainActivity.dw & draw_y+body.getHeight()/2>=-MainActivity.dw & draw_y<=2*MainActivity.dh-body.getHeight()/2)
        {
            Matrix mat = new Matrix();
            mat.setTranslate(draw_x, draw_y);

            mat.preRotate(angle, body.getWidth()/2, body.getHeight()/2);
            mat.postRotate(attach_angle, attach_x-offset_x, attach_y-offset_y);
            matrix=mat;
        }
        else{
            matrix=null;
        }
    }

    boolean far_away(float offset_x,float offset_y){
    if (y>4*MainActivity.dh-offset_y|y<-4*MainActivity.dh-offset_y |x>4*MainActivity.dh-offset_x|x<-4*MainActivity.dh-offset_x)
        return true;
    else
        return false;
    }


}
