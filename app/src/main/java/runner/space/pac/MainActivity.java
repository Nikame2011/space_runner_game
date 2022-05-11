package runner.space.pac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.MotionEvent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.rewarded.RewardedAd;
//import com.pf.application.databinding.ActivityMain2Binding;

import java.util.Date;


//import com.pf.application.databinding.ActivityMainBinding;

/*
требования к демо-версии, пригодной для публикации:
+ улучшения запускаются только на 0-й высоте
+ деньги несчитаются и не отображаются
+ скорость не отображается
+ время улучшений возрастает
+ рекорды для улучшений возрастают и их выполнение возможно
+ увеличение характеристик не резкое
+ первая картинка двигается от 2/3 высоты экрана и до своей высоты
+ вторая картинка двигается,когда первая перестала закрывать весь экран и до своей высоты
+ два языка
+ сохранение прогресса
+ сохранение улучшений
+ улучшения ускоряются кликаньем
+ прыжки усиливаются кликаньем
+ анимация и движение синхронизированы(взмах и ускорение в один момент)
+ анимация на разные виды деятельности

на будущее:
- с изменением высоты картинки заменяются на другие подгружаясь из памяти, ненужные картинки не хранятся
- подсказки в начале игры
- новый вид меню: кнопка прыжка кругом без надписей, улучшения стрелкой влево с анимацией +++ когда доступно
улучшение,над ним кнопка настроек, концентрация и энергия правее чем сейчас, слева  рекорд и количество монет



 */

/*
 коды версий
 0.0.0.0
 Х.0.0.0 максимально глобальные изменения, вносимые в код, хз какие пока, возможно, добавление новых режимов
 0.Х.0.0 добавление функционала или глобальное улучшение имеющегося - увеличение высоты, добавление магазина
 0.0.Х.0 незначительные улучшения текущего функционала например, добавленbе нескольких предметов в магазин
 0.0.0.Х исправление багов, в том числе исправление коэффициентов, мешающих прохождению, технические улучшения, не добавляющие функционал(оптимизация кода)
 */

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    public static boolean setup=false;

    public  static String game_mode ="hard";

    public static int dw,dh;

    public static GameView gw;

    public static boolean new_game=false;

    public static Context cont;
    public static String version="0.0.0.0";

    public static String Active_menu="Main";
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cont=this;
        dw= getResources().getDisplayMetrics().widthPixels;//получаем ширину экрана
        dh= getResources().getDisplayMetrics().heightPixels;//получаем ширину экрана

        gw= new GameView(this);

        ConstraintLayout gameLayout=(ConstraintLayout) findViewById(R.id.GL); // находим gameLayout
       /* ConstraintLayout.LayoutParams l=(ConstraintLayout.LayoutParams) gameLayout.getLayoutParams();
        l.height=dh;
        l.width=dw;
        gameLayout.setLayoutParams(l);*/

        gameLayout.addView(gw); // и добавляем в него gameView

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();

    }




    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }



