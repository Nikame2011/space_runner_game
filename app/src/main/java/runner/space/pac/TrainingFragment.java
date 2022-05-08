package runner.space.pac;

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
        //import com.pf.application.databinding.FragmentSecondBinding;

        import runner.space.pac.databinding.FragmentTrainingBinding;

public class TrainingFragment extends Fragment implements View.OnTouchListener, View.OnClickListener {

    private FragmentTrainingBinding binding;
    public static ImageButton Up_fly;
    public static ImageButton Up_jump;
    public static ImageButton Up_energy;
    public static ImageButton Up_concentration;
    public static ImageButton Tr1;
    public static ImageButton Tr2;
    public static ImageButton Tr3;
    public static ImageButton StrtTr;
    public static ConstraintLayout CL ;
    public static ConstraintLayout CL1 ;
    public static ConstraintLayout CL2 ;
    public static TextView TV1;
    public static TextView TV2;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentTrainingBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (MainActivity.setup) /*if (update == -1)*/{
                    MainActivity.Fly.setImageResource(R.drawable.b1);
                    //Setup.setImageResource(R.drawable.b2);
                    //Setup.setBackgroundResource(R.drawable.upgrade_menu);
                    //Setup.setBackgroundResource(R.drawable.b2);
                    MainActivity.setup = false;
                    //Config.setVisibility(View.VISIBLE);
                    //Up_fly.setVisibility(View.INVISIBLE);
                    //Up_jump.setVisibility(View.INVISIBLE);
                    //Up_energy.setVisibility(View.INVISIBLE);
                    //Reward.setVisibility(View.INVISIBLE);
                    MainActivity.Fly.setVisibility(View.VISIBLE);
                    //Reward.setVisibility(View.INVISIBLE);
                    //Fly.setVisibility(View.VISIBLE);
                 /*   if (MainActivity.gw.pen.status == "STF"){
                        if (MainActivity.gw.pen.savedstatus == "RCV")
                            MainActivity.gw.pen.status = "RCV";
                        else if (MainActivity.gw.pen.savedstatus == "UPD")
                            MainActivity.gw.pen.status = "UPD";
                        else if (MainActivity.gw.pen.savedstatus == "RTF")
                            MainActivity.gw.pen.status = "RTF";
                        MainActivity.gw.pen.savedstatus = "NON";
                    }*/
                }






                NavHostFragment.findNavController(TrainingFragment.this)
                        .navigate(R.id.action_trainingFragment_to_FirstFragment);
            }
        });


        MainActivity.Active_menu="Training";
        Up_fly = (ImageButton) view.findViewById(R.id.Up_fly);
        Up_jump = (ImageButton) view.findViewById(R.id.Up_jump);
        Up_energy = (ImageButton) view.findViewById(R.id.Up_energy);
        Up_concentration = (ImageButton) view.findViewById(R.id.Up_concentration);
        Tr1= (ImageButton) view.findViewById(R.id.Training1);
        Tr2= (ImageButton) view.findViewById(R.id.Training2);
        Tr3= (ImageButton) view.findViewById(R.id.Training3);
        StrtTr=(ImageButton) view.findViewById(R.id.Strt_training);
        CL =  view.findViewById(R.id.CL_buttons_training);
        CL1 =  view.findViewById(R.id.CL_buttons_training_2);
        CL2 =  view.findViewById(R.id.CL_buttons_training_3);
        TV1 = view.findViewById(R.id.test_tw);
        TV2= view.findViewById(R.id.TV_training_2);

        CL1.setVisibility(View.INVISIBLE);
        CL2.setVisibility(View.INVISIBLE);

        ConstraintLayout.LayoutParams par= (ConstraintLayout.LayoutParams)Tr3.getLayoutParams();
        par.width=MainActivity.dw/4;
        par.height=MainActivity.dw/4;
        par.leftMargin=MainActivity.dw*11/16;
        par.topMargin=MainActivity.dw/100;
        Tr3.setLayoutParams(par);



        par= (ConstraintLayout.LayoutParams)Tr2.getLayoutParams();
        par.width=MainActivity.dw/4;
        par.height=MainActivity.dw/4;
        par.leftMargin=MainActivity.dw/16;
        par.topMargin=MainActivity.dw/100;
        Tr2.setLayoutParams(par);

        par= (ConstraintLayout.LayoutParams)Tr1.getLayoutParams();
        par.width=MainActivity.dw/4;
        par.height=MainActivity.dw/4;
        par.leftMargin=MainActivity.dw*6/16;
        par.topMargin=MainActivity.dw/100;
        Tr1.setLayoutParams(par);

        par= (ConstraintLayout.LayoutParams)CL.getLayoutParams();
        //par.width=MainActivity.dw/4;
        par.height= (int) (MainActivity.dw*2.1/3);
        //par.leftMargin=MainActivity.dw*6/16;
        //par.topMargin=MainActivity.dw/100;
        CL.setLayoutParams(par);

        rotator(0,0);

       // if(!MainActivity.energy_show)Up_energy.setVisibility(View.INVISIBLE);
        Up_fly.setOnTouchListener(this);
        Up_jump.setOnTouchListener(this);
        Up_energy.setOnTouchListener(this);
        Up_concentration.setOnTouchListener(this);
        CL.setOnTouchListener(this);

        Tr1.setOnClickListener(this);
        Tr2.setOnClickListener(this);
        Tr3.setOnClickListener(this);

