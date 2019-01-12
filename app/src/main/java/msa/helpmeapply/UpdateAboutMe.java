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

        //FOR CV UPLOAD
        /*
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(UpdateAboutMe.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_STORAGE);
            }
        });
        */


    }

    //IMPLEMENT UPLOAD CV DIRECTLY TO EMAIL ACTIVITY!!!

    /*
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == REQUEST_CODE_STORAGE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    */

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
