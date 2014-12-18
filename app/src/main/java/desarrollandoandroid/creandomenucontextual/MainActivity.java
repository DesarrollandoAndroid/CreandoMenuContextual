package desarrollandoandroid.creandomenucontextual;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    // Variables ListView
    String[] mDatos;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);

        // ListView con Adapter
        mDatos = getResources().getStringArray(R.array.elementos);
        mListView = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatos);
        mListView.setAdapter(mAdapter);

        // Asocio el menú contextual al TextView y al ListView.
        registerForContextMenu(mListView);

        Toast.makeText(getApplicationContext(), "Manten pulsado un elemento del ListView", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        int mPosition;
        // Se comprueba si se ha pulsado algún elemento del ListView
        if (v.getId() == R.id.listView){
            // Obtenemos la posición del elemento que ha sido pulsado.
            mPosition = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;
            // Establecemos como título del submenú la opción pulsado del ListView
            menu.setHeaderTitle(mListView.getAdapter().getItem(mPosition).toString());
            // Inflamos el submenú
            this.getMenuInflater().inflate(R.menu.menu_main, menu);
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

            switch (item.getItemId()) {
                case R.id.eleUno:
                    Intent unoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://desarrollandoandroid.wordpress.com"));
                    startActivity(unoIntent);
                    return true;
                case R.id.eleDos:
                    Intent dosIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/DesarrollandoAndroid?tab=repositories"));
                    startActivity(dosIntent);
                    return true;
                case R.id.eleTres:
                    Intent tresIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",
                            getResources().getString(R.string.correo), null));
                    tresIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.dudas));
                    startActivity(Intent.createChooser(tresIntent, getResources().getString(R.string.enviar)));
                    return true;
            }
        return super.onContextItemSelected(item);
    }
}
