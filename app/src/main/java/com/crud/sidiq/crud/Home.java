package com.crud.sidiq.crud;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Home extends ListActivity {

    Intent intent;
    TextView kampusId;
    DBController controller = new DBController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ListView listView = (ListView) findViewById(R.id.list);

        ArrayList<HashMap<String, String>> kampusList = controller.getAllkampus();
		/* jikta tidak kosong, tampilkan data kampus ke ListView
		 *
		 */
        if (kampusList.size() != 0) {
            ListAdapter adapter = new SimpleAdapter(Home.this,
                    kampusList, R.layout.listshow, new String[] {
                    "kampusId", "kampusName", "alamat" }, new int[] {
                    R.id.kampusId, R.id.kampusName, R.id.alamat });
            setListAdapter(adapter);
            registerForContextMenu(listView);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menuc, View v, ContextMenu.ContextMenuInfo menuinfo) {
        super.onCreateContextMenu(menuc, v, menuinfo);

        menuc.setHeaderTitle("Pilih Option");
        menuc.add(0, v.getId(), 0, "Update");
        menuc.add(0, v.getId(), 0, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem itemc) {
        if (itemc.getTitle() == "Update") {
            Toast.makeText(getApplication(), "Update", Toast.LENGTH_LONG).show();
        } else if (itemc.getTitle() == "Delete") {
            Toast.makeText(getApplication(), "Delete", Toast.LENGTH_LONG).show();
        }
        else {
            return false;
        }
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuoption, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.tambah) {
            Intent tambah = new Intent(getApplicationContext(), Tambah.class);
            startActivity(tambah);
        }
        if (item.getItemId()==R.id.help) {
            Toast.makeText(this, "Help Selected", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}
