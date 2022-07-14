package runner.space.pac;

import static runner.space.pac.MainActivity.dh;
import static runner.space.pac.MainActivity.dw;

import android.graphics.Matrix;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class LocalServer {

    ArrayList<Space_meteorite> met=new ArrayList<>();
    ArrayList<float[]> stars=new ArrayList<>();
    ArrayList<Space_station> stations=new ArrayList<>();
    Space_ship player;
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
        player=new Space_ship( "0",300+rand.nextInt(200),0,0,0,0);
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
        met.add(m0);
        }
    }

    public void resieveMessage(JSONObject j){
        new Thread(new Runnable(){
            public void run() {
        if(j!=null){
            try {
                switch ((String)j.get("Type")){
                    case "Login":
                        JSONObject answer=new JSONObject();
                        answer.put("Type","Login");
                        if (((String)j.get("Login")).equals("player") && ((String)(j.get("Password"))).equals("1111")){
                            answer.put("Status","OK");
                            answer.put("Player",player);
                            answer.put("Target",target);
                            answer.put("Stations",stations);
                            new Thread(new Runnable(){
                                public void run() {
                                    ArrayList<Integer>visible=new ArrayList<>();
                                    while (true){
                                        ArrayList<Space_meteorite>toSend=new ArrayList<>();
                                        for(int i=0;i< met.size();i++){
                                            if(!visible.contains(i)){
                                                if(!met.get(i).isFarAway(-player.x, -player.y)){
                                                    toSend.add(met.get(i));
                                                    visible.add(i);
                                                }
                                            }
                                            else if(met.get(i).isFarAway(-player.x, -player.y)){
                                                visible.remove(visible.indexOf(i));
                                            }
                                        }
                                        if(toSend.size()>0){
                                            try {
                                                JSONObject ans=new JSONObject();
                                                ans.put("Type","newObjects");
                                                ans.put("Meteorites",toSend);
                                                MainActivity.gw.localServerTransmit(ans);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }
                            }).start();

                            //answer.put по идее сразу нужно передавать и данные мира
                        }
                        else{
                            answer.put("Status","FAIL");
                        }
                        MainActivity.gw.localServerTransmit(answer);
                        break;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
            }
        }).start();
    }

}
