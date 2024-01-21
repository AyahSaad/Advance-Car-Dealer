package edu.birzeit.advancecardealer;

import static edu.birzeit.advancecardealer.CarJsonParser.cars;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarInfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int clickedPosition;
    private List<Car> list;
    private TextView positionCheck;
    private ImageView imageView;
    SharedPrefManager sharedPrefManager;
    RequestOptions option;


    // ... Other necessary variables and methods

    public CarInfoFragment(int position,List<Car> list) {
        this.clickedPosition = position;
        this.list = list;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    public CarInfoFragment() {
        // Required empty public constructor
    }

    public void updateDetails(Car car) {
        // Update UI components with car details
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CarInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CarInfoFragment newInstance(String param1, String param2) {
        CarInfoFragment fragment = new CarInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPrefManager = SharedPrefManager.getInstance(getActivity());
        DataBaseHelper dataBaseInfo = new DataBaseHelper(getActivity(), "CarsDatabase", null, 1);


        View rootView = inflater.inflate(R.layout.fragment_car_info, container, false);
        // Inflate the layout for this fragment
        imageView = rootView.findViewById(R.id.carsImagesViews);
        TextView name = rootView.findViewById(R.id.textName);
        TextView type = rootView.findViewById(R.id.textType);
        TextView model = rootView.findViewById(R.id.textModel);
        TextView factory = rootView.findViewById(R.id.textFactory);
        TextView fuel = rootView.findViewById(R.id.textFuel);
        TextView spare = rootView.findViewById(R.id.textSpare);
        TextView color = rootView.findViewById(R.id.textColor);
        TextView price = rootView.findViewById(R.id.textPrice);
        TextView offer = rootView.findViewById(R.id.textOffers);
        TextView doors = rootView.findViewById(R.id.textDoor);
        Button reserve = rootView.findViewById(R.id.ReserveButton);
        //TextView name = rootView.findViewById(R.id.textName);


        Glide.with(this).load(list.get(clickedPosition).getImage()).apply(option).into(imageView);
        name.setText(list.get(clickedPosition).getName());
        type.setText(list.get(clickedPosition).getType());
        model.setText(list.get(clickedPosition).getModel());
        factory.setText(list.get(clickedPosition).getFactoryName());
        fuel.setText(list.get(clickedPosition).getFuelType());
        spare.setText(list.get(clickedPosition).getHasAspare());
        color.setText(list.get(clickedPosition).getColor());
        double newPrice = (1 - list.get(clickedPosition).getOffer())*list.get(clickedPosition).getPrice();
        price.setText(String.valueOf( (int)newPrice ));
        offer.setText(String.valueOf(list.get(clickedPosition).getOffer()));
        doors.setText(String.valueOf(list.get(clickedPosition).getDoorsCount()));
        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Reserve reserve1 = new Reserve();
                //todo: reserv table
                Favorite favorite = new Favorite(1,sharedPrefManager.readString("currentUserEmail",""),list.get(clickedPosition).getId());
                dataBaseInfo.insertFav(favorite);
               // dataBaseInfo.insertReservation(reserve1);

            }
        });
        //Todo : onklick like





            return rootView;

    }
}

