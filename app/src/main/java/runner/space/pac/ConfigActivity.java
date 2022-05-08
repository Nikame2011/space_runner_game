package runner.space.pac;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.ui.AppBarConfiguration;

import runner.space.pac.R;


public class ConfigActivity extends AppCompatActivity implements View.OnTouchListener{

    private AppBarConfiguration appBarConfiguration;
    //private ActivityConfigBinding binding;
    public static ImageButton Back;
    public static ImageButton New_game;
    //public static Switch Down_toggle;
    public static ImageButton Skip_fall;
    public static ImageButton Ask_brok;
    public static int dw,dh;
    private static byte shure=0;
    TextView S_F_text;
    ConstraintLayout Shure_lo;
    ConstraintLayout Settings_lo;

    TextView TV_shure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_config);
        dw=MainActivity.dw;
        dh=MainActivity.dh;
        //binding = ActivityConfigBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        Back = (ImageButton) findViewById(R.id.Back);
        New_game = (ImageButton) findViewById(R.id.new_game);
        Skip_fall = (ImageButton) findViewById(R.id.skip_fall);
        Ask_brok = (ImageButton) findViewById(R.id.ib_ask_brok);
//        TableLayout.LayoutParams par= (TableLayout.LayoutParams)Back.getLayoutParams();
 //       par.width=dw/8;
 //       par.height=dw/8;
        /*par.leftMargin=dw/100;
        par.topMargin=dw/100;*/
 //       Back.setLayoutParams (par);
        Back.setOnTouchListener(ConfigActivity.this);

        New_game.setOnTouchListener(ConfigActivity.this);

        Skip_fall.setOnTouchListener(ConfigActivity.this);
        Ask_brok.setOnTouchListener(ConfigActivity.this);

        ImageButton Yes=(ImageButton) findViewById(R.id.yes);
        ImageButton No= (ImageButton) findViewById(R.id.no);
        Yes.setOnTouchListener(ConfigActivity.this);
        No.setOnTouchListener(ConfigActivity.this);
        TV_shure=findViewById(R.id.tv_shure_0);

        S_F_text=findViewById(R.id.skip_fall_text);
        //Down_toggle = (Switch) findViewById(R.id.toggle_down);
        //Down_toggle.setOnTouchListener(ConfigActivity.this);
        Shure_lo=findViewById(R.id.shure_layout_0);
        Shure_lo.setVisibility(View.INVISIBLE);
        Settings_lo=findViewById(R.id.settings_layout_0);
       /* if (MainActivity.quick_down==true){
            //Down_toggle.setChecked(true);

            S_F_text.setText(MainActivity.cont.getString(R.string.toggle_down_1));
        }
        else {
            //Down_toggle.setChecked(false);
            S_F_text.setText(MainActivity.cont.getString(R.string.toggle_down_0));
        }*/
    }

    @Override
    public boolean onTouch(View button, MotionEvent motion) {
        switch(button.getId()) { // определяем какая кнопка
            case R.id.Back:
                switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                        //Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
                        //startActivity(intent);
                        finish();
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                break;
            case R.id.new_game:
                switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                        TV_shure.setText(R.string.new_game);
                        get_shure();
                        Thread t = new Thread(new Runnable() {
                            public void run() {
                                while(shure==0){
                                    try {
                                        Thread.sleep(10);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (shure==1){
                                    MainActivity.new_game=true;
                                    shure=0;
                                    finish();
                                }
                            }
                        });
                        t.start();

                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                }

                break;
            case R.id.ib_ask_brok:
                /*switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                        TV_shure.setText(R.string.ask_brok);
                        get_shure();
                        Thread t = new Thread(new Runnable() {
                            public void run() {
                                while(shure==0){
                                    try {
                                        Thread.sleep(10);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (shure==1){
                                    MainActivity.ask_number=1;
                                    shure=0;
                                    //finish();
                                }
                            }
                        });
                        t.start();

                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                }
*/
                break;
            case R.id.yes:
                switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                        shure=1;
                        close_shure();
                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                }

                break;
            case R.id.no:
                switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                        shure=-1;
                        close_shure();
                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                }

                break;
            /*case R.id.toggle_down:
                switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:

                        break;
                    case MotionEvent.ACTION_UP:
                        if (Down_toggle.isChecked()){
                            Down_toggle.setChecked(false);
                            MainActivity.quick_down=false;
                        }
                        else {
                            Down_toggle.setChecked(true);
                            MainActivity.quick_down=true;
                        }
                        MainActivity.gw.pen.save();
                        break;
                }


                break;*/
            case R.id.skip_fall:
               /* switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:

                        break;
                    case MotionEvent.ACTION_UP:
                        if (MainActivity.quick_down==false){
                            S_F_text.setText(MainActivity.cont.getString(R.string.toggle_down_1));
                            MainActivity.quick_down=true;
                        }
                        else {
                            S_F_text.setText(MainActivity.cont.getString(R.string.toggle_down_0));
                            MainActivity.quick_down=false;
                        }
                        MainActivity.gw.pen.save();
                        break;
                }

*/
                break;
        }
        return true;
    }

    private void get_shure(){
        Shure_lo.setVisibility(View.VISIBLE);
        Settings_lo.setVisibility(View.INVISIBLE);
    }
    private void close_shure(){
        Shure_lo.setVisibility(View.INVISIBLE);
        Settings_lo.setVisibility(View.VISIBLE);
    }

}