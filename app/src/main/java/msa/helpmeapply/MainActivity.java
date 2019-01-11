package msa.helpmeapply;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.aboutme) {
            Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
            startActivity(intent);
            return false;
        }

        if(id == R.id.companies) {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            return false;
        }

        return super.onOptionsItemSelected(item);
    }

}
