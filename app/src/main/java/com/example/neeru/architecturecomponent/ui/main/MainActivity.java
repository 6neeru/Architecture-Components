package com.example.neeru.architecturecomponent.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.neeru.architecturecomponent.R;
import com.example.neeru.architecturecomponent.ui.movie.MoviesListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.button_1)
    Button button1;
    @BindView(R.id.button_2)
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle(R.string.title_home);
        button2.setVisibility(View.GONE);
    }

    @OnClick({R.id.button_1, R.id.button_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_1:
                startActivity(new Intent(this, MoviesListActivity.class));
                break;
            case R.id.button_2:
                break;
        }
    }
}
