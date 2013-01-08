package com.amlogic.DTVPlayer;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.util.Log;
import android.content.Intent;

import android.view.WindowManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.KeyEvent;

public class DTVVchipTv extends Activity{
    private static final String TAG = "DTVVchipTv";
    
    
    static private int[] Rating_status_ALL={1,1,1,1,1,1,};
    static private int[] Rating_status_FV={-1,1,-1,-1,-1,-1,};
    static private int[] Rating_status_V={-1,-1,-1,1,1,1,};
    static private int[] Rating_status_S={-1,-1,-1,1,1,1,};
    static private int[] Rating_status_L={-1,-1,-1,1,1,1,};
    static private int[] Rating_status_D={-1,-1,-1,1,1,-1,};
    
    ListView list_mpaa_all;
    RatingAdapter list_mpaa_adapter_all=null;
    
    ListView list_mpaa_fv;
    RatingAdapter list_mpaa_adapter_fv=null;
    
    ListView list_mpaa_v;
    RatingAdapter list_mpaa_adapter_v=null;
    
    ListView list_mpaa_s;
    RatingAdapter list_mpaa_adapter_s=null;
    
    ListView list_mpaa_l;
    RatingAdapter list_mpaa_adapter_l=null;

	ListView list_mpaa_d;
    RatingAdapter list_mpaa_adapter_d=null;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

       
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.vchip_tv);    
        InitTVMap();
    }
    
    protected void onStart(){
		super.onStart();
		/*
		VchipProvider.attach(this);
		
		VchipProvider.addDimension(getContentResolver(), 0, "ALL", Rating_status_ALL);
		VchipProvider.addDimension(getContentResolver(), 0, "FV", Rating_status_FV);
		VchipProvider.addDimension(getContentResolver(), 0, "V", Rating_status_V);
		VchipProvider.addDimension(getContentResolver(), 0, "S", Rating_status_S);
		VchipProvider.addDimension(getContentResolver(), 0, "L", Rating_status_L);
		VchipProvider.addDimension(getContentResolver(), 0, "D", Rating_status_D);

		VchipProvider.query(getContentResolver(), 0, "ALL", Rating_status_ALL);
		VchipProvider.query(getContentResolver(), 0, "FV", Rating_status_FV);
		VchipProvider.query(getContentResolver(), 0, "V", Rating_status_V);
		VchipProvider.query(getContentResolver(), 0, "S", Rating_status_S);
		VchipProvider.query(getContentResolver(), 0, "L", Rating_status_L);
		VchipProvider.query(getContentResolver(), 0, "D", Rating_status_D);
		*/
	}
	
	protected void onStop(){
		super.onStop();
		/*
		VchipProvider.update(getContentResolver(), 0, "ALL", Rating_status_ALL, false);
		VchipProvider.update(getContentResolver(), 0, "FV", Rating_status_FV, false);
		VchipProvider.update(getContentResolver(), 0, "V", Rating_status_V, false);
		VchipProvider.update(getContentResolver(), 0, "S", Rating_status_S, false);
		VchipProvider.update(getContentResolver(), 0, "L", Rating_status_L, false);
		VchipProvider.update(getContentResolver(), 0, "D", Rating_status_D, true);
		
		VchipProvider.detach();
		*/
	}

    private class listOnItemClick implements OnItemClickListener{
    	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long position) {   
    		System.out.println("id---------" + arg2);
        	Log.d("#####","#####################"+position);
        	int p=(int)position;

        	switch (arg0.getId()) {
			
			case R.id.list_All:
				if(p<=1)
				{
					if(Rating_status_ALL[p]==0) //block
		        	{
						for(int i=p;i<2;i++)
		        		{
							Rating_status_ALL[i]=1;
		        		}
						if(Rating_status_FV[1]==0)
							Rating_status_FV[1]=1;
						
		        	}	
		        	else  //unblock
		        	{
		        		for(int i=0;i<=p;i++)
		        		{
		        			if(Rating_status_ALL[i]<0)
		        				continue;
		        			else
		        				Rating_status_ALL[i]=0;
		        		}	

						if(Rating_status_FV[1]==1&&p==1)
							Rating_status_FV[1]=0;
		        	}	
					
					
				}
				else
				{
			
					if(Rating_status_ALL[p]==0) //block
		        	{
						for(int i=p;i<Rating_status_ALL.length;i++)
		        		{
							Rating_status_ALL[i]=1;
							if(Rating_status_V[i]!=-1)
								Rating_status_V[i]=1;
							if(Rating_status_S[i]!=-1)
								Rating_status_S[i]=1;
							if(Rating_status_L[i]!=-1)
								Rating_status_L[i]=1;
							if(Rating_status_D[i]!=-1)
								Rating_status_D[i]=1;
							
		        		}	

						
		        	}	
		        	else //unblock
		        	{
		        		for(int i=2;i<=p;i++)
		        		{
		        			if(Rating_status_ALL[i]<0)
		        				continue;
		        			else
		        			{
		        				Rating_status_ALL[i]=0;
								if(Rating_status_V[i]==1)
									Rating_status_V[i]=0;
								if(Rating_status_S[i]==1)
									Rating_status_S[i]=0;
								if(Rating_status_L[i]==1)
									Rating_status_L[i]=0;
								if(Rating_status_D[i]==1)
									Rating_status_D[i]=0;
		        			}
		        		}	

						
		        	}	
				}
				
				list_mpaa_adapter_all.notifyDataSetChanged();
				list_mpaa_adapter_fv.notifyDataSetChanged();
				list_mpaa_adapter_v.notifyDataSetChanged();
				list_mpaa_adapter_s.notifyDataSetChanged();
				list_mpaa_adapter_l.notifyDataSetChanged();
				list_mpaa_adapter_d.notifyDataSetChanged();
				break;
			case R.id.list_FV:
				if(Rating_status_FV[p]==0)  //block
	        	{
	        		for(int i=p;i<Rating_status_FV.length;i++)
	        		{
						if(Rating_status_FV[i]<0)
	        				continue;
	        			else
						    Rating_status_FV[i]=1;
	        		}	
					
	        	}	
	        	else    //unblock
	        	{
	        		for(int i=0;i<=p;i++)
	        		{
	        			if(Rating_status_FV[i]<0)
	        				continue;
	        			else
	        				Rating_status_FV[i]=0;
	        		}	

					Rating_status_ALL[0]=0;
					Rating_status_ALL[1]=0;
	        	}	
				
				list_mpaa_adapter_fv.notifyDataSetChanged();
				list_mpaa_adapter_all.notifyDataSetChanged();
				break;
			case R.id.list_V:
				if(Rating_status_V[p]==0) //block
	        	{
	        		for(int i=p;i<Rating_status_V.length;i++)
	        		{
	        			Rating_status_V[i]=1;
	        		}		
	        	}	
	        	else
	        	{
	        		for(int i=0;i<=p;i++)
	        		{
	        			if(Rating_status_V[i]<0)
	        			{
	        				continue;
	        			}
						else
	        			{
	        				Rating_status_V[i]=0;
							
							if(Rating_status_ALL[i]==1)
								Rating_status_ALL[i]=0;
						}
	        		}	

					
	        	}	
				
				list_mpaa_adapter_v.notifyDataSetChanged();
				list_mpaa_adapter_all.notifyDataSetChanged();
				break;
			case R.id.list_S:
				if(Rating_status_S[p]==0)
	        	{
	        		for(int i=p;i<Rating_status_S.length;i++)
	        		{
	        			Rating_status_S[i]=1;
	        		}	
	        	}	
	        	else
	        	{
	        		for(int i=0;i<=p;i++)
	        		{
	        			if(Rating_status_S[i]<0)
	        				continue;
	        			else
	        			{
	        				Rating_status_S[i]=0;
	        				if(Rating_status_ALL[i]==1)
								Rating_status_ALL[i]=0;
						}
	        		}	
	        	}	
				
				list_mpaa_adapter_s.notifyDataSetChanged();
				list_mpaa_adapter_all.notifyDataSetChanged();
				break;
			case R.id.list_L:
				if(Rating_status_L[p]==0)
	        	{
	        		for(int i=p;i<Rating_status_L.length;i++)
	        		{
	        			Rating_status_L[i]=1;
	        		}	
	        	}	
	        	else
	        	{
	        		for(int i=0;i<=p;i++)
	        		{
	        			if(Rating_status_L[i]<0)
	        				continue;
	        			else
	        			{
	        				Rating_status_L[i]=0;
							if(Rating_status_ALL[i]==1)
								Rating_status_ALL[i]=0;
	        			}
						
	        		}	

				
	        	}	
				
				list_mpaa_adapter_l.notifyDataSetChanged();
				list_mpaa_adapter_all.notifyDataSetChanged();
				break;
			case R.id.list_D:
				if(Rating_status_D[p]==0)
	        	{
	        		for(int i=p;i<Rating_status_D.length;i++)
	        		{
						if(Rating_status_D[i]<0)
	        				continue;
	        			else
							Rating_status_D[i]=1;
	        		}	
	        	}	
	        	else
	        	{
	        		for(int i=0;i<=p;i++)
	        		{
	        			if(Rating_status_D[i]<0)
	        				continue;
	        			else
	        			{
							Rating_status_D[i]=0;
							if(Rating_status_ALL[i]==1)
								Rating_status_ALL[i]=0;
	        			}	
	        		}	
	        	}	
				
				list_mpaa_adapter_d.notifyDataSetChanged();
				list_mpaa_adapter_all.notifyDataSetChanged();
				break;
        	}
        	
 
        	
        }      	
    }
    
    	
    void InitTVMap()
    {
    	 list_mpaa_all = (ListView) findViewById(R.id.list_All);
    	 list_mpaa_all.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
         list_mpaa_adapter_all = new RatingAdapter(this,Rating_status_ALL);
         list_mpaa_all.setOnItemClickListener(new listOnItemClick());
         list_mpaa_all.setAdapter(list_mpaa_adapter_all);
         
         
         list_mpaa_fv = (ListView) findViewById(R.id.list_FV);
         list_mpaa_fv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
         list_mpaa_adapter_fv = new RatingAdapter(this,Rating_status_FV);
         list_mpaa_fv.setOnItemClickListener(new listOnItemClick());
         list_mpaa_fv.setAdapter(list_mpaa_adapter_fv);
         
         list_mpaa_v = (ListView) findViewById(R.id.list_V);
         list_mpaa_v.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
         list_mpaa_adapter_v = new RatingAdapter(this,Rating_status_V);
         list_mpaa_v.setOnItemClickListener(new listOnItemClick());
         list_mpaa_v.setAdapter(list_mpaa_adapter_v);
         
         list_mpaa_s = (ListView) findViewById(R.id.list_S);
         list_mpaa_fv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
         list_mpaa_adapter_s = new RatingAdapter(this,Rating_status_S);
         list_mpaa_s.setOnItemClickListener(new listOnItemClick());
         list_mpaa_s.setAdapter(list_mpaa_adapter_s);
         
         list_mpaa_l = (ListView) findViewById(R.id.list_L);
         list_mpaa_l.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
         list_mpaa_adapter_l = new RatingAdapter(this,Rating_status_L);
         list_mpaa_l.setOnItemClickListener(new listOnItemClick());
         list_mpaa_l.setAdapter(list_mpaa_adapter_l);
         
         list_mpaa_d = (ListView) findViewById(R.id.list_D);
         list_mpaa_d.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
         list_mpaa_adapter_d = new RatingAdapter(this,Rating_status_D);
         list_mpaa_d.setOnItemClickListener(new listOnItemClick());
         list_mpaa_d.setAdapter(list_mpaa_adapter_d);
    	
    }
   
	private static class RatingAdapter extends BaseAdapter {
		private LayoutInflater mInflater;
		private Bitmap mIcon1;
		private Bitmap mIcon2;

		private Context cont;
		private List<Map<String, Object>> listItems;
		private int[] listitem; 

		static class ViewHolder {
	    ImageView image;
		}

		public RatingAdapter(Context context) {
		super();
		cont = context;
		mInflater=LayoutInflater.from(context);
		
		}

		public RatingAdapter(Context context,int[] list) {
			super();
			cont = context;
			listitem=list;
			mInflater=LayoutInflater.from(context);
			
			}
		
		
		public int getCount() {
		
		return listitem.length;
		}

		
		public Object getItem(int position) {
		
		return position;
		}
		
	
		public long getItemId(int position) {
		return position;
		}
		
		
		public boolean isEnabled(int position) {
			if(listitem[position]==-1)
			  return false;
			return super.isEnabled(position);
		}


	
		public View getView(int position, View convertView, ViewGroup parent) {
		Log.d(TAG,"getView"+position);	
		ViewHolder holder;
		if (convertView == null) {
		   convertView = mInflater.inflate(R.layout.tv_rating_list_item, null);
		   holder = new ViewHolder();
		   holder.image = (ImageView) convertView.findViewById(R.id.image);
		   //holder.iboolean = (ImageButton)convertView.findViewById(R.id.iboolean);
		   //holder.icon1 = (ImageView)convertView.findViewById(R.id.icon1);
		   convertView.setTag(holder);
		}else {
		  // Get the ViewHolder back to get fast access to the TextView
		  // and the ImageView.
		  holder = (ViewHolder) convertView.getTag();
		  }
		
		  // Bind the data efficiently with the holder.
		 if(listitem[position]==1)
		 {
			 holder.image.setImageResource(R.drawable.rating_locked);
			 holder.image.setVisibility(View.VISIBLE);
		 }	 
			 
			 
		 else
			 //holder.image.setBackgroundResource(R.drawable.rating_unlocked);
			 holder.image.setVisibility(View.INVISIBLE);

		  
		  switch(position)
		  {
		  	case 1: 
		  	
			break;
	
		  }
		  
		  return convertView;
		}
	  }	



	 private void blockAll()
	 {

		for(int i=0;i<Rating_status_ALL.length;i++)
		{
			if(Rating_status_ALL[i]!=-1)
				Rating_status_ALL[i]=1;
		}
		
		for(int i=0;i<Rating_status_FV.length;i++)
		{
			if(Rating_status_FV[i]!=-1)
				Rating_status_FV[i]=1;
		}

		for(int i=0;i<Rating_status_V.length;i++)
		{
			if(Rating_status_V[i]!=-1)
				Rating_status_V[i]=1;
		}

		for(int i=0;i<Rating_status_S.length;i++)
		{
			if(Rating_status_S[i]!=-1)
				Rating_status_S[i]=1;
		}
		for(int i=0;i<Rating_status_L.length;i++)
		{
			if(Rating_status_L[i]!=-1)
				Rating_status_L[i]=1;
		}
		for(int i=0;i<Rating_status_D.length;i++)
		{
			if(Rating_status_D[i]!=-1)
				Rating_status_D[i]=1;
		}
				
		list_mpaa_adapter_all.notifyDataSetChanged();
		list_mpaa_adapter_fv.notifyDataSetChanged();
		list_mpaa_adapter_v.notifyDataSetChanged();
		list_mpaa_adapter_s.notifyDataSetChanged();
		list_mpaa_adapter_l.notifyDataSetChanged();
		list_mpaa_adapter_d.notifyDataSetChanged();

	
	 }

	 private void unblockAll()
	 {

		for(int i=0;i<Rating_status_ALL.length;i++)
		{
			if(Rating_status_ALL[i]!=-1)
				Rating_status_ALL[i]=0;
		}
		
		for(int i=0;i<Rating_status_FV.length;i++)
		{
			if(Rating_status_FV[i]!=-1)
				Rating_status_FV[i]=0;
		}

		for(int i=0;i<Rating_status_V.length;i++)
		{
			if(Rating_status_V[i]!=-1)
				Rating_status_V[i]=0;
		}

		for(int i=0;i<Rating_status_S.length;i++)
		{
			if(Rating_status_S[i]!=-1)
				Rating_status_S[i]=0;
		}
		for(int i=0;i<Rating_status_L.length;i++)
		{
			if(Rating_status_L[i]!=-1)
				Rating_status_L[i]=0;
		}
		for(int i=0;i<Rating_status_D.length;i++)
		{
			if(Rating_status_D[i]!=-1)
				Rating_status_D[i]=0;
		}
				
		list_mpaa_adapter_all.notifyDataSetChanged();
		list_mpaa_adapter_fv.notifyDataSetChanged();
		list_mpaa_adapter_v.notifyDataSetChanged();
		list_mpaa_adapter_s.notifyDataSetChanged();
		list_mpaa_adapter_l.notifyDataSetChanged();
		list_mpaa_adapter_d.notifyDataSetChanged();

	
	 }


	  public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

	   switch (keyCode) {
		
		case KeyEvent.KEYCODE_A:		
    		unblockAll();
			break;
		case KeyEvent.KEYCODE_D:
    		blockAll();
			break;
		}
		return super.onKeyDown(keyCode, event);
	}		
   
}