//    public short ads_timeout=0;
//public static boolean ask_on=false;
/*
private void set_ask(){
        ask_on=true;
    TableRow.LayoutParams l;
    switch (ask_number){
        case 0:
            ask.setText(R.string.ask_hello);
            ask_no.setText(R.string.No);
            ask_yes.setText(R.string.Yes);
            Ask_image.setImageResource(0);
            l=(TableRow.LayoutParams) Ask_image.getLayoutParams();
            l.height=0;
            l.width=0;
            break;
        case 1:
            ask.setText(R.string.ask_0);
            ask_no.setText(R.string.ask_stop);
            ask_yes.setText(R.string.ask_next);
            Ask_image.setImageResource(0);
            l=(TableRow.LayoutParams) Ask_image.getLayoutParams();
            l.height=0;
            l.width=0;
            break;
        case 2:
            ask.setText(R.string.ask_1);
            ask_no.setText(R.string.ask_stop);
            ask_yes.setText(R.string.ask_next);

            Ask_image.setImageResource(R.drawable.b1);
            l=(TableRow.LayoutParams) Ask_image.getLayoutParams();
            l.height=dw/4;
            l.width=dw/4;
            Ask_image.setLayoutParams(l);
            break;
        case 3:
            ask.setText(R.string.ask_2);
            ask_no.setText(R.string.ask_stop);
            ask_yes.setText(R.string.ask_next);


            Ask_image.setImageResource(R.drawable.b1);
            l=(TableRow.LayoutParams) Ask_image.getLayoutParams();
            l.height=dw/4;
            l.width=dw/4;
            Ask_image.setLayoutParams(l);
            break;
        case 4:
            ask.setText(R.string.ask_3);
            ask_no.setText(R.string.ask_stop);
            ask_yes.setText(R.string.ask_next);

            Ask_image.setImageResource(0);
            l=(TableRow.LayoutParams) Ask_image.getLayoutParams();
            l.height=0;
            l.width=0;
            Ask_image.setLayoutParams(l);
            break;
        case 5:
            ask.setText(R.string.ask_4);
            ask_no.setText(R.string.ask_stop);
            ask_yes.setText(R.string.ask_next);
            Ask_image.setImageResource(0);
            l=(TableRow.LayoutParams) Ask_image.getLayoutParams();
            l.height=0;
            l.width=0;
            break;
        case 6:
            ask.setText(R.string.ask_5);
            ask_no.setText(R.string.ask_stop);
            ask_yes.setText(R.string.ask_next);

            Ask_image.setImageResource(R.drawable.b2);
            l=(TableRow.LayoutParams) Ask_image.getLayoutParams();
            l.height=dw/4;
            l.width=dw/4;
            Ask_image.setLayoutParams(l);
            break;
        case 7:
            ask.setText(R.string.ask_6);
            ask_no.setText(R.string.ask_stop);
            ask_yes.setText(R.string.ask_next);

            Ask_image.setImageResource(0);
            l=(TableRow.LayoutParams) Ask_image.getLayoutParams();
            l.height=0;
            l.width=0;
            Ask_image.setLayoutParams(l);
            break;
        case 8:
            ask.setText(R.string.ask_7);
            ask_no.setText(R.string.ask_stop);
            ask_yes.setText(R.string.ask_next);

            Ask_image.setImageResource(0);
            l=(TableRow.LayoutParams) Ask_image.getLayoutParams();
            l.height=0;
            l.width=0;
            Ask_image.setLayoutParams(l);
            break;
        case 9:
            ask.setText(R.string.ask_8);
            ask_no.setText(R.string.ask_stop);
            ask_yes.setText(R.string.ask_next);

            Ask_image.setImageResource(0);
            l=(TableRow.LayoutParams) Ask_image.getLayoutParams();
            l.height=0;
            l.width=0;
            Ask_image.setLayoutParams(l);
            break;
        case 10:
            ask.setText(R.string.ask_9);
            ask_no.setText(R.string.ask_stop);
            ask_yes.setText(R.string.ask_next);

            Ask_image.setImageResource(0);
            l=(TableRow.LayoutParams) Ask_image.getLayoutParams();
            l.height=0;
            l.width=0;
            Ask_image.setLayoutParams(l);
            break;
        case 11:
            ask.setText(R.string.ask_10);
            ask_no.setText(R.string.ask_stop);
            ask_yes.setText(R.string.ask_next);

            Ask_image.setImageResource(R.drawable.bust_training);
            l=(TableRow.LayoutParams) Ask_image.getLayoutParams();
            l.height=dw/4;
            l.width=dw/4;
            Ask_image.setLayoutParams(l);
            break;
    }
    Ask_l.setVisibility(View.VISIBLE);

}
*/
    @Override
    protected void onPause() {
        super.onPause();
        try{
            //gw.pen.save();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onTouch(View button, MotionEvent motion) {
        switch(button.getId()) { // определяем какая кнопка
            /*case R.id.B1:
                switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                        flying = true;

                        break;
                    case MotionEvent.ACTION_UP:
                        flying = false;
                        break;
                }


                break;*/
            case R.id.B2:
                switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:

                        /*if (!setup){
                            if (gw.pen.status=="RCV"||gw.pen.status=="RTF"||gw.pen.status=="UPD") {
                                if (gw.pen.status != "STF"){
                                    if (gw.pen.status == "RCV")
                                        gw.pen.savedstatus = "RCV";
                                    else if (gw.pen.status == "RTF")
                                        gw.pen.savedstatus = "RTF";
                                    else if (gw.pen.status == "UPD")
                                        gw.pen.savedstatus = "UPD";
                                    gw.pen.status = "STF";
                                }
                                Fly.setImageResource(R.drawable.bust_training);
                                //Fly.setImageResource(R.drawable.imb);
                                //Setup.setImageResource();
                                //Setup.setBackground(R.drawable.back_b);
                                Setup.setBackgroundResource(R.drawable.back_b);
                                setup = true;

                                if(gw.pen.savedstatus != "UPD")
                                    Fly.setVisibility(View.INVISIBLE);
                                else if (MainActivity.mRewardedAd != null)
                                    Reward.setVisibility(View.VISIBLE);
                                //Config.setVisibility(View.INVISIBLE);
                                //Up_fly.setVisibility(View.VISIBLE);
                                //Up_jump.setVisibility(View.VISIBLE);
                                /*if (energy_show) Up_energy.setVisibility(View.VISIBLE);
                                else {
                                    if (gw.pen.next_bust > 0) {
                                        MainActivity.energy_show = true;
                                        Up_energy.setVisibility(View.VISIBLE);
                                    }
                                }*/
                      /*      }
                        }
                        else /*if (update == -1)*///{
                          /*  Fly.setImageResource(R.drawable.b1);
                            //Setup.setImageResource(R.drawable.b2);
                            //Setup.setBackgroundResource(R.drawable.upgrade_menu);
                            Setup.setBackgroundResource(R.drawable.b2);
                            setup = false;
                            //Config.setVisibility(View.VISIBLE);
                            //Up_fly.setVisibility(View.INVISIBLE);
                            //Up_jump.setVisibility(View.INVISIBLE);
                            //Up_energy.setVisibility(View.INVISIBLE);
                            //Reward.setVisibility(View.INVISIBLE);
                            Fly.setVisibility(View.VISIBLE);
                            Reward.setVisibility(View.INVISIBLE);
                            //Fly.setVisibility(View.VISIBLE);
                            if (gw.pen.status == "STF"){
                                if (gw.pen.savedstatus == "RCV")
                                    gw.pen.status = "RCV";
                                else if (gw.pen.savedstatus == "UPD")
                                    gw.pen.status = "UPD";
                                else if (gw.pen.savedstatus == "RTF")
                                    gw.pen.status = "RTF";
                                gw.pen.savedstatus = "NON";
                            }
                        }*/
                        break;
                }
                break;
          /*  case R.id.Up_fly:
                switch (motion.getAction()) { // определяем нажата или отпущена
                   /* case MotionEvent.ACTION_DOWN:
                        if (update == -1) {
                            update = 1;

                        }
                        break;*/
          /*          case MotionEvent.ACTION_DOWN:
                        startX= motion.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        movedX= motion.getX();
                        break;
                }
                break;
            case R.id.Up_jump:
                switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                        if (update == -1) {
                            update = 0;

                        }
                        break;
                }
                break;
            case R.id.Up_energy:
                switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                        if (update == -1) {
                            update = 2;
                        }
                        break;
                }
                break;
*/


            /*case R.id.Reward:
                switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                        try{
                            mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdShowedFullScreenContent() {
                                    // Called when ad is shown.
                                    Log.d(TAG, "Ad was shown.");
                                }

                                @Override
                                public void onAdFailedToShowFullScreenContent(AdError adError) {
                                    // Called when ad fails to show.
                                    Log.d(TAG, "Ad failed to show.");
                                }

                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    // Called when ad is dismissed.
                                    // Set the ad reference to null so you don't show the ad a second time.
                                    Log.d(TAG, "Ad was dismissed.");
                                    mRewardedAd = null;
                                }
                            });
                            if (mRewardedAd != null) {
                                Activity activityContext = MainActivity.this;
                                mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                                    @Override
                                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                        // Handle the reward.
                                        Log.d(TAG, "The user earned the reward.");
                                        int rewardAmount = rewardItem.getAmount();
                                        String rewardType = rewardItem.getType();

                                        gw.pen.short_update+=60;
                                        gw.pen.shorting=new Date();

                                        //reward_load();

                                    }
                                });
                            } else {
                                Log.d(TAG, "The rewarded ad wasn't ready yet.");
                            }

                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        need_rew=true;
                        break;
                }
                break;*/
            case R.id.ask_stop_b:
                /*switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                        if (shure==-1){
                            shure=0;
                            ask.setText(R.string.ask_exit);
                            ask_no.setText(R.string.ret);
                            ask_yes.setText(R.string.okay);
                            Ask_image.setImageResource(R.drawable.config_btn);
                            TableRow.LayoutParams l;
                            l=(TableRow.LayoutParams) Ask_image.getLayoutParams();
                            l.height=dw/4;
                            l.width=dw/4;
                            Ask_image.setLayoutParams(l);
                        }
                        else if (shure==0){
                            shure=-1;
                            ask_on=false;
                        }
                        break;
                }
*/
                break;
            case R.id.ask_select_b:
              /*  switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:

                        break;
                    case MotionEvent.ACTION_UP:
                        if(shure==-1){
                            ask_number+=1;
                            Ask_l.setVisibility(View.INVISIBLE);
                        }
                        else if(shure==0){
                            shure=-1;
                            ask_number=-1;
                            Ask_l.setVisibility(View.INVISIBLE);
                        }
                        //gw.pen.save_ask();
                        ask_on=false;
                        break;
                }*/
                break;
        }
        return true;
    }

    /*private void reward_load(){
    // в манифест оригинал "ca-app-pub-8592750491177297~1345545766"
        //в объявление оригинал "ca-app-pub-8592750491177297/4490583854"
        RewardedAd.load(cont, "ca-app-pub-8592750491177297/4490583854"
                //в объявление тестовый "ca-app-pub-3940256099942544/5224354917",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d(TAG, loadAdError.getMessage());
                        mRewardedAd = null;
                        need_rew=true;
                        rew_error+=1;
                        if (rew_error==100) {

                            MobileAds.initialize(cont, new OnInitializationCompleteListener() {
                                @Override
                                public void onInitializationComplete(InitializationStatus initializationStatus) {
                                }
                            });

                        adRequest = new AdRequest.Builder().build();
                            rew_error=90;
                            //ads_timeout=100;
                         }
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        Log.d(TAG, "Ad was loaded.");
                        rew_error=0;
                    }
                });
    }*/









}