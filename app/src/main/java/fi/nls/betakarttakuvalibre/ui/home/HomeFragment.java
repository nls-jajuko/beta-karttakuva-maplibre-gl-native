package fi.nls.betakarttakuvalibre.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import fi.nls.betakarttakuvalibre.R;

public class HomeFragment extends Fragment implements OnMapReadyCallback {


    private String styleUrl;
    private MapView mapView;
    private MapboxMap mapboxMap;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        styleUrl = getString(R.string.bk_defaultStyleUrl);
        Mapbox.getInstance(this.getContext(),
                getString(R.string.mb_accesstoken));

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        mapView = (MapView) root.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        return root;
    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;
        changeStyle(styleUrl);
    }

    public void changeStyle(String styleUrl) {
        if (mapboxMap == null) {
            return;
        }
        if (styleUrl == null) {
            return;
        }

        mapboxMap.setStyle(
                styleUrl,
                new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {


                    }
                });
    }
}