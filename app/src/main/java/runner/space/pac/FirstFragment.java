package runner.space.pac;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import runner.space.pac.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment implements View.OnTouchListener, View.OnClickListener{

private FragmentFirstBinding binding;
public static ImageButton Config;
public static ImageButton Setup;
static ImageButton Road;
    //ImageView Setup;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

      binding = FragmentFirstBinding.inflate(inflater, container, false);
      return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       binding.UpdateMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!MainActivity.setup){

//                    if (MainActivity.gw.pen.status=="RCV"||MainActivity.gw.pen.status=="RTF"||MainActivity.gw.pen.status=="UPD") {
//                        if (MainActivity.gw.pen.status != "STF"){
//                            if (MainActivity.gw.pen.status == "RCV")
//                                MainActivity.gw.pen.savedstatus = "RCV";
//                            else if (MainActivity.gw.pen.status == "RTF")
//                                MainActivity.gw.pen.savedstatus = "RTF";
//                            else if (MainActivity.gw.pen.status == "UPD")
//                                MainActivity.gw.pen.savedstatus = "UPD";
//                            MainActivity.gw.pen.status = "STF";
//                        }
//                        MainActivity.Fly.setImageResource(R.drawable.bust_training);
//                        //Fly.setImageResource(R.drawable.imb);
//                        //Setup.setImageResource();
//                        //Setup.setBackground(R.drawable.back_b);
//                        //Setup.setBackgroundResource(R.drawable.back_b);
//                        MainActivity.setup = true;
//
//                        if(MainActivity.gw.pen.savedstatus != "UPD")
//                            MainActivity.Fly.setVisibility(View.INVISIBLE);
//                        //else if (MainActivity.mRewardedAd != null)
//                            //Reward.setVisibility(View.VISIBLE);
//                        //Config.setVisibility(View.INVISIBLE);
//                        //Up_fly.setVisibility(View.VISIBLE);
//                        //Up_jump.setVisibility(View.VISIBLE);
//                                /*if (energy_show) Up_energy.setVisibility(View.VISIBLE);
//                                else {
//                                    if (gw.pen.next_bust > 0) {
//                                        MainActivity.energy_show = true;
//                                        Up_energy.setVisibility(View.VISIBLE);
//                                    }
//                                }*/
//                    }
                }




                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_trainingFragment);
                        //.navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
        Config = (ImageButton) view.findViewById(R.id.Сonfig);
        ConstraintLayout.LayoutParams par= (ConstraintLayout.LayoutParams)Config.getLayoutParams();
        par.width=MainActivity.dw/8;
        par.height=MainActivity.dw/8;
        par.leftMargin=MainActivity.dw/100;
        par.topMargin=MainActivity.dw/100;
        Config.setLayoutParams(par);
        //Config.setOnClickListener(this);
        Config.setOnTouchListener(this);

        Setup=  view.findViewById(R.id.Update_menu_button);
        par= (ConstraintLayout.LayoutParams)Setup.getLayoutParams();
        par.width=MainActivity.dw/4;
        par.height=MainActivity.dw/4;
        par.leftMargin=MainActivity.dw/100;
        //par.bottomMargin=MainActivity.dw/100;
        par.bottomMargin=MainActivity.dh/2-MainActivity.dw/8;
        Setup.setLayoutParams(par);

        Road=  view.findViewById(R.id.Road_IV);
        Road.setOnTouchListener(this);
        //Thread t = new Thread(new Runnable() {

     /*       public void run() {

                while(true){
                    try {
                        Thread.sleep(100);
                        TextView t0 = view.findViewById(R.id.TW_run);
                        t0.setText("0");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                //remove.clear();
            }
        });
        t.start();*/

    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

