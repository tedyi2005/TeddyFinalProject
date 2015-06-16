package com.example.all_inone_app;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

import com.shaznee.blogreader.MainListActivity;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class NewsFragment extends Fragment {
	
	public static final String TAG = NewsFragment.class.getSimpleName();
	ListView lv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.newslayout, container, false);

	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		lv =(ListView) getActivity().findViewById(R.id.listView1);
		
		
//		ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),R.array.news_section, android.R.layout.simple_list_item_1); 
//		lv.setAdapter(adapter);
	}
	
	private class GetBlogPostTask extends AsyncTask<Object, Void, JSONObject>{

		@Override
		protected JSONObject doInBackground(Object... params) {
			
			int responseCode = -1;
			JSONObject jsonResponse = null;
			
			try{
				
				URL blogFeedURL = new URL("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20google"
			+ ".news%20where%20q%20%3D%20%22new%20york%22&format=json&diagnostics=true&env="
			+ "store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback= ");
				
				HttpURLConnection  connection = (HttpURLConnection) blogFeedURL.openConnection();
				connection.connect();
				responseCode = connection.getResponseCode();

				if(responseCode == HttpURLConnection.HTTP_OK){
					
					InputStream inputStream = connection.getInputStream();
					Reader reader = new InputStreamReader(inputStream);
					int contentLenght = connection.getContentLength();
					char[] charArray = new char[contentLenght];
					reader.read(charArray);
					String responseData = new String(charArray);
					jsonResponse = new JSONObject(responseData);
					
//					String status = jsonResponse.getString("title");
//					Log.v(TAG, status);

//					for (int i = 0; i < jsonPosts.length(); i++) {
//						JSONObject jsonPost = jsonPosts.getJSONObject(i);
//						String title = jsonPost.getString("title");
//						Log.v(TAG, "Post" + i + ": " + title);					
//					}
					
				} else {
					Log.i(TAG, "Unsuccessful HTTP Response Code" + responseCode);
				}

				Log.i(TAG, "Code" + responseCode);
				
			} catch (MalformedURLException e) {
				Log.e(TAG, "Exception Caught" + e);
			} catch (IOException e) {
				Log.e(TAG, "Exception Caught" + e);
			} catch (Exception e) {
				Log.e(TAG, "Exception Caught" + e);
			}
			return jsonResponse;
		}
		
		@Override
		protected void onPostExecute(JSONObject result) {
			blogData = result;
			updateList();
		}
	}
}
