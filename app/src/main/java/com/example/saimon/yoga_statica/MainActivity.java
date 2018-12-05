package com.example.saimon.yoga_statica;

import android.content.res.Configuration;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;


public class MainActivity extends AppCompatActivity {
    FrameLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, WorkoutDetailFragment.newInstance())
                    .commitNow();
        }
        initViews();
        initListFragment();
        showDetailsFragmentAtStart();
    }

    private void initViews() {
        container = findViewById(R.id.fragmentContainer);
    }

    private void showDetailsFragmentAtStart() {
        if(container.getTag().equals("tablet_display")) {
            initDetailFragment(0);
        }
    }

    void initDetailFragment(int position) {
        // Инстантиируем Fragment
        WorkoutDetailFragment detailFragment = new WorkoutDetailFragment();
        // Устанавливаем то упражнение, которое хотим показать
        detailFragment.setWorkout(position);
        // Начинаем транзакцию Fragment через Support FragmentManager
        // (класс, который управляет созданием и хранением Fragment в системе)
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Указываем, какой фрагмент и в какой контейнер хотим поместить
        transaction.replace(R.id.fragmentContainer, detailFragment);
        // Добавляем фрагмент в стек для возможности возврата
        transaction.addToBackStack(null);
        // Запускаем транзакцию (остальное берет на себя система)
        transaction.commit();
    }

    void initListFragment() {
        if(container.getTag().equals("usual_display")) {
            WorkoutListFragment listFragment = new WorkoutListFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, listFragment);
            transaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            //... do something
        } else {

        }
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
           // case R.id.menu_main.time30:

              //  return true;
          //  case R.id.menu.time_45:

              //  return true;
          //  case R.id.menu.time_90:

              //  return true;
                default:
                    return super.onOptionsItemSelected(item);

        }
    }
}

