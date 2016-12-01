package com.morse.mutils;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.morse.mutils.beans.ContactBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author：Morse
 * time：2016/11/10 14:53
 * Descripte：
 */

public class ContactUtils {

    private IContactCallBack callBack;

    private Map<Integer, ContactBean> contactIdMap = null;
    private MyAsyncQueryHandler asyncQueryHandler;

    public ContactUtils(Context context) {
        asyncQueryHandler = new MyAsyncQueryHandler(context.getContentResolver());
        initQuery(context);
    }

    private void initQuery(Context context) {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI; // 联系人Uri；
        // 查询的字段
        String[] projection = {ContactsContract.CommonDataKinds.Phone._ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.DATA1, "sort_key",
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.PHOTO_ID,
                ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY};
        // 按照sort_key升序查詢
        asyncQueryHandler.startQuery(0, null, uri, projection, null, null,
                "sort_key COLLATE LOCALIZED asc");
    }

    public void setContactCallBack(IContactCallBack callBack) {
        this.callBack = callBack;
    }

    public interface IContactCallBack {
        void onSuccess(List<ContactBean> contactBeans);

        void onFailure();
    }

    private class MyAsyncQueryHandler extends AsyncQueryHandler {

        public MyAsyncQueryHandler(ContentResolver cr) {
            super(cr);
        }

        @Override
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                contactIdMap = new HashMap<Integer, ContactBean>();
                List<ContactBean> list = new ArrayList<ContactBean>();
                cursor.moveToFirst(); // 游标移动到第一项
                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToPosition(i);
                    String name = cursor.getString(1);
                    String number = cursor.getString(2);
                    String sortKey = cursor.getString(3);
                    int contactId = cursor.getInt(4);
                    Long photoId = cursor.getLong(5);
                    String lookUpKey = cursor.getString(6);

                    if (contactIdMap.containsKey(contactId)) {
                        // 无操作
                    } else {
                        // 创建联系人对象
                        ContactBean contact = new ContactBean();
                        contact.setDesplayName(name);
                        contact.setPhoneNum(number);
                        contact.setSortKey(sortKey);
                        contact.setPhotoId(photoId);
                        contact.setLookUpKey(lookUpKey);
                        list.add(contact);

                        contactIdMap.put(contactId, contact);
                    }
                }
                if (null != callBack) {
                    callBack.onSuccess(list);
                }
            }

            super.onQueryComplete(token, cookie, cursor);
        }

    }

}
