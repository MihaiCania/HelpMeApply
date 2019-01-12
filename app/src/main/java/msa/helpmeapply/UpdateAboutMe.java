package msa.helpmeapply;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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

public class UpdateAboutMe extends AppCompatActivity {

    EditText edtFirst, edtLast, edtEmail, edtPhone, edtAge, edtUniversity, edtDomain, edtExperience;
    ImageView imageView;
    Button btnChoose, btnAdd;

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
        imageView = (ImageView) findViewById(R.id.imageView);
        btnChoose = (Button) findViewById(R.id.btnChoose);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        sqLiteHelper = new SQLiteHelper(this, "AboutmeDB.sqlite", null, 1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS ABOUTME (Id INTEGER PRIMARY KEY AUTOINCREMENT, first VARCHAR, last VARCHAR, email VARCHAR, phone VARCHAR, age VARCHAR, university VARCHAR, domain VARCHAR, experience VARCHAR, cv BLOB)");

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(UpdateAboutMe.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_STORAGE);
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_STORAGE) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                startActivityForResult(intent.createChooser(intent, "Select pdf file"), REQUEST_CODE_STORAGE);
            } else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
