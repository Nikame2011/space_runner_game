package runner.space.pac;

import static runner.space.pac.MainActivity.dh;
import static runner.space.pac.MainActivity.dw;

import android.graphics.Matrix;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class LocalServer {

    //ArrayList<Space_meteorite> met=new ArrayList<>();
    ArrayList<float[]> stars=new ArrayList<>();
    ArrayList<Space_station> stations=new ArrayList<>();
    HashMap<String,Space_ship>players=new HashMap<>();
    HashMap<String,ArrayList<Long>>visibleMeteorites=new HashMap<>();
    HashMap<String,ArrayList<Long>>toAddMeteorites=new HashMap<>();
    HashMap<String,ArrayList<Long>>toRemoveMeteorites=new HashMap<>();

    HashMap<Long,Space_meteorite>met=new HashMap<>();
    long uIdMeteorites=0;
    int target;

    public LocalServer() {
        Random rand=new Random();
        /*for (int hei = 0; hei < dh + dw; hei++)
            for (int wit = 0; wit < dh + dw; wit++)
                if (rand.nextInt(5000) == 0) {
                    stars.add(new float[]{wit, hei});

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
        */

        //player.init(getContext(),(byte) 0,300+rand.nextInt(200),0,0,0,0);

        Space_station s= new Space_station("0",100000,0,0,0,0);
        //s.init(getContext(), (byte) 0,100000,0,0,0,0);
        stations.add(s);

        long target_y=-10000-rand.nextInt(1000);
        long target_x= (long) Math.sqrt(12000*12000-target_y*target_y);

        s= new Space_station( "0",100000,target_x,target_y,0,0);
        //s.init(getContext(), (byte) 0,100000,target_x,target_y,0,0);
        stations.add(s);
        target=stations.size()-1;

        for (int i=0;i<100+rand.nextInt(900);i++){
            //rand=new Random();
            Space_meteorite m0 = new Space_meteorite( "0", 500 + rand.nextInt(50), (-500+rand.nextInt(1000))*5, (-500+rand.nextInt(1000))*5, 0, 0);
            //m0.init(getContext(), (byte) 0, 500 + rand.nextInt(50), pos_x, pos_y, spd_x, spd_y);
            met.put(uIdMeteorites++,m0);
           // met.add(m0);
        }
    }
int ddw;
    int ddh;
    public void resieveMessage(JSONObject j){
        new Thread(new Runnable(){
            public void run() {
        if(j!=null){
            try {
                switch ((String)j.get("Type")){
                    case "Login":
                        JSONObject answer=new JSONObject();
                        ddw=(int)j.get("dw");
                        ddh=(int)j.get("dh");
                        answer.put("Type","Login");
                        if (((String)j.get("Login")).equals("player") && ((String)(j.get("Password"))).equals("1111")){
                            answer.put("Status","OK");
                            //TODO вот здесь создаём игрока и добавляем его в перечень играющих с уникальным идентификатором(логином, он потом везде будет передаваться)
                            Random rand=new Random();
                            Space_ship player=new Space_ship( "0",300+rand.nextInt(200),0,0,0,0);
                            players.put((String)j.get("Login"),player);

                            visibleMeteorites.put((String)j.get("Login"),new ArrayList<>());
                            toAddMeteorites.put((String)j.get("Login"),new ArrayList<>());
                            toRemoveMeteorites.put((String)j.get("Login"),new ArrayList<>());

                            answer.put("Player",player);
                            answer.put("Target",target);

                            //TODO передавать только стартовую и финишную станции, остальные должны передаться сами при приближении
                            answer.put("Stations",stations);

                            //answer.put по идее сразу нужно передавать и данные мира
                        }
                        else{
                            answer.put("Status","FAIL");
                        }
                        MainActivity.gw.localServerTransmit(answer);
                        break;
                    case "Player_info":
                        players.put((String)j.get("Login"),(Space_ship) j.get("player"));
                        //player=(Space_ship) j.get("player");
                        break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
            }
        }).start();
    }

    private void calculateCollision(){
        new Thread(new Runnable(){
            public void run() {
                //ArrayList<Integer>visible=new ArrayList<>();
                while (true){
                    //ArrayList<Space_meteorite>toSend=new ArrayList<>();
                    for(long key:met.keySet()/*int i=0;i< met.size();i++*/){

                        for (String keyPlay:players.keySet()){
                            if(visibleMeteorites.get(keyPlay).contains(key)){//если уже передан игроку, проверяем, не нужно ли удалять
                                if(met.get(key).isFarAway(-players.get(keyPlay).x, -players.get(keyPlay).y)){//если улетел далеко,
                                    toRemoveMeteorites.get(keyPlay).add(key);//добавляем в список на удаление
                                }
                                else{//если не улетел, проверяем на столкновения

                                }
                            }
                            else {//если не было в списке игрока, проверяем, нужно ли добавить туда
                                if(met.get(key).isvisible(-players.get(keyPlay).x, -players.get(keyPlay).y)){
                                    toAddMeteorites.get(keyPlay).add(key);
                                }
                            }
                        }

                    }

                    for (String keyPlay:players.keySet()){//для каждого игрока
                        if(toAddMeteorites.size()>0){//если есть, что добавить
                            HashMap<Long,Space_meteorite>toSend=new HashMap();
                            for (long key:toAddMeteorites.get(keyPlay)){
                                toSend.put(key,met.get(key));//собираем пакет с новыми метеоритами
                                visibleMeteorites.get(keyPlay).add(key);//сохраняем новые метеориты
                            }
                            try {//отправляем пакет
                                JSONObject ans=new JSONObject();
                                ans.put("Type","newObjects");
                                ans.put("Meteorites",toSend);
                                MainActivity.gw.localServerTransmit(ans);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            toAddMeteorites.get(keyPlay).clear();
                            toSend.clear();
                        }

                        if(toRemoveMeteorites.size()>0){//если есть что удалить,
                            try {//отправляем ключи игроку
                                JSONObject ans=new JSONObject();
                                ans.put("Type","deleteObjects");
                                ans.put("Meteorites",toRemoveMeteorites);
                                MainActivity.gw.localServerTransmit(ans);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            for (long key:toRemoveMeteorites.get(keyPlay)){
                                visibleMeteorites.get(keyPlay).remove(key);//удаляем на сервере
                            }
                            toRemoveMeteorites.get(keyPlay).clear();
                        }
                    }
                }
            }
        }).start();
    }


}