set_visible_0();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    float startX=0;
    float movedX=0;

    @Override
    public boolean onTouch(View button, MotionEvent motion) {

        byte pressed = -1;


        switch (button.getId()) { // определяем какая кнопка
            case R.id.Up_jump:
                pressed = 0;
                break;

            case R.id.Up_fly:
                pressed = 1;
                break;

            case R.id.Up_energy:
                pressed = 2;
                break;

            case R.id.Up_concentration:
                pressed = 3;
                break;

            case R.id.CL_buttons_training:
                pressed = 5;
                break;


        }








      /*  switch(button.getId()) { // определяем какая кнопка

           /* case R.id.Up_fly:
                switch (motion.getAction()) { // определяем нажата или отпущена
                   /* case MotionEvent.ACTION_DOWN:
                        if (update == -1) {
                            update = 1;

                        }
                        break;*/
                 /*   case MotionEvent.ACTION_DOWN:
                        //startX= motion.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        moving=true;
                        if(startX==0)startX= motion.getX();

                        movedX= motion.getX();
                        rotator(startX,movedX);
                        startX=movedX;
                      //  }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (!moving){
                            select_update=1;
                        }
                        else{
                            if (startX!=movedX) {
                                select_update = (byte) (5 * ((Math.PI * 4 - (grad - Math.PI / 5 - Math.PI * 3 / 2)) % (Math.PI * 2)) / (Math.PI * 2));
                            }
                            else{
                                select_update=1;
                            }
                            startX = 0;
                            moving = false;

                        }
                        double need_angle=(Math.PI*2+Math.PI*3/2-TrainingFragment.select_update*Math.PI*2/5)%(Math.PI*2);
                        if(TrainingFragment.grad==need_angle)
                            TrainingFragment.set_visible_1();
                        else{
                            CL1.setVisibility(View.INVISIBLE);
                            CL2.setVisibility(View.INVISIBLE);
                        }
                        break;
                }
                break;
            case R.id.Up_jump:
                switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                     /*   if (update == -1) {
                            update = 0;

                        }*/
                 /*       break;
                    case MotionEvent.ACTION_MOVE:
                        moving=true;
                        if(startX==0)startX= motion.getX();

                        movedX= motion.getX();
                        rotator(startX,movedX);
                        startX=movedX;
                        //  }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (!moving){
                            select_update=0;
                        }
                        else{
                            if (startX!=movedX) {

                                select_update = (byte) (5 * ((Math.PI * 4 - (grad - Math.PI / 5 - Math.PI * 3 / 2)) % (Math.PI * 2)) / (Math.PI * 2));
                            }
                            else{
                                select_update=0;

                            }
                            startX = 0;
                            moving = false;

                        }
                        double need_angle=(Math.PI*2+Math.PI*3/2-TrainingFragment.select_update*Math.PI*2/5)%(Math.PI*2);
                        if(TrainingFragment.grad==need_angle)
                            TrainingFragment.set_visible_1();
                        break;
                }
                break;
            case R.id.Up_energy:
                switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                       /* if (update == -1) {
                            update = 2;
                        }*/
             /*           break;
                    case MotionEvent.ACTION_MOVE:
                        moving=true;
                        if(startX==0)startX= motion.getX();

                        movedX= motion.getX();
                        rotator(startX,movedX);
                        startX=movedX;
                        //  }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (!moving){
                            select_update=2;
                        }
                        else{
                            if (startX!=movedX) {

                                select_update = (byte) (5 * ((Math.PI * 4 - (grad - Math.PI / 5 - Math.PI * 3 / 2)) % (Math.PI * 2)) / (Math.PI * 2));
                            }
                            else{
                                select_update=2;

                            }
                            startX = 0;
                            moving = false;
                        }
                        double need_angle=(Math.PI*2+Math.PI*3/2-TrainingFragment.select_update*Math.PI*2/5)%(Math.PI*2);
                        if(TrainingFragment.grad==need_angle)
                            TrainingFragment.set_visible_1();
                        break;
                }
                break;
            case R.id.Up_concentration:
                switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                       /* if (update == -1) {
                            update = 2;
                        }*/
                  /*      break;
                    case MotionEvent.ACTION_MOVE:
                        moving=true;
                        if(startX==0)startX= motion.getX();

                        movedX= motion.getX();
                        rotator(startX,movedX);
                        startX=movedX;
                        //  }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (!moving){
                            select_update=3;
                        }
                        else{
                            if (startX!=movedX) {

                                select_update = (byte) (5 * ((Math.PI * 4 - (grad - Math.PI / 5 - Math.PI * 3 / 2)) % (Math.PI * 2)) / (Math.PI * 2));
                            }
                            else{
                                select_update=3;

                            }
                            startX = 0;
                            moving = false;
                        }
                        double need_angle=(Math.PI*2+Math.PI*3/2-TrainingFragment.select_update*Math.PI*2/5)%(Math.PI*2);
                        if(TrainingFragment.grad==need_angle)
                            TrainingFragment.set_visible_1();
                        break;
                }
                break;
            case R.id.CL_buttons_training:
                switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                       /* if (update == -1) {
                            update = 2;
                        }*/
           /*             break;
                    case MotionEvent.ACTION_MOVE:
                        moving=true;
                        if(startX==0)startX= motion.getX();

                        movedX= motion.getX();
                        rotator(startX,movedX);
                        startX=movedX;
                        //TextView tw = button.findViewById(R.id.test_tw);
                        //tw.setText(String.valueOf (5*((Math.PI*4-(grad-Math.PI/5-Math.PI*3/2))%(Math.PI*2))/(Math.PI*2)));
                        //  }
                        break;
                    case MotionEvent.ACTION_UP:
                        startX= 0;
                        moving=false;
                        select_update= (byte) (5*((Math.PI*4-(grad-Math.PI/5-Math.PI*3/2))%(Math.PI*2))/(Math.PI*2));
                        double need_angle=(Math.PI*2+Math.PI*3/2-TrainingFragment.select_update*Math.PI*2/5)%(Math.PI*2);
                        if(TrainingFragment.grad==need_angle)
                            TrainingFragment.set_visible_1();
                        break;
                }
                break;



        }
*/
        if (pressed >= 0 && pressed <= 5){
            switch (motion.getAction()) { // определяем нажата или отпущена
                   /* case MotionEvent.ACTION_DOWN:
                        if (update == -1) {
                            update = 1;

                        }
                        break;*/
                case MotionEvent.ACTION_DOWN:
                    //startX= motion.getX();
                    break;
                case MotionEvent.ACTION_MOVE:
                    moving = true;
                    if (startX == 0) startX = motion.getX();
                    else startX = movedX;
                    movedX = motion.getX();
                    rotator(startX, movedX);

                    double need_angl = (Math.PI * 2 + Math.PI * 3 / 2 - TrainingFragment.select_update * Math.PI * 2 / 5) % (Math.PI * 2);
                    if (TrainingFragment.grad == need_angl)
                        TrainingFragment.set_visible_1();
                    else {
                        CL1.setVisibility(View.INVISIBLE);
                        CL2.setVisibility(View.INVISIBLE);
                    }
                    //TextView tw = button.findViewById(R.id.test_tw);
                    //tw.setText("0");

                    //tw.setText(String.valueOf (5*((Math.PI*4-(grad-Math.PI/5-Math.PI*3/2))%(Math.PI*2))/(Math.PI*2)));

                    //  }
                    break;
                case MotionEvent.ACTION_UP:
                    if (!moving) {
                        if (pressed < 5 && pressed > -1) select_update = pressed;
                    } else {
                        if (startX != movedX) {
                            select_update = (byte) (5 * ((Math.PI * 4 - (grad - Math.PI / 5 - Math.PI * 3 / 2)) % (Math.PI * 2)) / (Math.PI * 2));
                        } else {
                            if (pressed < 5 && pressed > -1) select_update = pressed;
                        }
                        startX = 0;
                        movedX=0;
                        moving = false;
                    }
                    double need_angle = (Math.PI * 2 + Math.PI * 3 / 2 - TrainingFragment.select_update * Math.PI * 2 / 5) % (Math.PI * 2);
                    if (TrainingFragment.grad == need_angle)
                        TrainingFragment.set_visible_1();
                    else {
                        CL1.setVisibility(View.INVISIBLE);
                        CL2.setVisibility(View.INVISIBLE);
                    }
                    break;
            }
    }




        return true;
    }

    public static void rotator(float startX,float movedX){

        //ConstraintLayout.LayoutParams par= (ConstraintLayout.LayoutParams)Up_fly.getLayoutParams();
        grad-=(startX - movedX)*2/MainActivity.dw;
        if (TrainingFragment.grad>Math.PI*2)
            TrainingFragment.grad-=Math.PI*2;
        if (TrainingFragment.grad<0)
            TrainingFragment.grad+=Math.PI*2;

        Up_jump.setLayoutParams(rotator2((ConstraintLayout.LayoutParams)Up_jump.getLayoutParams(),0));
        Up_fly.setLayoutParams(rotator2((ConstraintLayout.LayoutParams)Up_fly.getLayoutParams(),1));
        Up_energy.setLayoutParams(rotator2((ConstraintLayout.LayoutParams)Up_energy.getLayoutParams(),2));
        Up_concentration.setLayoutParams(rotator2((ConstraintLayout.LayoutParams)Up_concentration.getLayoutParams(),3));
    }

    private static ConstraintLayout.LayoutParams rotator2(ConstraintLayout.LayoutParams par, int sctr) {

        float sector= (float) ((grad+sctr*Math.PI*2/5)%(Math.PI*2));

        float x= (float) ((MainActivity.dw/2-MainActivity.dw/8)*Math.cos(sector));
        float y= (float) ((MainActivity.dw/2-MainActivity.dw/8)*Math.sin(sector));
        if (sector>=0 && sector<Math.PI) {
            par.topMargin = (int) (MainActivity.dw / 4 - y*(0.55+Math.abs(sector/Math.PI-0.5)*0.25/0.5));

        }
        else{
            par.topMargin = (int) (MainActivity.dw / 4 - y*(0.55+Math.abs((sector)/Math.PI-1.5)*0.25/0.5));
        }
double coef=(sector)/Math.PI-1.5;
        if (coef<-1)coef+=2;
        par.width= (int) (MainActivity.dw/4*(1-Math.abs(coef/*(sector)/Math.PI-1.5*/)*0.25));
        par.height= par.width;

        par.leftMargin=(int) (MainActivity.dw/2+x-MainActivity.dw/8);

        return (par);
    }

    public static void set_visible_0(){
      /*  Up_jump.setImageResource(0);
        if(MainActivity.gw.pen.maxY<0.5)Up_fly.setImageResource(R.drawable.blocked);
        else Up_fly.setImageResource(0);
        if(MainActivity.gw.pen.maxY<2.0)Up_energy.setImageResource(R.drawable.blocked);
        else Up_energy.setImageResource(0);
        if(MainActivity.gw.pen.maxY<10.0)Up_concentration.setImageResource(R.drawable.blocked);
        else Up_concentration.setImageResource(0);*/
    }

    public static void set_visible_1(){
/*
        switch (select_update){
            case 0:
                TV1.setText(R.string.jump_power);
                Tr1.setBackgroundResource(R.drawable.b1);
                Tr1.setImageResource(0);
                Tr2.setBackgroundResource(R.drawable.b1);
                Tr3.setBackgroundResource(R.drawable.b1);
                if(MainActivity.gw.pen.next_jump>10) {
                    Tr2.setImageResource(0);
                }
                else{
                    Tr2.setImageResource(R.drawable.blocked);
                }
                if(MainActivity.gw.pen.next_jump>20) {
                    Tr3.setImageResource(0);
                }
                else{
                    Tr3.setImageResource(R.drawable.blocked);
                }
                break;
            case 1:
                TV1.setText(R.string.boost_power);
                Tr1.setBackgroundResource(R.drawable.b1);
                Tr2.setBackgroundResource(R.drawable.b1);
                Tr3.setBackgroundResource(R.drawable.b1);

                if (MainActivity.gw.pen.maxY>=MainActivity.gw.pen.bust_record[0]) {
                    Tr1.setImageResource(0);
                }
                else{
                    Tr1.setImageResource(R.drawable.blocked);
                }

                if(MainActivity.gw.pen.next_bust>10) {
                    Tr2.setImageResource(0);
                }
                else{
                    Tr2.setImageResource(R.drawable.blocked);
                }
                if(MainActivity.gw.pen.next_bust>20) {
                    Tr3.setImageResource(0);
                }
                else{
                    Tr3.setImageResource(R.drawable.blocked);
                }
                break;
            case 2:
                TV1.setText(R.string.energy);
                Tr1.setBackgroundResource(R.drawable.b1);
                Tr2.setBackgroundResource(R.drawable.b1);
                Tr3.setBackgroundResource(R.drawable.b1);

                if (MainActivity.gw.pen.maxY>=MainActivity.gw.pen.energy_record[1]) {
                    Tr1.setImageResource(0);
                }
                else{
                    Tr1.setImageResource(R.drawable.blocked);
                }

                if(MainActivity.gw.pen.next_energy>10) {
                    Tr2.setImageResource(0);
                }
                else{
                    Tr2.setImageResource(R.drawable.blocked);
                }
                if(MainActivity.gw.pen.next_energy>20) {
                    Tr3.setImageResource(0);
                }
                else{
                    Tr3.setImageResource(R.drawable.blocked);
                }
                break;
            case 3:
                TV1.setText(R.string.concentration);

                Tr1.setBackgroundResource(R.drawable.b1);
                Tr1.setImageResource(R.drawable.blocked);

                Tr2.setBackgroundResource(R.drawable.b1);
                Tr2.setImageResource(R.drawable.blocked);

                Tr3.setBackgroundResource(R.drawable.b1);
                Tr3.setImageResource(R.drawable.blocked);
                break;
            case 4:
                TV1.setText("");

                Tr1.setBackgroundResource(0);
                Tr1.setImageResource(0);

                Tr2.setBackgroundResource(0);
                Tr2.setImageResource(0);

                Tr3.setBackgroundResource(0);
                Tr3.setImageResource(0);
                break;

        }

        CL1.setVisibility(View.VISIBLE);
*/
    }


    public static void set_visible_2(byte training){
    /*    switch (select_update){
            case 0:
                switch (training){

                    case 0:

                        String to_show= MainActivity.cont.getString(R.string.strong_training);
                        to_show+="\n"+String.valueOf(MainActivity.gw.pen.next_jump);
                        to_show+=MainActivity.cont.getString(R.string.level);
                        to_show+=String.valueOf(MainActivity.gw.pen.next_jump);

                        TV2.setText(to_show);

                        //TV2.setText(R.string.strong_training+"\n"+String.valueOf(MainActivity.gw.pen.next_jump)+R.string.level);
                        StrtTr.setVisibility(View.VISIBLE);
                        break;
                    case 1:

                        if(MainActivity.gw.pen.next_jump>10) {
                            TV2.setText("");
                            StrtTr.setVisibility(View.VISIBLE);
                        }
                        else{
                            TV2.setText("");
                            StrtTr.setVisibility(View.INVISIBLE);
                        }

                        break;
                    case 2:
                        if(MainActivity.gw.pen.next_jump>20) {
                            TV2.setText("");
                            StrtTr.setVisibility(View.VISIBLE);
                        }
                        else{
                            TV2.setText("");
                            StrtTr.setVisibility(View.INVISIBLE);
                        }
                        break;
                }

                break;
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:
                break;

        }

        CL2.setVisibility(View.VISIBLE);
*/
    }

    public static double grad=Math.PI*3/2;
    public static byte select_update=0;
    public static boolean moving=false;

    @Override
    public void onClick(View view) {
        byte btn=-1;
        switch(view.getId()) { // определяем какая кнопка

            case R.id.Training1:
                btn=0;
                break;
            case R.id.Training2:
                btn=1;
                break;
            case R.id.Training3:
                btn=2;
                break;
        }
        set_visible_2(btn);
    }
}