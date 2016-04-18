package com.nexfi.yuanpeigen.nexfi_android_ble.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.database.ContentObserver;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.nexfi.yuanpeigen.nexfi_android_ble.R;
import com.nexfi.yuanpeigen.nexfi_android_ble.activity.GroupChatActivity;
import com.nexfi.yuanpeigen.nexfi_android_ble.adapter.UserListViewAdapter;
import com.nexfi.yuanpeigen.nexfi_android_ble.application.BleApplication;
import com.nexfi.yuanpeigen.nexfi_android_ble.bean.UserMessage;
import com.nexfi.yuanpeigen.nexfi_android_ble.dao.BleDBDao;
import com.nexfi.yuanpeigen.nexfi_android_ble.util.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark on 2016/4/12.
 */
public class FragmentNearby extends Fragment implements View.OnClickListener {

    private PopupWindow mPopupWindow = null, mPopupWindow_share = null;
    private ImageView iv_add;
    private View View_pop, view_share, v_parent;
    private LinearLayout addChatRoom, share;
    private ListView lv_userlist;
    private List<UserMessage> userMessageList = new ArrayList<UserMessage>();
    private UserListViewAdapter userListViewAdapter;

    private String userId;

    BleDBDao bleDBDao=new BleDBDao(BleApplication.getContext());
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView(inflater, container);
        setClickListener();
        initData();

        getActivity().getContentResolver().registerContentObserver(
                Uri.parse("content://www.nexfi_ble_user.com"), true,
                new Myobserve(new Handler()));

        return v_parent;
    }


    private class Myobserve extends ContentObserver {
        public Myobserve(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            initData();
            super.onChange(selfChange);
        }
    }


    private void initData() {
        userMessageList.clear();//每次取之前清空
        userId=UserInfo.initUserId(userId, BleApplication.getContext());
        //从数据库中获取用户数据
        userMessageList=bleDBDao.findAllUsers(userId);
        userListViewAdapter = new UserListViewAdapter(BleApplication.getContext(), userMessageList);
        lv_userlist.setAdapter(userListViewAdapter);
        if(userListViewAdapter!=null){
            userListViewAdapter.notifyDataSetChanged();
        }
        Log.e("TAG","--=initData======---------------------------------===FragmentNearby");
//        if(null!=userMessageList && userMessageList.size()>0){
//            if(Debug.DEBUG){
//                Log.e("TAG",userMessageList.size()+"------获取的用户人数");
//                for (UserMessage user:userMessageList) {
//                    Log.e("TAG",user.toString()+"--===========User");
//                }
//            }
//            userListViewAdapter = new UserListViewAdapter(BleApplication.getContext(), userMessageList);
//            lv_userlist.setAdapter(userListViewAdapter);
//        }
    }


    private void setClickListener() {
        share.setOnClickListener(this);
        addChatRoom.setOnClickListener(this);
        iv_add.setOnClickListener(this);
    }

    private void initView(LayoutInflater inflater, ViewGroup container) {
        v_parent = inflater.inflate(R.layout.fragment_nearby, container, false);
        View_pop = inflater.inflate(R.layout.pop_menu_add, null);
        view_share = inflater.inflate(R.layout.layout_share, null);
        lv_userlist = (ListView) v_parent.findViewById(R.id.lv_userlist);
        addChatRoom = (LinearLayout) View_pop.findViewById(R.id.add_chatRoom);
        share = (LinearLayout) View_pop.findViewById(R.id.share);
        iv_add = (ImageView) v_parent.findViewById(R.id.iv_add);
    }


    private void initPop() {
        if (mPopupWindow == null) {
            mPopupWindow = new PopupWindow(View_pop, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        }
        mPopupWindow.showAsDropDown(iv_add, 0, 0);
    }


    private void initPopShare() {
        if (mPopupWindow_share == null) {
            mPopupWindow_share = new PopupWindow(view_share, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mPopupWindow_share.setBackgroundDrawable(new ColorDrawable(0x00000000));
        }
        mPopupWindow_share.showAtLocation(v_parent, Gravity.CENTER, 0, 0);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share:
                mPopupWindow.dismiss();
                initPopShare();
                break;

            case R.id.add_chatRoom:
                Intent intent = new Intent(getActivity(), GroupChatActivity.class);
                startActivity(intent);
//                getActivity().finish();
                break;

            case R.id.iv_add:
                initPop();
                break;
        }
    }
}