/*    public static float startX=0;
    public static float movedX=0;
    public static float startY=0;
    public static float movedY=0;
    public static boolean moving=false;
    public static boolean rotating=false;
    float d=0;
    public static float rot=0;*/

    public static float startX=0;
    public static float movedX=0;
    public static float startY=0;
    public static float movedY=0;
    public static boolean moving=false;
    public static boolean rotating=false;
    float d=0;
    public static float rot=0;


    @Override
    public boolean onTouch(View button, MotionEvent motion) {
        switch(button.getId()) { // определяем какая кнопка

            case R.id.Road_IV:
               /* switch (motion.getAction()) { // определяем нажата или отпущена

                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        if (motion.getPointerCount()==1) {
                            moving = true;
                        }
                        if (motion.getPointerCount()==2) {
                            moving = false;
                            rotating=true;
                        }
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if (motion.getPointerCount()==1){
                            moving=true;

                            if (startX == 0) startX = motion.getX();
                            movedX = motion.getX();

                            if (startY == 0) startY = motion.getY();
                            movedY = motion.getY();

                            rotating=false;
                            rot=0;
                            d=0;
                        }
                        if (motion.getPointerCount()==2) {
                            rotating=true;

                            if (d==0) d = (float) Math.atan2(motion.getY(0) - motion.getY(1), motion.getX(0) - motion.getX(1));
                            rot=(float)Math.toDegrees( Math.atan2(motion.getY(0)-motion.getY(1),motion.getX(0)-motion.getX(1))-d);

                            if (rot<=30&rot>=-30){
                                if (startX == 0) startX = (motion.getX(0)+motion.getX(1))/2;
                                movedX = (motion.getX(0)+motion.getX(1))/2;

                                if (startY == 0) startY = (motion.getY(0)+motion.getY(1))/2;
                                movedY = (motion.getY(0)+motion.getY(1))/2;
                            }

                            moving=false;

                        }
                        break;

                    case MotionEvent.ACTION_POINTER_UP:
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        if (motion.getPointerCount()==2) {
                            rotating=false;
                            rot=0;
                            d=0;
                        }
                        if(motion.getPointerCount()==1) {
                            startX = 0;
                            movedX=0;
                            startY = 0;
                            movedY=0;
                            moving = false;
                        }
                        break;


                }*/









                switch (motion.getAction()) { // определяем нажата или отпущена

                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        if (motion.getPointerCount()==1) {
                            moving = true;
                            rotating=false;
                            rot=0;
                            d=0;
                        }
                        if (motion.getPointerCount()==2) {

                            rotating=true;
                            moving=false;
                            startX = 0;
                            movedX=0;
                            startY = 0;
                            movedY=0;
                        }
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if (motion.getPointerCount()==1){
                            moving=true;

                            if (startX == 0) startX = motion.getX();
                            movedX = motion.getX();

                            if (startY == 0) startY = motion.getY();
                            movedY = motion.getY();

                            rotating=false;
                            rot=0;
                            d=0;
                        }
                        if (motion.getPointerCount()==2) {
                            rotating=true;

                            if (d==0)d = (float) Math.atan2(motion.getY(0) - motion.getY(1), motion.getX(0) - motion.getX(1));
                            rot=(float)Math.toDegrees( Math.atan2(motion.getY(0)-motion.getY(1),motion.getX(0)-motion.getX(1))-d);


                            moving=false;
                            startX = 0;
                            movedX=0;
                            startY = 0;
                            movedY=0;
                        }
                        break;

                    case MotionEvent.ACTION_POINTER_UP:
                    case MotionEvent.ACTION_UP:

                        if (motion.getPointerCount()==2) {
                            rotating=false;
                            rot=0;
                            d=0;
                        }
                        if(motion.getPointerCount()==1) {
                            rotating=false;
                            rot=0;
                            d=0;
                            moving = false;
                            startX = 0;
                            movedX=0;
                            startY = 0;
                            movedY=0;

                        }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                       // if (motion.getPointerCount()==2) {
                            rotating=false;
                            rot=0;
                            d=0;
                       // }
                       // if(motion.getPointerCount()==1) {
                            moving = false;
                            startX = 0;
                            movedX=0;
                            startY = 0;
                            movedY=0;

                       // }
                        break;
                }
                break;


        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) { // определяем какая кнопка
            case R.id.Сonfig:
                //switch (motion.getAction()) { // определяем нажата или отпущена
                   // case MotionEvent.ACTION_DOWN:
                        //Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
                        Intent intent = new Intent(getContext(), ConfigActivity.class);
                        startActivity(intent);
                        //break;
                //}
                break;
        }
    }
}