package com.amlogic.DTVPlayer;

import android.util.Log;
import android.os.Bundle;
import com.amlogic.tvutil.TVMessage;
import com.amlogic.tvutil.TVConst;
import com.amlogic.tvutil.TVProgram;
import com.amlogic.tvutil.TVProgramNumber;
import com.amlogic.tvactivity.TVActivity;
import com.amlogic.tvutil.TVChannelParams;
import com.amlogic.tvutil.TVScanParams;
import com.amlogic.tvutil.TVConst;
import com.amlogic.tvutil.TVEvent;
import com.amlogic.tvutil.TVGroup;
import com.amlogic.tvutil.TVBooking;
import com.amlogic.tvutil.DTVPlaybackParams;
import com.amlogic.tvutil.DTVRecordParams;

import java.util.*;
import java.text.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.widget.*;
import android.widget.AbsListView.OnScrollListener;
import android.app.*;
import android.app.AlertDialog.*;
import android.content.*;
import android.graphics.*;
import android.view.ViewGroup.*;
import android.text.*;
import android.text.method.*;
import android.database.*;
import android.os.SystemProperties;
import android.os.*;
import java.lang.reflect.Field;
/**
 *DTV Activity
 */
abstract public class DTVActivity extends TVActivity{
    private static final String TAG="DTVActivity";
	private TVProgram TVProgram=null;
	private static int dtvactivity_actived_num = 0;
	//private static int dtvlayout_gravity = Gravity.CENTER;
	
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

