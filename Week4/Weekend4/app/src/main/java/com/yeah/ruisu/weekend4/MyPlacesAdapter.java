package com.yeah.ruisu.weekend4;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MyPlacesAdapter extends BaseAdapter
    implements Filterable
{
    private LayoutInflater myInflater;
    private Context myContext;
    private String usrLoc;

    ArrayList<MyGooglePlaces> places = new ArrayList<>();

    public MyPlacesAdapter(Context context, String usrLoc)
    {
        super();

        myInflater = LayoutInflater.from(context);
        myContext = context;

        this.usrLoc = usrLoc;
    }

    @Override
    public int getCount()
    {
        return places.size();
    }

    @Override
    public Object getItem(int position)
    {
        return places.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        /* A ViewHolder keeps references to children views to avoid *
         *  unnecessary calls to findViewById() on each row.        */
        ViewHolder holder;

        /* When convertView is not null, we can reuse it directly,  *
         *  there is no need to reinflate it. We only inflate a new *
         *  View when the convertView supplied by ListView is null. */
        if (convertView == null)
        {
            convertView = myInflater.inflate(R.layout.autocomplete, null);

            /* Creates a ViewHolder and store references to the two *
             *  children views we want to bind data to.             */
            holder = new ViewHolder();

            holder.text = convertView.findViewById(R.id.myplaces);
            holder.address = convertView.findViewById(R.id.address);

            /*  Bind the data efficiently with the holder. */
            convertView.setTag(holder);

        }
        else
        {
            /* Get the ViewHolder back to get fast access to the *
             *  TextView and the ImageView.                      */
            holder = (ViewHolder) convertView.getTag();
        }
        /* If weren't re-ordering this you could rely on what *
         *  set last time.                                    */
        try
        {
            holder.text.setText(Html.fromHtml("<b>" +
                    places.get(position).getName() +
                    "<b>"));
            holder.address.setText(places.get(position).getVicinity());
        }
        catch (Exception e)
        {

        }


        return convertView;
    }

    @Override
    public Filter getFilter()
    {
        Filter myFilter = new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence constraint)
            {
                FilterResults myResults = new FilterResults();

                if (constraint != null)
                {
                    places = getPredictions(constraint.toString());

                    if (places != null)
                    {
                        myResults.values = places;
                        myResults.count = places.size();
                    }
                }

                return myResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results)
            {
                /* The API returned at least one result, update the data. */
                if (results != null && results.count > 0)
                {
                    notifyDataSetChanged();
                }
                /* The API did not return any results, invalidate the data set. */
                else
                {
                    notifyDataSetInvalidated();
                }
            }
        };

        return myFilter;
    }

    /* Method to get different places nearby search location. */
    private ArrayList<MyGooglePlaces> getPredictions (String constraint)
    {
        /* We pass the current latitude and longitude to find nearby and *
         *  ranky=distance means places will be found in ascending order *
         *  according to distance.                                       */
        double myLatitude = 30.7333;
        double myLongitude = 76.7794;

        /*String url = Constants.BASE_URL +
                        Constants.PLACES_URLS +
                        myLatitude + "," +
                        myLongitude +
                        "&rankby=distance&name=" +
                        constraint +
                        "&key=" +
                        Constants.API_KEY;*/

        String url = Constants.BASE_URL +
                Constants.PLACES_URLS +
                usrLoc +
                "&rankby=distance&name=" +
                constraint +
                "&key=" +
                Constants.API_KEY;

        return getPlaces(url);
    }

    private ArrayList<MyGooglePlaces> getPlaces(String constraint)
    {
        /* Code for API level 23 as httpclient is depricated in API 23. */
        StringBuffer sb=null;
        URL url;
        HttpURLConnection urlConnection = null;

        try
        {
            url = new URL(constraint);

            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);

            int data = isw.read();

            sb = new StringBuffer("");

            while (data != -1)
            {
                sb.append((char)data);
                // char current = (char) data;
                data = isw.read();
                // System.out.print(current);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (urlConnection != null)
            {
                urlConnection.disconnect();
            }
        }

        return   parseGoogleParse(sb.toString());
    }

    /* Method to parse the json returned by googleplaces api. */
    private static ArrayList parseGoogleParse(final String response)
    {
        ArrayList<MyGooglePlaces> temp = new ArrayList<>();

        try
        {
            /* Make an jsonObject in order to parse the response. */
            JSONObject jsonObject = new JSONObject(response);

            /* Make an jsonObject in order to parse the response. */
            if (jsonObject.has("results"))
            {
                JSONArray jsonArray = jsonObject.getJSONArray ("results");

                for (int i = 0; i < jsonArray.length(); i++)
                {
                    MyGooglePlaces poi = new MyGooglePlaces();

                    if (jsonArray.getJSONObject(i).has("name"))
                    {
                        poi.setName(jsonArray.getJSONObject(i).optString("name"));
                        poi.setRating(jsonArray.getJSONObject(i).optString("rating",
                                                                            " "));

                        if (jsonArray.getJSONObject(i).has("opening_hours"))
                        {
                            if (jsonArray.getJSONObject(i)
                                            .getJSONObject("opening_hours")
                                            .has("open_now"))
                            {
                                if (jsonArray.getJSONObject(i)
                                                .getJSONObject("opening_hours")
                                                .getString("open_now")
                                                .equals("true"))
                                {
                                    poi.setOpennow("YES");
                                }
                                else
                                {
                                    poi.setOpennow("NO");
                                }
                            }
                        }
                        else
                        {
                            poi.setOpennow("Not Known");
                        }

                        if (jsonArray.getJSONObject(i).has("geometry"))
                        {
                            if (jsonArray.getJSONObject(i)
                                            .getJSONObject("geometry")
                                            .has("location"))
                            {
                                if (jsonArray.getJSONObject(i)
                                                .getJSONObject("geometry")
                                                .getJSONObject("location")
                                                .has("lat"))
                                {
                                    poi.setLatLng (
                                            Double.parseDouble (jsonArray.getJSONObject(i)
                                                                        .getJSONObject("geometry")
                                                                        .getJSONObject("location")
                                                                        .getString("lat")),
                                            Double.parseDouble (jsonArray.getJSONObject(i)
                                                                        .getJSONObject("geometry")
                                                                        .getJSONObject("location")
                                                                        .getString("lng")));
                                }
                            }
                        }

                        if (jsonArray.getJSONObject(i).has("vicinity"))
                        {
                            poi.setVicinity(jsonArray.getJSONObject(i)
                                                        .optString("vicinity"));
                        }

                        if (jsonArray.getJSONObject(i).has("types"))
                        {
                            JSONArray typesArray = jsonArray.getJSONObject(i)
                                                            .getJSONArray("types");

                            for (int j = 0; j < typesArray.length(); j++)
                            {
                                poi.setCategory(typesArray.getString(j) +
                                                            ", " +
                                                            poi.getCategory());
                            }
                        }
                    }

                    temp.add(poi);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList();
        }
        return temp;
    }

    /* Viewholder class to hold adapter views. */
    static class ViewHolder
    {
        TextView text, address;
    }
}
