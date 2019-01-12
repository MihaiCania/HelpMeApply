package msa.helpmeapply;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;

public class UpdateAboutMe extends AppCompatActivity {

    EditText edtFirst, edtLast, edtEmail, edtPhone, edtAge, edtUniversity, edtDomain, edtExperience;
    Button btnAdd;

    final int REQUEST_CODE_STORAGE= 999;

    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_about_me);

        edtFirst = (EditText) findViewById(R.id.edtFirst);
        edtLast = (EditText) findViewById(R.id.edtLast);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtAge = (EditText) findViewById(R.id.edtAge);
        edtUniversity = (EditText) findViewById(R.id.edtUniversity);
        edtDomain = (EditText) findViewById(R.id.edtDomain);
        edtExperience = (EditText) findViewById(R.id.edtExperience);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        sqLiteHelper = new SQLiteHelper(this, "TEST.sqlite", null, 1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS TEST (Id INTEGER PRIMARY KEY AUTOINCREMENT, first TEXT, last TEXT, email TEXT, phone TEXT, age TEXT, university TEXT, domain TEXT, experience TEXT)");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sqLiteHelper.insertData(
                            edtFirst.getText().toString().trim(),
                            edtLast.getText().toString().trim(),
                            edtEmail.getText().toString().trim(),
                            edtPhone.getText().toString().trim(),
                            edtAge.getText().toString().trim(),
                            edtUniversity.getText().toString().trim(),
                            edtDomain.getText().toString().trim(),
                            edtExperience.getText().toString().trim()
                    );
                    Toast.makeText(getApplicationContext(), "Updated succesfully!", Toast.LENGTH_SHORT).show();

                }
                catch(Exception e) {
                    e.printStackTrace();
                }

            }
        });

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
            Intent intent = new Intent(UpdateAboutMe.this, AboutMeActivity.class);
            startActivity(intent);
            return false;
        }

        if(id == R.id.companies) {
            Intent intent = new Intent(UpdateAboutMe.this, MainActivity.class);
            startActivity(intent);
            return false;
        }

        return super.onOptionsItemSelected(item);
    }
}
