package runner.space.pac;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.HashMap;

public class Resources {
    private static HashMap info=new HashMap();

    private static Context mContext;

    public static void init(Context context){
        mContext=context;

        HashMap ships= new HashMap();
        ships.put("0",createshipInfo());

        HashMap meteorites= new HashMap();
        meteorites.put("0",createMeteoriteInfo());

        HashMap stations= new HashMap();
        stations.put("0",createStationInfo());

        info.put(Space_station.class, stations);
        info.put(Space_ship.class, ships);
        info.put(Space_meteorite.class, meteorites);
    }

    public static HashMap getObject(Class classificator, String type){
        return (HashMap)((HashMap) info.get(classificator)).get(type);
    }

    private static HashMap createshipInfo(){
        Bitmap body;
        Bitmap fire_b;
        Bitmap fire_r;
        Bitmap fire_l;
        Bitmap fire_f;
        int max_heal;
        float max_speed;
        float size;

        float tupe_size=0;
        int bitmapId = R.drawable.runner_0;//f0;// определяем начальные параметры

        Bitmap cBitmap = BitmapFactory.decodeResource(mContext.getResources(), bitmapId);
        body = Bitmap.createScaledBitmap(
                cBitmap, (int)(MainActivity.dw/(6-tupe_size)), (int)(MainActivity.dw/(6-tupe_size)), false);
        cBitmap.recycle();

        bitmapId = R.drawable.back_fire;//f0;// определяем начальные параметры
        cBitmap = BitmapFactory.decodeResource(mContext.getResources(), bitmapId);
        fire_b= Bitmap.createScaledBitmap(
                cBitmap, (int)(MainActivity.dw/(6-tupe_size)), (int)(MainActivity.dw/(6-tupe_size)), false);
        cBitmap.recycle();

        bitmapId = R.drawable.forward_fire;//f0;// определяем начальные параметры
        cBitmap = BitmapFactory.decodeResource(mContext.getResources(), bitmapId);
        fire_f= Bitmap.createScaledBitmap(
                cBitmap, (int)(MainActivity.dw/(6-tupe_size)), (int)(MainActivity.dw/(6-tupe_size)), false);
        cBitmap.recycle();

        bitmapId = R.drawable.left_fire;//f0;// определяем начальные параметры
        cBitmap = BitmapFactory.decodeResource(mContext.getResources(), bitmapId);
        fire_l= Bitmap.createScaledBitmap(
                cBitmap, (int)(MainActivity.dw/(6-tupe_size)), (int)(MainActivity.dw/(6-tupe_size)), false);
        cBitmap.recycle();

        bitmapId = R.drawable.right_fire;//f0;// определяем начальные параметры
        cBitmap = BitmapFactory.decodeResource(mContext.getResources(), bitmapId);
        fire_r= Bitmap.createScaledBitmap(
                cBitmap, (int)(MainActivity.dw/(6-tupe_size)), (int)(MainActivity.dw/(6-tupe_size)), false);
        cBitmap.recycle();

        max_speed=30;
        max_heal=1000;
        size=body.getWidth()/2;

        HashMap map=new HashMap();

        map.put("body",body);
        map.put("fire_b",fire_b);
        map.put("fire_r",fire_r);
        map.put("fire_l",fire_l);
        map.put("fire_f",fire_f);
        map.put("max_heal",max_heal);
        map.put("max_speed",max_speed);
        map.put("size",size);

        return map;
    }

    private static HashMap createMeteoriteInfo(){
        Bitmap body;
        int max_heal;
        float size;

        float tupe_size=0;
        int bitmapId = R.drawable.meteorid_0;//f0;// определяем начальные параметры
        Bitmap cBitmap = BitmapFactory.decodeResource(mContext.getResources(), bitmapId);
        body = Bitmap.createScaledBitmap(
                cBitmap, (int)(MainActivity.dw/(8+tupe_size)), (int)(MainActivity.dw/(8+tupe_size)), false);
        cBitmap.recycle();

        max_heal=1000;
        size=body.getWidth()/2;
        HashMap map=new HashMap();
        map.put("body",body);
        map.put("max_heal",max_heal);
        map.put("size",size);
        return map;
    }

    private static HashMap createStationInfo(){
        Bitmap body;
        int max_heal;
        float size;

        int bitmapId = R.drawable.space_station;//f0;// определяем начальные параметры
        Bitmap cBitmap = BitmapFactory.decodeResource(mContext.getResources(), bitmapId);
        body = Bitmap.createScaledBitmap(
                cBitmap, (int)(MainActivity.dw/(1-0)), (int)(MainActivity.dw/(1-0)), false);
        cBitmap.recycle();

        max_heal=1000;
        size=body.getWidth()/2;
        HashMap map=new HashMap();
        map.put("body",body);
        map.put("max_heal",max_heal);
        map.put("size",size);
        return map;
    }
}