package com.example.sachinmalik.sports.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.sachinmalik.sports.R;
import com.example.sachinmalik.sports.fragments.dummy.DummyContent;
import com.example.sachinmalik.sports.utils.AppController;
import com.example.sachinmalik.sports.utils.ItemClickSupport;
import com.example.sachinmalik.sports.utils.Modal;
import com.example.sachinmalik.sports.utils.Modal1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.sachinmalik.sports.utils.AppController.TAG;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    JSONArray response1;
    RecyclerView recyclerView=null;
    private OnListFragmentInteractionListener mListener;
    MyItemRecyclerViewAdapter adapter;



    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        volleyReq();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
             recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

        }
        adapter=new MyItemRecyclerViewAdapter(Modal.itemlist, mListener);
        recyclerView.setAdapter(adapter);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                mListener.onListFragmentInteraction(position);
            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
  //                  + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(int position);
    }

    void volleyReq(){
        // Tag used to cancel the request
        String tag_json_arry = "json_array_req";

        String url = "http://43.252.91.208:5015/v1/score";

        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonArrayRequest req = new JsonArrayRequest(Request.Method.POST,url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(TAG, response.toString());
                        response1=response;
                        handler.sendEmptyMessage(0);
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getLocalizedMessage());
                pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(req, tag_json_arry);
    }

    private final Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {

            for (int i = 0; i < response1.length(); i++) {
                String jsonStr = null;
                try {
                    jsonStr = response1.getString(i);
                    JSONObject shiftJson = new JSONObject(jsonStr);
                     Modal.itemlist.add(new Modal1(shiftJson.getBoolean("islive"),shiftJson.getString("team1"),shiftJson.getString("team1Score")
                             ,shiftJson.getString("team1Over"),shiftJson.getString("team1Logo"),shiftJson.getString("team2")
                             ,shiftJson.getString("team2Score"),shiftJson.getString("team2Over"),shiftJson.getString("team2Logo")));
                }


                catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            adapter.notifyDataSetChanged();
        }
    };
}
