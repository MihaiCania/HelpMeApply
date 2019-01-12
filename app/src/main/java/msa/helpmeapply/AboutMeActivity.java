package msa.helpmeapply;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AboutMeActivity extends AppCompatActivity {

    TextView first, firstDB, last, lastDB, email, emailDB, phone, phoneDB, age, ageDB, university, universityDB, domain, domainDB, experience, experienceDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        first = (TextView) findViewById(R.id.first);
        last = (TextView) findViewById(R.id.last);
        email = (TextView) findViewById(R.id.email);
        phone = (TextView) findViewById(R.id.phone);
        age = (TextView) findViewById(R.id.age);
        university = (TextView) findViewById(R.id.university);
        domain = (TextView) findViewById(R.id.domain);
        experience = (TextView) findViewById(R.id.experience);

        firstDB = (TextView) findViewById(R.id.firstDB);
        lastDB = (TextView) findViewById(R.id.lastDB);
        emailDB = (TextView) findViewById(R.id.emailDB);
        phoneDB = (TextView) findViewById(R.id.phoneDB);
        ageDB = (TextView) findViewById(R.id.ageDB);
        universityDB = (TextView) findViewById(R.id.universityDB);
        domainDB = (TextView) findViewById(R.id.domainDB);
        experienceDB = (TextView) findViewById(R.id.experienceDB);

        Cursor cursor = UpdateAboutMe.sqLiteHelper.getData("SELECT * FROM TEST");

        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String first = cursor.getString(1);
            String last = cursor.getString(2);
            String email = cursor.getString(3);
            String phone = cursor.getString(4);
            String age = cursor.getString(5);
            String university = cursor.getString(6);
            String domain = cursor.getString(7);
            String experience = cursor.getString(8);

            firstDB.setText(first);
            lastDB.setText(last);
            emailDB.setText(email);
            phoneDB.setText(phone);
            ageDB.setText(age);
            universityDB.setText(university);
            domainDB.setText(domain);
            experienceDB.setText(experience);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.companies) {
            Intent intent = new Intent(AboutMeActivity.this, MainActivity.class);
            startActivity(intent);
            return false;
        }

        if(id == R.id.update) {
            Intent intent = new Intent(AboutMeActivity.this, UpdateAboutMe.class);
            startActivity(intent);
            return false;
        }

        return super.onOptionsItemSelected(item);
    }


}