	@Override
	public void setContentView (int layoutResID){
		//super.setContentView(layoutResID);
		setContentViewdtv(layoutResID);
	}
	private void setContentViewdtv (int layoutResID){
		/*reset layout by reproduction rate*/
		String outputmode = SystemProperties.get("ubootenv.var.outputmode");
		int x = 0, y = 0, w = 0, h = 0;
		
		Log.d(TAG, "setContentView " + outputmode);
		
		if(outputmode.equals("1080p") == true){
			x = Integer.parseInt(SystemProperties.get("ubootenv.var.1080poutputx"));
			y = Integer.parseInt(SystemProperties.get("ubootenv.var.1080poutputy"));
			w = Integer.parseInt(SystemProperties.get("ubootenv.var.1080poutputwidth"));
			h = Integer.parseInt(SystemProperties.get("ubootenv.var.1080poutputheight"));
		}
		else if(outputmode.equals("1080i") == true){
			x = Integer.parseInt(SystemProperties.get("ubootenv.var.1080ioutputx"));
			y = Integer.parseInt(SystemProperties.get("ubootenv.var.1080ioutputy"));
			w = Integer.parseInt(SystemProperties.get("ubootenv.var.1080ioutputwidth"));
			h = Integer.parseInt(SystemProperties.get("ubootenv.var.1080ioutputheight"));		
		}
		else if(outputmode.equals("720p") == true){
			x = Integer.parseInt(SystemProperties.get("ubootenv.var.720poutputx"));
			y = Integer.parseInt(SystemProperties.get("ubootenv.var.720poutputy"));
			w = Integer.parseInt(SystemProperties.get("ubootenv.var.720poutputwidth"));
			h = Integer.parseInt(SystemProperties.get("ubootenv.var.720poutputheight"));		
		}
		else if(outputmode.equals("576p") == true){
			x = Integer.parseInt(SystemProperties.get("ubootenv.var.576poutputx"));
			y = Integer.parseInt(SystemProperties.get("ubootenv.var.576poutputy"));
			w = Integer.parseInt(SystemProperties.get("ubootenv.var.576poutputwidth"));
			h = Integer.parseInt(SystemProperties.get("ubootenv.var.576poutputheight"));			
		}
		else if(outputmode.equals("576i") == true){
			x = Integer.parseInt(SystemProperties.get("ubootenv.var.576ioutputx"));
			y = Integer.parseInt(SystemProperties.get("ubootenv.var.576ioutputy"));
			w = Integer.parseInt(SystemProperties.get("ubootenv.var.576ioutputwidth"));
			h = Integer.parseInt(SystemProperties.get("ubootenv.var.576ioutputheight"));					
		}
		else if(outputmode.equals("480p") == true){
			x = Integer.parseInt(SystemProperties.get("ubootenv.var.480poutputx"));
			y = Integer.parseInt(SystemProperties.get("ubootenv.var.480poutputy"));
			w = Integer.parseInt(SystemProperties.get("ubootenv.var.480poutputwidth"));
			h = Integer.parseInt(SystemProperties.get("ubootenv.var.480poutputheight"));			
		}
		else if(outputmode.equals("480i") == true){
			x = Integer.parseInt(SystemProperties.get("ubootenv.var.480ioutputx"));
			y = Integer.parseInt(SystemProperties.get("ubootenv.var.480ioutputy"));
			w = Integer.parseInt(SystemProperties.get("ubootenv.var.480ioutputwidth"));
			h = Integer.parseInt(SystemProperties.get("ubootenv.var.480ioutputheight"));				
		}		
/*
		AbsoluteLayout root = new AbsoluteLayout(this);

		if(x == 0 && y == 0 && w == 0 && h == 0){
			Log.e(TAG, "screen error");
		}
		else{
			LayoutParams params = new AbsoluteLayout.LayoutParams(w, h, x, y);
			root.setLayoutParams(params);
		}

		LayoutInflater inflater = LayoutInflater.from(this);
		View reproduction_view = inflater.inflate(layoutResID, null); 

		if(x == 0 && y == 0 && w == 0 && h == 0){
			Log.e(TAG, "reproduction error");
		}
		else{
			LayoutParams reproduction_params = new AbsoluteLayout.LayoutParams(w, h, x, y);
			
			root.addView(reproduction_view);

			super.setContentView(root, reproduction_params);	
			
			Log.d(TAG, "DTVActivity_ResetLayoutbyReprodRate " + x + " " + y + " " + w + " " + h);
		}
*/		


		AbsoluteLayout root1 = new AbsoluteLayout(this);
		RelativeLayout root2 = new RelativeLayout(this);
		
		LayoutParams params1 = new LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
		//root1.setLayoutParams(params1);
		LayoutParams params2 = new LayoutParams(w, h);
		//root2.setLayoutParams(params2);
		//root2.setGravity(dtvlayout_gravity); 

		LayoutInflater inflater = LayoutInflater.from(this);
		View reproduction_view = inflater.inflate(layoutResID, null); 

		LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
		//Log.d(TAG, "########## w=" + params.width + "h=" + params.height);

		if(x == 0 && y == 0 && w == 0 && h == 0){
			Log.e(TAG, "reproduction error");
		}
		else{

			super.setContentView(root1, params1);

			root1.addView(root2, params2);

			root2.setX(x);
			root2.setY(y);
			
			root2.addView(reproduction_view, params);

			/*
			AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams)root.getLayoutParams();
			params.x = x;
			params.y = y;
			params.width = w;
			params.height = h;
			root.setLayoutParams(params);
			*/
			
			Log.d(TAG, "DTVActivity_ResetLayoutbyReprodRate " + x + " " + y + " " + w + " " + h);
		}

			
	}	
	
	@Override
	protected void onStart(){
		Log.d(TAG, "onStart");
		DTVActivity_ActivedStateManage(true);
		super.onStart();
	}

	@Override
	protected void onStop(){
		Log.d(TAG, "onStop");
		DTVActivity_ActivedStateManage(false);
		super.onStop();
	}

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

	private void DTVActivity_ActivedStateManage(boolean actived){
		Log.d(TAG, "DTVActivity_ActivedStateManage actived " + actived + " dtvactivity_actived_num " + dtvactivity_actived_num);
		if(actived){
			if(dtvactivity_actived_num == 0){
				/*stop music play when dtv actived*/
				Intent stopMusicIntent = new Intent();
				stopMusicIntent.setAction("com.android.music.musicservicecommand.pause");
				stopMusicIntent.putExtra("command", "stop");
				sendBroadcast(stopMusicIntent);

				Intent exitMusicIntent = new Intent();
				exitMusicIntent.setAction("com.android.music.musicservicecommand.pause");
				exitMusicIntent.putExtra("command", "exit");
				sendBroadcast(exitMusicIntent);

				/*set vpath*/
				setInputSource(TVConst.SourceInput.SOURCE_DTV);
			}

			dtvactivity_actived_num++;
		}
		else{
			dtvactivity_actived_num--;

			if(dtvactivity_actived_num == 0){
				/*reset vpath, this is borrow SOURCE_ATV parameter*/
				setInputSource(TVConst.SourceInput.SOURCE_ATV);
			}			
		}
	}
	
