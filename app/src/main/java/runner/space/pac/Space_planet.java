package runner.space.pac;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.renderscript.Byte2;
import android.renderscript.Float2;

import java.util.ArrayList;

public class Space_planet extends Space_object{


    //количество орбит

    /////для каждой орбиты:
    ArrayList<orbit> orbits;
    public Space_planet( String ltype, int lheal, float lx, float ly,float lspeed_x, float lspeed_y){
        super( ltype, lheal, lx, ly,lspeed_x, lspeed_y);
    }

   /* void generate_matrix(Float2 offset, float attach_angle, Float2 attach, Byte2 world_position){

    }*/

    void get_world_position(Byte2 set_world_position){
        world_position=set_world_position;
    }

    Byte2 world_position;

    @Override
    void draw(Paint paint, Canvas canvas, float offset_x,float offset_y,float attach_angle,float attach_x,float attach_y){

        if (world_position.x==0){//планета
            if(world_position.y==-1){//полёт в атмосфере

            }
            else {//если на станции

            }
        }
        else{//космос
            if(world_position.y==-1) {//полёт в космосе
                for (Space_station st:orbits.get(world_position.x).stations) { //рисуем станции
                    st.draw(paint, canvas, offset_x, offset_y, attach_angle, attach_x, attach_y);
                }
                for (Space_meteorite met:orbits.get(world_position.x).meteorites) { //рисуем станции

                    //met.draw(paint, canvas, offset_x, offset_y, attach_angle, attach_x, attach_y);
                    met.alternate_draw(paint, canvas);
                }

                //orbits.get(world_position.x).//рисуем большие метеориты
                //orbits.get(world_position.x).//рисуем обломки
                //рисуем мелкие метеориты
            }
            else {//если на станции
                //stations.get(world_position.y).draw_in();
            }
        }


       // float draw_x=x-offset_x;
        //float draw_y=y-offset_y;
        /*if (draw_x>=-MainActivity.dw & draw_x<=2*MainActivity.dw & draw_y>=-MainActivity.dw & draw_y<=2* dh)
        {
            Matrix matrix = new Matrix();
            matrix.setTranslate(draw_x, draw_y);

            matrix.preRotate(angle, body.getWidth()/2, body.getHeight()/2);
            matrix.postRotate(attach_angle, attach_x-offset_x, attach_y-offset_y);

            canvas.drawBitmap(body, matrix, paint);

        }
        draw_2(paint, canvas,draw_x,draw_y, attach_angle, attach_x-offset_x, attach_y-offset_y);*/
    }




    private class orbit{
        //крупные объекты space_object:
         ArrayList <Space_station> stations=new ArrayList<>(); //станции
         ArrayList <Space_meteorite> meteorites=new ArrayList<>();//метеориты
         //ArrayList <Space_big_meteorite> meteorites=new ArrayList<>();//метеориты для добычи
         //ArrayList <Space_crush> crushes=new ArrayList<>();//обломки
        float height;//высоты орбит (расстояние для перемещения)
        float start_speed;//скорость на орбите
        byte meteorite_chance;//вероятность метеоритов со скоростью орбиты 0-100%
        Float2 chaotic_meteorite_speed;//скорость нестационарных метеоритов
        byte chaotic_meteorite_chance;//вероятность нестационарных метеоритов %
        private orbit(){
            height=70000;
            start_speed=0;
            meteorite_chance=10;
            chaotic_meteorite_chance=0;
            //stations.add
        }






    }
}