	/***************DTVPlayer****************/
	public String secToTime(long i, Boolean isTotalTime){
		String retStr = null;
		long hour = 0;
		long minute = 0;
		long second = 0;
		if (i <= 0){
			if (isTotalTime && i<0)
				return "99:59:59";
			else
				return "00:00:00";
		}
		else{
			minute = i/60;
			if (minute < 60){
				second = i%60;
				retStr = "00:" + unitFormat(minute) + ":" + unitFormat(second);
			}
			else{
				hour = minute/60;
				if (hour > 99)
					return "99:59:59";
				minute = minute%60;
				second = i%60;
				retStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
			}
		}
		return retStr;
	}
    
    public String unitFormat(long i){
		String retStr = null;
		if (i >= 0 && i < 10)
			retStr = "0" + Long.toString(i);
		else
			retStr = Long.toString(i);
		return retStr;
    }

	public long DTVPlayerGetRecordDuration(){
		DTVRecordParams recPara = getRecordingParams();
		if (recPara != null) {
			//Log.d(TAG, "Record: time "+recPara.getCurrentRecordTime()/1000+" / "+
				//recPara.getTotalRecordTime()/1000+", size "+recPara.getCurrentRecordSize());
			return recPara.getCurrentRecordTime()/1000;
		} else {
			//Log.d(TAG, "Cannot get record params");
		}
		return 0;
	}

	public boolean DTVPlayerIsRecording(){
		DTVRecordParams recPara = getRecordingParams();
		if (recPara != null) 
			return true;
		return false;
	}

	public void DTVPlayerStartRecording(){
		startRecording();
	}

	public void DTVPlayerStartRecordingWithTime(long start,long duration){
		TVProgram mTVProgram = DTVPlayerGetDataByCurrentID();
		if(mTVProgram!=null)
			TVBooking.bookProgram(this,mTVProgram,TVBooking.FL_RECORD,start,duration);
	}

	public void DTVPlayerStopRecording(){
		stopRecording();
	}

	public void DTVPlayerPlayCurrentProgram(){

	}

	public void DTVPlayerPlayByProNo(int pronumber){
		TVProgramNumber mTVProgramNumber = new TVProgramNumber(pronumber);
		playProgram(mTVProgramNumber);
	}

	public void DTVPlayerPlayAtscByProNo(int major, int minor){
		TVProgramNumber mTVProgramNumber = new TVProgramNumber(major,minor);
		playProgram(mTVProgramNumber);
	}
	
	public void DTVPlayerPlayById(int db_id){
		playProgram(db_id);
	}

	public void DTVPlayerPlayDown(){
		channelDown();
	}

	public void DTVPlayerPlayUp(){
		channelUp();
	}

	public void DTVPlayerStopPlay(){
		stopPlaying();
	}

	public boolean DTVPlayerSetFav(boolean f){
		int db_id=-1;
		db_id=DTVPlayerGetCurrentProgramID();
		TVProgram mTVProgram = TVProgram.selectByID(this,db_id);
		if(mTVProgram.getFavoriteFlag()!=f){
			mTVProgram.setFavoriteFlag(f);
			return true;
		}	
		else
			return false;
	}

	/*************TeleText**************/

	public void DTVTTShow(){
		ttShow();
	}

	public void DTVTTHide(){
		ttHide();
	}

	public void DTVTTGotoNextPage() {
        ttGotoNextPage();
    }
	
    public void DTVTTGotoPreviousPage() {
      ttGotoPreviousPage();
    }
	
    public void DTVTTGotoPage(int page) {
        ttGotoPage(page);
    }

    public void DTVTTGoHome() {
        ttGoHome();
    }
	
    public void DTVTTGotoColorLink(int color) {
        ttGotoColorLink(color);
    }


	/**********Timeshifting*************/
	public void DTVTimeShiftingForward(int speed){
		fastForward(speed);
	}

	public void DTVTimeShiftingBackward(int speed){
		fastBackward(speed);
	}

	public void DTVTimeShiftingPause(){
		pause();
	}

	public void DTVTimeShiftingResume(){
		resume();
	}
	
	public void DTVTimeShiftingSeek(int pos){
		seekTo(pos);
	}

	public void DTVTimeShiftingPlay(){
		
	}
	public void DTVTimeShiftingStop(){
		stopTimeshifting();
	}

	public void DTVSubtitleStop(){}
	public void DTVTeletextStop(){}

	/*REC pvr Player*/
	public void DTVPvrPlayerStop(){
		stopPlayback();
	}

	/*DTVProgramManager*/
	public void DTVProgramManagerDeletePro(int id){
		
	}

	public void DTVProgramManagerEditPro(int id){
		
	}

	public void DTVProgramManagerAddLock(int id){}

	public void DTVProgramManagerSkipPro(int id,boolean f){}
	public void DTVProgramManagerFavSet(int id,boolean f){}
	public void DTVProgramManagerAddGroup(String name){
		TVGroup.addGroup(this,name);
	}

	public void DTVProgramManagerDeleteGroup(int id){
		TVGroup.deleteGroup(this,id);
	}
	
	public void DTVProgramManagerEditGroupName(int id,String name){
		TVGroup.editGroup(this,id,name);
	}
	
	public TVGroup[] DTVProgramManagerGetGroupList(){
		TVGroup[] mProgramGroup=null;
		mProgramGroup = TVGroup.selectByGroup(this,true);
		return mProgramGroup;
	}

	public TVProgram[] DTVProgramManagerGetFav(){
		TVProgram[] mTVProgram=null;
		mTVProgram = TVProgram.selectByFavorite(this,true);
		return mTVProgram;
	}
	
	public TVProgram[] DTVProgramManagerGetProByGroup(int id){
		TVProgram[] mTVProgram=null;
		mTVProgram = TVProgram.selectByGroupMap(this,id,true);
		return mTVProgram;
	}

	/***********DTV EPG****************/
	public int DTVEpgGetID(){
		return getCurrentProgramID();
	}

	public String DTVEpg_get_currentprogname(){
		String value = " ";
		TVProgram mTVProgram=TVProgram.selectByID(this,DTVEpgGetID());
		value = mTVProgram.getName();
		return value; 
	}

	public TVEvent[] DTVEpg_getDateEIT(long start,long duration){

		TVProgram mTVProgram=TVProgram.selectByID(this,DTVEpgGetID());
		return  mTVProgram.getScheduleEvents(this,start,duration);
		
	}

	public String DTVEpg_get_detailinfo(int srv_id){
		String value = " ";
		TVEvent mTVEvent=TVEvent.selectByID(this,srv_id);
		value = mTVEvent.getEventDescr();
		return value; 
	}

	public void DTVEpg_updateEvent(int db_id){
	}

	public void DTVEpg_deleteRec(int eventid){
	}


	public void DTVEpg_subscribeEPGEvent(int eventid){

	}

	public void DTVEpg_recordByEventID(int eventid){

	}

	/*********settings************/
	public boolean DTVGetSubtitleStatus(){
		return getBooleanConfig("tv:subtitle:enable");
	}

	public void DTVSetSubtitleStatus(boolean value){
		setConfig("tv:subtitle:enable",value);
	}

	public boolean DTVGetATSCAtvEnable(){
		return getBooleanConfig("tv:atv:enable");
	}

	public int DTVGetScreenMode(){
		return 0;
	}

	public void DTVSetScreenMode(int mode){
		return;
	}

	public int DTVGetAudioTrack(){
		return 2;
	}

	public void DTVSetAudioTrack(int mode){

	}

	public int DTVGetTimeShiftingDuration(){
		return 600;
	}

	public void  DTVSetTimeShiftingDuration(int value){
	}
	
	public int DTVGetParentalRating(){
		return 0;
	}

	public void DTVSetParentalRating(int value){
		return;
	}

	public int DTVGetTeletextRegion(){
		return 0;
	}

	public void DTVSetTeletextRegion(int value){

	}

	public int DTVPlayerGetCurrentProgramID(){
		return getCurrentProgramID();
	}

	public TVProgram DTVPlayerGetDataByCurrentID(){
		int db_id=-1;
		db_id=DTVPlayerGetCurrentProgramID();
		Log.d(TAG,"current id="+db_id);
	
		return TVProgram.selectByID(this,db_id);
	}
	
}
	
